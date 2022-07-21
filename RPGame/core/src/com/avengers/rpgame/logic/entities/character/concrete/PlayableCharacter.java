package com.avengers.rpgame.logic.entities.character.concrete;

import com.avengers.rpgame.logic.entities.Attack;
import com.avengers.rpgame.logic.entities.Item;
import com.avengers.rpgame.logic.entities.Skill;
import com.avengers.rpgame.logic.entities.character.abstractCharacter.AbstractCharacter;
import com.avengers.rpgame.logic.entities.character.components.AnimatedCharacter;
import com.avengers.rpgame.logic.entities.character.components.CharacterClass;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

////Class to contain any specific information for a Playable Character
public class PlayableCharacter extends AbstractCharacter {
    private int experiencePoints;

    public PlayableCharacter() {
        super.setAttacks(new ArrayList<Attack>());
        super.setItems(new ArrayList<Item>());
        super.setSkills(new ArrayList<Skill>());
    }

    public PlayableCharacter(int experiencePoints) {
        this.experiencePoints = experiencePoints;
    }

    public PlayableCharacter(int idCharacter, String name, String description, Vector2 position, double level, int healthPoints, int magicPoints, int strength, int speed, int magic, int resistance, int luck, int coins, CharacterClass characterClass, AnimatedCharacter animatedEntity, ArrayList<Item> items, ArrayList<Attack> attacks, ArrayList<Skill> skills) {
        super(idCharacter, name, description, position, level, healthPoints, magicPoints, strength, speed, magic, resistance, luck, coins, characterClass, animatedEntity, items, attacks, skills);
    }

    public PlayableCharacter(int idCharacter, String name, String description, Vector2 position, double level, int healthPoints, int magicPoints, int strength, int speed, int magic, int resistance, int luck, int coins, CharacterClass characterClass, AnimatedCharacter animatedEntity, ArrayList<Item> items, ArrayList<Attack> attacks, ArrayList<Skill> skills, int experiencePoints) {
        super(idCharacter, name, description, position, level, healthPoints, magicPoints, strength, speed, magic, resistance, luck, coins, characterClass, animatedEntity, items, attacks, skills);
        this.experiencePoints = experiencePoints;
    }

    public int getExperiencePoints() {
        return experiencePoints;
    }

    public void setExperiencePoints(int experiencePoints) {
        this.experiencePoints = experiencePoints;
    }
}
