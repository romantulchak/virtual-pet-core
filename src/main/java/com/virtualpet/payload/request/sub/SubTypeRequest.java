package com.virtualpet.payload.request.sub;

import lombok.Data;

@Data
public class SubTypeRequest {

    private String name;

    private int health;

    private int attack;

    private int defence;
}
