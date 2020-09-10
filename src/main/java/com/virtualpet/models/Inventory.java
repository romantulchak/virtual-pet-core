package com.virtualpet.models;

import javax.persistence.*;

@Entity
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "inventory")
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
