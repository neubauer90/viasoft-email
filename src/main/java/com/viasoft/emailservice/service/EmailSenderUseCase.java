package com.viasoft.emailservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.viasoft.emailservice.dto.EmailRequestDTO;

public interface EmailSenderUseCase {
    void processEmail(EmailRequestDTO emailRequestDTO) throws JsonProcessingException;
}