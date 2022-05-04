package com.virtualpet.payload.request.skill;

import lombok.Data;

@Data
public class DamageSkillRequest {

    private String name;

    private String description;

    private int price;

    private int maxCooldown;

    private int damage;

    private double criticalChance;
}
