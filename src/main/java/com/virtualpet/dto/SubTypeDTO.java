package com.virtualpet.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.virtualpet.model.Views;
import lombok.Data;

@Data
public class SubTypeDTO {

    @JsonView(Views.SubTypeView.class)
    private long id;

    @JsonView(Views.SubTypeView.class)
    private String name;

    @JsonView(Views.SubTypeView.class)
    private int attack;

    @JsonView(Views.SubTypeView.class)
    private int defence;

    @JsonView(Views.SubTypeView.class)
    private String modelPath;

    @JsonView(Views.SubTypeView.class)
    private String iconPath;

}
