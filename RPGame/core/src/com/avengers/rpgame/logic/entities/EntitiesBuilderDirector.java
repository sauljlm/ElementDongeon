package com.avengers.rpgame.logic.entities;

import com.avengers.rpgame.RPGame;
import com.avengers.rpgame.data.dataStorage.ProxyDataManager;
import com.avengers.rpgame.data.gameStatus.GameStatus;
import com.avengers.rpgame.game.GameConfig;
import com.avengers.rpgame.logic.entities.character.abstractCharacter.AbstractCharacter;
import com.avengers.rpgame.logic.entities.character.builder.ICharacterBuilder;
import com.avengers.rpgame.logic.entities.character.builder.ICharacterDirector;
import com.avengers.rpgame.logic.entities.character.components.CharacterClass;
import com.avengers.rpgame.logic.entities.character.components.animatedCharacter.AAnimatedCharacter;
import com.avengers.rpgame.logic.entities.character.components.animatedCharacter.DynamicAnimatedCharacter;
import com.avengers.rpgame.logic.entities.character.components.animatedCharacter.StaticAnimatedCharacter;
import com.avengers.rpgame.logic.entities.character.components.skin.ISkin;
import com.avengers.rpgame.logic.entities.character.components.skin.abstractFactory.*;
import com.avengers.rpgame.logic.entities.character.concrete.PlayableCharacter;
import com.avengers.rpgame.utils.StartingPositionsReducer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import java.util.ArrayList;

public class EntitiesBuilderDirector implements ICharacterDirector {
    private GameConfig gameConfig = GameConfig.getInstance();
    private GameStatus gameStatus = GameStatus.getInstance();
    private ProxyDataManager proxyDataManager = new ProxyDataManager();

    private StartingPositionsReducer startingPositionsReducer = new StartingPositionsReducer();

    ISkinFactory skinFactory;
    int idCharacter;
    String name;
    Vector2 position = new Vector2((int)gameConfig.getResolutionHorizontal()*12/5, (int)gameConfig.getResolutionVertical() /5);
    double level;
    CharacterClass characterClass;
    int coins = 1000;
    int experiencePoints;

    //This method builds a Dummy from any complete character. A dummy only contains the animatedCharacter data for it to render on the game. This is used on battle screen /Character selection screen.
    public void buildBattleDummy(ICharacterBuilder builder, RPGame rpGame, AbstractCharacter baseCharacter){
        ISkin skin = baseCharacter.getAnimatedCharacter().getSkin();
        AAnimatedCharacter animatedCharacter = new StaticAnimatedCharacter(skin, rpGame, new Vector2(0,0));
        builder.setAnimatedCharacter(animatedCharacter);
    }

    // Playable characters
    public void buildKnight(ICharacterBuilder builder, World world, RPGame rpGame, String playerName){
        position = startingPositionsReducer.getPosition("player");
        skinFactory = new KnighSkinFactory();
        ISkin skin = skinFactory.createSkin();
        AAnimatedCharacter animatedCharacter = new DynamicAnimatedCharacter(skin, rpGame, position);
        builder.setAnimatedCharacter(animatedCharacter);

        this.idCharacter=1;
        this.name=playerName;
        this.level=1;
        this.characterClass = proxyDataManager.getCharacterClass("Knight");
        this.experiencePoints = 0;

        ArrayList<Item> items = proxyDataManager.getConsumableItemsList("Knight",1);
 //       items.add(proxyDataManager.getSpecialItem("Talisman inicial"));
        items.add(proxyDataManager.getSpecialItem("Talisman fuego"));
        if(true){ //Keys for debug
//        if(gameConfig.isGodMode()){ //Keys for debug
            items.add(proxyDataManager.getSpecialItem("Llave tierra"));
            items.add(proxyDataManager.getSpecialItem("Llave agua"));
            items.add(proxyDataManager.getSpecialItem("Llave viento"));
            items.add(proxyDataManager.getSpecialItem("Llave fuego"));
        }
        ArrayList<Attack> attacks = proxyDataManager.getAttacksList("Knight",1);
        ArrayList<Skill> skills = proxyDataManager.getSkillsList("Knight",1);

        builder.setCharacterBasicInfo(this.idCharacter,this.name,characterClass.getDescription(),position,this.level,characterClass.getInitialHealthPoints(),characterClass.getInitialMagicPoints(),characterClass.getInitialHealthPoints(), characterClass.getInitialMagicPoints(), this.coins);
        builder.setCharacterAttributes(characterClass.getInitialStrength(),characterClass.getInitialSpeed(),characterClass.getInitialMagic(),characterClass.getInitialResistance(),characterClass.getInitialLuck());
        builder.setPlayableCharacterInfo(this.experiencePoints);
        builder.setCharacterClass(this.characterClass);
        builder.setItems(items);
        builder.setAttacks(attacks);
        builder.setSkills(skills);
    }

