package com.viasoft.emailservice.gateway;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.viasoft.emailservice.dto.EmailOciDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class OciEmailSenderGatewayTest {

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private OciEmailSenderGateway gateway;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldSerializeAndPrintEmailDto() throws JsonProcessingException {
        EmailOciDTO ociDTO = new EmailOciDTO(
                "recipient@example.com",
                "Recipient Name",
                "sender@example.com",
                "Test Subject",
                "Test Content"
        );

        when(objectMapper.writeValueAsString(ociDTO)).thenReturn("{\"recipientEmail\":\"recipient@example.com\",...}");

        gateway.sendEmail(ociDTO);

        verify(objectMapper, times(1)).writeValueAsString(ociDTO);
    }

    @Test
    void shouldThrowJsonProcessingExceptionWhenSerializationFails() throws JsonProcessingException {
        EmailOciDTO ociDTO = new EmailOciDTO(
                "recipient@example.com",
                "Recipient Name",
                "sender@example.com",
                "Test Subject",
                "Test Content"
        );

        when(objectMapper.writeValueAsString(ociDTO)).thenThrow(new JsonProcessingException("Serialization failed") {});

        assertThrows(JsonProcessingException.class, () -> gateway.sendEmail(ociDTO));
    }
}