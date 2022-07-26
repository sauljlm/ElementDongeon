package com.avengers.rpgame.logic.entities;

import com.avengers.rpgame.RPGame;
import com.avengers.rpgame.data.dataStorage.ProxyDataManager;
import com.avengers.rpgame.data.gameStatus.GameStatus;
import com.avengers.rpgame.game.GameConfig;
import com.avengers.rpgame.logic.entities.character.builder.CharacterBuilder;
import com.avengers.rpgame.logic.entities.character.builder.ICharacterDirector;
import com.avengers.rpgame.logic.entities.character.components.CharacterClass;
import com.avengers.rpgame.logic.entities.character.components.animatedCharacter.AAnimatedCharacter;
import com.avengers.rpgame.logic.entities.character.components.animatedCharacter.DynamicAnimatedCharacter;
import com.avengers.rpgame.logic.entities.character.components.animatedCharacter.StaticAnimatedCharacter;
import com.avengers.rpgame.logic.entities.character.components.skin.ISkin;
import com.avengers.rpgame.logic.entities.character.components.skin.abstractFactory.*;
import com.avengers.rpgame.logic.entities.character.concrete.PlayableCharacter;
import com.avengers.rpgame.utils.Resources;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import java.util.ArrayList;

public class EntitiesBuilderDirector implements ICharacterDirector {
    private GameConfig gameConfig = GameConfig.getInstance();
    private GameStatus gameStatus = GameStatus.getInstance();

    private ProxyDataManager proxyDataManager = new ProxyDataManager();

    ISkinFactory skinFactory;
    int idCharacter;
    String name;
    Vector2 position = new Vector2((int)gameConfig.getResolutionHorizontal()*12/5, (int)gameConfig.getResolutionVertical() /5);
    double level;
    CharacterClass characterClass;
    int coins = 1000;
    int experiencePoints;

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

    public void buildFireSkeletonDummy(CharacterBuilder builder, RPGame rpGame){
        skinFactory = new FireSkeletonSkinFactory();
        ISkin skin = skinFactory.createSkin();
        AAnimatedCharacter animatedCharacter = new StaticAnimatedCharacter(skin, rpGame, new Vector2(0,0));
        builder.setAnimatedCharacter(animatedCharacter);
    }

    public void buildWaterSkeletonDummy(CharacterBuilder builder, RPGame rpGame){
        skinFactory = new WaterSkeletonSkinFactory();
        ISkin skin = skinFactory.createSkin();
        AAnimatedCharacter animatedCharacter = new StaticAnimatedCharacter(skin, rpGame, new Vector2(0,0));
        builder.setAnimatedCharacter(animatedCharacter);
    }

    public void buildWindSkeletonDummy(CharacterBuilder builder, RPGame rpGame){
        skinFactory = new WindSkeletonSkinFactory();
        ISkin skin = skinFactory.createSkin();
        AAnimatedCharacter animatedCharacter = new StaticAnimatedCharacter(skin, rpGame, new Vector2(0,0));
        builder.setAnimatedCharacter(animatedCharacter);
    }

    public void buildChiefEarthDummy(CharacterBuilder builder, RPGame rpGame){
        skinFactory = new ChiefEarthSkinFactory();
        ISkin skin = skinFactory.createSkin();
        AAnimatedCharacter animatedCharacter = new StaticAnimatedCharacter(skin, rpGame, new Vector2(0,0));
        builder.setAnimatedCharacter(animatedCharacter);
    }

    public void buildChiefWindDummy(CharacterBuilder builder, RPGame rpGame){
        skinFactory = new ChiefWindSkinFactory();
        ISkin skin = skinFactory.createSkin();
        AAnimatedCharacter animatedCharacter = new StaticAnimatedCharacter(skin, rpGame, new Vector2(0,0));
        builder.setAnimatedCharacter(animatedCharacter);
    }

    public void buildChiefWaterDummy(CharacterBuilder builder, RPGame rpGame){
        skinFactory = new ChiefWaterSkinFactory();
        ISkin skin = skinFactory.createSkin();
        AAnimatedCharacter animatedCharacter = new StaticAnimatedCharacter(skin, rpGame, new Vector2(0,0));
        builder.setAnimatedCharacter(animatedCharacter);
    }

