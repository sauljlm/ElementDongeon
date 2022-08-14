package com.avengers.rpgame.data.dataStorage;

import com.avengers.rpgame.data.dataStorage.DataStorage;
import com.avengers.rpgame.logic.entities.*;
import com.avengers.rpgame.logic.entities.character.components.CharacterClass;
import com.avengers.rpgame.utils.Resources;

import java.util.ArrayList;

public class DataStorageProxy implements IDataStorage {

    private DataStorage dataStorage = DataStorage.getInstance() ;

    @Override
    public ArrayList<CharacterClass> getCharacterClassList() {
        ArrayList<CharacterClass> result = dataStorage.getCharacterClassList();
        if (result.isEmpty()){
            CharacterClass basic = new CharacterClass(1, "Knight/Archer/Mage", "Desconocido", 100, 0, 10, 0, 80, 30, 0, 30, 70);
            result.add(basic);
        }
        return result;
    }

    @Override
    public ArrayList<Attack> getAttacksList() {
        ArrayList<Attack> result = dataStorage.getAttacksList();
        if (result.isEmpty()){
            Attack basic = new Attack(1,"Knight/Archer/Mage", "Ataque basico", 0, 1,Resources.ironSword, 3, 0, 10);
            result.add(basic);
        }
        return result;
    }

    @Override
    public ArrayList<Skill> getSkillsList() {
        ArrayList<Skill> result = dataStorage.getSkillsList();
        if (result.isEmpty()){
            Skill basic = new Skill(1,"Knight/Archer/Mage", "Habilidad basica", 1200, 12, Resources.elementalSword, 2, 0, 0,0,0,0,0,0,20,"external");
            result.add(basic);
        }
        return result;
    }

    @Override
    public ArrayList<Item> getSpecialItemsList() {
        ArrayList<Item> result = dataStorage.getSpecialItemsList();
        if (result.isEmpty()){
            Item basic = new Item(1,"Knight/Mage/Archer", "Talisman/Llave",0 ,0, Resources.talismanEarth, 0, 0, 0, 0, 0, 0, 0, 0);
            result.add(basic);
        }
        return result;
    }

    @Override
    public ArrayList<Item> getConsumableItemsList() {
        ArrayList<Item> result = dataStorage.getConsumableItemsList();
        if (result.isEmpty()){
            Consumable basic = new Consumable("Knight/Mage/Archer", "Pocion organica", 800, 1, 0, 0, 0, 0, 0, 0, 50);
            result.add(basic);
        }
        return result;
    }

    @Override
    public ArrayList<Item> getWearableItemsList() {
        ArrayList<Item> result = dataStorage.getWearableItemsList();
        if (result.isEmpty()){
            Wearable basic = new Wearable(1,"Knight/Mage/Archer", "Armadura basica", 500, 6,Resources.potion,1, 30, 0, 0, 30, 0, 0, 10);
            result.add(basic);
        }
        return result;
    }

    @Override
    public ArrayList<CharacterClass> getEnemyClassList() {
        ArrayList<CharacterClass> result = dataStorage.getEnemyClassList();
        if (result.isEmpty()){
            CharacterClass basic = new CharacterClass(4, "EarthSkeleton/WaterSkeleton/WindSkeleton/FireSkeleton/EarthChief/WaterChief/WindChief/FireChief", "Enemigo", 30, 0, 10, 0, 10, 30, 0, 10, 10);
            result.add(basic);
        }
        return result;
    }

    @Override
    public ArrayList<Attack> getEnemyAttacksList() {
        ArrayList<Attack> result = dataStorage.getEnemyAttacksList();
        if (result.isEmpty()){
            Attack basic = new Attack(1, "EarthSkeleton/WaterSkeleton/WindSkeleton/FireSkeleton/EarthChief/WaterChief/WindChief/FireChief", "Ataque basico", 0, 1, Resources.ironSword, 3, 0, 10);
            result.add(basic);
        }
        return result;
    }

    @Override
    public ArrayList<Item> getEnemyItemsList() {
        ArrayList<Item> result = dataStorage.getEnemyItemsList();
        if (result.isEmpty()){
            Item basic = new Item (1,"EarthSkeleton/WaterSkeleton/WindSkeleton/FireSkeleton/EarthChief/WaterChief/WindChief/FireChief","Escudo basico", 0, 1, Resources.skeletonShield, 0, 0,0,0,20,0,0,5);
            result.add(basic);
        }
        return result;
    }
}
