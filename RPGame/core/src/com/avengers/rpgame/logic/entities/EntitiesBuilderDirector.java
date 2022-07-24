package com.avengers.rpgame.logic.entities;

import com.avengers.rpgame.RPGame;
import com.avengers.rpgame.data.gameStatus.GameStatus;
import com.avengers.rpgame.game.GameConfig;
import com.avengers.rpgame.logic.entities.character.builder.CharacterBuilder;
import com.avengers.rpgame.logic.entities.character.builder.ICharacterDirector;
import com.avengers.rpgame.logic.entities.character.components.animatedCharacter.AAnimatedCharacter;
import com.avengers.rpgame.logic.entities.character.components.CharacterClass;
import com.avengers.rpgame.logic.entities.character.components.animatedCharacter.DynamicAnimatedCharacter;
import com.avengers.rpgame.logic.entities.character.components.animatedCharacter.StaticAnimatedCharacter;
import com.avengers.rpgame.logic.entities.character.components.skin.ISkin;
import com.avengers.rpgame.logic.entities.character.components.skin.components.AnimationAssets;
import com.avengers.rpgame.logic.entities.character.components.skin.abstractFactory.*;
import com.avengers.rpgame.utils.Resources;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import java.util.ArrayList;

import static com.avengers.rpgame.utils.FileManager.*;
import static com.avengers.rpgame.utils.Resources.*;

public class EntitiesBuilderDirector implements ICharacterDirector {
    private GameConfig gameConfig = GameConfig.getInstance();
    private GameStatus gameStatus = GameStatus.getInstance();
    ISkinFactory skinFactory;
    int idCharacter;
    String name;
    Vector2 position = new Vector2((int)gameConfig.getResolutionHorizontal()*12/5, (int)gameConfig.getResolutionVertical() /5);
    double level;
    CharacterClass characterClass;
    int coins = 1000;

    //DummyCharacters only contains the animatedCharacter data for it to render on the game. This is used on battle screen /Character selection screen
    public void buildKnightDummy(CharacterBuilder builder, RPGame rpGame){
        skinFactory = new KnighSkinFactory();
        ISkin skin = skinFactory.createSkin();
        AAnimatedCharacter animatedCharacter = new StaticAnimatedCharacter(skin, rpGame, new Vector2(0,0));
        builder.setAnimatedCharacter(animatedCharacter);
    }

    public void buildArcherDummy(CharacterBuilder builder, RPGame rpGame){
        skinFactory = new ArcherSkinFactory();
        ISkin skin = skinFactory.createSkin();
        AAnimatedCharacter animatedCharacter = new StaticAnimatedCharacter(skin, rpGame, new Vector2(0,0));
        builder.setAnimatedCharacter(animatedCharacter);
    }

    public void buildMageDummy(CharacterBuilder builder, RPGame rpGame){
        skinFactory = new MageSkinFactory();
        ISkin skin = skinFactory.createSkin();
        AAnimatedCharacter animatedCharacter = new StaticAnimatedCharacter(skin, rpGame, new Vector2(0,0));
        builder.setAnimatedCharacter(animatedCharacter);
    }

    public void buildEarthSkeletonDummy(CharacterBuilder builder, RPGame rpGame){
        skinFactory = new EarthSkeletonSkinFactory();
        ISkin skin = skinFactory.createSkin();
        AAnimatedCharacter animatedCharacter = new StaticAnimatedCharacter(skin, rpGame, new Vector2(0,0));
        builder.setAnimatedCharacter(animatedCharacter);
    }

    // Playable characters
    public void buildKnight(CharacterBuilder builder, World world, RPGame rpGame, String playerName){
        skinFactory = new KnighSkinFactory();
        ISkin skin = skinFactory.createSkin();
        AAnimatedCharacter animatedCharacter = new DynamicAnimatedCharacter(skin, rpGame);
        builder.setAnimatedCharacter(animatedCharacter);

        //TODO: Implementacion temporal con valores quemados
        ArrayList<Item> items = new ArrayList<>();
        ArrayList<Attack> attacks = new ArrayList<>();
        ArrayList<Skill> skills = new ArrayList<>();

        this.idCharacter=1;
        this.name=playerName;
        this.level=1;
        this.characterClass = new CharacterClass(1, "Knight", "Caballero", 60, 61, 62, 63, 64, 65, 66, 67, 68);

        Item lifePotion = new Item (0, "Pocion vida","Description Joyeria magica", 100, 1, Resources.potion, 3, 0,0,0,0,0,0,50);
        Item shield = new Item (1, "Escudo","Disminuye golpe de ataques basicos", 200, 1, Resources.potion, 3, 0,0,0,20,0,0,50);
        items.add(lifePotion);
        items.add(shield);
        Attack swordAttack = new Attack("Ataque espada","Ataque basico con espada",1,0,10);
        attacks.add(swordAttack);
        Skill herculesStrength = new Skill("Fuerza hercules", "Incrementa 20% la fuerza del personaje", 300, 1, 0, 20, 0,0,0,0,0,0);
        skills.add(herculesStrength);

        builder.setCharacterBasicInfo(this.idCharacter,this.name,characterClass.getDescription(),position,this.level,characterClass.getInitialHealthPoints(),characterClass.getInitialMagicPoints(), this.coins);
        builder.setCharacterAttributes(characterClass.getInitialStrength(),characterClass.getInitialSpeed(),characterClass.getInitialMagic(),characterClass.getInitialResistance(),characterClass.getInitialLuck());
        builder.setCharacterClass(this.characterClass);
        builder.setItems(items);
        builder.setAttacks(attacks);
        builder.setSkills(skills);
    }

