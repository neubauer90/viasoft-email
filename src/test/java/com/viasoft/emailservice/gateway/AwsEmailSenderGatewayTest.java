package com.viasoft.emailservice.gateway;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.viasoft.emailservice.dto.EmailAwsDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class AwsEmailSenderGatewayTest {

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private AwsEmailSenderGateway gateway;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldSerializeAndPrintEmailDto() throws JsonProcessingException {
        EmailAwsDTO awsDTO = new EmailAwsDTO(
                "recipient@example.com",
                "Recipient Name",
                "sender@example.com",
                "Test Subject",
                "Test Content"
        );

        when(objectMapper.writeValueAsString(awsDTO)).thenReturn("{\"recipient\":\"recipient@example.com\",...}");

        gateway.sendEmail(awsDTO);

        verify(objectMapper, times(1)).writeValueAsString(awsDTO);
    }

    @Test
    void shouldThrowJsonProcessingExceptionWhenSerializationFails() throws JsonProcessingException {
        EmailAwsDTO awsDTO = new EmailAwsDTO(
                "recipient@example.com",
                "Recipient Name",
                "sender@example.com",
                "Test Subject",
                "Test Content"
        );

        when(objectMapper.writeValueAsString(awsDTO)).thenThrow(new JsonProcessingException("Serialization failed") {});

        assertThrows(JsonProcessingException.class, () -> gateway.sendEmail(awsDTO));
    }
}