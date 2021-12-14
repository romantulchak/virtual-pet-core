package com.virtualpet.exeption.skill;

public class SkillAlreadyExistException extends RuntimeException{
    public SkillAlreadyExistException(String skillName){
        super(String.format("Skill with name: %s already exist", skillName));
    }
}
