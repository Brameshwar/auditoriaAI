package com.auditoriaAI.controller;

import com.auditoriaAI.dto.SendEMailResponseDTO;
import com.auditoriaAI.dto.SendEmailReqDTO;
import com.auditoriaAI.mapper.SendEmailMapper;
import com.auditoriaAI.utils.FileUtils;
import com.auditoriaAI.utils.SmtpUtils;
import com.auditoriaAI.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    StringUtils stringUtils;


    @PostMapping(
            value="/v1/sendEmail",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<SendEMailResponseDTO> sendEmail( @Valid @RequestBody SendEmailReqDTO sendEmailReqDTO)
            throws Exception {

        log.info("Request received to send email to {}", sendEmailReqDTO.getToEmailId());

        SendEMailResponseDTO sendEMailResponseDTO = null;

        if( !(sendEmailReqDTO.getToEmailId().endsWith("@gmail.com") && sendEmailReqDTO.getToEmailId().endsWith("@gmail.com"))){
            sendEMailResponseDTO = sendEmailMapper.errorResponse("Email domain is incorrect, please use gmail.com");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(sendEMailResponseDTO);
        }

        try{
            String workspacePath = System.getProperty("user.dir");

            fileUtils.writeDataToFile(
                    stringUtils.replaceAllOccurrences(
                    stringUtils.replaceAllOccurrences(
                            fileUtils.parseRtfFile(workspacePath+"/src/main/resources/SampleFile.rtf"),
                            "^([^\\]]*)Tax Identification:([^\\]][^a-z]*)\\n([^\\]]*)$",
                            "$1Tax Identification: replaceTaxIdentification\n$3"
                    ),"^([^\\]]*)Type of Tax Identification Number:([^\\]][^a-z]*)\\n([^\\]]*)$",
                    "$1Type of Tax Identification Number: replaceTaxType\n$3" )
                            .replaceAll("replaceTaxIdentification","222-33-4444")
                            .replaceAll("replaceTaxType","ITIN")
                            .replaceAll("Tax Identification:\n","Tax Identification: 222-33-4444\n ")
                            .replaceAll("Type of Tax Identification Number:\n","Type of Tax Identification Number: ITIN\n")
                    ,workspacePath+"/target/updatedSampleFile.txt");


            if(fileUtils.zipDirectory(workspacePath,
                    workspacePath+"/target/temp.zip"))
                log.info("Zipping successful");
            else
                log.info("Zipping failed");


            sendEMailResponseDTO = smtpUtils.sendEmail(sendEmailReqDTO, new String[]{workspacePath+"/target/updatedSampleFile.txt",
                    workspacePath+"/target/temp.zip"});

            return ResponseEntity.status(HttpStatus.OK)
                    .body(sendEMailResponseDTO);

        }catch (Exception e){
            log.error("Exception occurred while sending email {}", e.getMessage());
            sendEMailResponseDTO = sendEmailMapper.errorResponse(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(sendEMailResponseDTO);
        }

    }

}