    public void buildArcher(ICharacterBuilder builder, World world, RPGame rpGame, String playerName){
        position = startingPositionsReducer.getPosition("player");
        skinFactory = new ArcherSkinFactory();
        ISkin skin = skinFactory.createSkin();
        AAnimatedCharacter animatedCharacter = new DynamicAnimatedCharacter(skin, rpGame, position);
        builder.setAnimatedCharacter(animatedCharacter);

        this.idCharacter=2;
        this.name=playerName;
        this.level=1;
        this.characterClass = proxyDataManager.getCharacterClass("Archer");
        this.experiencePoints = 0;

        ArrayList<Item> items = proxyDataManager.getConsumableItemsList("Archer",1);
        items.add(proxyDataManager.getSpecialItem("Talisman inicial"));
        ArrayList<Attack> attacks = proxyDataManager.getAttacksList("Archer",1);
        ArrayList<Skill> skills = proxyDataManager.getSkillsList("Archer",1);

        builder.setCharacterBasicInfo(this.idCharacter,this.name,characterClass.getDescription(),position,this.level,characterClass.getInitialHealthPoints(),characterClass.getInitialMagicPoints(),characterClass.getInitialHealthPoints(), characterClass.getInitialMagicPoints(), this.coins);
        builder.setCharacterAttributes(characterClass.getInitialStrength(),characterClass.getInitialSpeed(),characterClass.getInitialMagic(),characterClass.getInitialResistance(),characterClass.getInitialLuck());
        builder.setPlayableCharacterInfo(this.experiencePoints);
        builder.setCharacterClass(this.characterClass);
        builder.setItems(items);
        builder.setAttacks(attacks);
        builder.setSkills(skills);
    }

    public void buildMage(ICharacterBuilder builder, World world, RPGame rpGame, String playerName){
        position = startingPositionsReducer.getPosition("player");
        skinFactory = new MageSkinFactory();
        ISkin skin = skinFactory.createSkin();
        AAnimatedCharacter animatedCharacter = new DynamicAnimatedCharacter(skin, rpGame, position);
        builder.setAnimatedCharacter(animatedCharacter);

        this.idCharacter=3;
        this.name=playerName;
        this.level=1;
        this.characterClass = proxyDataManager.getCharacterClass("Mage");
        this.experiencePoints = 0;

        ArrayList<Item> items = proxyDataManager.getConsumableItemsList("Mage",1);
        items.add(proxyDataManager.getSpecialItem("Talisman inicial"));
        ArrayList<Attack> attacks = proxyDataManager.getAttacksList("Mage",1);
        ArrayList<Skill> skills = proxyDataManager.getSkillsList("Mage",1);


        builder.setCharacterBasicInfo(this.idCharacter,this.name,characterClass.getDescription(),position,this.level,characterClass.getInitialHealthPoints(),characterClass.getInitialMagicPoints(), characterClass.getInitialHealthPoints(), characterClass.getInitialMagicPoints(), this.coins);
        builder.setCharacterAttributes(characterClass.getInitialStrength(),characterClass.getInitialSpeed(),characterClass.getInitialMagic(),characterClass.getInitialResistance(),characterClass.getInitialLuck());
        builder.setPlayableCharacterInfo(this.experiencePoints);
        builder.setCharacterClass(this.characterClass);
        builder.setItems(items);
        builder.setAttacks(attacks);
        builder.setSkills(skills);
    }

