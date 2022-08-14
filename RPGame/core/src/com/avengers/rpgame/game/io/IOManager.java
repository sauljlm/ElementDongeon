package com.avengers.rpgame.game.io;

import com.avengers.rpgame.RPGame;
import com.avengers.rpgame.audio.SoundEffectsManager;
import com.avengers.rpgame.data.gameStatus.GameStatus;
import com.avengers.rpgame.game.GameConfig;
import com.avengers.rpgame.graphics.screens.*;
import com.avengers.rpgame.logic.entities.Item;
import com.avengers.rpgame.logic.entities.Party;
import com.avengers.rpgame.logic.entities.character.components.animatedCharacter.DynamicAnimatedCharacter;
import com.avengers.rpgame.utils.Utils;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

import static com.avengers.rpgame.utils.Resources.steps;

//TODO Improve this and create different controls for different screens
public class IOManager {
    private static IOManager instance;
    private static MyInputProcessor inputProcessor;
    private GameConfig config;
    private RPGame game;

    private GameStatus gameStatus;

    private IOManager() {
        inputProcessor = new MyInputProcessor();
        Gdx.input.setInputProcessor(inputProcessor);
        config = GameConfig.getInstance();
        game = RPGame.getInstance();
        gameStatus = GameStatus.getInstance();
    }

    //the idea for this method is to process different request of IO processInput differently acording to the screen
    public void processInput(String type, float delta, Party playerParty){
//        playerParty.getPartyMember1().setPosition(playerParty.getPartyMember1().getAnimatedCharacter().getPlayer().getPosition());
        if(type.equals("overworld")){
            overworldUpdate(delta, playerParty);
            setOverworldLocation();
        }
        if(type.equals("battle")){
            battleUpdate(delta, playerParty);
        }
        if(type.equals("pauseMenu")){

        }
        if(type.equals("mainScreen")){

        }
    }

    private void setOverworldLocation() {
        Vector2 pos = ((DynamicAnimatedCharacter) gameStatus.getPlayerParty().getActivePartyMember().getAnimatedCharacter()).getPlayer().getPosition();
        gameStatus.setCurrentLocation("overworld"); //Default location
        //EarthDungeon
        if (155 < pos.x && pos.x < 258 && 20 < pos.y && pos.y < 92) {
            gameStatus.setCurrentLocation("earthDungeon");
        }
        //WindDungeon
        if (262 < pos.x && pos.x < 370 && 100 < pos.y && pos.y < 172) {
            gameStatus.setCurrentLocation("windDungeon");
        }
        //WaterDungeon
        if (39 < pos.x && pos.x < 140 && 103 < pos.y && pos.y < 166) {
            gameStatus.setCurrentLocation("waterDungeon");
        }
        //FireDungeon
        if (165 < pos.x && pos.x < 254 && 177 < pos.y && pos.y < 242) {
            gameStatus.setCurrentLocation("fireDungeon");
        }
        //Maze
        if (20 < pos.x && pos.x < 148 && 173 < pos.y && pos.y < 237) {
            gameStatus.setCurrentLocation("maze");
        }
    }

    private void switchPartyMember() {
        System.out.println("Changing party active focus");
        int activeMember = gameStatus.getPlayerParty().getActivePartyMemberId();
        if(activeMember+1 <= 3){
            activeMember = activeMember+1;
        }else {
            activeMember=1;
        }
        gameStatus.getPlayerParty().setActivePartyMemberId(activeMember);
    }

    //Process input for overworld gameplay
    private void overworldUpdate(float delta, Party playerParty) {
        float horizontalForce = 0;
        float verticalForce = 0;
        float movementSpeed = 1.5f;
        Vector2 velocity = new Vector2();
        if(config.isGodMode()){
            movementSpeed = 4;
        }
        if(inputProcessor.isMoveLeft()) {
            playerParty.getActivePartyMember().getAnimatedCharacter().setAction("runningLeft");
            horizontalForce = -movementSpeed;
            verticalForce = 0;
        }
        if(inputProcessor.isMoveRight()){
            playerParty.getActivePartyMember().getAnimatedCharacter().setAction("runningRight");
            horizontalForce = movementSpeed;
            verticalForce = 0;
        }
        if(inputProcessor.isMoveUp()) {
            playerParty.getActivePartyMember().getAnimatedCharacter().setAction("runningUp");
            horizontalForce = 0;
            verticalForce = movementSpeed;
        }
        if(inputProcessor.isMoveDown()){
            playerParty.getActivePartyMember().getAnimatedCharacter().setAction("runningDown");
            verticalForce = -movementSpeed;
            horizontalForce = 0;
        }
        if(inputProcessor.isStoreOpened()){
            ScreeenManager.getInstance().changeScreen("StoreScreen");
        }
        if(inputProcessor.isTab()&& Utils.getInstance().skipFrames()){
            switchPartyMember();
        }

        if(horizontalForce != 0 || verticalForce != 0){
            SoundEffectsManager.getInstance().play(steps, true);
        } else{
            SoundEffectsManager.getInstance().stop(steps);
        }
//        if(velocity.x == 0 || velocity.y == 0){
//            SoundEffectsManager.getInstance().stop(menuSoundEffect);
//        }

        velocity.x = horizontalForce * 5;
        velocity.y = verticalForce * 5;

        ((DynamicAnimatedCharacter)playerParty.getActivePartyMember().getAnimatedCharacter()).getPlayer().setLinearVelocity(velocity.x,velocity.y);

        if(inputProcessor.isCreditMode()) {
            for (Item itemFound: playerParty.getActivePartyMember().getItems()) {
                if (itemFound.getDescription().equals("Llave elemental")) {
                    ScreeenManager.getInstance().changeScreen("CreditScreen");
                }
            }
        }

        if(inputProcessor.isPause()){
            ScreeenManager.getInstance().changeScreen("PauseScreen");
        }

    }

    //Process input for overworld gameplay
    private void battleUpdate(float delta, Party playerParty) {

        float horizontalForce = 0;
        float verticalForce = 0;
        float movementSpeed = 1.5f;
        Vector2 velocity = new Vector2();
        if(config.isGodMode()){
            movementSpeed = 4;
        }
        if(inputProcessor.isMoveLeft()) {
            playerParty.getActivePartyMember().getAnimatedCharacter().setAction("runningLeft");
            horizontalForce = -movementSpeed;
            verticalForce = 0;
        }
        if(inputProcessor.isMoveRight()){
            playerParty.getActivePartyMember().getAnimatedCharacter().setAction("runningRight");
            horizontalForce = movementSpeed;
            verticalForce = 0;
        }
        if(inputProcessor.isMoveUp()) {
            playerParty.getActivePartyMember().getAnimatedCharacter().setAction("runningUp");
            horizontalForce = 0;
            verticalForce = movementSpeed;
        }
        if(inputProcessor.isMoveDown()){
            playerParty.getActivePartyMember().getAnimatedCharacter().setAction("runningDown");
            verticalForce = -movementSpeed;
            horizontalForce = 0;
        }

        velocity.x = horizontalForce * 5;
        velocity.y = verticalForce * 5;

        ((DynamicAnimatedCharacter)playerParty.getActivePartyMember().getAnimatedCharacter()).getPlayer().setLinearVelocity(velocity.x,velocity.y);

    }

    public void dispose(){
    }

    public static IOManager getInstance() {
        //There's a bug with Input processor
//        if (instance == null) {
            instance = new IOManager();
//        }
        return instance;
    }

}