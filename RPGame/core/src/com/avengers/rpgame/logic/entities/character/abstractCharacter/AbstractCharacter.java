package com.avengers.rpgame.logic.entities.character.abstractCharacter;

import com.avengers.rpgame.logic.entities.Attack;
import com.avengers.rpgame.logic.entities.Item;
import com.avengers.rpgame.logic.entities.Skill;
import com.avengers.rpgame.logic.entities.character.components.AnimatedCharacter;
import com.avengers.rpgame.logic.entities.character.components.CharacterClass;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
//Abstract Father class for all types of characters
public abstract class AbstractCharacter {
    private int idCharacter;
    private String name; //Example: King Jorge 9th
    private String description; //Example: King Jorge 9th was the saved princess Leia when he was 20yo.....
    private Vector2 position;
    private double level;
    private int healthPoints;
    private int magicPoints;
    private int strength;
    private int speed;
    private int magic;
    private int resistance; //Affects hPRecoveryRate/mPRecoveryRate by some math formula
    private int luck;
    private CharacterClass characterClass;
    private AnimatedCharacter animatedCharacter;
    private ArrayList<Item> items;
    private ArrayList<Attack> attacks;
    private ArrayList<Skill> skills;

    public AbstractCharacter() {
    }

    public AbstractCharacter(int idCharacter, String name, String description, Vector2 position, double level, int healthPoints, int magicPoints, int strength, int speed, int magic, int resistance, int luck, CharacterClass characterClass, AnimatedCharacter animatedEntity, ArrayList<Item> items, ArrayList<Attack> attacks, ArrayList<Skill> skills) {
        this.idCharacter = idCharacter;
        this.name = name;
        this.description = description;
        this.position = position;
        this.level = level;
        this.healthPoints = healthPoints;
        this.magicPoints = magicPoints;
        this.strength = strength;
        this.speed = speed;
        this.magic = magic;
        this.resistance = resistance;
        this.luck = luck;
        this.characterClass = characterClass;
        this.animatedCharacter = animatedEntity;
        this.items = items;
        this.attacks = attacks;
        this.skills = skills;
    }

    public int getIdCharacter() {
        return idCharacter;
    }

    public void setIdCharacter(int idCharacter) {
        this.idCharacter = idCharacter;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public double getLevel() {
        return level;
    }

    public void setLevel(double level) {
        this.level = level;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    public int getMagicPoints() {
        return magicPoints;
    }

    public void setMagicPoints(int magicPoints) {
        this.magicPoints = magicPoints;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getMagic() {
        return magic;
    }

    public void setMagic(int magic) {
        this.magic = magic;
    }

    public int getResistance() {
        return resistance;
    }

    public void setResistance(int resistance) {
        this.resistance = resistance;
    }

    public int getLuck() {
        return luck;
    }

    public void setLuck(int luck) {
        this.luck = luck;
    }

    public CharacterClass getCharacterClass() {
        return characterClass;
    }

    public void setCharacterClass(CharacterClass characterClass) {
        this.characterClass = characterClass;
    }

    public AnimatedCharacter getAnimatedCharacter() {
        return animatedCharacter;
    }

    public void setAnimatedCharacter(AnimatedCharacter animatedCharacter) {
        this.animatedCharacter = animatedCharacter;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public ArrayList<Attack> getAttacks() {
        return attacks;
    }

    public void setAttacks(ArrayList<Attack> attacks) {
        this.attacks = attacks;
    }

    public ArrayList<Skill> getSkills() {
        return skills;
    }

    public void setSkills(ArrayList<Skill> skills) {
        this.skills = skills;
    }
}
