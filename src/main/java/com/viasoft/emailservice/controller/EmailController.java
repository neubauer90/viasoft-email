package com.viasoft.emailservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.viasoft.emailservice.dto.EmailRequestDTO;
import com.viasoft.emailservice.service.EmailSenderUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/email")
public class EmailController {

    private final EmailSenderUseCase emailSenderUseCase;

    public EmailController(EmailSenderUseCase emailSenderUseCase) {
        this.emailSenderUseCase = emailSenderUseCase;
    }

    @PostMapping
    public ResponseEntity<Void> sendEmail(@Valid @RequestBody EmailRequestDTO emailRequestDTO) throws JsonProcessingException {
        emailSenderUseCase.processEmail(emailRequestDTO);
        return ResponseEntity.noContent().build();
    }
}