    public void buildChiefFireDummy(CharacterBuilder builder, RPGame rpGame){
        skinFactory = new ChiefFireSkinFactory();
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

        this.idCharacter=1;
        this.name=playerName;
        this.level=1;
        this.characterClass = proxyDataManager.getCharacterClass("Knight");
        this.experiencePoints = 0;

        ArrayList<Item> items = proxyDataManager.getConsumableItemsList("Knight");
        items.add(proxyDataManager.getSpecialItem("Talisman tierra"));
        ArrayList<Attack> attacks = proxyDataManager.getAttacksList("Knight",1);
        ArrayList<Skill> skills = new ArrayList<>();

        builder.setCharacterBasicInfo(this.idCharacter,this.name,characterClass.getDescription(),position,this.level,characterClass.getInitialHealthPoints(),characterClass.getInitialMagicPoints(),characterClass.getInitialHealthPoints(), characterClass.getInitialMagicPoints(), this.coins);
        builder.setCharacterAttributes(characterClass.getInitialStrength(),characterClass.getInitialSpeed(),characterClass.getInitialMagic(),characterClass.getInitialResistance(),characterClass.getInitialLuck());
        builder.setPlayableCharacterInfo(this.experiencePoints);
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

        this.idCharacter=2;
        this.name=playerName;
        this.level=1;
        this.characterClass = proxyDataManager.getCharacterClass("Archer");
        this.experiencePoints = 0;

        ArrayList<Item> items = proxyDataManager.getConsumableItemsList("Archer");
        items.add(proxyDataManager.getSpecialItem("Talisman tierra"));
        ArrayList<Attack> attacks = proxyDataManager.getAttacksList("Archer",1);
        ArrayList<Skill> skills = new ArrayList<>();

        builder.setCharacterBasicInfo(this.idCharacter,this.name,characterClass.getDescription(),position,this.level,characterClass.getInitialHealthPoints(),characterClass.getInitialMagicPoints(),characterClass.getInitialHealthPoints(), characterClass.getInitialMagicPoints(), this.coins);
        builder.setCharacterAttributes(characterClass.getInitialStrength(),characterClass.getInitialSpeed(),characterClass.getInitialMagic(),characterClass.getInitialResistance(),characterClass.getInitialLuck());
        builder.setPlayableCharacterInfo(this.experiencePoints);
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

        this.idCharacter=3;
        this.name=playerName;
        this.level=1;
        this.characterClass = proxyDataManager.getCharacterClass("Mage");
        this.experiencePoints = 0;

        ArrayList<Item> items = new ArrayList<>();
        items.add(proxyDataManager.getConsumableItemsList("Mage").get(0));
        items.add(proxyDataManager.getSpecialItem("Talisman tierra"));
        ArrayList<Attack> attacks = proxyDataManager.getAttacksList("Mage",1);
        ArrayList<Skill> skills = new ArrayList<>();

        builder.setCharacterBasicInfo(this.idCharacter,this.name,characterClass.getDescription(),position,this.level,characterClass.getInitialHealthPoints(),characterClass.getInitialMagicPoints(), characterClass.getInitialHealthPoints(), characterClass.getInitialMagicPoints(), this.coins);
        builder.setCharacterAttributes(characterClass.getInitialStrength(),characterClass.getInitialSpeed(),characterClass.getInitialMagic(),characterClass.getInitialResistance(),characterClass.getInitialLuck());
        builder.setPlayableCharacterInfo(this.experiencePoints);
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
        this.characterClass = proxyDataManager.getCharacterClass("Knight");
        this.experiencePoints = ((PlayableCharacter)gameStatus.getParty().getPartyMember(partyMember)).getExperiencePoints();

        builder.setCharacterBasicInfo(this.idCharacter,this.name,characterClass.getDescription(),gameStatus.getParty().getPartyMember(partyMember).getPosition(),this.level,gameStatus.getParty().getPartyMember(partyMember).getHealthPoints(),gameStatus.getParty().getPartyMember(partyMember).getMagicPoints(),characterClass.getInitialHealthPoints(), characterClass.getInitialMagicPoints(), this.coins);
        builder.setCharacterAttributes(gameStatus.getParty().getPartyMember(partyMember).getStrength(),gameStatus.getParty().getPartyMember(partyMember).getSpeed(),gameStatus.getParty().getPartyMember(partyMember).getMagic(),gameStatus.getParty().getPartyMember(partyMember).getResistance(),gameStatus.getParty().getPartyMember(partyMember).getLuck());
        builder.setPlayableCharacterInfo(this.experiencePoints);
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

        ArrayList<Item> items = gameStatus.getParty().getPartyMember(partyMember).getItems();
        ArrayList<Attack> attacks = gameStatus.getParty().getPartyMember(partyMember).getAttacks();
        ArrayList<Skill> skills = gameStatus.getParty().getPartyMember(partyMember).getSkills();

        this.idCharacter=2;
        this.name=gameStatus.getParty().getPartyMember(partyMember).getName();
        this.level=gameStatus.getParty().getPartyMember(partyMember).getLevel();
        this.coins=gameStatus.getParty().getPartyMember(partyMember).getCoins();
        this.characterClass = proxyDataManager.getCharacterClass("Archer");
        this.experiencePoints = ((PlayableCharacter)gameStatus.getParty().getPartyMember(partyMember)).getExperiencePoints();

        builder.setCharacterBasicInfo(this.idCharacter,this.name,characterClass.getDescription(),gameStatus.getParty().getPartyMember(partyMember).getPosition(),this.level,gameStatus.getParty().getPartyMember(partyMember).getHealthPoints(),gameStatus.getParty().getPartyMember(partyMember).getMagicPoints(),characterClass.getInitialHealthPoints(), characterClass.getInitialMagicPoints(), this.coins);
        builder.setCharacterAttributes(gameStatus.getParty().getPartyMember(partyMember).getStrength(),gameStatus.getParty().getPartyMember(partyMember).getSpeed(),gameStatus.getParty().getPartyMember(partyMember).getMagic(),gameStatus.getParty().getPartyMember(partyMember).getResistance(),gameStatus.getParty().getPartyMember(partyMember).getLuck());
        builder.setPlayableCharacterInfo(this.experiencePoints);
        builder.setCharacterClass(this.characterClass);
        builder.setItems(items);
        builder.setAttacks(attacks);
        builder.setSkills(skills);
    }

