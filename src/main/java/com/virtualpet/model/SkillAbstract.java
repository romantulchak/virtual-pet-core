package com.virtualpet.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonView;
import com.virtualpet.model.enums.ESkillCategory;
import com.virtualpet.model.skills.DamageSkill;
import com.virtualpet.model.skills.DefenceSkill;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@DiscriminatorColumn(name = "item_type")
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type",
        visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = DamageSkill.class, name = "damageSkill"),
        @JsonSubTypes.Type(value = DefenceSkill.class, name = "defenceSkill")
})
@Getter
@Setter
public abstract class SkillAbstract {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView({Views.ShopView.class, Views.SubView.class, Views.SkillView.class})
    private long id;

    @JsonView({Views.SkillView.class, Views.ShopView.class, Views.SubView.class})
    @NotBlank
    @Size(max = 46)
    private String name;

    @Enumerated(EnumType.STRING)
    @JsonView({Views.SkillView.class, Views.ShopView.class, Views.SubView.class, Views.InventoryView.class})
    private ESkillCategory skillCategory;

    @JsonView({Views.SkillView.class, Views.ShopView.class, Views.SubView.class, Views.InventoryView.class})
    private int price;

    @JsonView({Views.SkillView.class, Views.SubView.class,Views.InventoryView.class})
    @NotBlank
    @Size(max = 300)
    private String skillDescription;

    @JsonView({Views.SkillView.class, Views.SubView.class, Views.InventoryView.class})
    private LocalDateTime cooldown;

    @JsonView({Views.SkillView.class, Views.ShopView.class, Views.SubView.class, Views.InventoryView.class})
    private int maxCooldown;

    @JsonView({Views.SkillView.class, Views.ShopView.class, Views.SubView.class, Views.InventoryView.class})
    private String icon;

    @ManyToOne
    private Sub sub;

    @ManyToOne
    private Shop shop;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SkillAbstract)) return false;
        SkillAbstract that = (SkillAbstract) o;
        return id == that.id && Objects.equals(name, that.name) && skillCategory == that.skillCategory;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, skillCategory);
    }
}
