package com.auditoriaAI.utils;

import com.auditoriaAI.dto.SendEMailResponseDTO;
import com.auditoriaAI.dto.SendEmailReqDTO;
import com.auditoriaAI.mapper.SendEmailMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SmtpUtils {

    @Autowired
    FileUtils fileUtils;

    @Autowired
    SendEmailMapper sendEmailMapper;


    public SendEMailResponseDTO sendEmail(SendEmailReqDTO sendEmailReqDTO) throws Exception {

        return sendEmailMapper.successResponse("success");

    }

}