    public void buildMageFromDB(CharacterBuilder builder, World world, RPGame rpGame, int partyMember){
        skinFactory = new MageSkinFactory();
        ISkin skin = skinFactory.createSkin();
        AAnimatedCharacter animatedCharacter = new DynamicAnimatedCharacter(skin, rpGame);
        builder.setAnimatedCharacter(animatedCharacter);

        ArrayList<Item> items = gameStatus.getParty().getPartyMember(partyMember).getItems();
        ArrayList<Attack> attacks = gameStatus.getParty().getPartyMember(partyMember).getAttacks();
        ArrayList<Skill> skills = gameStatus.getParty().getPartyMember(partyMember).getSkills();

        this.idCharacter=3;
        this.name=gameStatus.getParty().getPartyMember(partyMember).getName();
        this.level=gameStatus.getParty().getPartyMember(partyMember).getLevel();
        this.coins=gameStatus.getParty().getPartyMember(partyMember).getCoins();
        this.characterClass = proxyDataManager.getCharacterClass("Mage");
        this.experiencePoints = ((PlayableCharacter)gameStatus.getParty().getPartyMember(partyMember)).getExperiencePoints();

        builder.setCharacterBasicInfo(this.idCharacter,this.name,characterClass.getDescription(),gameStatus.getParty().getPartyMember(partyMember).getPosition(),this.level,gameStatus.getParty().getPartyMember(partyMember).getHealthPoints(),gameStatus.getParty().getPartyMember(partyMember).getMagicPoints(), characterClass.getInitialHealthPoints(), characterClass.getInitialMagicPoints(), this.coins);
        builder.setCharacterAttributes(gameStatus.getParty().getPartyMember(partyMember).getStrength(),gameStatus.getParty().getPartyMember(partyMember).getSpeed(),gameStatus.getParty().getPartyMember(partyMember).getMagic(),gameStatus.getParty().getPartyMember(partyMember).getResistance(),gameStatus.getParty().getPartyMember(partyMember).getLuck());
        builder.setPlayableCharacterInfo(this.experiencePoints);
        builder.setCharacterClass(this.characterClass);
        builder.setItems(items);
        builder.setAttacks(attacks);
        builder.setSkills(skills);
    }

