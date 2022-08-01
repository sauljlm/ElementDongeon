package com.avengers.rpgame.ai.concrete;

import com.avengers.rpgame.RPGame;
import com.avengers.rpgame.ai.Interfaces.Observer;
import com.avengers.rpgame.data.dataStorage.ProxyDataManager;
import com.avengers.rpgame.data.gameStatus.GameStatus;
import com.avengers.rpgame.game.GameConfig;
import com.avengers.rpgame.graphics.dialog.Dialog;
import com.avengers.rpgame.logic.entities.Item;
import com.avengers.rpgame.logic.entities.character.abstractCharacter.AbstractCharacter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.physics.box2d.Body;

import java.util.ArrayList;

public class ChestObserver implements Observer {

    private String observerName;
    private ArrayList<Item> dataItems;
    private ProxyDataManager proxyDataManager = new ProxyDataManager();
    public ChestObserver(String pN) {
        observerName=pN;
    }

    private boolean verifyAccess(AbstractCharacter playerCharacter, String currentMapObject) {
        boolean access = true;

        return access;
    }

    private void provideTalisman(AbstractCharacter playerCharacter, String currentMapObject) {
        dataItems = proxyDataManager.getSpecialItemsList();

        for (Item item: dataItems){
            //if (item.getUnlockLevel() <= playerCharacter.getLevel()) {
                if (item.getDescription().equals("Talisman agua") && currentMapObject.contains("earth")) {
                    GameStatus.getInstance().getParty().getActivePartyMember().addNewItem(item);
                } else if (item.getDescription().equals("Talisman viento") && currentMapObject.contains("water")) {
                    GameStatus.getInstance().getParty().getActivePartyMember().addNewItem(item);
                } else if (item.getDescription().equals("Talisman fuego") && currentMapObject.contains("wind")) {
                    GameStatus.getInstance().getParty().getActivePartyMember().addNewItem(item);
                } else if (item.getDescription().equals("Talisman fuego") && currentMapObject.contains("fire")) {
                    GameStatus.getInstance().getParty().getActivePartyMember().addNewItem(item);
                }
            //}
        }
    }

    @Override
    public String getObserverName() {
        return observerName;
    }

    @Override
    public void actionTrigger(AbstractCharacter playerCharacter, Body currentBody, String currentMapObject) {
        Dialog dialog = new Dialog();
        dialog.updateSeaker("Cofre");
        if (verifyAccess(playerCharacter, currentMapObject)) {
            provideTalisman(playerCharacter, currentMapObject);
            dialog.updateDialog("Has obtenido un talisman, ve donde el rey para cambiarlo por una llave");
        } else {
            dialog.updateDialog("Debes vencer al jefe para abrir el cofre");
        }
    }
}