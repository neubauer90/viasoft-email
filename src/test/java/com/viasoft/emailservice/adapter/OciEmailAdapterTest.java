package com.viasoft.emailservice.adapter;

import com.viasoft.emailservice.dto.EmailDTO;
import com.viasoft.emailservice.dto.EmailOciDTO;
import com.viasoft.emailservice.dto.EmailRequestDTO;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OciEmailAdapterTest {

    private OciEmailAdapter adapter;

    @BeforeEach
    void setUp() {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        adapter = new OciEmailAdapter(validator);
    }

    @Test
    void shouldAdaptEmailRequestToOciDtoSuccessfully() {
        EmailRequestDTO request = new EmailRequestDTO(
                "recipient@example.com",
                "Recipient Name",
                "sender@example.com",
                "Test Subject",
                "Test Content"
        );

        EmailDTO ociDTO = adapter.adapt(request);

        assertEquals(request.recipient(), ociDTO.getRecipient());
        assertEquals(request.recipientName(), ociDTO.getRecipientName());
        assertEquals(request.sender(), ociDTO.getSender());
        assertEquals(request.subject(), ociDTO.getSubject());
        assertEquals(request.content(), ociDTO.getContent());
    }

    @Test
    void shouldThrowValidationExceptionWhenSubjectExceedsMaxSize() {
        String longSubject = "a".repeat(101); // 101 characters
        EmailRequestDTO request = new EmailRequestDTO(
                "recipient@example.com",
                "Recipient Name",
                "sender@example.com",
                longSubject,
                "Test Content"
        );

        assertThrows(jakarta.validation.ValidationException.class, () -> adapter.adapt(request));
    }
}