    //Enemies
     public void buildEarthSkeleton(CharacterBuilder builder, World world, RPGame rpGame, String playerName){
        skinFactory = new EarthSkeletonSkinFactory();
        ISkin skin = skinFactory.createSkin();
        AAnimatedCharacter animatedCharacter = new DynamicAnimatedCharacter(skin, rpGame);
        builder.setAnimatedCharacter(animatedCharacter);

        this.idCharacter=4;
        this.level=1;
        this.characterClass = proxyDataManager.getEnemyClass("EarthSkeleton");
        this.name= characterClass.getDescription();

        ArrayList<Item> items = proxyDataManager.getEnemyItemsList("EarthSkeleton");
        ArrayList<Attack> attacks = proxyDataManager.getEnemyAttacksList("EarthSkeleton");
        ArrayList<Skill> skills = new ArrayList<>();

        builder.setCharacterBasicInfo(this.idCharacter,this.name,characterClass.getDescription(),position,this.level,characterClass.getInitialHealthPoints(),characterClass.getInitialMagicPoints(),characterClass.getInitialHealthPoints(), characterClass.getInitialMagicPoints(), this.coins);
        builder.setCharacterAttributes(characterClass.getInitialStrength(),characterClass.getInitialSpeed(),characterClass.getInitialMagic(),characterClass.getInitialResistance(),characterClass.getInitialLuck());
        builder.setCharacterClass(this.characterClass);
        builder.setItems(items);
        builder.setAttacks(attacks);
        builder.setSkills(skills);
    }

    public void buildWaterSkeleton(CharacterBuilder builder, World world, RPGame rpGame, String playerName){
        skinFactory = new FireSkeletonSkinFactory();
        ISkin skin = skinFactory.createSkin();
        AAnimatedCharacter animatedCharacter = new DynamicAnimatedCharacter(skin, rpGame);
        builder.setAnimatedCharacter(animatedCharacter);

        this.idCharacter=5;
        this.level=7;
        this.characterClass = proxyDataManager.getEnemyClass("WaterSkeleton");
        this.name= characterClass.getDescription();

        ArrayList<Item> items = proxyDataManager.getEnemyItemsList("WaterSkeleton");
        ArrayList<Attack> attacks = proxyDataManager.getEnemyAttacksList("WaterSkeleton");
        ArrayList<Skill> skills = new ArrayList<>();

        builder.setCharacterBasicInfo(this.idCharacter,this.name,characterClass.getDescription(),position,this.level,characterClass.getInitialHealthPoints(),characterClass.getInitialMagicPoints(),characterClass.getInitialHealthPoints(), characterClass.getInitialMagicPoints(), this.coins);
        builder.setCharacterAttributes(characterClass.getInitialStrength(),characterClass.getInitialSpeed(),characterClass.getInitialMagic(),characterClass.getInitialResistance(),characterClass.getInitialLuck());
        builder.setCharacterClass(this.characterClass);
        builder.setItems(items);
        builder.setAttacks(attacks);
        builder.setSkills(skills);
    }

