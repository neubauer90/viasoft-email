package com.viasoft.emailservice.gateway;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.viasoft.emailservice.dto.EmailDTO;

public interface EmailSenderGateway {
    void sendEmail(EmailDTO emailDTO) throws JsonProcessingException;
}