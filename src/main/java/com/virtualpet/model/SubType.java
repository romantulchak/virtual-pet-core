package com.virtualpet.model;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class SubType extends SubAbstract{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime localDateTime;

    private String subName;

    @OneToMany(mappedBy = "subType")
    private List<Sub> subs;

    public SubType(){
        super();
    }

    public SubType(String subTypeName, int attack , int defence, String modelPath, String iconPath, int health) {
        super(attack, defence);
        this.localDateTime = LocalDateTime.now();
        setModelPath(modelPath);
        setIconPath(iconPath);
        setSubName(subTypeName);
        setHealth(health);
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

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }
}
