package com.avengers.rpgame.logic.entities.character;

import com.avengers.rpgame.data.DataManager;
import com.avengers.rpgame.data.DatabaseAccess;
import com.avengers.rpgame.data.SavedFile;
import com.avengers.rpgame.logic.entities.character.CharacterDAO;
import com.avengers.rpgame.logic.entities.character.abstractCharacter.AbstractCharacter;
import com.avengers.rpgame.utils.Resources;

public class CharacterDAOImplementation implements CharacterDAO {
    private static CharacterDAOImplementation instance;
    @Override
    public int save(int id, AbstractCharacter character) {

        DataManager.saveFile(id, character);
        return 0;
    }
    @Override
    public SavedFile get(int id) {
        SavedFile savedFile = DatabaseAccess.loadFile(id);
        return savedFile;
    }

    public static CharacterDAOImplementation getInstance() {
        if (instance == null) {
            instance = new CharacterDAOImplementation();
        }
        return instance;
    }


}
