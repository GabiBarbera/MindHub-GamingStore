package com.wellplayed.gaming.store.dtos;

public class MailDTO {
    private String addressee;
    private String issue;
    private String comment;

    public MailDTO() {
    }

    public String getAddressee() {
        return addressee;
    }

    public String getIssue() {
        return issue;
    }

    public String getComment() {
        return comment;
    }
}