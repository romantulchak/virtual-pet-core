package com.virtualpet.exeption;

public class SkillNotFoundException extends RuntimeException{
    public SkillNotFoundException(){
        super("Skill not found exception");
    }
}
