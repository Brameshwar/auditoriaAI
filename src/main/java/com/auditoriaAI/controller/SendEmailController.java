package com.auditoriaAI.controller;

import com.auditoriaAI.utils.FileUtils;
import com.auditoriaAI.utils.SmtplUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/mock")
@Slf4j
public class SendEmailController {

    @Autowired
    FileUtils fileUtils;

    @Autowired
    SmtplUtils smtplUtils;


    @GetMapping(value="/sendEmail", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity sendEmail(
            @RequestParam(name="fromEmailId", defaultValue = "bramesh.srit@gmail.com") String fromEmailId,
            @RequestParam(name="toEmailId", defaultValue = "cool.bramesh@gmail.com") String toEmailId,
            @RequestParam(name="password", defaultValue = "**************") String password
    ){

        return ResponseEntity.status(HttpStatus.OK)
                .body("testing:test");

    }

}
