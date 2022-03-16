package com.auditoriaAI.utils;

import com.auditoriaAI.config.SendEmailConfig;
import com.auditoriaAI.dto.SendEMailResponseDTO;
import com.auditoriaAI.dto.SendEmailReqDTO;
import com.auditoriaAI.mapper.SendEmailMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.util.Properties;

@Component
@Slf4j
public class SmtpUtils {

    @Autowired
    SendEmailMapper sendEmailMapper;

    @Autowired
    SendEmailConfig sendEmailConfig;


    public SendEMailResponseDTO sendEmail(SendEmailReqDTO sendEmailReqDTO, String[] filesPath) throws Exception {

        Properties properties = System.getProperties();
        properties.put("mail.smtp.host", sendEmailConfig.getSmtpHost());
        properties.put("mail.smtp.port", sendEmailConfig.getSmtpPort());
        properties.put("mail.smtp.ssl.enable", sendEmailConfig.isSsLRequired());
        properties.put("mail.smtp.auth", sendEmailConfig.isAuthRequired());
        properties.put("mail.debug", sendEmailConfig.isEnableDebug());
        properties.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.socketFactory.fallback", "false");

        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication(sendEmailReqDTO.getFromEmailId(),
                        sendEmailReqDTO.getPassword());

            }

        });

        session.setDebug(true);

        try {
            MimeMessage message = new MimeMessage(session);

            message.setFrom(new InternetAddress(sendEmailReqDTO.getFromEmailId()));

            message.addRecipient(Message.RecipientType.TO, new InternetAddress(sendEmailReqDTO.getToEmailId()));

            message.setSubject(sendEmailReqDTO.getEmailSubject());

            Multipart multipart = new MimeMultipart();

            MimeBodyPart textPart = new MimeBodyPart();

            try {

                for(String filePath:filesPath) {
                    File file = new File(filePath);
                    DataSource source = new FileDataSource(file);
                    MimeBodyPart attachmentPart = new MimeBodyPart();
                    attachmentPart.setDataHandler(new DataHandler(source));
                    attachmentPart.setFileName(file.getName());
                    multipart.addBodyPart(attachmentPart);
                }
                textPart.setText(sendEmailReqDTO.getEmailContent());
                multipart.addBodyPart(textPart);

            } catch (Exception e) {
                e.printStackTrace();
            }

            message.setContent(multipart);

            log.info("sending...");
            Transport.send(message);
            log.info("Sent message successfully....");
            return sendEmailMapper.successResponse("Email Sent successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return sendEmailMapper.errorResponse(e.getMessage());
        }
    }

}
