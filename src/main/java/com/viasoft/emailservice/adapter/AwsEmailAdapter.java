package com.viasoft.emailservice.adapter;

import com.viasoft.emailservice.dto.EmailAwsDTO;
import com.viasoft.emailservice.dto.EmailDTO;
import com.viasoft.emailservice.dto.EmailRequestDTO;
import jakarta.validation.Validator;
import org.springframework.stereotype.Component;

@Component
public class AwsEmailAdapter implements EmailAdapter {

    private final Validator validator;

    public AwsEmailAdapter(Validator validator) {
        this.validator = validator;
    }

    @Override
    public EmailDTO adapt(EmailRequestDTO emailRequestDTO) {
        EmailAwsDTO awsDTO = new EmailAwsDTO(
                emailRequestDTO.recipient(),
                emailRequestDTO.recipientName(),
                emailRequestDTO.sender(),
                emailRequestDTO.subject(),
                emailRequestDTO.content()
        );

        var violations = validator.validate(awsDTO);
        if (!violations.isEmpty()) {
            throw new jakarta.validation.ValidationException("AWS DTO validation failed: " + violations.iterator().next().getMessage());
        }

        return awsDTO;
    }
}