package com.avengers.rpgame.data;

import com.avengers.rpgame.game.GameInformation;
import com.avengers.rpgame.logic.entities.Party;
import com.avengers.rpgame.logic.entities.character.abstractCharacter.AbstractCharacter;

import java.util.ArrayList;

public class SavedFile {


    private static SavedFile instance;
    private AbstractCharacter characterInfo;
    private int idFile;

    private int idCharacterClass;

    private Party playerParty;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private String username;



    public int getIdCharacterClass() {
        return idCharacterClass;
    }

    public void setIdCharacterClass(int idCharacterClass) {
        this.idCharacterClass = idCharacterClass;
    }

    public Party getPlayerParty() {
        return playerParty;
    }

    private SavedFile(){}
    public void setPlayerParty(Party playerParty) {
        this.playerParty = playerParty;
    }



    public AbstractCharacter getCharacterInfo() {
        return characterInfo;
    }

    public void setCharacterInfo(AbstractCharacter characterInfo) {
        this.characterInfo = characterInfo;
    }

    public int getIdFile() {
        return idFile;
    }

    public void setIdFile(int idFile) {
        this.idFile = idFile;
    }

    public void updateLocation(){
        playerParty.getPartyMember1().setPosition(playerParty.getPartyMember1().getAnimatedCharacter().getPlayer().getPosition());
    }

    public static SavedFile getInstance() {
        if (instance == null) {
            instance = new SavedFile();
        }
        return instance;
    }
}
