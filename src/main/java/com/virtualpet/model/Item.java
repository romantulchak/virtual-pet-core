package com.virtualpet.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonView;
import com.virtualpet.model.enums.EItemCategory;
import com.virtualpet.model.enums.EItemType;
import com.virtualpet.model.enums.EUniqueness;
import com.virtualpet.model.items.Armor;
import com.virtualpet.model.items.Sword;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@DiscriminatorColumn(name = "item_type")
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type",
        visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Sword.class, name = "swordItem"),
        @JsonSubTypes.Type(value = Armor.class, name = "armorItem")
})
@Getter
@Setter
public abstract class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView({Views.ShopView.class, Views.InventoryView.class, Views.SubView.class})
    private long id;

    @Enumerated(EnumType.STRING)
    @JsonView({Views.InventoryView.class, Views.SubView.class, Views.ShopView.class})
    private EUniqueness uniqueness;

    @JsonView({Views.InventoryView.class,Views.ShopView.class, Views.SubView.class})
    private String iconPath;

    @JsonView({Views.InventoryView.class, Views.SubView.class, Views.ShopView.class})
    @NotBlank
    @Size(max = 60)
    private String name;

    @JsonView({Views.InventoryView.class, Views.ShopView.class, Views.SubView.class})
    @Enumerated(EnumType.STRING)
    private EItemType itemType;

    @Enumerated(EnumType.STRING)
    @JsonView({Views.InventoryView.class, Views.ShopView.class, Views.SubView.class})
    private EItemCategory itemCategory;

    @JsonView({Views.InventoryView.class, Views.ShopView.class, Views.SubView.class})
    private int price;

    @ManyToOne
    private Inventory inventory;

    private boolean hasDressed;

    protected Item(){

    }

    protected Item(long id, EUniqueness uniqueness, String iconPath, String name, EItemCategory itemCategory, EItemType itemType, int price){
        this.id = id;
        this.uniqueness = uniqueness;
        this.iconPath = iconPath;
        this.name = name;
        this.itemCategory = itemCategory;
        this.itemType = itemType;
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;
        Item item = (Item) o;
        return id == item.id && price == item.price && uniqueness == item.uniqueness && Objects.equals(iconPath, item.iconPath) && Objects.equals(name, item.name) && itemType == item.itemType && itemCategory == item.itemCategory;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, uniqueness, iconPath, name, itemType, itemCategory, price);
    }
}