    public void buildArcher(CharacterBuilder builder, World world, RPGame rpGame, String playerName){
        skinFactory = new ArcherSkinFactory();
        ISkin skin = skinFactory.createSkin();
        AAnimatedCharacter animatedCharacter = new DynamicAnimatedCharacter(skin, rpGame);
        builder.setAnimatedCharacter(animatedCharacter);

        //TODO: Implementacion temporal con valores quemados
        ArrayList<Item> items = new ArrayList<>();
        ArrayList<Attack> attacks = new ArrayList<>();
        ArrayList<Skill> skills = new ArrayList<>();

        this.idCharacter=2;
        this.name=playerName;
        this.level=1;
        this.characterClass = new CharacterClass(2, "Archer", "Arquero", 70, 71, 72, 73, 74, 75, 76, 77, 78);
        Item lifePotion = new Item (0, "Pocion vida","Description Joyeria magica", 100, 1, Resources.potion, 3, 0,0,0,0,0,0,50);
        items.add(lifePotion);

        Item earthKey = new Item("Knight/Mage/Archer", "Llave tierra", 0, 6, 0, 0, 0, 0, 0, 0, 0);
        Item waterKey = new Item("Knight/Mage/Archer", "Llave agua", 0, 12, 0, 0, 0, 0, 0, 0, 0);
        Item windKey = new Item("Knight/Mage/Archer", "Llave viento", 0, 18, 0, 0, 0, 0, 0, 0, 0);
        Item fireKey = new Item("Knight/Mage/Archer", "Llave fuego", 0, 24, 0, 0, 0, 0, 0, 0, 0);
        items.add(earthKey);
        items.add(waterKey);
        items.add(windKey);
        items.add(fireKey);

        Attack arrowAttack = new Attack("Ataque flecha","Ataque basico con flecha",1,0,10);
        attacks.add(arrowAttack);
        Skill robinHoodReflects = new Skill("Reflejo Robin Hood", "Incrementa 30% la velocidad del personaje", 300, 1, 0, 0, 30,0,0,0,0,0);
        skills.add(robinHoodReflects);

        builder.setCharacterBasicInfo(this.idCharacter,this.name,characterClass.getDescription(),position,this.level,characterClass.getInitialHealthPoints(),characterClass.getInitialMagicPoints(), this.coins);
        builder.setCharacterAttributes(characterClass.getInitialStrength(),characterClass.getInitialSpeed(),characterClass.getInitialMagic(),characterClass.getInitialResistance(),characterClass.getInitialLuck());
        builder.setCharacterClass(this.characterClass);
        builder.setItems(items);
        builder.setAttacks(attacks);
        builder.setSkills(skills);

    }

    public void buildMage(CharacterBuilder builder, World world, RPGame rpGame, String playerName){
        skinFactory = new MageSkinFactory();
        ISkin skin = skinFactory.createSkin();
        AAnimatedCharacter animatedCharacter = new DynamicAnimatedCharacter(skin, rpGame);
        builder.setAnimatedCharacter(animatedCharacter);

        //TODO: Implementacion temporal con valores quemados
        ArrayList<Item> items = new ArrayList<>();
        ArrayList<Attack> attacks = new ArrayList<>();
        ArrayList<Skill> skills = new ArrayList<>();

        this.idCharacter=3;
        this.name=playerName;
        this.level=1;
        this.characterClass = new CharacterClass(3, "Mage", "Mago", 50, 51, 52, 53, 54, 55, 56, 57, 58);
        Item lifePotion = new Item (0, "Pocion vida","Description Joyeria magica", 100, 1, Resources.potion, 3, 0,0,0,0,0,0,50);
        items.add(lifePotion);
        Attack spellAttack = new Attack("Ataque hechizo","Ataque basico con hechizo",1,1,10);
        attacks.add(spellAttack);
        Skill teamPatchUp = new Skill("Recuperacion compartida", "Ayuda a recuperar a todo el equipo", 0, 1, 0, 0, 0,0,0,0,0,100);
        skills.add(teamPatchUp);


        builder.setCharacterBasicInfo(this.idCharacter,this.name,characterClass.getDescription(),position,this.level,characterClass.getInitialHealthPoints(),characterClass.getInitialMagicPoints(), this.coins);
        builder.setCharacterAttributes(characterClass.getInitialStrength(),characterClass.getInitialSpeed(),characterClass.getInitialMagic(),characterClass.getInitialResistance(),characterClass.getInitialLuck());
        builder.setCharacterClass(this.characterClass);
        builder.setItems(items);
        builder.setAttacks(attacks);
        builder.setSkills(skills);
    }

