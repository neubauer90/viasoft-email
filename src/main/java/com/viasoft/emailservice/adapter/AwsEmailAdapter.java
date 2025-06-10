package com.viasoft.emailservice.adapter;

import com.viasoft.emailservice.dto.EmailAwsDTO;
import com.viasoft.emailservice.dto.EmailDTO;
import com.viasoft.emailservice.dto.EmailRequestDTO;
import jakarta.validation.ValidationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AwsEmailAdapter implements EmailAdapter {

    private final Validator validator;

    @Override
    public EmailDTO adapt(EmailRequestDTO emailRequestDTO) {
        EmailAwsDTO awsDTO = new EmailAwsDTO(
                emailRequestDTO.recipient(),
                emailRequestDTO.recipientName(),
                emailRequestDTO.sender(),
                StringUtils.isNotBlank(emailRequestDTO.subject()) ? emailRequestDTO.subject() : "(no subject)",
                emailRequestDTO.content()
        );

        var violations = validator.validate(awsDTO);
        if (!violations.isEmpty()) {
            throw new ValidationException("AWS DTO validation failed: " + violations.iterator().next().getMessage());
        }

        return awsDTO;
    }
}