package com.virtualpet.exeption.skill;

public class SkillAlreadyInShopException extends RuntimeException{
    public SkillAlreadyInShopException(String skillName){
        super(String.format("Skill name: %s already in shop", skillName));
    }
}
