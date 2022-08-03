package com.avengers.rpgame.data.dataStorage;

import com.avengers.rpgame.logic.entities.*;
import com.avengers.rpgame.logic.entities.character.components.CharacterClass;
import com.avengers.rpgame.utils.Resources;

import java.util.ArrayList;

public class DataStorage {

    private static DataStorage instance;
    private ArrayList<CharacterClass> characterClassList;
    private ArrayList<Skill> skillsList;
    private ArrayList<CharacterClass> enemyClassList;
    private ArrayList<Attack> attacksList;
    private ArrayList<Attack> enemyAttacksList;
    private ArrayList<Item> specialItemsList;
    private ArrayList<Item> consumableItemsList;
    private ArrayList<Item> wearableItemsList;
    private ArrayList<Item> enemyItemsList;

    private DataStorage() {
        characterClassList = new ArrayList<>();
        enemyClassList = new ArrayList<>();
        attacksList = new ArrayList<>();
        enemyAttacksList = new ArrayList<>();
        skillsList = new ArrayList<>();
        specialItemsList = new ArrayList<>();
        consumableItemsList = new ArrayList<>();
        wearableItemsList = new ArrayList<>();
        enemyItemsList = new ArrayList<>();
        generateCharacterClassList();
        generateAttackList();
        generateSkillList();
        generateItemList();
    }

    public ArrayList<CharacterClass> getCharacterClassList() {
        return characterClassList;
    }

    public ArrayList<CharacterClass> getEnemyClassList() {
        return enemyClassList;
    }

    public ArrayList<Attack> getEnemyAttacksList() {
        return enemyAttacksList;
    }

    public ArrayList<Attack> getAttacksList() {
        return attacksList;
    }

    public ArrayList<Skill> getSkillsList() {
        return skillsList;
    }

    public ArrayList<Item> getSpecialItemsList() {
        return specialItemsList;
    }

    public ArrayList<Item> getConsumableItemsList() {
        return consumableItemsList;
    }

    public ArrayList<Item> getWearableItemsList() {
        return wearableItemsList;
    }

    public ArrayList<Item> getEnemyItemsList() {
        return enemyItemsList;
    }

    private void generateCharacterClassList() {
        CharacterClass knight = new CharacterClass(1, "Knight", "Caballero", 100, 0, 10, 0, 70, 30, 0, 80, 60);
        CharacterClass archer = new CharacterClass(2, "Archer", "Arquero", 100, 0, 10, 0, 30, 70, 0, 40, 40);
        CharacterClass mage = new CharacterClass(3, "Mage", "Mago", 100, 100, 10, 10, 50, 50, 100, 20, 50);
        characterClassList.add(knight);
        characterClassList.add(archer);
        characterClassList.add(mage);

        CharacterClass earthSkeleton = new CharacterClass(4, "EarthSkeleton", "Esqueleto de tierra", 30, 0, 10, 0, 5, 20, 0, 1, 1);
        CharacterClass waterSkeleton = new CharacterClass(5, "WaterSkeleton", "Esqueleto de agua", 45, 0, 10, 0, 10, 20, 0, 1, 1);
        CharacterClass windSkeleton = new CharacterClass(6, "WindSkeleton", "Esqueleto de viento", 60, 0, 10, 0, 15, 20, 0, 1, 1);
        CharacterClass fireSkeleton = new CharacterClass(7, "FireSkeleton", "Esqueleto de fuego", 100, 0, 10, 0, 20, 20, 0, 1, 1);
        CharacterClass earthChief = new CharacterClass(8, "EarthChief", "Jefe de tierra", 60, 0, 10, 0, 10, 40, 10, 3, 10);
        CharacterClass waterChief = new CharacterClass(9, "WaterChief", "Jefe de agua", 90, 0, 10, 0, 15, 40, 10, 3, 20);
        CharacterClass windChief = new CharacterClass(10, "WindChief", "Jefe de viento", 100, 0, 10, 0, 20, 40, 10, 3, 40);
        CharacterClass fireChief = new CharacterClass(11, "FireChief", "Jefe de fuego", 100, 0, 10, 0, 25, 40, 10, 3, 80);
        enemyClassList.add(earthSkeleton);
        enemyClassList.add(waterSkeleton);
        enemyClassList.add(windSkeleton);
        enemyClassList.add(fireSkeleton);
        enemyClassList.add(earthChief);
        enemyClassList.add(waterChief);
        enemyClassList.add(windChief);
        enemyClassList.add(fireChief);
    }

