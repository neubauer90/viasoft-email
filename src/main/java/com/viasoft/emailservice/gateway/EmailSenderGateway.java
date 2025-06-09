package com.viasoft.emailservice.gateway;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface EmailSenderGateway {
    void sendEmail(Object emailDTO) throws JsonProcessingException;
}