    public void buildWindSkeleton(CharacterBuilder builder, World world, RPGame rpGame, String playerName){
        skinFactory = new FireSkeletonSkinFactory();
        ISkin skin = skinFactory.createSkin();
        AAnimatedCharacter animatedCharacter = new DynamicAnimatedCharacter(skin, rpGame);
        builder.setAnimatedCharacter(animatedCharacter);

        this.idCharacter=6;
        this.level=13;
        this.characterClass = proxyDataManager.getEnemyClass("WindSkeleton");
        this.name= characterClass.getDescription();

        ArrayList<Item> items = proxyDataManager.getEnemyItemsList("WindSkeleton");
        ArrayList<Attack> attacks = proxyDataManager.getEnemyAttacksList("WindSkeleton");
        ArrayList<Skill> skills = new ArrayList<>();

        builder.setCharacterBasicInfo(this.idCharacter,this.name,characterClass.getDescription(),position,this.level,characterClass.getInitialHealthPoints(),characterClass.getInitialMagicPoints(),characterClass.getInitialHealthPoints(), characterClass.getInitialMagicPoints(), this.coins);
        builder.setCharacterAttributes(characterClass.getInitialStrength(),characterClass.getInitialSpeed(),characterClass.getInitialMagic(),characterClass.getInitialResistance(),characterClass.getInitialLuck());
        builder.setCharacterClass(this.characterClass);
        builder.setItems(items);
        builder.setAttacks(attacks);
        builder.setSkills(skills);
    }

    public void buildFireSkeleton(CharacterBuilder builder, World world, RPGame rpGame, String playerName){
        skinFactory = new FireSkeletonSkinFactory();
        ISkin skin = skinFactory.createSkin();
        AAnimatedCharacter animatedCharacter = new DynamicAnimatedCharacter(skin, rpGame);
        builder.setAnimatedCharacter(animatedCharacter);

        this.idCharacter=7;
        this.level=19;
        this.characterClass = proxyDataManager.getEnemyClass("FireSkeleton");
        this.name= characterClass.getDescription();

        ArrayList<Item> items = proxyDataManager.getEnemyItemsList("FireSkeleton");
        ArrayList<Attack> attacks = proxyDataManager.getEnemyAttacksList("FireSkeleton");
        ArrayList<Skill> skills = new ArrayList<>();

        builder.setCharacterBasicInfo(this.idCharacter,this.name,characterClass.getDescription(),position,this.level,characterClass.getInitialHealthPoints(),characterClass.getInitialMagicPoints(),characterClass.getInitialHealthPoints(), characterClass.getInitialMagicPoints(), this.coins);
        builder.setCharacterAttributes(characterClass.getInitialStrength(),characterClass.getInitialSpeed(),characterClass.getInitialMagic(),characterClass.getInitialResistance(),characterClass.getInitialLuck());
        builder.setCharacterClass(this.characterClass);
        builder.setItems(items);
        builder.setAttacks(attacks);
        builder.setSkills(skills);
    }

    public void buildChiefEarth(CharacterBuilder builder, World world, RPGame rpGame, String playerName){
        skinFactory = new EarthSkeletonSkinFactory();
        ISkin skin = skinFactory.createSkin();
        AAnimatedCharacter animatedCharacter = new DynamicAnimatedCharacter(skin, rpGame);
        builder.setAnimatedCharacter(animatedCharacter);

        this.idCharacter=8;
        this.level=6;
        this.characterClass = proxyDataManager.getEnemyClass("EarthChief");
        this.name= characterClass.getDescription();

        ArrayList<Item> items = proxyDataManager.getEnemyItemsList("EarthChief");
        ArrayList<Attack> attacks = proxyDataManager.getEnemyAttacksList("EarthChief");
        ArrayList<Skill> skills = new ArrayList<>();

        builder.setCharacterBasicInfo(this.idCharacter,this.name,characterClass.getDescription(),position,this.level,characterClass.getInitialHealthPoints(),characterClass.getInitialMagicPoints(),characterClass.getInitialHealthPoints(), characterClass.getInitialMagicPoints(), this.coins);
        builder.setCharacterAttributes(characterClass.getInitialStrength(),characterClass.getInitialSpeed(),characterClass.getInitialMagic(),characterClass.getInitialResistance(),characterClass.getInitialLuck());
        builder.setCharacterClass(this.characterClass);
        builder.setItems(items);
        builder.setAttacks(attacks);
        builder.setSkills(skills);
    }