    //Playable characters from DB
    public void buildKnightFromDB(ICharacterBuilder builder, World world, RPGame rpGame, int partyMember){
        position = startingPositionsReducer.getPosition("player");
        skinFactory = new KnighSkinFactory();
        ISkin skin = skinFactory.createSkin();
        AAnimatedCharacter animatedCharacter = new DynamicAnimatedCharacter(skin, rpGame, position);
        builder.setAnimatedCharacter(animatedCharacter);

        ArrayList<Item> items = gameStatus.getPlayerParty().getPartyMember(partyMember).getItems();
        ArrayList<Attack> attacks = gameStatus.getPlayerParty().getPartyMember(partyMember).getAttacks();
        ArrayList<Skill> skills = gameStatus.getPlayerParty().getPartyMember(partyMember).getSkills();

        this.idCharacter=1;
        this.name=gameStatus.getPlayerParty().getPartyMember(partyMember).getName();
        this.level=gameStatus.getPlayerParty().getPartyMember(partyMember).getLevel();
        this.coins=gameStatus.getPlayerParty().getPartyMember(partyMember).getCoins();
        this.characterClass = proxyDataManager.getCharacterClass("Knight");
        this.experiencePoints = ((PlayableCharacter)gameStatus.getPlayerParty().getPartyMember(partyMember)).getExperiencePoints();

        builder.setCharacterBasicInfo(this.idCharacter,this.name,characterClass.getDescription(),gameStatus.getPlayerParty().getPartyMember(partyMember).getPosition(),this.level,gameStatus.getPlayerParty().getPartyMember(partyMember).getHealthPoints(),gameStatus.getPlayerParty().getPartyMember(partyMember).getMagicPoints(),characterClass.getInitialHealthPoints(), characterClass.getInitialMagicPoints(), this.coins);
        builder.setCharacterAttributes(gameStatus.getPlayerParty().getPartyMember(partyMember).getStrength(),gameStatus.getPlayerParty().getPartyMember(partyMember).getSpeed(),gameStatus.getPlayerParty().getPartyMember(partyMember).getMagic(),gameStatus.getPlayerParty().getPartyMember(partyMember).getResistance(),gameStatus.getPlayerParty().getPartyMember(partyMember).getLuck());
        builder.setPlayableCharacterInfo(this.experiencePoints);
        builder.setCharacterClass(this.characterClass);
        builder.setItems(items);
        builder.setAttacks(attacks);
        builder.setSkills(skills);
    }

    public void buildArcherFromDB(ICharacterBuilder builder, World world, RPGame rpGame, int partyMember){
        position = startingPositionsReducer.getPosition("player");
        skinFactory = new ArcherSkinFactory();
        ISkin skin = skinFactory.createSkin();
        AAnimatedCharacter animatedCharacter = new DynamicAnimatedCharacter(skin, rpGame, position);
        builder.setAnimatedCharacter(animatedCharacter);

        ArrayList<Item> items = gameStatus.getPlayerParty().getPartyMember(partyMember).getItems();
        ArrayList<Attack> attacks = gameStatus.getPlayerParty().getPartyMember(partyMember).getAttacks();
        ArrayList<Skill> skills = gameStatus.getPlayerParty().getPartyMember(partyMember).getSkills();

        this.idCharacter=2;
        this.name=gameStatus.getPlayerParty().getPartyMember(partyMember).getName();
        this.level=gameStatus.getPlayerParty().getPartyMember(partyMember).getLevel();
        this.coins=gameStatus.getPlayerParty().getPartyMember(partyMember).getCoins();
        this.characterClass = proxyDataManager.getCharacterClass("Archer");
        this.experiencePoints = ((PlayableCharacter)gameStatus.getPlayerParty().getPartyMember(partyMember)).getExperiencePoints();

        builder.setCharacterBasicInfo(this.idCharacter,this.name,characterClass.getDescription(),gameStatus.getPlayerParty().getPartyMember(partyMember).getPosition(),this.level,gameStatus.getPlayerParty().getPartyMember(partyMember).getHealthPoints(),gameStatus.getPlayerParty().getPartyMember(partyMember).getMagicPoints(),characterClass.getInitialHealthPoints(), characterClass.getInitialMagicPoints(), this.coins);
        builder.setCharacterAttributes(gameStatus.getPlayerParty().getPartyMember(partyMember).getStrength(),gameStatus.getPlayerParty().getPartyMember(partyMember).getSpeed(),gameStatus.getPlayerParty().getPartyMember(partyMember).getMagic(),gameStatus.getPlayerParty().getPartyMember(partyMember).getResistance(),gameStatus.getPlayerParty().getPartyMember(partyMember).getLuck());
        builder.setPlayableCharacterInfo(this.experiencePoints);
        builder.setCharacterClass(this.characterClass);
        builder.setItems(items);
        builder.setAttacks(attacks);
        builder.setSkills(skills);
    }

