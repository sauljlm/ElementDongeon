package com.avengers.rpgame.data.gameStatus;

import com.avengers.rpgame.logic.entities.Party;
import com.avengers.rpgame.logic.entities.character.components.animatedCharacter.DynamicAnimatedCharacter;
import com.avengers.rpgame.logic.entities.character.concrete.PlayableCharacter;
import com.badlogic.gdx.physics.box2d.World;

public class GameStatus {
    private static GameStatus instance;
    private Party party;
    private int saveSlot = 0;
    private String status;
    private World world;

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

    public void updateLocation(){
        party.getPartyMember1().setPosition(((DynamicAnimatedCharacter)party.getPartyMember1().getAnimatedCharacter()).getPlayer().getPosition());
    }

    public void saveOnDB() {
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
