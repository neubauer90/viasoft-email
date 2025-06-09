package com.viasoft.emailservice.dto;

import jakarta.validation.constraints.Size;

public record EmailAwsDTO(
        @Size(max = 45, message = "Recipient email must not exceed 45 characters")
        String recipient,

        @Size(max = 60, message = "Recipient name must not exceed 60 characters")
        String recipientName,

        @Size(max = 45, message = "Sender email must not exceed 45 characters")
        String sender,

        @Size(max = 120, message = "Subject must not exceed 120 characters")
        String subject,

        @Size(max = 256, message = "Content must not exceed 256 characters")
        String content
) implements EmailDTO {
}