    public void buildMageFromDB(ICharacterBuilder builder, World world, RPGame rpGame, int partyMember){
        position = startingPositionsReducer.getPosition("player");
        skinFactory = new MageSkinFactory();
        ISkin skin = skinFactory.createSkin();
        AAnimatedCharacter animatedCharacter = new DynamicAnimatedCharacter(skin, rpGame, position);
        builder.setAnimatedCharacter(animatedCharacter);

        ArrayList<Item> items = gameStatus.getPlayerParty().getPartyMember(partyMember).getItems();
        ArrayList<Attack> attacks = gameStatus.getPlayerParty().getPartyMember(partyMember).getAttacks();
        ArrayList<Skill> skills = gameStatus.getPlayerParty().getPartyMember(partyMember).getSkills();

        this.idCharacter=3;
        this.name=gameStatus.getPlayerParty().getPartyMember(partyMember).getName();
        this.level=gameStatus.getPlayerParty().getPartyMember(partyMember).getLevel();
        this.coins=gameStatus.getPlayerParty().getPartyMember(partyMember).getCoins();
        this.characterClass = proxyDataManager.getCharacterClass("Mage");
        this.experiencePoints = ((PlayableCharacter)gameStatus.getPlayerParty().getPartyMember(partyMember)).getExperiencePoints();

        builder.setCharacterBasicInfo(this.idCharacter,this.name,characterClass.getDescription(),gameStatus.getPlayerParty().getPartyMember(partyMember).getPosition(),this.level,gameStatus.getPlayerParty().getPartyMember(partyMember).getHealthPoints(),gameStatus.getPlayerParty().getPartyMember(partyMember).getMagicPoints(), characterClass.getInitialHealthPoints(), characterClass.getInitialMagicPoints(), this.coins);
        builder.setCharacterAttributes(gameStatus.getPlayerParty().getPartyMember(partyMember).getStrength(),gameStatus.getPlayerParty().getPartyMember(partyMember).getSpeed(),gameStatus.getPlayerParty().getPartyMember(partyMember).getMagic(),gameStatus.getPlayerParty().getPartyMember(partyMember).getResistance(),gameStatus.getPlayerParty().getPartyMember(partyMember).getLuck());
        builder.setPlayableCharacterInfo(this.experiencePoints);
        builder.setCharacterClass(this.characterClass);
        builder.setItems(items);
        builder.setAttacks(attacks);
        builder.setSkills(skills);
    }

