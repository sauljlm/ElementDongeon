package com.avengers.rpgame.data.gameStatus;

import com.avengers.rpgame.game.GameConfig;
import com.avengers.rpgame.logic.entities.Party;
import com.avengers.rpgame.logic.entities.character.abstractCharacter.AbstractCharacter;
import com.avengers.rpgame.logic.entities.character.components.animatedCharacter.DynamicAnimatedCharacter;
import com.avengers.rpgame.logic.entities.character.concrete.PlayableCharacter;
import com.avengers.rpgame.utils.StartingPositionsReducer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import java.util.ArrayList;
import java.util.HashMap;

public class GameStatus {
    private static GameStatus instance;
    private Party playerParty;
    private Party enemyParty;
    private int saveSlot = 0;
    private String status;
    private World world;
    private String currentLocation;
    private BDGameStatusDAO bdGameStatusDAO;
    private ArrayList<AbstractCharacter> enemies;
    private HashMap<String, Integer> enemiesHealth;
    private String damageStatus;

    private Vector2 cameraPosition;

    private GameStatus() {
        bdGameStatusDAO = new BDGameStatusDAO();
        playerParty = new Party();
        playerParty.setPartyMember(1,new PlayableCharacter()); //Just to avoid null
        playerParty.setPartyMember(2,new PlayableCharacter()); //Just to avoid null
        playerParty.setPartyMember(3,new PlayableCharacter()); //Just to avoid null
        enemyParty = new Party();
        enemies = new ArrayList<>();
        enemiesHealth = new HashMap<>();
        enemyParty.setPartyMember(1,new PlayableCharacter()); //Just to avoid null
        enemyParty.setPartyMember(2,new PlayableCharacter()); //Just to avoid null
        enemyParty.setPartyMember(3,new PlayableCharacter()); //Just to avoid null
        status = "newGame"; //default value to let all other components know this is a new game, otherwise game loader needs to set this to other state
        damageStatus = "ok";
        cameraPosition = new Vector2(338,44);
    }

    private GameStatus(Party party) {
        this.playerParty = party;
    }

    public Party getPlayerParty() {
        return playerParty;
    }

    public void setPlayerParty(Party playerParty) {
        this.playerParty = playerParty;
    }

    public int getSaveSlot() {
        return saveSlot;
    }

    public void setSaveSlot(int saveSlot) {
        this.saveSlot = saveSlot;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public Party getEnemyParty() {
        return enemyParty;
    }

    public void setEnemyParty(Party enemyParty) {
        this.enemyParty = enemyParty;
    }

    public ArrayList<AbstractCharacter> getEnemies() {
        return enemies;
    }

    public void setEnemies(ArrayList<AbstractCharacter> enemies) {
        this.enemies = enemies;
    }

    public HashMap<String, Integer> getEnemiesHealth() {
        return enemiesHealth;
    }

    public void setEnemiesHealth(HashMap<String, Integer> enemiesHealth) {
        this.enemiesHealth = enemiesHealth;
    }

    public String getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
    }

    public String getDamageStatus() {
        return damageStatus;
    }

    public void setDamageStatus(String damageStatus) {
        this.damageStatus = damageStatus;
    }

    public void updateLocation(){
        playerParty.getPartyMember(1).setPosition(((DynamicAnimatedCharacter) playerParty.getActivePartyMember().getAnimatedCharacter()).getPlayer().getPosition());
        playerParty.getPartyMember(2).setPosition(((DynamicAnimatedCharacter) playerParty.getActivePartyMember().getAnimatedCharacter()).getPlayer().getPosition());
        playerParty.getPartyMember(3).setPosition(((DynamicAnimatedCharacter) playerParty.getActivePartyMember().getAnimatedCharacter()).getPlayer().getPosition());
    }

    public Vector2 getCameraPosition() {
        return cameraPosition;
    }

    public void setCameraPosition(Vector2 cameraPosition) {
        this.cameraPosition = cameraPosition;
    }

    public void saveOnDB() {
        updateLocation();
        bdGameStatusDAO.saveGameStatus();
    }
    public void loadFromDB(int saveSlot) {
        bdGameStatusDAO.loadGameStatus(saveSlot);
    }

    public static GameStatus getInstance() {
        if (instance == null) {
            instance = new GameStatus();
        }
        return instance;
    }
}
