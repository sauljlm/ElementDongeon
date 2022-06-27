package com.avengers.rpgame.game;

public class GameInformation {
    private int idCharacterClass;
    private String username;

    public GameInformation() {
    }

    public GameInformation(int idCharacterClass, String username) {
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
}
