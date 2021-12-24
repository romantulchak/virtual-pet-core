package com.virtualpet.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.virtualpet.model.items.Armor;
import com.virtualpet.model.items.Sword;
import com.virtualpet.model.Views;
import lombok.Data;

import java.util.List;

@Data
public class InventoryDTO {

    @JsonView(Views.InventoryView.class)
    private long id;

    @JsonView(Views.InventoryView.class)
    private int maxInventorySize;

    @JsonView(Views.InventoryView.class)
    private List<Sword> swords;

    @JsonView(Views.InventoryView.class)
    private List<Armor> armors;

}
