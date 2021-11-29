package com.virtualpet.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "boss", uniqueConstraints = {
        @UniqueConstraint(columnNames = "name")
})
@Getter
@Setter
public class Boss extends SubAbstract {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotBlank
    @Size(max = 34)
    private String name;

    private int droppedMoney;

    public Boss(String name, int attack, int defence, String iconPath, String modelPath, int health) {
        super(attack, defence);
        setIconPath(iconPath);
        setModelPath(modelPath);
        setHealth(health);
        setName(name);
    }

    public Boss() {

    }
}
