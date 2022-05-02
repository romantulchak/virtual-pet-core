package com.virtualpet.model;


import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

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
@Accessors(chain = true)
public class SubType extends SubAbstract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private LocalDateTime createdAt;

    @NotBlank
    @Size(max = 34)
    private String name;

    @OneToMany(mappedBy = "subType")
    private List<Sub> subs;

    public SubType() {

    }

    @Override
    public SubType setAttack(int attack) {
        super.setAttack(attack);
        return this;
    }

    @Override
    public SubType setDefence(int defence) {
        super.setDefence(defence);
        return this;
    }

    @Override
    public SubType setHealth(int health) {
        super.setHealth(health);
        return this;
    }

    @Override
    public SubType setIconPath(String iconPath) {
        super.setIconPath(iconPath);
        return this;
    }

    @Override
    public SubType setModelPath(String modelPath) {
        super.setModelPath(modelPath);
        return this;
    }
}
