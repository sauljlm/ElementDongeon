package com.avengers.rpgame.json;

import com.avengers.rpgame.logic.entities.*;
import com.avengers.rpgame.logic.entities.character.components.CharacterClass;

import java.util.ArrayList;

public class DataStorage {

    private ArrayList<CharacterClass> characterClassList;
    private ArrayList<Item> itemsList;
    private ArrayList<Skill> skillsList;
    private ArrayList<Attack> attacksList;

    public String getData() {
        characterClassList = new ArrayList<>();
        itemsList = new ArrayList<>();
        skillsList = new ArrayList<>();
        attacksList = new ArrayList<>();
        generateCharacterClassList();
        generateAttackList();
        generateSkillList();
        generateItemList();

        return toString();
    }

    @Override
    public String toString() {
        return "DataStorage{" +
                "characterClassList=" + characterClassList +
                ", itemsList=" + itemsList +
                ", skillsList=" + skillsList +
                ", attacksList=" + attacksList +
                '}';
    }

    private void generateCharacterClassList() {
        CharacterClass knight = new CharacterClass(1, "Knight", "Caballero", 100, 0, 10, 0, 80, 30, 0, 30, 70);
        CharacterClass archer = new CharacterClass(2, "Archer", "Arquero", 100, 0, 10, 0, 20, 80, 0, 80, 30);
        CharacterClass mage = new CharacterClass(3, "Mage", "Mago", 100, 100, 10, 10, 40, 40, 80, 40, 10);
        characterClassList.add(knight);
        characterClassList.add(archer);
        characterClassList.add(mage);
    }

    private void generateAttackList() {
        Attack k1 = new Attack("Knight", "Espada de hierro", 1, 0, 10);
        Attack k2 = new Attack("Knight", "Espadas de plata", 3, 0, 15);
        Attack k3 = new Attack("Knight", "Espadas doble mano", 7, 0, 20);
        Attack k4 = new Attack("Knight", "Hachas de plata / hierro", 9, 0, 25);
        attacksList.add(k1);
        attacksList.add(k2);
        attacksList.add(k3);
        attacksList.add(k4);

        Attack a1 = new Attack("Archer", "Flecha sencilla", 1, 0, 10);
        Attack a2 = new Attack("Archer", "Doble flecha", 3, 0, 15);
        Attack a3 = new Attack("Archer", "Triple flecha", 7, 0, 20);
        Attack a4 = new Attack("Archer", "Flecha bomba", 9, 0, 25);
        attacksList.add(a1);
        attacksList.add(a2);
        attacksList.add(a3);
        attacksList.add(a4);

        Attack m1 = new Attack("Mage", "Golpe mental", 1, 10, 10);
        Attack m2 = new Attack("Mage", "Paz mental", 1, 12, 12);
        attacksList.add(m1);
        attacksList.add(m2);

        Attack e1 = new Attack("EarthEnemy", "Ataque arena", 1, 0, 15);
        Attack e2 = new Attack("EarthChief", "Disparo lodo", 6, 0, 35);
        Attack e3 = new Attack("WaterEnemy", "Salpicadura", 7, 0, 20);
        Attack e4 = new Attack("WaterChief", "Hidrobomba", 12, 0, 45);
        Attack e5 = new Attack("WindEnemy", "Viento cortante", 13, 0, 25);
        Attack e6 = new Attack("WindChief", "Viento hielo", 18, 0, 60);
        Attack e7 = new Attack("FireEnemy", "Llamarada", 19, 0, 30);
        Attack e8 = new Attack("FireChief", "Giro fuego", 24, 0, 70);
        attacksList.add(e1);
        attacksList.add(e2);
        attacksList.add(e3);
        attacksList.add(e4);
        attacksList.add(e5);
        attacksList.add(e6);
        attacksList.add(e7);
        attacksList.add(e8);
    }

