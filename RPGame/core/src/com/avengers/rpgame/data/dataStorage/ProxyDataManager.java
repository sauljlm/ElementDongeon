package com.avengers.rpgame.data.dataStorage;

import com.avengers.rpgame.logic.entities.Attack;
import com.avengers.rpgame.logic.entities.Item;
import com.avengers.rpgame.logic.entities.Skill;
import com.avengers.rpgame.logic.entities.character.components.CharacterClass;

import java.util.ArrayList;

public class ProxyDataManager {

    IDataStorage dataStorageProxy;

    public ProxyDataManager(){
        dataStorageProxy = new DataStorageProxy();
    }

    public CharacterClass getCharacterClass(String pName) {
        CharacterClass result = null;
        for (CharacterClass temp: dataStorageProxy.getCharacterClassList()){
            if (temp.getName().equalsIgnoreCase(pName)){
                result = temp;
            }
        }
        return result;
    }

    public ArrayList<Attack> getAttacksList(String pName, int pLevel) {
        ArrayList<Attack> result = new ArrayList<>();
        for (Attack temp: dataStorageProxy.getAttacksList()){
            if (temp.getName().contains(pName) && temp.getUnlockLevel()<=pLevel){
                result.add(temp);
            }
        }
        return result;
    }

    public ArrayList<Skill> getSkillsList(String pName, int pLevel) {
        ArrayList<Skill> result = new ArrayList<>();
        for (Skill temp: dataStorageProxy.getSkillsList()){
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

    public CharacterClass getEnemyClass(String pName) {
        CharacterClass result = null;
        for (CharacterClass temp: dataStorageProxy.getEnemyClassList()){
            if (temp.getName().equalsIgnoreCase(pName)){
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
