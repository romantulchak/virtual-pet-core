package com.virtualpet.model;

import com.virtualpet.model.items.Armor;
import com.virtualpet.model.items.Sword;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToMany(mappedBy = "shop", cascade = CascadeType.ALL)
    private List<Sword> itemSwords = new ArrayList<>();

    @OneToMany(mappedBy = "shop", cascade = CascadeType.ALL)
    private List<Armor> itemArmors = new ArrayList<>();

}