    private void generateAttackList() {
        Attack k1 = new Attack("Knight", "Espada de hierro", 1, 0, 10);
        Attack k2 = new Attack("Knight", "Espadas de plata", 3, 0, 15);
        Attack k3 = new Attack("Knight", "Espadas doble mano", 7, 0, 20);
        Attack k4 = new Attack("Knight", "Hachas de plata y hierro", 9, 0, 25);
        attacksList.add(k1);
        attacksList.add(k2);
        attacksList.add(k3);
        attacksList.add(k4);

        Attack a1 = new Attack("Archer", "Flecha sencilla", 1, 0, 10);
        Attack a2 = new Attack("Archer", "Doble flecha", 3, 0, 15);
        Attack a3 = new Attack("Archer", "Triple flecha", 7, 0, 25);
        Attack a4 = new Attack("Archer", "Flecha bomba", 9, 0, 30);
        attacksList.add(a1);
        attacksList.add(a2);
        attacksList.add(a3);
        attacksList.add(a4);

        Attack m1 = new Attack("Mage", "Golpe mental", 1, 10, 10);
        attacksList.add(m1);

        Attack e1 = new Attack("EarthSkeleton/EarthChief", "Ataque arena", 1, 0, 15);
        Attack e2 = new Attack("WaterSkeleton/WaterChief", "Salpicadura", 7, 0, 20);
        Attack e3 = new Attack("WindSkeleton/WindChief", "Viento cortante", 13, 0, 25);
        Attack e4 = new Attack("FireSkeleton/FireChief", "Llamarada", 19, 0, 30);

        Attack e5 = new Attack("EarthChief", "Disparo lodo", 6, 0, 35);
        Attack e6 = new Attack("WaterChief", "Hidrobomba", 12, 0, 40);
        Attack e7 = new Attack("WindChief", "Viento hielo", 18, 0, 45);
        Attack e8 = new Attack("FireChief", "Giro fuego", 24, 0, 50);
        enemyAttacksList.add(e1);
        enemyAttacksList.add(e2);
        enemyAttacksList.add(e3);
        enemyAttacksList.add(e4);
        enemyAttacksList.add(e5);
        enemyAttacksList.add(e6);
        enemyAttacksList.add(e7);
        enemyAttacksList.add(e8);
    }

    private void generateSkillList() {
        Skill k1 = new Skill("Knight", "Espadas elementales", 1200, 12, 0, 0,0,0,0,0,0,30,"external");
        Skill k2 = new Skill("Knight", "Hachas elementales", 1500, 15, 0, 0,0,0,0,0,0,40,"external");
        Skill k3 = new Skill("Knight", "Espada y hacha de oro", 1900, 19, 0, 0,0,0,0,0,0,50,"external");
        skillsList.add(k1);
        skillsList.add(k2);
        skillsList.add(k3);

        Skill a1 = new Skill("Archer", "Flecha de agua y viento", 1200, 12, 0, 0,0,0,0,0,0,20,"external");
        Skill a2 = new Skill("Archer", "Flecha de fuego", 1500, 15, 0, 0,0,0,0,0,0,30,"external");
        Skill a3 = new Skill("Archer", "Lluvia de flechas", 1900, 19, 0, 0,0,0,0,0,0,40,"external");
        skillsList.add(a1);
        skillsList.add(a2);
        skillsList.add(a3);

        Skill m0 = new Skill("Mage", "Paz mental", 0, 1, 0, 0,0,10,0,0,10,10,"internal");
        Skill m1 = new Skill("Mage", "Recuperacion 5%", 300, 3, 0, 0,0,2,0,0,50,5,"internal");
        Skill m2 = new Skill("Mage", "Soplo de hielo", 700, 7, 15, 0,0,2,0,0,5,20,"external");
        Skill m3 = new Skill("Mage", "Lanzallamas", 700, 7, 15, 0,0,2,0,0,7,22,"external");
        Skill m4 = new Skill("Mage", "Rama afilada", 900, 9, 20, 0,0,2,0,0,12,25,"external");
        Skill m5 = new Skill("Mage", "Ciclon", 1100, 11, 30, 0,0,2,0,0,16,30,"external");
        Skill m6 = new Skill("Mage", "Recuperacion 20%", 1200, 12, 45, 0,0,2,0,0,20,35,"internal");
        Skill m7 = new Skill("Mage", "Claridad mental", 1500, 15, 50, 0,0,2,0,0,25,35,"internal");
        Skill m8 = new Skill("Mage", "Vitalidad Grupal", 1900, 19, 50, 0,0,2,0,0,28,35,"internal");
        Skill m9 = new Skill("Mage", "Bomba de hielo", 2200, 22, 55, 0,0,2,0,0,32,40,"external");
        Skill m10 = new Skill("Mage", "Fundir", 2200, 22, 55, 0,0,2,0,0,35,60,"external");
        skillsList.add(m0);
        skillsList.add(m1);
        skillsList.add(m2);
        skillsList.add(m3);
        skillsList.add(m4);
        skillsList.add(m5);
        skillsList.add(m6);
        skillsList.add(m7);
        skillsList.add(m8);
        skillsList.add(m9);
        skillsList.add(m10);
    }

