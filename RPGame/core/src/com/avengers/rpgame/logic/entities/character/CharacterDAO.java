package com.avengers.rpgame.logic.entities.character;


import com.avengers.rpgame.data.SavedFile;
import com.avengers.rpgame.logic.entities.character.abstractCharacter.AbstractCharacter;


public interface CharacterDAO {
    public int save(int id, AbstractCharacter character);
    public SavedFile get(int id);
}
