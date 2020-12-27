package com.virtualpet.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.virtualpet.model.SubType;
import com.virtualpet.model.Views;

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

    public SubTypeDTO() {
    }

    public SubTypeDTO(SubType subType) {
        this.id = subType.getId();
        this.name = subType.getSubName();
        this.attack = subType.getAttack();
        this.defence = subType.getDefence();
        this.modelPath = subType.getModelPath();
        this.iconPath = subType.getIconPath();
    }

    public Integer getAttack() {
        return attack;
    }

    public void setAttack(Integer attack) {
        this.attack = attack;
    }

    public Integer getDefence() {
        return defence;
    }

    public void setDefence(Integer defence) {
        this.defence = defence;
    }

    public String getModelPath() {
        return modelPath;
    }

    public void setModelPath(String modelPath) {
        this.modelPath = modelPath;
    }

    public String getIconPath() {
        return iconPath;
    }

    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
