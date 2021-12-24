package com.virtualpet.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.virtualpet.model.skills.SkillAbstract;
import com.virtualpet.model.sub.Currency;
import com.virtualpet.model.sub.Money;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "sub", uniqueConstraints = {
        @UniqueConstraint(columnNames = "name")
})
@Getter
@Setter
@Accessors(chain = true)
public class Sub extends SubAbstract {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(Views.SubView.class)
    private long id;

    @JsonView({Views.SubView.class, Views.FriendView.class})
    @NotBlank
    @Size(max = 40)
    private String name;

    @OneToOne(orphanRemoval = true)
    @JsonView(Views.SubView.class)
    private Inventory inventory;

    @ManyToOne
    @JsonView(Views.SubView.class)
    private User user;


    @ManyToOne
    @JsonView(Views.FriendView.class)
    private SubType subType;

    @Embedded
    private Money money;

    @ManyToOne
    private Level level;

    @Embedded
    private SubAttack subAttack;

    @Embedded
    private Currency currency;

    @OneToOne
    private DressedItem dressedItems;

    @OneToMany(mappedBy = "sub")
    private List<SkillAbstract> skills;

    public Sub() {
        this.currency = new Currency();
        this.subAttack = new SubAttack();
        this.money = new Money();
    }

    @Override
    public Sub setAttack(int attack) {
        super.setAttack(attack);
        return this;
    }

    @Override
    public Sub setDefence(int defence) {
        super.setDefence(defence);
        return this;
    }

    @Override
    public Sub setHealth(int health) {
        super.setHealth(health);
        return this;
    }

    @Override
    public Sub setIconPath(String iconPath) {
        super.setIconPath(iconPath);
        return this;
    }

    @Override
    public Sub setModelPath(String modelPath) {
        super.setModelPath(modelPath);
        return this;
    }
}
