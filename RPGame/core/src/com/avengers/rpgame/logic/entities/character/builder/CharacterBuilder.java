package com.avengers.rpgame.logic.entities.character.builder;

import com.avengers.rpgame.logic.entities.Attack;
import com.avengers.rpgame.logic.entities.Item;
import com.avengers.rpgame.logic.entities.Skill;
import com.avengers.rpgame.logic.entities.character.abstractCharacter.AbstractCharacter;
import com.avengers.rpgame.logic.entities.character.components.animatedCharacter.AAnimatedCharacter;
import com.avengers.rpgame.logic.entities.character.components.CharacterClass;
import com.avengers.rpgame.logic.entities.character.concrete.PlayableCharacter;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

//Implementation of ICharacterBuilder with different builders for all different/possible steps to build a Character
public class CharacterBuilder implements ICharacterBuilder {
    int idCharacter;
    String name;
    String description;
    Vector2 position;
    double level;
    int healthPoints;
    int magicPoints;
    int strength;
    int speed;
    int magic;
    int resistance;
    int luck;
    int coins;
    CharacterClass characterClass;
    AAnimatedCharacter animatedCharacter;
    ArrayList<Item> items;
    ArrayList<Attack> attacks;
    ArrayList<Skill> skills;

    @Override
    public void setCharacterBasicInfo(int idCharacter, String name, String description,Vector2 position, double level, int healthPoints, int magicPoints, int coins) {
    this.idCharacter = idCharacter;
    this.name = name;
    this.description = description;
    this.position = position;
    this.level = level;
    this.healthPoints = healthPoints;
    this.magicPoints = magicPoints;
    this.coins = coins;
    }

    @Override
    public void setCharacterAttributes(int strength, int speed, int magic, int resistance, int luck) {
    this.strength = strength;
    this.speed = speed;
    this.magic = magic;
    this.resistance = resistance;
    this.luck = luck;
    }

    @Override
    public void setCharacterClass(CharacterClass characterClass) {
    this.characterClass = characterClass;
    }

    @Override
    public void setAnimatedCharacter(AAnimatedCharacter animatedCharacter) {
    this.animatedCharacter = animatedCharacter;
    }

    @Override
    public void setItems(ArrayList<Item> items) {
    this.items = items;
    }

    @Override
    public void setAttacks(ArrayList<Attack> attacks) {
    this.attacks = attacks;
    }

    @Override
    public void setSkills(ArrayList<Skill> skills) {
    this.skills = skills;
    }

    public AbstractCharacter getResult(){
        return new PlayableCharacter(idCharacter, name, description, position, level, healthPoints, magicPoints, strength, speed, magic, resistance, luck, coins, characterClass, animatedCharacter, items, attacks, skills);
    }
}
