package com.virtualpet.payload.response;

import com.fasterxml.jackson.annotation.JsonView;
import com.virtualpet.model.Views;
import org.springframework.http.HttpStatus;

public class SubResponse<T> {

    @JsonView( Views.SubView.class)
    private T entity;

    @JsonView(Views.SubView.class)
    private String messageResponse;

    public SubResponse(){

    }
    public SubResponse(T entity, String messageResponse){
        this.entity = entity;
        this.messageResponse = messageResponse;
    }

    public String getMessageResponse() {
        return messageResponse;
    }

    public void setMessageResponse(String messageResponse) {
        this.messageResponse = messageResponse;
    }

    public T getEntity() {
        return entity;
    }

    public void setEntity(T entity) {
        this.entity = entity;
    }

}
