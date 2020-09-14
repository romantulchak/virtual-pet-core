package com.virtualpet.models;

import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;

@Entity
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({Views.InventoryView.class ,Views.SubView.class})
    private Long id;

    @OneToOne(mappedBy = "inventory")
    @JsonView(Views.InventoryView.class)
    private Sub sub;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Sub getSub() {
        return sub;
    }

    public void setSub(Sub sub) {
        this.sub = sub;
    }
}
