package com.viasoft.emailservice.factory;

import com.viasoft.emailservice.exception.InvalidIntegrationException;
import com.viasoft.emailservice.provider.EmailProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class EmailProviderFactory {

    private final Map<String, EmailProvider> providerMap;
    private final String integration;

    public EmailProviderFactory(Map<String, EmailProvider> providerMap, @Value("${mail.integracao}") String integration) {
        this.providerMap = providerMap;
        this.integration = integration.toUpperCase();
    }

    public EmailProvider getProvider() {
        EmailProvider provider = providerMap.get(integration);
        if (provider == null) {
            throw new InvalidIntegrationException("Invalid integration: " + integration);
        }
        return provider;
    }
}