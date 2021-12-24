package com.virtualpet.model;

import com.virtualpet.model.enums.ERole;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 15)
    private ERole name;

    public Role(){

    }
}
