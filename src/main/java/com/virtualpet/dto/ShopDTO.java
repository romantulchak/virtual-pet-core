package com.virtualpet.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.virtualpet.model.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ShopDTO {

    private long id;

    @JsonView(Views.ShopView.class)
    private List<DamageSkillDTO> damageSkills = new ArrayList<>();

    @JsonView(Views.ShopView.class)
    private List<DefenceSkillDTO> defenceSkills = new ArrayList<>();

    @JsonView(Views.ShopView.class)
    private List<SwordDTO> itemSwords = new ArrayList<>();

    @JsonView(Views.ShopView.class)
    private List<ArmorDTO> itemArmors = new ArrayList<>();

}