    public void buildChiefWater(CharacterBuilder builder, World world, RPGame rpGame, String playerName){
        skinFactory = new ChiefWaterSkinFactory();
        ISkin skin = skinFactory.createSkin();
        AAnimatedCharacter animatedCharacter = new DynamicAnimatedCharacter(skin, rpGame);
        builder.setAnimatedCharacter(animatedCharacter);

        this.idCharacter=9;
        this.level=12;
        this.characterClass = proxyDataManager.getEnemyClass("WaterChief");
        this.name= characterClass.getDescription();

        ArrayList<Item> items = proxyDataManager.getEnemyItemsList("WaterChief");
        ArrayList<Attack> attacks = proxyDataManager.getEnemyAttacksList("WaterChief");
        ArrayList<Skill> skills = new ArrayList<>();

        builder.setCharacterBasicInfo(this.idCharacter,this.name,characterClass.getDescription(),position,this.level,characterClass.getInitialHealthPoints(),characterClass.getInitialMagicPoints(),characterClass.getInitialHealthPoints(), characterClass.getInitialMagicPoints(), this.coins);
        builder.setCharacterAttributes(characterClass.getInitialStrength(),characterClass.getInitialSpeed(),characterClass.getInitialMagic(),characterClass.getInitialResistance(),characterClass.getInitialLuck());
        builder.setCharacterClass(this.characterClass);
        builder.setItems(items);
        builder.setAttacks(attacks);
        builder.setSkills(skills);
    }

    public void buildChiefWind(CharacterBuilder builder, World world, RPGame rpGame, String playerName){
        skinFactory = new ChiefWindSkinFactory();
        ISkin skin = skinFactory.createSkin();
        AAnimatedCharacter animatedCharacter = new DynamicAnimatedCharacter(skin, rpGame);
        builder.setAnimatedCharacter(animatedCharacter);

        this.idCharacter=10;
        this.level=18;
        this.characterClass = proxyDataManager.getEnemyClass("WindChief");
        this.name= characterClass.getDescription();

        ArrayList<Item> items = proxyDataManager.getEnemyItemsList("WindChief");
        ArrayList<Attack> attacks = proxyDataManager.getEnemyAttacksList("WindChief");
        ArrayList<Skill> skills = new ArrayList<>();

        builder.setCharacterBasicInfo(this.idCharacter,this.name,characterClass.getDescription(),position,this.level,characterClass.getInitialHealthPoints(),characterClass.getInitialMagicPoints(),characterClass.getInitialHealthPoints(), characterClass.getInitialMagicPoints(), this.coins);
        builder.setCharacterAttributes(characterClass.getInitialStrength(),characterClass.getInitialSpeed(),characterClass.getInitialMagic(),characterClass.getInitialResistance(),characterClass.getInitialLuck());
        builder.setCharacterClass(this.characterClass);
        builder.setItems(items);
        builder.setAttacks(attacks);
        builder.setSkills(skills);
    }

    public void buildChiefFire(CharacterBuilder builder, World world, RPGame rpGame, String playerName){
        skinFactory = new ChiefFireSkinFactory();
        ISkin skin = skinFactory.createSkin();
        AAnimatedCharacter animatedCharacter = new DynamicAnimatedCharacter(skin, rpGame);
        builder.setAnimatedCharacter(animatedCharacter);

        this.idCharacter=11;
        this.level=20;
        this.characterClass = proxyDataManager.getEnemyClass("FireChief");
        this.name= characterClass.getDescription();

        ArrayList<Item> items = proxyDataManager.getEnemyItemsList("FireChief");
        ArrayList<Attack> attacks = proxyDataManager.getEnemyAttacksList("FireChief");
        ArrayList<Skill> skills = new ArrayList<>();

        builder.setCharacterBasicInfo(this.idCharacter,this.name,characterClass.getDescription(),position,this.level,characterClass.getInitialHealthPoints(),characterClass.getInitialMagicPoints(),characterClass.getInitialHealthPoints(), characterClass.getInitialMagicPoints(), this.coins);
        builder.setCharacterAttributes(characterClass.getInitialStrength(),characterClass.getInitialSpeed(),characterClass.getInitialMagic(),characterClass.getInitialResistance(),characterClass.getInitialLuck());
        builder.setCharacterClass(this.characterClass);
        builder.setItems(items);
        builder.setAttacks(attacks);
        builder.setSkills(skills);
    }

}