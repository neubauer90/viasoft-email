package com.viasoft.emailservice.dto;

import jakarta.validation.constraints.Size;

public record EmailOciDTO(
        @Size(max = 40, message = "Recipient email must not exceed 40 characters")
        String recipientEmail,

        @Size(max = 50, message = "Recipient name must not exceed 50 characters")
        String recipientName,

        @Size(max = 40, message = "Sender email must not exceed 40 characters")
        String senderEmail,

        @Size(max = 100, message = "Subject must not exceed 100 characters")
        String subject,

        @Size(max = 250, message = "Body must not exceed 250 characters")
        String body
) implements EmailDTO {
}