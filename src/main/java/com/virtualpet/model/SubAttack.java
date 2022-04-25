package com.virtualpet.model;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@Setter
public class SubAttack {

    @JsonView({Views.SubView.class, Views.GameSubView.class})
    private int attackUpLevel;

    @JsonView({Views.SubView.class, Views.GameSubView.class})
    private int attackMultiplier;

    @JsonView({Views.SubView.class, Views.GameSubView.class})
    private long attackMoneyUp;

    public SubAttack(){
        this.attackUpLevel = 1;
        this.attackMultiplier = 5;
        this.attackMoneyUp = 150L;
    }
}
