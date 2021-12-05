package com.virtualpet.model.items;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonView;
import com.virtualpet.model.Item;
import com.virtualpet.model.Shop;
import com.virtualpet.model.Views;
import com.virtualpet.model.enums.EItemCategory;
import com.virtualpet.model.enums.EItemType;
import com.virtualpet.model.enums.EUniqueness;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@DiscriminatorValue(value = "Sword")
@JsonTypeName("swordItem")
@Table(name = "sword")
@Getter
@Setter
public class Sword extends Item {

    @JsonView({Views.InventoryView.class, Views.SubView.class, Views.ShopView.class} )
    private int attack;

    @JsonView({Views.InventoryView.class, Views.SubView.class, Views.ShopView.class})
    private boolean isShieldAllowed;

    @ManyToOne
    private Shop shop;

    public Sword(int id, EUniqueness uniqueness, String iconPath, String name, int attack, boolean isShieldAllowed, EItemCategory eItemCategory, EItemType eItemType, int price) {
        super(id, uniqueness, iconPath, name, eItemCategory, eItemType, price);
        this.attack = attack;
        this.isShieldAllowed = isShieldAllowed;
    }

    public Sword() {
        super();
    }
}
