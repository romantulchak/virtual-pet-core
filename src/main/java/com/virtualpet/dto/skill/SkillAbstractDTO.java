package com.virtualpet.dto.skill;

import com.fasterxml.jackson.annotation.JsonView;
import com.virtualpet.model.Views;
import com.virtualpet.model.enums.ESkillCategory;
import lombok.Data;

@Data
public class SkillAbstractDTO {

    @JsonView({Views.SubView.class, Views.SkillView.class})
    private long id;

    @JsonView({Views.SubView.class, Views.SkillView.class})
    private String name;

    @JsonView({Views.SubView.class, Views.SkillView.class})
    private ESkillCategory category;

    @JsonView({Views.SubView.class, Views.SkillView.class})
    private int price;

    @JsonView({Views.SubView.class, Views.SkillView.class})
    private String description;

    @JsonView({Views.SubView.class, Views.SkillView.class})
    private int maxCooldown;

    @JsonView(Views.SkillView.class)
    private String icon;

}
