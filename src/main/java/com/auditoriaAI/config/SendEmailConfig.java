/*
package com.auditoriaAI.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.context.annotation.ApplicationScope;

@ApplicationScope
@Setter
@Getter
@PropertySource(value={"classpath:sendEmail.properties"})
public class SendEmailConfig {

    @Value("${smtpHost}")
    private String smtpHost;

    @Value("${smtpPort}")
    private int smtpPort;

    @Value("${enableSsl}")
    private boolean enableSsl;

    @Value("${isAuth}")
    private boolean isAuth;

}
*/
