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
        Attack k1 = new Attack(35,"Knight", "Espada de hierro", 0, 1, Resources.ironSword, 1, 10, 0);
        Attack k2 = new Attack(36,"Knight", "Espadas de plata", 500, 3, Resources.silverSword, 1, 15, 0);
        Attack k3 = new Attack(37,"Knight", "Espadas doble mano", 800, 7, Resources.doubleHandSword, 1, 20, 0);
        Attack k4 = new Attack(38,"Knight", "Hachas de plata y hierro", 1000, 9, Resources.axe, 1, 25, 0);
        attacksList.add(k1);
        attacksList.add(k2);
        attacksList.add(k3);
        attacksList.add(k4);

        Attack a1 = new Attack(39,"Archer", "Flecha sencilla", 0, 1, Resources.arrow, 1, 10, 0);
        Attack a2 = new Attack(40,"Archer", "Doble flecha", 500, 3, Resources.doubleArrow, 1, 15, 0);
        Attack a3 = new Attack(41,"Archer", "Triple flecha", 800, 7, Resources.tripleArrow, 1, 25, 0);
        Attack a4 = new Attack(42,"Archer", "Flecha bomba", 1000, 9, Resources.bombArrow, 1, 30, 0);
        attacksList.add(a1);
        attacksList.add(a2);
        attacksList.add(a3);
        attacksList.add(a4);

        Attack m1 = new Attack(43,"Mage", "Golpe mental", 0, 1, Resources.mindBlow, 1, 8, 10);
        attacksList.add(m1);

        Attack e1 = new Attack(44,"EarthSkeleton/EarthChief", "Ataque arena", 0, 1, Resources.potion, 1, 15, 0);
        Attack e2 = new Attack(45,"WaterSkeleton/WaterChief", "Salpicadura", 0, 7, Resources.potion, 1, 20, 0);
        Attack e3 = new Attack(46,"WindSkeleton/WindChief", "Viento cortante", 0, 13, Resources.potion, 1, 25, 0);
        Attack e4 = new Attack(47,"FireSkeleton/FireChief", "Llamarada", 0, 19, Resources.potion, 1, 30, 0);

        Attack e5 = new Attack(48,"EarthChief", "Disparo lodo", 0, 6, Resources.potion, 1, 35, 0);
        Attack e6 = new Attack(49,"WaterChief", "Hidrobomba", 0, 12, Resources.potion, 1, 40, 0);
        Attack e7 = new Attack(50,"WindChief", "Viento hielo", 0, 18, Resources.potion, 1, 45, 0);
        Attack e8 = new Attack(51,"FireChief", "Giro fuego", 0, 24, Resources.potion, 1, 50, 0);
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
        Skill k1 = new Skill(18,"Knight", "Espadas elementales", 1200, 12, Resources.elementalSword, 2, 0, 0,0,0,0,0,0,30,"external");
        Skill k2 = new Skill(19,"Knight", "Hachas elementales", 1500, 15, Resources.elementalAxe, 2, 0, 0,0,0,0,0,0,40,"external");
        Skill k3 = new Skill(20,"Knight", "Espada y hacha de oro", 1900, 19, Resources.goldSwordAxe, 2, 0, 0,0,0,0,0,0,50,"external");
        skillsList.add(k1);
        skillsList.add(k2);
        skillsList.add(k3);

        Skill a1 = new Skill(21,"Archer", "Flecha de agua y viento", 1200, 12, Resources.windWaterArrow, 2, 0, 0,0,0,0,0,0,20,"external");
        Skill a2 = new Skill(22,"Archer", "Flecha de fuego", 1500, 15, Resources.fireArrow, 2, 0, 0,0,0,0,0,0,30,"external");
        Skill a3 = new Skill(23,"Archer", "Lluvia de flechas", 1900, 19, Resources.rainArrows, 2, 0, 0,0,0,0,0,0,40,"external");
        skillsList.add(a1);
        skillsList.add(a2);
        skillsList.add(a3);

        Skill m0 = new Skill(24,"Mage", "Paz mental", 0, 1, Resources.peaceMind, 2, 0, 0,0,10,0,0,50,0,"internal");
        Skill m1 = new Skill(25,"Mage", "Recuperacion 5%", 300, 3, Resources.health5, 2, 0, 0,0,2,0,0,5,5,"internal");
        Skill m2 = new Skill(26,"Mage", "Soplo de hielo", 700, 7, Resources.blowIce, 2, 15, 0,0,2,0,0,5,20,"external");
        Skill m3 = new Skill(27,"Mage", "Lanzallamas", 700, 7, Resources.throwFlames, 2, 15, 0,0,2,0,0,7,22,"external");
        Skill m4 = new Skill(28,"Mage", "Rama afilada", 900, 9, Resources.sharpBranch, 2, 20, 0,0,2,0,0,12,25,"external");
        Skill m5 = new Skill(29,"Mage", "Ciclon", 1100, 11, Resources.ciclon, 2, 30, 0,0,2,0,0,16,30,"external");
        Skill m6 = new Skill(30,"Mage", "Recuperacion 20%", 1200, 12, Resources.health20, 2, 45, 0,0,2,0,0,20,35,"internal");
        Skill m7 = new Skill(31,"Mage", "Claridad mental", 1500, 15, Resources.mentalClarity, 2, 50, 0,0,2,0,0,25,35,"internal");
        Skill m8 = new Skill(32,"Mage", "Vitalidad Grupal", 1900, 19, Resources.groupVitality, 2, 50, 0,0,2,0,0,28,35,"internal");
        Skill m9 = new Skill(33,"Mage", "Bomba de hielo", 2200, 22, Resources.iceBomb, 2, 55, 0,0,2,0,0,32,40,"external");
        Skill m10 = new Skill(34,"Mage", "Fundir", 2200, 22, Resources.melt, 2, 55, 0,0,2,0,0,35,60,"external");
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
        Consumable p1 = new Consumable(6,"Knight/Mage/Archer", "Pocion ligera", 600, 1, Resources.lightHealth, 3, 0, 0, 0, 0, 0, 50, 50);
        Consumable p2 = new Consumable(7,"Mage", "Pocion media", 900, 7, Resources.midHealth,3, 0, 0, 0, 0, 0, 75, 75);
        Consumable p3 = new Consumable(8,"Mage", "Pocion total", 1200,12,Resources.totalHealth, 3, 0, 0, 0, 0, 0, 100, 100);
        consumableItemsList.add(p1);
        consumableItemsList.add(p2);
        consumableItemsList.add(p3);

        Item initialTalisman = new Item(1,"Knight/Mage/Archer", "Talisman inicial",0 ,0, Resources.talismanEarth, 0, 0, 0, 0, 0, 0, 0, 0);
        Item earthTalisman = new Item(2,"Knight/Mage/Archer", "Talisman tierra", 0 ,0, Resources.talismanEarth, 0, 0, 0, 0, 0, 0, 0, 0);
        Item waterTalisman = new Item(3,"Knight/Mage/Archer", "Talisman agua", 0 ,0, Resources.talismanWater, 0, 0, 0, 0, 0, 0, 0, 0);
        Item windTalisman = new Item(4,"Knight/Mage/Archer", "Talisman viento", 0 ,0, Resources.talismanWind, 0, 0, 0, 0, 0, 0, 0, 0);
        Item fireTalisman = new Item(5,"Knight/Mage/Archer", "Talisman fuego", 0 ,0, Resources.talismanFire, 0, 0, 0, 0, 0, 0, 0, 0);
        specialItemsList.add(initialTalisman);
        specialItemsList.add(earthTalisman);
        specialItemsList.add(waterTalisman);
        specialItemsList.add(windTalisman);
        specialItemsList.add(fireTalisman);

        Item earthKey = new Item(9,"Knight/Mage/Archer", "Llave tierra",0, 6, Resources.keyEarth, 0, 0, 0, 0, 0, 0, 0, 0);
        Item waterKey = new Item(10,"Knight/Mage/Archer", "Llave agua", 0, 18, Resources.keyWater, 0, 0, 0, 0, 0, 0, 0, 0);
        Item windKey = new Item(11,"Knight/Mage/Archer", "Llave viento", 0, 12, Resources.keyWind, 0, 0, 0, 0, 0, 0, 0, 0);
        Item fireKey = new Item(12,"Knight/Mage/Archer", "Llave fuego", 0, 24, Resources.keyFire, 0, 0, 0, 0, 0, 0, 0, 0);
        Item elementKey = new Item(13,"Knight/Mage/Archer", "Llave elemental", 0, 24, Resources.keyElemental, 0, 0, 0, 0, 0, 0, 0, 0);
        specialItemsList.add(earthKey);
        specialItemsList.add(waterKey);
        specialItemsList.add(windKey);
        specialItemsList.add(fireKey);
        specialItemsList.add(elementKey);

        Wearable armor = new Wearable(16,"Knight/Mage/Archer", "Armadura", 500, 6, Resources.armor,3, 30, 0, 0, 30, 0, 0, 10);
        Wearable magicJewelry = new Wearable(17,"Knight/Mage/Archer", "Joyeria magica", 1000, 12, Resources.jewelers,3, 0, 0, 30, 5, 0, 10, 0);
        wearableItemsList.add(armor);
        wearableItemsList.add(magicJewelry);

        Item shield = new Item (14, "EarthSkeleton/WaterSkeleton/WindSkeleton/FireSkeleton","Escudo esqueleto", 0, 1, Resources.skeletonShield, 0, 0,0,0,20,0,0,10);
        Item shieldChief = new Item (15, "EarthChief/WaterChief/WindChief/FireChief","Mega escudo esqueleto", 0, 1, Resources.skeletonMegaShield, 0, 0,0,0,20,0,0,25);
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
