package com.virtualpet.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.virtualpet.model.items.Armor;
import com.virtualpet.model.items.Sword;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "dressed_items")
public class DressedItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView({Views.SubView.class,Views.InventoryView.class})
    private long id;

    @JsonView({Views.SubView.class,Views.InventoryView.class})
    @OneToOne
    private Sword sword;

    @JsonView({Views.SubView.class,Views.InventoryView.class})
    @OneToOne
    private Armor legs;
    @JsonView({Views.SubView.class,Views.InventoryView.class})
    @OneToOne
    private Armor shoulders;
    @JsonView({Views.SubView.class,Views.InventoryView.class})
    @OneToOne
    private Armor body;
    @JsonView({Views.SubView.class,Views.InventoryView.class})
    @OneToOne
    private Armor hands;
    @JsonView({Views.SubView.class,Views.InventoryView.class})
    @OneToOne
    private Armor head;
    @JsonView({Views.SubView.class, Views.InventoryView.class})
    @OneToOne
    private Armor shield;

}
