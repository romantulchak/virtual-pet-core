package com.virtualpet.payload.response;

import com.fasterxml.jackson.annotation.JsonView;
import com.virtualpet.model.Views;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
public class SubResponse<T> {

    @JsonView( Views.SubView.class)
    private T entity;

    @JsonView(Views.SubView.class)
    private String messageResponse;

    public SubResponse(){

    }
}
