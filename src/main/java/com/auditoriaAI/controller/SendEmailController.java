package com.auditoriaAI.controller;

import com.auditoriaAI.dto.SendEMailResponseDTO;
import com.auditoriaAI.dto.SendEmailReqDTO;
import com.auditoriaAI.mapper.SendEmailMapper;
import com.auditoriaAI.utils.FileUtils;
import com.auditoriaAI.utils.SmtpUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


@RestController
@RequestMapping("/api")
@Slf4j
public class SendEmailController {

    @Autowired
    FileUtils fileUtils;

    @Autowired
    SmtpUtils smtpUtils;

    @Autowired
    SendEmailMapper sendEmailMapper;


    @PostMapping(
            value="/v1/sendEmail",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<SendEMailResponseDTO> sendEmail( @Valid @RequestBody SendEmailReqDTO sendEmailReqDTO)
            throws Exception {

        log.info("Request received to send email from {} to {}", sendEmailReqDTO.getFromEmailId(),
                sendEmailReqDTO.getFromEmailId());

        SendEMailResponseDTO sendEMailResponseDTO = null;

        try{

            sendEMailResponseDTO = sendEmailMapper.successResponse("Checking");

        }catch (Exception e){
            log.error("Exception occurred while sending email {}", e.getMessage());
            sendEMailResponseDTO = sendEmailMapper.errorResponse(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(sendEMailResponseDTO);

    }

}
