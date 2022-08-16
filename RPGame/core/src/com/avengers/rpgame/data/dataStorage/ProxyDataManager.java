package com.avengers.rpgame.data.dataStorage;

import com.avengers.rpgame.logic.entities.Attack;
import com.avengers.rpgame.logic.entities.Item;
import com.avengers.rpgame.logic.entities.Skill;
import com.avengers.rpgame.logic.entities.character.components.CharacterClass;

import java.util.ArrayList;

/*******************************************************************************
 *Manager to access all game data such as attacks, items, character class, etc
 * using Proxy Pattern
 *******************************************************************************/
public class ProxyDataManager {

    IDataStorage dataStorageProxy;

    public ProxyDataManager(){
        dataStorageProxy = new DataStorageProxy();
    }

    public CharacterClass getCharacterClass(String pName) {
        CharacterClass result = null;
        for (CharacterClass temp: dataStorageProxy.getCharacterClassList()){
            if (temp.getName().contains(pName)){
                result = temp;
            }
        }
        return result;
    }

    public ArrayList<Attack> getAttacksList(String pName, double pLevel) {
        ArrayList<Attack> result = new ArrayList<Attack>();
        for (Attack temp: dataStorageProxy.getAttacksList()){
            if (temp.getName().contains(pName) && temp.getUnlockLevel()<=pLevel){
                result.add(temp);
            }
        }
        return result;
    }

    public ArrayList<Skill> getSkillsList(String pName, double pLevel) {
        ArrayList<Skill> result = new ArrayList<Skill>();
        for (Skill temp: dataStorageProxy.getSkillsList()){
            if (temp.getName().contains(pName) && temp.getUnlockLevel()<=pLevel){
                result.add(temp);
            }
        }
        return result;
    }

    public ArrayList<Item> getConsumableItemsList(String pName, double pLevel) {
        ArrayList<Item> result = new ArrayList<>();
        for (Item temp: dataStorageProxy.getConsumableItemsList()){
            if (temp.getName().contains(pName) && temp.getUnlockLevel()<=pLevel){
                result.add(temp);
            }
        }
        return result;
    }

    public ArrayList<Item> getConsumableItemsList(String pName) {
        ArrayList<Item> result = new ArrayList<>();
        for (Item temp: dataStorageProxy.getConsumableItemsList()){
            if (temp.getName().contains(pName)){
                result.add(temp);
            }
        }
        return result;
    }

    public ArrayList<Item> getWearableItemsList() {
        return dataStorageProxy.getWearableItemsList();
    }

    public ArrayList<Item> getWearableItemsList(String pName, double pLevel) {
        ArrayList<Item> result = new ArrayList<>();
        for (Item temp: dataStorageProxy.getWearableItemsList()){
            if (temp.getName().contains(pName) && temp.getUnlockLevel()<=pLevel){
                result.add(temp);
            }
        }
        return result;
    }

    public ArrayList<Item> getSpecialItemsList() {
        return dataStorageProxy.getSpecialItemsList();
    }

    public Item getSpecialItem(String description) {
        Item itemFound = null;
        for (Item currentItem: dataStorageProxy.getSpecialItemsList()) {
            if (currentItem.getDescription().equals(description)) {
                itemFound = currentItem;
            }
        }
        return itemFound;
    }

    public CharacterClass getEnemyClass(String pName) {
        CharacterClass result = null;
        for (CharacterClass temp: dataStorageProxy.getEnemyClassList()){
            if (temp.getName().contains(pName)){
                result = temp;
            }
        }
        return result;
    }

    public ArrayList<Attack> getEnemyAttacksList(String pName) {
        ArrayList<Attack> result = new ArrayList<>();
        for (Attack temp: dataStorageProxy.getEnemyAttacksList()){
            if (temp.getName().contains(pName)){
                result.add(temp);
            }
        }
        return result;
    }

    public ArrayList<Item> getEnemyItemsList(String pName) {
        ArrayList<Item> result = new ArrayList<>();
        for (Item temp: dataStorageProxy.getEnemyItemsList()){
            if (temp.getName().contains(pName)){
                result.add(temp);
            }
        }
        return result;
    }
}
