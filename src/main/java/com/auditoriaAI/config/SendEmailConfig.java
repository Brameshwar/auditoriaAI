package com.auditoriaAI.config;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Setter
@Getter
@PropertySource(value={"classpath:smtp.properties"})
public class SendEmailConfig {


    @Value("${mail.smtp.host}")
    private String smtpHost;

    @Value("${mail.smtp.port}")
    private int smtpPort;

    @Value("${mail.smtp.ssl.enable}")
    private boolean isSsLRequired;

    @Value("${mail.smtp.auth}")
    private boolean isAuthRequired;

    @Value("${mail.debug}")
    private boolean enableDebug;


}
