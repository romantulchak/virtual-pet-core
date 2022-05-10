package com.virtualpet.payload.request.boss;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
public class CreateBossRequest {

    @NotBlank
    private String name;

    private int droppedMoney;

    private int attack;

    private int defence;

    private int health;

    private List<Long> itemsToBeDropped;

}
