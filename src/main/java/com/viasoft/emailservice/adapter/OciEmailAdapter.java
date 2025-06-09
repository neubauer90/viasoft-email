package com.viasoft.emailservice.adapter;

import com.viasoft.emailservice.dto.EmailDTO;
import com.viasoft.emailservice.dto.EmailOciDTO;
import com.viasoft.emailservice.dto.EmailRequestDTO;
import jakarta.validation.Validator;
import org.springframework.stereotype.Component;

@Component
public class OciEmailAdapter implements EmailAdapter {

    private final Validator validator;

    public OciEmailAdapter(Validator validator) {
        this.validator = validator;
    }

    @Override
    public EmailDTO adapt(EmailRequestDTO emailRequestDTO) {
        EmailOciDTO ociDTO = new EmailOciDTO(
                emailRequestDTO.recipient(),
                emailRequestDTO.recipientName(),
                emailRequestDTO.sender(),
                emailRequestDTO.subject(),
                emailRequestDTO.content()
        );

        var violations = validator.validate(ociDTO);
        if (!violations.isEmpty()) {
            throw new jakarta.validation.ValidationException("OCI DTO validation failed: " + violations.iterator().next().getMessage());
        }

        return ociDTO;
    }
}