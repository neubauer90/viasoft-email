package com.viasoft.emailservice.gateway;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class AwsEmailSenderGateway implements EmailSenderGateway {

    private final ObjectMapper objectMapper;

    public AwsEmailSenderGateway(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void sendEmail(Object emailDTO) throws JsonProcessingException {
        String json = objectMapper.writeValueAsString(emailDTO);
        System.out.println("Simulating AWS email sending: " + json);
    }
}