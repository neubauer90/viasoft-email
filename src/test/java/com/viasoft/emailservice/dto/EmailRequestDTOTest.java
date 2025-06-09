package com.viasoft.emailservice.dto;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class EmailRequestDTOTest {

    private static Validator validator;

    @BeforeAll
    static void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    void shouldFailValidationWhenRecipientIsBlank() {
        EmailRequestDTO request = new EmailRequestDTO(
                "",
                "Recipient Name",
                "sender@example.com",
                "Test Subject",
                "Test Content"
        );

        Set<ConstraintViolation<EmailRequestDTO>> violations = validator.validate(request);
        assertFalse(violations.isEmpty());
        assertEquals("Recipient email is required", violations.iterator().next().getMessage());
    }

    @Test
    void shouldFailValidationWhenSenderEmailIsInvalid() {
        EmailRequestDTO request = new EmailRequestDTO(
                "recipient@example.com",
                "Recipient Name",
                "invalid-email",
                "Test Subject",
                "Test Content"
        );

        Set<ConstraintViolation<EmailRequestDTO>> violations = validator.validate(request);
        assertFalse(violations.isEmpty());
        assertEquals("Invalid sender email", violations.iterator().next().getMessage());
    }
}