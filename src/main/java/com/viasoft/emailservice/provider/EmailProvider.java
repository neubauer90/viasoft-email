package com.viasoft.emailservice.provider;

import com.viasoft.emailservice.adapter.EmailAdapter;
import com.viasoft.emailservice.gateway.EmailSenderGateway;

public interface EmailProvider {
    EmailAdapter getAdapter();
    EmailSenderGateway getGateway();
}