    private void generateSkillList() {
        Skill k1 = new Skill("Knight", "Espadas elementales", 1200, 12, 0, 0,0,0,0,0,0,20);
        Skill k2 = new Skill("Knight", "Hachas elementales", 1500, 15, 0, 0,0,0,0,0,0,30);
        Skill k3 = new Skill("Knight", "Espadas / hachas de oro", 1900, 19, 0, 0,0,0,0,0,0,40);
        skillsList.add(k1);
        skillsList.add(k2);
        skillsList.add(k3);

        Skill a1 = new Skill("Archer", "Flecha de agua / viento", 1200, 12, 0, 0,0,0,0,0,0,20);
        Skill a2 = new Skill("Archer", "Flecha de fuego", 1500, 15, 0, 0,0,0,0,0,0,30);
        Skill a3 = new Skill("Archer", "Lluvia de flechas", 1900, 19, 0, 0,0,0,0,0,0,40);
        skillsList.add(a1);
        skillsList.add(a2);
        skillsList.add(a3);

        Skill m1 = new Skill("Mage", "Recuperacion 5%", 300, 3, 15, 0,0,2,0,0,5,15);
        Skill m2 = new Skill("Mage", "Soplo de hielo", 700, 7, 35, 0,0,2,0,0,5,20);
        Skill m3 = new Skill("Mage", "Lanzallamas", 700, 7, 35, 0,0,2,0,0,7,22);
        Skill m4 = new Skill("Mage", "Rama afilada", 900, 9, 40, 0,0,2,0,0,12,25);
        Skill m5 = new Skill("Mage", "Ciclon", 1100, 11, 45, 0,0,2,0,0,16,30);
        Skill m6 = new Skill("Mage", "Recuperacion 20%", 1200, 12, 45, 0,0,2,0,0,20,35);
        Skill m7 = new Skill("Mage", "Claridad mental (Equipo)", 1500, 15, 60, 0,0,2,0,0,25,35);
        Skill m8 = new Skill("Mage", "Vitalidad Grupal", 1900, 19, 60, 0,0,2,0,0,28,35);
        Skill m9 = new Skill("Mage", "Bomba de hielo", 2200, 22, 70, 0,0,2,0,0,32,40);
        Skill m10 = new Skill("Mage", "Fundir", 2200, 22, 70, 0,0,2,0,0,35,45);
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
        Consumable p1 = new Consumable("Knight/Mage/Archer", "Pocion Organica (50% HP)", 800, 1, 0, 0, 0, 0, 0, 0, 50);
        Consumable p2 = new Consumable("Mage", "Pocion Magica (50% MP)", 600, 1, 0, 0, 0, 0, 0, 50, 0);
        Consumable p3 = new Consumable("Mage", "Pocion Total (100% MP y HP)", 1200, 10, 0, 0, 0, 0, 0, 100, 100);
        itemsList.add(p1);
        itemsList.add(p2);
        itemsList.add(p3);

        Item jewel = new Item("Knight/Mage/Archer", "Joya", 0, 1, 0, 0, 0, 0, 0, 0, 0);
        Item earthKey = new Item("Knight/Mage/Archer", "Llave tierra", 0, 6, 0, 0, 0, 0, 0, 0, 0);
        Item waterKey = new Item("Knight/Mage/Archer", "Llave agua", 0, 12, 0, 0, 0, 0, 0, 0, 0);
        Item windKey = new Item("Knight/Mage/Archer", "Llave viento", 0, 18, 0, 0, 0, 0, 0, 0, 0);
        Item fireKey = new Item("Knight/Mage/Archer", "Llave fuego", 0, 24, 0, 0, 0, 0, 0, 0, 0);
        itemsList.add(jewel);
        itemsList.add(earthKey);
        itemsList.add(waterKey);
        itemsList.add(windKey);
        itemsList.add(fireKey);

        Wearable armor = new Wearable("Knight/Mage/Archer", "Armadura", 500, 6, 30, 0, 0, 30, 0, 0, 10);
        Wearable magicJewelry = new Wearable("Knight/Mage/Archer", "Joyeria magica", 1000, 12, 0, 0, 30, 5, 0, 10, 0);
        itemsList.add(armor);
        itemsList.add(magicJewelry);
    }

}
