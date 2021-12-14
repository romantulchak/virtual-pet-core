package com.virtualpet.exeption.skill;

public class SkillNotFoundException extends RuntimeException{
    public SkillNotFoundException(){
        super("Skill not found exception");
    }
}
