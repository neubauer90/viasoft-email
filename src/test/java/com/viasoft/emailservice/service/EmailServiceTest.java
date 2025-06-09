package com.viasoft.emailservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.viasoft.emailservice.adapter.EmailAdapter;
import com.viasoft.emailservice.dto.EmailDTO;
import com.viasoft.emailservice.dto.EmailRequestDTO;
import com.viasoft.emailservice.exception.InvalidIntegrationException;
import com.viasoft.emailservice.factory.EmailProviderFactory;
import com.viasoft.emailservice.gateway.EmailSenderGateway;
import com.viasoft.emailservice.provider.EmailProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class EmailServiceTest {

    @Mock
    private EmailProviderFactory providerFactory;

    @Mock
    private EmailAdapter emailAdapter;

    @Mock
    private EmailSenderGateway emailSenderGateway;

    @Mock
    private EmailProvider emailProvider;

    @InjectMocks
    private EmailService emailService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldProcessEmailSuccessfully() throws JsonProcessingException {
        EmailRequestDTO request = new EmailRequestDTO(
                "recipient@example.com",
                "Recipient Name",
                "sender@example.com",
                "Test Subject",
                "Test Content"
        );
        EmailDTO emailDTO = mock(EmailDTO.class);

        when(providerFactory.getProvider()).thenReturn(emailProvider);
        when(emailProvider.getAdapter()).thenReturn(emailAdapter);
        when(emailProvider.getGateway()).thenReturn(emailSenderGateway);
        when(emailAdapter.adapt(request)).thenReturn(emailDTO);
        doNothing().when(emailSenderGateway).sendEmail(emailDTO);

        emailService.processEmail(request);

        verify(emailAdapter, times(1)).adapt(request);
        verify(emailSenderGateway, times(1)).sendEmail(emailDTO);
    }

    @Test
    void shouldThrowExceptionWhenIntegrationIsInvalid() {
        when(providerFactory.getProvider()).thenThrow(new InvalidIntegrationException("Invalid integration"));

        EmailRequestDTO request = new EmailRequestDTO(
                "recipient@example.com",
                "Recipient Name",
                "sender@example.com",
                "Test Subject",
                "Test Content"
        );

        assertThrows(InvalidIntegrationException.class, () -> emailService.processEmail(request));
    }
}