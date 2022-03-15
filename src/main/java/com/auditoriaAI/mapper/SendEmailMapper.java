package com.auditoriaAI.mapper;

import com.auditoriaAI.dto.SendEMailResponseDTO;
import com.auditoriaAI.utils.SendEmailContants;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SendEmailMapper {


    public SendEMailResponseDTO successResponse(String message) throws Exception{
        SendEMailResponseDTO sendEMailResponseDTO = new SendEMailResponseDTO();
        try{
            sendEMailResponseDTO.setEmailStatus(SendEmailContants.SUCCESS);
            sendEMailResponseDTO.setStatus(SendEmailContants.SUCCESS);
            sendEMailResponseDTO.setMessage(message);

            ObjectMapper mapper = new ObjectMapper();

            log.debug("SendEmailMapper : sendEmail response"+ mapper.writeValueAsString(sendEMailResponseDTO));

            return sendEMailResponseDTO;

        }catch (Exception e){
            throw new Exception(""+e.getMessage());
        }


    }

    public SendEMailResponseDTO errorResponse(String errorMessage) throws Exception{
        SendEMailResponseDTO sendEMailResponseDTO = new SendEMailResponseDTO();
        try{
            sendEMailResponseDTO.setEmailStatus(SendEmailContants.FAILURE);
            sendEMailResponseDTO.setStatus(SendEmailContants.FAILURE);
            sendEMailResponseDTO.setMessage(errorMessage);

            ObjectMapper mapper = new ObjectMapper();

            log.debug("SendEmailMapper : sendEmail response"+ mapper.writeValueAsString(sendEMailResponseDTO));

            return sendEMailResponseDTO;

        }catch (Exception e){
            throw new Exception(""+e.getMessage());
        }


    }



}
