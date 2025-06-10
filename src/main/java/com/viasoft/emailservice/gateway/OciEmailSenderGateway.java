package com.viasoft.emailservice.gateway;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.viasoft.emailservice.dto.EmailDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OciEmailSenderGateway implements EmailSenderGateway {

    private final ObjectMapper objectMapper;

    @Override
    public void sendEmail(EmailDTO emailDTO) throws JsonProcessingException {
        String json = objectMapper.writeValueAsString(emailDTO);
        System.out.println("Simulating OCI email sending: " + json);
    }
}