package com.virtualpet.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "level")
@Getter
@Setter
public class Level {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private int level;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "level")
    private List<Sub> subs = new ArrayList<>();
}
