package com.virtualpet.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.virtualpet.model.Views;
import lombok.Data;

@Data
public class SubTypeDTO {

    @JsonView(Views.SubTypeView.class)
    private Long id;

    @JsonView(Views.SubTypeView.class)
    private String name;

    @JsonView(Views.SubTypeView.class)
    private Integer attack;

    @JsonView(Views.SubTypeView.class)
    private Integer defence;

    @JsonView(Views.SubTypeView.class)
    private String modelPath;

    @JsonView(Views.SubTypeView.class)
    private String iconPath;

}
