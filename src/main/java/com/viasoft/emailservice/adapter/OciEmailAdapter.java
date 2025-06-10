package com.viasoft.emailservice.adapter;

import com.viasoft.emailservice.dto.EmailDTO;
import com.viasoft.emailservice.dto.EmailOciDTO;
import com.viasoft.emailservice.dto.EmailRequestDTO;
import jakarta.validation.ValidationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OciEmailAdapter implements EmailAdapter {

    private final Validator validator;

    @Override
    public EmailDTO adapt(EmailRequestDTO emailRequestDTO) {
        EmailOciDTO ociDTO = new EmailOciDTO(
                emailRequestDTO.recipient(),
                emailRequestDTO.recipientName(),
                emailRequestDTO.sender(),
                StringUtils.isNotBlank(emailRequestDTO.subject()) ? emailRequestDTO.subject() : "(no subject)",
                emailRequestDTO.content()
        );

        var violations = validator.validate(ociDTO);
        if (!violations.isEmpty()) {
            throw new ValidationException("OCI DTO validation failed: " + violations.iterator().next().getMessage());
        }

        return ociDTO;
    }
}