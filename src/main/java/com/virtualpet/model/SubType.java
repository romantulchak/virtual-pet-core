package com.virtualpet.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "sub_type", uniqueConstraints = {
        @UniqueConstraint(columnNames = "name")
})
@Getter
@Setter
public class SubType extends SubAbstract{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private LocalDateTime createdAt;

    @NotBlank
    @Size(max = 34)
    private String name;

    @OneToMany(mappedBy = "subType")
    private List<Sub> subs;

    public SubType(){

    }

    public SubType(String name, int attack , int defence, String modelPath, String iconPath, int health) {
        super(attack, defence);
        this.createdAt = LocalDateTime.now();
        setModelPath(modelPath);
        setIconPath(iconPath);
        setName(name);
        setHealth(health);
    }
}
