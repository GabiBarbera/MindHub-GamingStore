package com.wellplayed.gaming.store.services;

import com.wellplayed.gaming.store.dtos.TicketDTO;
import com.wellplayed.gaming.store.dtos.TicketInfoDTO;
import com.wellplayed.gaming.store.models.Client;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.pdf.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Set;

@Service
public class ServiceBilling {
    @Autowired
    private ServiceEmailSend serviceEmailSend;
    @Autowired
    private ResourceLoader resourceLoader;
    private double calculateTotal(Set<TicketInfoDTO> buyComponents) {
        double total = 0;
        for (TicketInfoDTO buyComponent : buyComponents) {
            total += buyComponent.getQuantity() * buyComponent.getTotalAmount();
        }
        return total;
    }

    public ByteArrayOutputStream generateInvoicePDF(TicketDTO ticket, Set<TicketInfoDTO> buyComponent, Client client) throws DocumentException, IOException {

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Document document = new Document(PageSize.A4);
        PdfWriter writer = PdfWriter.getInstance(document, outputStream);

        document.open();
        // Agregar encabezado de la factura
        Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, Color.DARK_GRAY);
        Paragraph header = new Paragraph("Purchase invoice at Gaming Store", titleFont);
        header.setAlignment(Element.ALIGN_CENTER);
        header.setSpacingBefore(20);
        document.add(header);

        document.add(new Paragraph("\n"));

        // Agregar información de la orden (encabezado)
        Paragraph ticketInfo = new Paragraph("Buy number: " + ticket.getNumberOrder());
        Paragraph buyClient = new Paragraph("Client email: " + client.getEmail());
        Paragraph totalAmount = new Paragraph("Buy total amount: $" + calculateTotal(buyComponent));
        document.add(ticketInfo);
        document.add(buyClient);
        document.add(totalAmount);

        // Agregar tabla con los productos y sus detalles

        PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(100);
        table.setSpacingBefore(10f);
        table.setSpacingAfter(10f);

        float padding = 12f;
        PdfPCell cell;

        cell = new PdfPCell(new Phrase("Quantity"));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setPadding(padding);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Unit price"));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setPadding(padding);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Price for quantity"));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setPadding(padding);
        table.addCell(cell);

        for (TicketInfoDTO buyItem : buyComponent) {
            float paddingCell = 9f;

            PdfPCell cellQuantity = new PdfPCell(new Phrase(String.valueOf(buyItem.getQuantity())));
            cellQuantity.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellQuantity.setVerticalAlignment(Element.ALIGN_CENTER);
            cellQuantity.setPadding(paddingCell);
            table.addCell(cellQuantity);

            PdfPCell cellUnitPrice = new PdfPCell(new Phrase("$ " + buyItem.getTotalAmount()));
            cellUnitPrice.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellUnitPrice.setVerticalAlignment(Element.ALIGN_CENTER);
            cellUnitPrice.setPadding(paddingCell);
            table.addCell(cellUnitPrice);

            PdfPCell cellTotalAmount = new PdfPCell(new Phrase("$ " + buyItem.getTotalAmount() * buyItem.getQuantity()));
            cellTotalAmount.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cellTotalAmount.setVerticalAlignment(Element.ALIGN_CENTER);
            cellTotalAmount.setPadding(paddingCell);
            table.addCell(cellTotalAmount);
        }
        document.add(table);

        // Agregar total de la factura
        Paragraph total = new Paragraph("Total: $" + calculateTotal(buyComponent));
        document.add(total);

        document.add(new Paragraph("\n"));

        Resource logoResource = resourceLoader.getResource("classpath:static/web/images/Well_Played.png");
        Image logo = Image.getInstance(logoResource.getURL());
        logo.setAlignment(Image.ALIGN_CENTER);
        logo.scaleToFit(200, 200);
        document.add(logo);
        document.close();
        return outputStream;
    }
}