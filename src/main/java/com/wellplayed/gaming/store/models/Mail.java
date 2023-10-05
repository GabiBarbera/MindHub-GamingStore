package com.wellplayed.gaming.store.models;

import java.io.ByteArrayOutputStream;

public class Mail {
    private String sender = "gamingstore521@gmail.com";
    private String addressee;
    private String issue;
    private String comment;
    private ByteArrayOutputStream pdfData;

    public Mail() {
    }

    public Mail(String addressee, String issue, String comment) {
        this.addressee = addressee;
        this.issue = issue;
        this.comment = comment;
    }

    public Mail(String addressee, String issue, String comment, ByteArrayOutputStream pdfData) {
        this.addressee = addressee;
        this.issue = issue;
        this.comment = comment;
        this.pdfData = pdfData;
    }

    public String getSender() {
        return sender;
    }

    public String getAddressee() {
        return addressee;
    }

    public void setAddressee(String addressee) {
        this.addressee = addressee;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public ByteArrayOutputStream getPdfData() {
        return pdfData;
    }

    public void setPdfData(ByteArrayOutputStream pdfData) {
        this.pdfData = pdfData;
    }
}