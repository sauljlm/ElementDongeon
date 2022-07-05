package com.avengers.rpgame.game;

import com.avengers.rpgame.logic.entities.Party;

public class GameInformation {
    private static GameInformation instance;
    private int idCharacterClass;
    private String username;
    private Party playerParty;

    private GameInformation() {
    }

    private GameInformation(int idCharacterClass, String username) {
        this.idCharacterClass = idCharacterClass;
        this.username = username;
    }

    public int getIdCharacterClass() {
        return idCharacterClass;
    }

    public void setIdCharacterClass(int idCharacterClass) {
        this.idCharacterClass = idCharacterClass;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public static void setInstance(GameInformation instance) {
        GameInformation.instance = instance;
    }

    public Party getPlayerParty() {
        return playerParty;
    }

    public void setPlayerParty(Party playerParty) {
        this.playerParty = playerParty;
    }

    public void updateLocation(){
        playerParty.getPartyMember1().setPosition(playerParty.getPartyMember1().getAnimatedCharacter().getPlayer().getPosition());
    }

    //PATRON Singleton
    public static GameInformation getInstance() {
        if (instance == null) {
            instance = new GameInformation();
        }
        return instance;
    }
}
