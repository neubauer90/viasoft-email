package com.viasoft.emailservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.viasoft.emailservice.dto.EmailDTO;
import com.viasoft.emailservice.dto.EmailRequestDTO;
import com.viasoft.emailservice.factory.EmailProviderFactory;
import com.viasoft.emailservice.provider.EmailProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService implements EmailSenderUseCase {

    private final EmailProviderFactory providerFactory;

    @Override
    public void processEmail(EmailRequestDTO emailRequestDTO) throws JsonProcessingException {
        EmailProvider provider = providerFactory.getProvider();
        EmailDTO adaptedEmail = provider.getAdapter().adapt(emailRequestDTO);
        provider.getGateway().sendEmail(adaptedEmail);
    }
}