package com.virtualpet.exeption;

public class SkillAlreadyExistException extends RuntimeException{
    public SkillAlreadyExistException(String skillName){
        super(String.format("Skill with name: %s already exist", skillName));
    }
}
