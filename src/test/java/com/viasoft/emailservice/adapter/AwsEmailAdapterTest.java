package com.viasoft.emailservice.adapter;

import com.viasoft.emailservice.dto.EmailAwsDTO;
import com.viasoft.emailservice.dto.EmailDTO;
import com.viasoft.emailservice.dto.EmailRequestDTO;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AwsEmailAdapterTest {

    private AwsEmailAdapter adapter;

    @BeforeEach
    void setUp() {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        adapter = new AwsEmailAdapter(validator);
    }

    @Test
    void shouldAdaptEmailRequestToAwsDtoSuccessfully() {
        EmailRequestDTO request = new EmailRequestDTO(
                "recipient@example.com",
                "Recipient Name",
                "sender@example.com",
                "Test Subject",
                "Test Content"
        );

        EmailDTO awsDTO = adapter.adapt(request);

        assertEquals(request.recipient(), awsDTO.getRecipient());
        assertEquals(request.recipientName(), awsDTO.getRecipientName());
        assertEquals(request.sender(), awsDTO.getSender());
        assertEquals(request.subject(), awsDTO.getSubject());
        assertEquals(request.content(), awsDTO.getContent());
    }

    @Test
    void shouldThrowValidationExceptionWhenContentExceedsMaxSize() {
        String longContent = "a".repeat(257); // 257 characters
        EmailRequestDTO request = new EmailRequestDTO(
                "recipient@example.com",
                "Recipient Name",
                "sender@example.com",
                "Test Subject",
                longContent
        );

        assertThrows(jakarta.validation.ValidationException.class, () -> adapter.adapt(request));
    }
}