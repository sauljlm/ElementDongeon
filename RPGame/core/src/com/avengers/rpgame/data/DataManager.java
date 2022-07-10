package com.avengers.rpgame.data;

import com.avengers.rpgame.logic.entities.character.abstractCharacter.AbstractCharacter;
import com.avengers.rpgame.logic.entities.character.components.AnimatedCharacter;
import com.avengers.rpgame.utils.Resources;

public class DataManager {

    public static void saveFile(int fileId, AbstractCharacter abstractCharacter){
        SavedFile savedFile = SavedFile.getInstance();
        savedFile.setIdFile(fileId);
        savedFile.setCharacterInfo(abstractCharacter);
//

        DatabaseAccess.saveFile(1, savedFile);
    }

    public static SavedFile loadFile(int id){
        SavedFile savedFile = DatabaseAccess.loadFile(id);
        return savedFile;
    }

    //TODO: Agregar metodos para manejo de obtencion de datos por JSON
}
