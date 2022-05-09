package com.virtualpet.model.skills.template;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonView;
import com.virtualpet.model.Views;
import com.virtualpet.model.enums.ESkillCategory;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Objects;
import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@DiscriminatorColumn(name = "item_type")
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type",
        visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = DamageSkillTemplate.class, name = "damageSkillTemplate"),
        @JsonSubTypes.Type(value = DefenceSkillTemplate.class, name = "defenceSkillTemplate")
})
@Getter
@Setter
@Accessors(chain = true)
public abstract class SkillAbstractTemplate {
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

    private int maxCooldown;

    private String icon;

    private UUID reference;

    protected SkillAbstractTemplate() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SkillAbstractTemplate)) return false;
        SkillAbstractTemplate that = (SkillAbstractTemplate) o;
        return id == that.id && Objects.equals(name, that.name) && category == that.category;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, category);
    }
}