    //Playable characters from DB
    public void buildKnightFromDB(CharacterBuilder builder, World world, RPGame rpGame, int partyMember){
        skinFactory = new KnighSkinFactory();
        ISkin skin = skinFactory.createSkin();
        AAnimatedCharacter animatedCharacter = new DynamicAnimatedCharacter(skin, rpGame);
        builder.setAnimatedCharacter(animatedCharacter);

        ArrayList<Item> items = gameStatus.getParty().getPartyMember(partyMember).getItems();
        ArrayList<Attack> attacks = gameStatus.getParty().getPartyMember(partyMember).getAttacks();
        ArrayList<Skill> skills = gameStatus.getParty().getPartyMember(partyMember).getSkills();

        this.idCharacter=1;
        this.name=gameStatus.getParty().getPartyMember(partyMember).getName();
        this.level=gameStatus.getParty().getPartyMember(partyMember).getLevel();
        this.coins=gameStatus.getParty().getPartyMember(partyMember).getCoins();
        this.characterClass = new CharacterClass(1, "Knight", "Caballero", 60, 61, 62, 63, 64, 65, 66, 67, 68);

        builder.setCharacterBasicInfo(this.idCharacter,this.name,characterClass.getDescription(),gameStatus.getParty().getPartyMember(partyMember).getPosition(),this.level,gameStatus.getParty().getPartyMember(partyMember).getHealthPoints(),gameStatus.getParty().getPartyMember(partyMember).getMagicPoints(), this.coins);
        builder.setCharacterAttributes(gameStatus.getParty().getPartyMember(partyMember).getStrength(),gameStatus.getParty().getPartyMember(partyMember).getSpeed(),gameStatus.getParty().getPartyMember(partyMember).getMagic(),gameStatus.getParty().getPartyMember(partyMember).getResistance(),gameStatus.getParty().getPartyMember(partyMember).getLuck());
        builder.setCharacterClass(this.characterClass);
        builder.setItems(items);
        builder.setAttacks(attacks);
        builder.setSkills(skills);
    }

    public void buildArcherFromDB(CharacterBuilder builder, World world, RPGame rpGame, int partyMember){
        skinFactory = new ArcherSkinFactory();
        ISkin skin = skinFactory.createSkin();
        AAnimatedCharacter animatedCharacter = new DynamicAnimatedCharacter(skin, rpGame);
        builder.setAnimatedCharacter(animatedCharacter);

        //TODO: Implementacion temporal con valores quemados
        ArrayList<Item> items = gameStatus.getParty().getPartyMember(partyMember).getItems();
        ArrayList<Attack> attacks = gameStatus.getParty().getPartyMember(partyMember).getAttacks();
        ArrayList<Skill> skills = gameStatus.getParty().getPartyMember(partyMember).getSkills();

        this.idCharacter=2;
        this.name=gameStatus.getParty().getPartyMember(partyMember).getName();
        this.level=gameStatus.getParty().getPartyMember(partyMember).getLevel();
        this.coins=gameStatus.getParty().getPartyMember(partyMember).getCoins();
        this.characterClass = new CharacterClass(2, "Archer", "Arquero", 70, 71, 72, 73, 74, 75, 76, 77, 78);

        builder.setCharacterBasicInfo(this.idCharacter,this.name,characterClass.getDescription(),gameStatus.getParty().getPartyMember(partyMember).getPosition(),this.level,gameStatus.getParty().getPartyMember(partyMember).getHealthPoints(),gameStatus.getParty().getPartyMember(partyMember).getMagicPoints(), this.coins);
        builder.setCharacterAttributes(gameStatus.getParty().getPartyMember(partyMember).getStrength(),gameStatus.getParty().getPartyMember(partyMember).getSpeed(),gameStatus.getParty().getPartyMember(partyMember).getMagic(),gameStatus.getParty().getPartyMember(partyMember).getResistance(),gameStatus.getParty().getPartyMember(partyMember).getLuck());
        builder.setCharacterClass(this.characterClass);
        //TODO SET THIS FROM DB
        builder.setItems(items);
        builder.setAttacks(attacks);
        builder.setSkills(skills);
    }

