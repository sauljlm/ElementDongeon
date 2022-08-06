package com.avengers.rpgame.logic.entities.character.factory;

import com.avengers.rpgame.RPGame;
import com.avengers.rpgame.data.gameStatus.GameStatus;
import com.avengers.rpgame.logic.entities.EntitiesBuilderDirector;
import com.avengers.rpgame.logic.entities.character.abstractCharacter.AbstractCharacter;
import com.avengers.rpgame.logic.entities.character.builder.CharacterBuilder;
import com.avengers.rpgame.logic.entities.character.components.animatedCharacter.DynamicAnimatedCharacter;
import com.badlogic.gdx.physics.box2d.World;

public class CharacterFactory implements ICharacterFactory{
    private GameStatus gameStatus;
    private CharacterBuilder characterBuilder;
    private EntitiesBuilderDirector director;
    private World world;
    private RPGame rpGame;
    private AbstractCharacter player1;
    private AbstractCharacter player2;
    private AbstractCharacter player3;

    public CharacterFactory(World world, RPGame rpGame) {
        gameStatus = GameStatus.getInstance();
        characterBuilder = new CharacterBuilder();
        director = new EntitiesBuilderDirector();
        this.world = world;
        this.rpGame = rpGame;
    }

    public void createParty() {
        if (gameStatus.getStatus().equals("newGame")){
            createNewParty();
        }
        if (gameStatus.getStatus().equals("loadedGame") || gameStatus.getStatus().contains("defeated")){
            loadParty();
        }
        if (gameStatus.getStatus().equals("gameInProgress")){
            restoreParty();
        }
    }

    private void createNewParty(){
        System.out.println("creating new party");
        //TODO encapsulate this into an external class that takes care of creating the characters
        if(gameStatus.getPlayerParty().getPartyMember(1).getIdCharacter()==1){
            director.buildKnight(characterBuilder, world, rpGame, gameStatus.getPlayerParty().getPartyMember(1).getName());
            gameStatus.getPlayerParty().setPartyMember1(characterBuilder.getResult());
            director.buildMage(characterBuilder, world, rpGame, "Merlin");
            gameStatus.getPlayerParty().setPartyMember2(characterBuilder.getResult());
            director.buildArcher(characterBuilder, world, rpGame, "Robin");
            gameStatus.getPlayerParty().setPartyMember3(characterBuilder.getResult());
        }
        if(gameStatus.getPlayerParty().getPartyMember(1).getIdCharacter()==2){
            director.buildArcher(characterBuilder, world, rpGame, gameStatus.getPlayerParty().getPartyMember(1).getName());
            gameStatus.getPlayerParty().setPartyMember1(characterBuilder.getResult());
            director.buildKnight(characterBuilder, world, rpGame, "Lancelot");
            gameStatus.getPlayerParty().setPartyMember2(characterBuilder.getResult());
            director.buildMage(characterBuilder, world, rpGame, "Merlin");
            gameStatus.getPlayerParty().setPartyMember3(characterBuilder.getResult());
        }
        if(gameStatus.getPlayerParty().getPartyMember(1).getIdCharacter()==3){
            director.buildMage(characterBuilder, world, rpGame, gameStatus.getPlayerParty().getPartyMember(1).getName());
            gameStatus.getPlayerParty().setPartyMember1(characterBuilder.getResult());
            director.buildKnight(characterBuilder, world, rpGame, "Lancelot");
            gameStatus.getPlayerParty().setPartyMember2(characterBuilder.getResult());
            director.buildArcher(characterBuilder, world, rpGame, "Robin");
            gameStatus.getPlayerParty().setPartyMember3(characterBuilder.getResult());
        }
    }

    private void loadParty() {
        System.out.println("loading party");
//        GameStatus.getInstance().updateLocation();
        if(gameStatus.getPlayerParty().getPartyMember(1).getCharacterClass().getIdCharacterClass()==1){
            director.buildKnightFromDB(characterBuilder, world, rpGame, 1);
            gameStatus.getPlayerParty().setPartyMember1(characterBuilder.getResult());
            director.buildMageFromDB(characterBuilder, world, rpGame, 2);
            gameStatus.getPlayerParty().setPartyMember2(characterBuilder.getResult());
            director.buildArcherFromDB(characterBuilder, world, rpGame, 3);
            gameStatus.getPlayerParty().setPartyMember3(characterBuilder.getResult());
        }
        if(gameStatus.getPlayerParty().getPartyMember(1).getCharacterClass().getIdCharacterClass()==2){
            director.buildArcherFromDB(characterBuilder, world, rpGame, 1);
            gameStatus.getPlayerParty().setPartyMember1(characterBuilder.getResult());
            director.buildKnightFromDB(characterBuilder, world, rpGame, 2);
            gameStatus.getPlayerParty().setPartyMember2(characterBuilder.getResult());
            director.buildMageFromDB(characterBuilder, world, rpGame, 3);
            gameStatus.getPlayerParty().setPartyMember3(characterBuilder.getResult());
        }
        if(gameStatus.getPlayerParty().getPartyMember(1).getCharacterClass().getIdCharacterClass()==3){
            director.buildMageFromDB(characterBuilder, world, rpGame, 1);
            gameStatus.getPlayerParty().setPartyMember1(characterBuilder.getResult());
            director.buildKnightFromDB(characterBuilder, world, rpGame, 2);
            gameStatus.getPlayerParty().setPartyMember2(characterBuilder.getResult());
            director.buildArcherFromDB(characterBuilder, world, rpGame, 3);
            gameStatus.getPlayerParty().setPartyMember3(characterBuilder.getResult());
        }
    }

    private void restoreParty() {
        System.out.println("restoring party");
        ((DynamicAnimatedCharacter)gameStatus.getPlayerParty().getPartyMember1().getAnimatedCharacter()).recreateBody();
        ((DynamicAnimatedCharacter)gameStatus.getPlayerParty().getPartyMember2().getAnimatedCharacter()).recreateBody();
        ((DynamicAnimatedCharacter)gameStatus.getPlayerParty().getPartyMember3().getAnimatedCharacter()).recreateBody();
    }
}
