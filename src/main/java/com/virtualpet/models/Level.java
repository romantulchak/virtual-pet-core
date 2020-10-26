package com.virtualpet.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Level {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private int level;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "level")
    private List<Sub> subs = new ArrayList<>();

    public Level(){
        this.level = 1;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public List<Sub> getSubs() {
        return subs;
    }

    public void setSubs(List<Sub> subs) {
        this.subs = subs;
    }
}