    public void buildMageFromDB(CharacterBuilder builder, World world, RPGame rpGame, int partyMember){
        skinFactory = new MageSkinFactory();
        ISkin skin = skinFactory.createSkin();
        AAnimatedCharacter animatedCharacter = new DynamicAnimatedCharacter(skin, rpGame);
        builder.setAnimatedCharacter(animatedCharacter);

        //TODO: Implementacion temporal con valores quemados
        ArrayList<Item> items = gameStatus.getParty().getPartyMember(partyMember).getItems();
        ArrayList<Attack> attacks = gameStatus.getParty().getPartyMember(partyMember).getAttacks();
        ArrayList<Skill> skills = gameStatus.getParty().getPartyMember(partyMember).getSkills();

        this.idCharacter=3;
        this.name=gameStatus.getParty().getPartyMember(partyMember).getName();
        this.level=gameStatus.getParty().getPartyMember(partyMember).getLevel();
        this.coins=gameStatus.getParty().getPartyMember(partyMember).getCoins();
        this.characterClass = new CharacterClass(3, "Mage", "Mago", 50, 51, 52, 53, 54, 55, 56, 57, 58);

        builder.setCharacterBasicInfo(this.idCharacter,this.name,characterClass.getDescription(),gameStatus.getParty().getPartyMember(partyMember).getPosition(),this.level,gameStatus.getParty().getPartyMember(partyMember).getHealthPoints(),gameStatus.getParty().getPartyMember(partyMember).getMagicPoints(), this.coins);
        builder.setCharacterAttributes(gameStatus.getParty().getPartyMember(partyMember).getStrength(),gameStatus.getParty().getPartyMember(partyMember).getSpeed(),gameStatus.getParty().getPartyMember(partyMember).getMagic(),gameStatus.getParty().getPartyMember(partyMember).getResistance(),gameStatus.getParty().getPartyMember(partyMember).getLuck());
        builder.setCharacterClass(this.characterClass);
        builder.setItems(items);
        builder.setAttacks(attacks);
        builder.setSkills(skills);
    }

    //Enemies
    public void buildEarthSkeleton(CharacterBuilder builder, World world, RPGame rpGame, String playerName){
        //TODO: Implementacion temporal con valores quemados
        ArrayList<Item> items = new ArrayList<>();
        ArrayList<Attack> attacks = new ArrayList<>();
        ArrayList<Skill> skills = new ArrayList<>();

        skinFactory = new EarthSkeletonSkinFactory();
        ISkin skin = skinFactory.createSkin();
        skin.setRight(new AnimationAssets(loadTexture(resourceEarthSkeletonTextureRight), loadTextureAtlas(resourceEarthSkeletonTextureMapRight), "right"));
        AAnimatedCharacter animatedCharacter = new DynamicAnimatedCharacter(skin, rpGame);
        builder.setAnimatedCharacter(animatedCharacter);

        //TODO: Implementacion temporal con valores quemados
        this.idCharacter=4;
        this.name=playerName;
        this.level=1;
        this.characterClass = new CharacterClass(4, "Esqueleto de tierra", "Esqueleto maldito con los poderes elementales de tierra", 50, 51, 52, 53, 54, 55, 56, 57, 58);
//        Item lifePotion = new Item (0, "Pocion vida","Description Joyeria magica", 100, 1, Resources.potion, 3, 0,0,0,0,0,0,50);
//        this.items.add(lifePotion);
        Attack spellAttack = new Attack("Hueso de roca","El esqueleto se saca un hueso transformado en roca y lo lanza",1,1,10);
        attacks.add(spellAttack);
//        Skill teamPatchUp = new Skill("Recuperacion compartida", "Ayuda a recuperar a todo el equipo", 0, 1, 0, 0, 0,0,0,0,0,100);
//        this.skills.add(teamPatchUp);

        builder.setCharacterBasicInfo(this.idCharacter,this.name,characterClass.getDescription(),position,this.level,characterClass.getInitialHealthPoints(),characterClass.getInitialMagicPoints(), this.coins);
        builder.setCharacterAttributes(characterClass.getInitialStrength(),characterClass.getInitialSpeed(),characterClass.getInitialMagic(),characterClass.getInitialResistance(),characterClass.getInitialLuck());
        builder.setCharacterClass(this.characterClass);
        builder.setItems(items);
        builder.setAttacks(attacks);
//        builder.setSkills(skills);
    }



}