    private void generateItemList() {
        Consumable p1 = new Consumable(1,"Knight/Mage/Archer", "Pocion ligera", 800, 1, Resources.potion, 3, 0, 0, 0, 0, 0, 50, 50);
        Consumable p2 = new Consumable("Mage", "Pocion media", 600, 1, 0, 0, 0, 0, 0, 75, 75);
        Consumable p3 = new Consumable("Mage", "Pocion total", 1200, 10, 0, 0, 0, 0, 0, 100, 100);
        consumableItemsList.add(p1);
        consumableItemsList.add(p2);
        consumableItemsList.add(p3);

        Item earthTalisman = new Item("Knight/Mage/Archer", "Talisman tierra", 0, 1, 0, 0, 0, 0, 0, 0, 0);
        Item waterTalisman = new Item("Knight/Mage/Archer", "Talisman agua", 0, 1, 0, 0, 0, 0, 0, 0, 0);
        Item windTalisman = new Item("Knight/Mage/Archer", "Talisman viento", 0, 1, 0, 0, 0, 0, 0, 0, 0);
        Item fireTalisman = new Item("Knight/Mage/Archer", "Talisman fuego", 0, 1, 0, 0, 0, 0, 0, 0, 0);
        specialItemsList.add(earthTalisman);
        specialItemsList.add(waterTalisman);
        specialItemsList.add(windTalisman);
        specialItemsList.add(fireTalisman);

        Item earthKey = new Item("Knight/Mage/Archer", "Llave tierra", 0, 6, 0, 0, 0, 0, 0, 0, 0);
        Item waterKey = new Item("Knight/Mage/Archer", "Llave agua", 0, 12, 0, 0, 0, 0, 0, 0, 0);
        Item windKey = new Item("Knight/Mage/Archer", "Llave viento", 0, 18, 0, 0, 0, 0, 0, 0, 0);
        Item fireKey = new Item("Knight/Mage/Archer", "Llave fuego", 0, 24, 0, 0, 0, 0, 0, 0, 0);
        specialItemsList.add(earthKey);
        specialItemsList.add(waterKey);
        specialItemsList.add(windKey);
        specialItemsList.add(fireKey);

        Wearable armor = new Wearable("Knight/Mage/Archer", "Armadura", 500, 6, 30, 0, 0, 30, 0, 0, 10);
        Wearable magicJewelry = new Wearable("Knight/Mage/Archer", "Joyeria magica", 1000, 12, 0, 0, 30, 5, 0, 10, 0);
        wearableItemsList.add(armor);
        wearableItemsList.add(magicJewelry);

        Item shield = new Item ("EarthSkeleton/WaterSkeleton/WindSkeleton/FireSkeleton","Escudo esqueleto", 0, 1, 0,0,0,20,0,0,10);
        Item shieldChief = new Item ("EarthChief/WaterChief/WindChief/FireChief","Mega escudo esqueleto", 0, 1, 0,0,0,20,0,0,25);
        enemyItemsList.add(shield);
        enemyItemsList.add(shieldChief);
    }

    public static DataStorage getInstance() {
        if (instance == null) {
            instance = new DataStorage();
        }
        return instance;
    }

}
