package com.viasoft.emailservice.adapter;

import com.viasoft.emailservice.dto.EmailAwsDTO;
import com.viasoft.emailservice.dto.EmailRequestDTO;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AwsEmailAdapterTest {

    private AwsEmailAdapter adapter;
    private Validator validator;

    @BeforeEach
    void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
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

        EmailAwsDTO awsDTO = (EmailAwsDTO) adapter.adapt(request);

        assertEquals(request.recipient(), awsDTO.recipient());
        assertEquals(request.recipientName(), awsDTO.recipientName());
        assertEquals(request.sender(), awsDTO.sender());
        assertEquals(request.subject(), awsDTO.subject());
        assertEquals(request.content(), awsDTO.content());
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