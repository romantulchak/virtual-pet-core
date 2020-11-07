package com.virtualpet.models;

import com.fasterxml.jackson.annotation.JsonView;
import com.virtualpet.models.Enums.EItemCategory;
import com.virtualpet.models.Enums.EItemType;
import com.virtualpet.models.Enums.EUniqueness;

import javax.persistence.*;
import java.util.List;

@MappedSuperclass
public abstract class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Enumerated(EnumType.STRING)
    @JsonView({Views.InventoryView.class, Views.SubView.class})
    private EUniqueness uniqueness;

    @JsonView({Views.InventoryView.class})
    private String iconPath;

    @JsonView({Views.InventoryView.class, Views.SubView.class})
    private String name;
    @JsonView(Views.InventoryView.class)
    @Enumerated(EnumType.STRING)
    private EItemType eItemType;
    @Enumerated(EnumType.STRING)
    @JsonView(Views.InventoryView.class)
    private EItemCategory eItemCategory;

    protected Item(int id, EUniqueness uniqueness, String iconPath, String name, EItemCategory eItemCategory, EItemType eItemType){
        this.id = id;
        this.uniqueness = uniqueness;
        this.iconPath = iconPath;
        this.name = name;
        this.eItemCategory = eItemCategory;
        this.eItemType = eItemType;
    }
    protected Item(){

    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public EUniqueness getUniqueness() {
        return uniqueness;
    }

    public void setUniqueness(EUniqueness uniqueness) {
        this.uniqueness = uniqueness;
    }

    public String getIconPath() {
        return iconPath;
    }

    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EItemCategory geteItemCategory() {
        return eItemCategory;
    }

    public void seteItemCategory(EItemCategory eItemCategory) {
        this.eItemCategory = eItemCategory;
    }

    public EItemType geteItemType() {
        return eItemType;
    }

    public void seteItemType(EItemType eItemType) {
        this.eItemType = eItemType;
    }
}

