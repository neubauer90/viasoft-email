package com.viasoft.emailservice.dto;

public interface EmailDTO {
    String getRecipient();
    String getRecipientName();
    String getSender();
    String getSubject();
    String getContent();
}