package com.viasoft.emailservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record EmailRequestDTO(
        @NotBlank(message = "Recipient email is required")
        @Email(message = "Invalid recipient email")
        String recipient,

        @NotBlank(message = "Recipient name is required")
        String recipientName,

        @NotBlank(message = "Sender email is required")
        @Email(message = "Invalid sender email")
        String sender,

        @NotBlank(message = "Subject is required")
        String subject,

        @NotBlank(message = "Content is required")
        String content
) {}