package com.virtualpet.payload.response;

import com.fasterxml.jackson.annotation.JsonView;
import com.virtualpet.dtos.SubDTO;
import com.virtualpet.models.Views;
import org.springframework.http.HttpStatus;

public class SubResponse {

    @JsonView(Views.SubView.class)
    private SubDTO subDTO;

    @JsonView(Views.SubView.class)
    private String messageResponse;

    @JsonView(Views.SubView.class)
    private HttpStatus httpStatus;
    public SubResponse(){

    }
    public SubResponse(SubDTO subDTO, String messageResponse, HttpStatus httpStatus){
        this.subDTO = subDTO;
        this.messageResponse = messageResponse;
        this.httpStatus = httpStatus;
    }

    public String getMessageResponse() {
        return messageResponse;
    }

    public void setMessageResponse(String messageResponse) {
        this.messageResponse = messageResponse;
    }

    public SubDTO getSubDTO() {
        return subDTO;
    }

    public void setSubDTO(SubDTO subDTO) {
        this.subDTO = subDTO;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
}
