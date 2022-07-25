package com.avengers.rpgame.data.dataStorage;

import com.avengers.rpgame.data.dataStorage.DataStorage;
import com.avengers.rpgame.logic.entities.*;
import com.avengers.rpgame.logic.entities.character.components.CharacterClass;

import java.util.ArrayList;

public class DataStorageProxy implements IDataStorage {

    private DataStorage dataStorage = DataStorage.getInstance() ;

    @Override
    public ArrayList<CharacterClass> getCharacterClassList() {
        return dataStorage.getCharacterClassList();
    }

    @Override
    public ArrayList<Attack> getAttacksList() {
        return dataStorage.getAttacksList();
    }

    @Override
    public ArrayList<Skill> getSkillsList() {
        return dataStorage.getSkillsList();
    }

    @Override
    public ArrayList<Item> getSpecialItemsList() {
        return dataStorage.getSpecialItemsList();
    }

    @Override
    public ArrayList<Item> getConsumableItemsList() {
        return dataStorage.getConsumableItemsList();
    }

    @Override
    public ArrayList<Item> getWearableItemsList() {
        return dataStorage.getWearableItemsList();
    }

    @Override
    public ArrayList<CharacterClass> getEnemyClassList() {
        return dataStorage.getEnemyClassList();
    }

    @Override
    public ArrayList<Attack> getEnemyAttacksList() {
        return dataStorage.getEnemyAttacksList();
    }

    @Override
    public ArrayList<Item> getEnemyItemsList() {
        return dataStorage.getEnemyItemsList();
    }
}
