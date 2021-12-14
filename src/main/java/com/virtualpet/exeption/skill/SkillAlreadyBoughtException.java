package com.virtualpet.exeption.skill;

public class SkillAlreadyBoughtException extends RuntimeException{
    public SkillAlreadyBoughtException(String subName, String skillName){
        super(String.format("Sub %s have already bought skill %s", subName, skillName));
    }
}
