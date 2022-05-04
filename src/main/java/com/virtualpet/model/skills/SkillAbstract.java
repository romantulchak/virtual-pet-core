package com.virtualpet.model.skills;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.virtualpet.model.Sub;
import com.virtualpet.model.enums.ESkillCategory;
import com.virtualpet.model.skills.template.SkillAbstractTemplate;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@DiscriminatorColumn(name = "skill_type")
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type",
        visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = DamageSkill.class, name = "damageSkill"),
        @JsonSubTypes.Type(value = DefenceSkill.class, name = "defenceSkill")
})
public abstract class SkillAbstract {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotBlank
    @Size(max = 46)
    private String name;

    @Enumerated(EnumType.STRING)
    private ESkillCategory category;

    private int price;

    @NotBlank
    @Size(max = 300)
    private String description;

    private LocalDateTime cooldown;

    private int maxCooldown;

    private String icon;

    private UUID reference;

    @ManyToOne
    private Sub sub;

    protected SkillAbstract() {
    }

    protected SkillAbstract(SkillAbstractTemplate skillAbstract, Sub sub){
        this.name = skillAbstract.getName();
        this.category = skillAbstract.getCategory();
        this.price = skillAbstract.getPrice();
        this.description = skillAbstract.getDescription();
        this.maxCooldown = skillAbstract.getMaxCooldown();
        this.icon = skillAbstract.getIcon();
        this.reference = skillAbstract.getReference();
        this.sub = sub;
    }
}
