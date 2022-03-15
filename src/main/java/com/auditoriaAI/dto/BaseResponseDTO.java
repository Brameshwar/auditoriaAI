package com.auditoriaAI.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseResponseDTO extends ErrorResponseDTO{

    @JsonProperty("message")
    private String message;

    @JsonProperty("response_code")
    private String responseCode;

}
