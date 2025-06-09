package com.viasoft.emailservice.provider;

import com.viasoft.emailservice.adapter.EmailAdapter;
import com.viasoft.emailservice.adapter.OciEmailAdapter;
import com.viasoft.emailservice.gateway.EmailSenderGateway;
import com.viasoft.emailservice.gateway.OciEmailSenderGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("OCI")
public class OciEmailProvider implements EmailProvider {

    private final OciEmailAdapter adapter;
    private final OciEmailSenderGateway gateway;

    @Autowired
    public OciEmailProvider(OciEmailAdapter adapter, OciEmailSenderGateway gateway) {
        this.adapter = adapter;
        this.gateway = gateway;
    }

    @Override
    public EmailAdapter getAdapter() {
        return adapter;
    }

    @Override
    public EmailSenderGateway getGateway() {
        return gateway;
    }
}