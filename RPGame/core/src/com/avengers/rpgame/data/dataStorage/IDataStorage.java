package com.avengers.rpgame.data.dataStorage;

import com.avengers.rpgame.logic.entities.Attack;
import com.avengers.rpgame.logic.entities.Item;
import com.avengers.rpgame.logic.entities.Skill;
import com.avengers.rpgame.logic.entities.character.components.CharacterClass;

import java.util.ArrayList;

public interface IDataStorage {

    public ArrayList<CharacterClass> getCharacterClassList();
    public ArrayList<Attack> getAttacksList();
    public ArrayList<Skill> getSkillsList();
    public ArrayList<Item> getSpecialItemsList();
    public ArrayList<Item> getConsumableItemsList();
    public ArrayList<Item> getWearableItemsList();

    public ArrayList<CharacterClass> getEnemyClassList();
    public ArrayList<Attack> getEnemyAttacksList();
    public ArrayList<Item> getEnemyItemsList();
}
