package com.auditoriaAI.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SendEmailReqDTO {

    @JsonProperty(value = "fromEmailId", required = true)
    @NotNull(message = "fromEmailId cannot be Null or Blank")
    private String fromEmailId;

    @JsonProperty(value = "toEmailId", required = true)
    @NotNull(message = "toEmailId cannot be Null or Blank")
    private String toEmailId;

    @JsonProperty(value = "password", required = true)
    @NotNull(message = "password cannot be Null or Blank")
    private String password;

    @JsonProperty(value = "emailSubject", required = true)
    @NotNull(message = "emailSubject cannot be Null or Blank")
    private String emailSubject;

    @JsonProperty(value = "emailContent", required = true)
    @NotNull(message = "emailContent cannot be Null or Blank")
    private String emailContent;



}
