package com.virtualpet.models;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class SubType extends SubAbstract{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public LocalDateTime localDateTime;

    @OneToMany(mappedBy = "subType")
    private List<Sub> subs;

    public SubType(){
        super();
    }

    public SubType(Integer attack , Integer defence, String modelPath, String iconPath) {
        super(attack, defence);
        this.localDateTime = LocalDateTime.now();
        setModelPath(modelPath);
        setIconPath(iconPath);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public List<Sub> getSubs() {
        return subs;
    }

    public void setSubs(List<Sub> subs) {
        this.subs = subs;
    }
}
