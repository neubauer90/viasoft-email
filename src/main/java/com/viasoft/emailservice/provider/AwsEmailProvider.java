package com.viasoft.emailservice.provider;

import com.viasoft.emailservice.adapter.AwsEmailAdapter;
import com.viasoft.emailservice.adapter.EmailAdapter;
import com.viasoft.emailservice.gateway.AwsEmailSenderGateway;
import com.viasoft.emailservice.gateway.EmailSenderGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("AWS")
public class AwsEmailProvider implements EmailProvider {

    private final AwsEmailAdapter adapter;
    private final AwsEmailSenderGateway gateway;

    @Autowired
    public AwsEmailProvider(AwsEmailAdapter adapter, AwsEmailSenderGateway gateway) {
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