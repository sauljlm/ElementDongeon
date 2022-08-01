package com.avengers.rpgame.data.gameStatus;

import com.avengers.rpgame.logic.entities.Party;
import com.avengers.rpgame.logic.entities.character.abstractCharacter.AbstractCharacter;
import com.avengers.rpgame.logic.entities.character.components.animatedCharacter.DynamicAnimatedCharacter;
import com.avengers.rpgame.logic.entities.character.concrete.PlayableCharacter;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import java.util.ArrayList;
import java.util.HashMap;

public class GameStatus {
    private static GameStatus instance;
    private Party party;
    private int saveSlot = 0;
    private String status;
    private World world;

    private ArrayList<AbstractCharacter> enemies = new ArrayList<>();
    private HashMap<String, Integer> enemiesHealth = new HashMap<>();

    private GameStatus() {
        party = new Party();
        party.setPartyMember1(new PlayableCharacter()); //Just to avoid null
        party.setPartyMember2(new PlayableCharacter()); //Just to avoid null
        party.setPartyMember3(new PlayableCharacter()); //Just to avoid null
        status = "newGame"; //default value to let all other components know this is a new game, otherwise game loader needs to set this to other state
    }

    private GameStatus(Party party) {
        this.party = party;
    }

    public Party getParty() {
        return party;
    }

    public void setParty(Party party) {
        this.party = party;
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

    public void updateLocation(){
        party.getActivePartyMember().setPosition(((DynamicAnimatedCharacter)party.getActivePartyMember().getAnimatedCharacter()).getPlayer().getPosition());
    }

    public void saveOnDB() {
        updateLocation();
        BDGameStatusDAO bdGameStatusDAO = new BDGameStatusDAO();
        bdGameStatusDAO.saveGameStatus(instance);
    }
    public void loadFromDB(int saveSlot) {
        BDGameStatusDAO bdGameStatusDAO = new BDGameStatusDAO();
        bdGameStatusDAO.loadGameStatus(saveSlot);
    }

    public static GameStatus getInstance() {
        if (instance == null) {
            instance = new GameStatus();
        }
        return instance;
    }
}
