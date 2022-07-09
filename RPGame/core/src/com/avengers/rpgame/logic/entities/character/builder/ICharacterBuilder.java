package com.avengers.rpgame.logic.entities.character.builder;

import com.avengers.rpgame.logic.entities.Attack;
import com.avengers.rpgame.logic.entities.Item;
import com.avengers.rpgame.logic.entities.Skill;
import com.avengers.rpgame.logic.entities.character.components.AnimatedCharacter;
import com.avengers.rpgame.logic.entities.character.components.CharacterClass;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

//Interface to enforce all different/possible steps to build a character
public interface ICharacterBuilder {
    void setCharacterBasicInfo(int idCharacter, String name, String description, Vector2 position, double level, int healthPoints, int magicPoints, int coins);
    void setCharacterAttributes(int strength, int speed, int magic, int resistance, int luck);
    void setCharacterClass(CharacterClass characterClass);
    void setAnimatedCharacter(AnimatedCharacter animatedCharacter);
    void setItems(ArrayList<Item> items);
    void setAttacks(ArrayList<Attack> attacks);
    void setSkills(ArrayList<Skill> skills);
}
