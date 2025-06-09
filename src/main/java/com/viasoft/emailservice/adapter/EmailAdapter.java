package com.viasoft.emailservice.adapter;

import com.viasoft.emailservice.dto.EmailDTO;
import com.viasoft.emailservice.dto.EmailRequestDTO;

public interface EmailAdapter {
    EmailDTO adapt(EmailRequestDTO emailRequestDTO);
}