package com.virtualpet.payload.response;

import com.fasterxml.jackson.annotation.JsonView;
import com.virtualpet.dtos.SubDTO;
import com.virtualpet.models.Views;
import org.springframework.http.HttpStatus;

public class SubResponse<T> {

    @JsonView(Views.SubView.class)
    private T subDTO;

    @JsonView(Views.SubView.class)
    private String messageResponse;

    @JsonView(Views.SubView.class)
    private HttpStatus httpStatus;
    public SubResponse(){

    }
    public SubResponse(T subDTO, String messageResponse, HttpStatus httpStatus){
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

    public T getSubDTO() {
        return subDTO;
    }

    public void setSubDTO(T subDTO) {
        this.subDTO = subDTO;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
}