    //Enemies
     public void buildEarthSkeleton(ICharacterBuilder builder, World world, RPGame rpGame, String id){
        position = startingPositionsReducer.getPosition(id);
        skinFactory = new EarthSkeletonSkinFactory();
        ISkin skin = skinFactory.createSkin();
        AAnimatedCharacter animatedCharacter = new DynamicAnimatedCharacter(skin, rpGame, position);
        builder.setAnimatedCharacter(animatedCharacter);

        this.idCharacter=4;
        this.level=1;
        this.characterClass = proxyDataManager.getEnemyClass("EarthSkeleton");
        this.name= id;

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

    public void buildWaterSkeleton(ICharacterBuilder builder, World world, RPGame rpGame, String id){
        position = startingPositionsReducer.getPosition(id);
        skinFactory = new WaterSkeletonSkinFactory();
        ISkin skin = skinFactory.createSkin();
        AAnimatedCharacter animatedCharacter = new DynamicAnimatedCharacter(skin, rpGame, position);
        builder.setAnimatedCharacter(animatedCharacter);

        this.idCharacter=5;
        this.level=7;
        this.characterClass = proxyDataManager.getEnemyClass("WaterSkeleton");
        this.name= id;

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

    public void buildWindSkeleton(ICharacterBuilder builder, World world, RPGame rpGame, String id){
        position = startingPositionsReducer.getPosition(id);
        skinFactory = new WindSkeletonSkinFactory();
        ISkin skin = skinFactory.createSkin();
        AAnimatedCharacter animatedCharacter = new DynamicAnimatedCharacter(skin, rpGame, position);
        builder.setAnimatedCharacter(animatedCharacter);

        this.idCharacter=6;
        this.level=13;
        this.characterClass = proxyDataManager.getEnemyClass("WindSkeleton");
        this.name= id;

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

    public void buildFireSkeleton(ICharacterBuilder builder, World world, RPGame rpGame, String id){
        position = startingPositionsReducer.getPosition(id);
        skinFactory = new FireSkeletonSkinFactory();
        ISkin skin = skinFactory.createSkin();
        AAnimatedCharacter animatedCharacter = new DynamicAnimatedCharacter(skin, rpGame, position);
        ((DynamicAnimatedCharacter)animatedCharacter).setWorldPosition(new Vector2(0,0));
        builder.setAnimatedCharacter(animatedCharacter);

        this.idCharacter=7;
        this.level=19;
        this.characterClass = proxyDataManager.getEnemyClass("FireSkeleton");
        this.name= id;

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

    public void buildChiefEarth(ICharacterBuilder builder, World world, RPGame rpGame, String id){
        position = startingPositionsReducer.getPosition(id);
        skinFactory = new ChiefEarthSkinFactory();
        ISkin skin = skinFactory.createSkin();
        AAnimatedCharacter animatedCharacter = new DynamicAnimatedCharacter(skin, rpGame, position);
        builder.setAnimatedCharacter(animatedCharacter);

        this.idCharacter=8;
        this.level=6;
        this.characterClass = proxyDataManager.getEnemyClass("EarthChief");
        this.name= id;

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

    public void buildChiefWater(ICharacterBuilder builder, World world, RPGame rpGame, String id){
        position = startingPositionsReducer.getPosition(id);
        skinFactory = new ChiefWaterSkinFactory();
        ISkin skin = skinFactory.createSkin();
        AAnimatedCharacter animatedCharacter = new DynamicAnimatedCharacter(skin, rpGame, position);
        builder.setAnimatedCharacter(animatedCharacter);

        this.idCharacter=9;
        this.level=12;
        this.characterClass = proxyDataManager.getEnemyClass("WaterChief");
        this.name= id;

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

    public void buildChiefWind(ICharacterBuilder builder, World world, RPGame rpGame, String id){
        position = startingPositionsReducer.getPosition(id);
        skinFactory = new ChiefWindSkinFactory();
        ISkin skin = skinFactory.createSkin();
        AAnimatedCharacter animatedCharacter = new DynamicAnimatedCharacter(skin, rpGame, position);
        builder.setAnimatedCharacter(animatedCharacter);

        this.idCharacter=10;
        this.level=18;
        this.characterClass = proxyDataManager.getEnemyClass("WindChief");
        this.name= id;

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

    public void buildChiefFire(ICharacterBuilder builder, World world, RPGame rpGame, String id){
        position = startingPositionsReducer.getPosition(id);
        skinFactory = new ChiefFireSkinFactory();
        ISkin skin = skinFactory.createSkin();
        AAnimatedCharacter animatedCharacter = new DynamicAnimatedCharacter(skin, rpGame, position);
        builder.setAnimatedCharacter(animatedCharacter);

        this.idCharacter=11;
        this.level=20;
        this.characterClass = proxyDataManager.getEnemyClass("FireChief");
        this.name= id;

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