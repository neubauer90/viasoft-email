package com.viasoft.emailservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.boot.context.properties.bind.DefaultValue;

public record EmailRequestDTO(
        @NotBlank(message = "Recipient email is required")
        @Email(message = "Invalid recipient email")
        String recipient,

        @NotBlank(message = "Recipient name is required")
        String recipientName,

        @NotBlank(message = "Sender email is required")
        @Email(message = "Invalid sender email")
        String sender,

        String subject,

        @NotBlank(message = "Content is required")
        String content
) {}