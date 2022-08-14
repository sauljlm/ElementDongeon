package com.avengers.rpgame.logic.entities.character.concrete;

import com.avengers.rpgame.logic.entities.Attack;
import com.avengers.rpgame.logic.entities.Item;
import com.avengers.rpgame.logic.entities.Skill;
import com.avengers.rpgame.logic.entities.character.abstractCharacter.AbstractCharacter;
import com.avengers.rpgame.logic.entities.character.behaviour.endFightActions.IVisitor;
import com.avengers.rpgame.logic.entities.character.components.CharacterClass;
import com.avengers.rpgame.logic.entities.character.components.animatedCharacter.AAnimatedCharacter;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;

////Class to contain any specific information for a Playable Character
public class PlayableCharacter extends AbstractCharacter {
    private int experiencePoints;
    private int[] experienceProgression;
    private int experiencePointsMax;

    public PlayableCharacter() {
//        super.setAttacks(new ArrayList<Attack>());
//        super.setItems(new ArrayList<Item>());
//        super.setSkills(new ArrayList<Skill>());
//        experienceProgression = new int[]{0,2,4,6,8,10,50,100,150,200,250,300,2000,3500,5000,6500,8000,9500,40000,70000,100000,130000,160000, 100000000};
//        experiencePointsMax = experienceProgression[((int) getLevel())];
    }

    public PlayableCharacter(int idCharacter, String name, String description, Vector2 position, double level, int healthPoints, int magicPoints, int healthPointsMax, int magicPointsMax, int strength, int speed, int magic, int resistance, int luck, int coins, CharacterClass characterClass, AAnimatedCharacter animatedEntity, ArrayList<Item> items, ArrayList<Attack> attacks, ArrayList<Skill> skills, int experiencePoints) {
        super(idCharacter, name, description, position, level, healthPoints, magicPoints, healthPointsMax, magicPointsMax, strength, speed, magic, resistance, luck, coins, characterClass, animatedEntity, items, attacks, skills);
        this.experiencePoints = experiencePoints;
        experienceProgression = new int[]{0,2,4,6,8,10,50,100,150,200,250,300,2000,3500,5000,6500,8000,9500,40000,70000,100000,130000,160000, 100000000};
        experiencePointsMax = experienceProgression[((int) getLevel())];
    }

    @Override
    public void accept(IVisitor visitor) {
        visitor.visitPlayableCharacter(this);
    }

    public int getExperiencePoints() {
        return experiencePoints;
    }

    public void setExperiencePoints(int experiencePoints) {
        this.experiencePoints = experiencePoints;
    }

    public int[] getExperienceProgression() {
        return experienceProgression;
    }

    private void setExperienceProgression(int[] experienceProgression) {
        this.experienceProgression = experienceProgression;
    }

    public int getExperiencePointsMax() {
        return experiencePointsMax;
    }

    public void setExperiencePointsMax(int experiencePointsMax) {
        this.experiencePointsMax = experiencePointsMax;
    }
}
