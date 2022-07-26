package com.avengers.rpgame.ai.concrete;

import com.avengers.rpgame.ai.Interfaces.Observer;
import com.avengers.rpgame.data.dataStorage.ProxyDataManager;
import com.avengers.rpgame.data.gameStatus.GameStatus;
import com.avengers.rpgame.graphics.dialog.Dialog;
import com.avengers.rpgame.logic.entities.Item;
import com.avengers.rpgame.logic.entities.character.abstractCharacter.AbstractCharacter;
import com.badlogic.gdx.physics.box2d.Body;

import javax.xml.crypto.Data;
import java.util.ArrayList;


public class KingObserver  implements Observer {

    private String observerName;
    private ArrayList<Item> dataItems;
    private ProxyDataManager proxyDataManager = new ProxyDataManager();
    public KingObserver(String pN) {
        observerName=pN;
    }

    private boolean verifyAccess(AbstractCharacter playerCharacter) {
        boolean access = false;

        for (Item itemFound: playerCharacter.getItems()){
            if (itemFound.getDescription().equals("Talisman tierra")){
                access = true;
            } else if (itemFound.getDescription().equals("Talisman agua")) {
                access = true;
            } else if (itemFound.getDescription().equals("Talisman viento")) {
                access = true;
            } else if (itemFound.getDescription().equals("Talisman fuego")) {
                access = true;
            }
        }

        if (playerCharacter.getLevel() >= 1) {
            access = true;
        }

        return access;
    }

    private void deleteTalisman(String talisman) {
        AbstractCharacter character = GameStatus.getInstance().getParty().getActivePartyMember();
        int idToDelete = 0;
        for (Item itemFound: character.getItems()){
            if (itemFound.getDescription().equals(talisman)) {
                idToDelete = itemFound.getId();
            }
        }
        character.deleteItem(idToDelete);
    }

    private void provideKey(AbstractCharacter playerCharacter) {
        dataItems = proxyDataManager.getSpecialItemsList();

        String talisman = "";

        for (Item itemFound: playerCharacter.getItems()){
            if (itemFound.getDescription().equals("Talisman tierra")){
                talisman = itemFound.getDescription();
            } else if (itemFound.getDescription().equals("Talisman agua")) {
                talisman = itemFound.getDescription();
            } else if (itemFound.getDescription().equals("Talisman viento")) {
                talisman = itemFound.getDescription();
            } else if (itemFound.getDescription().equals("Talisman fuego")) {
                talisman = itemFound.getDescription();
            }
        }
        for (Item item: dataItems){
            if (item.getDescription().equals("Llave tierra") && talisman.equals("Talisman tierra")){
                GameStatus.getInstance().getParty().getActivePartyMember().addNewItem(item);
                deleteTalisman(talisman);
            } else if (item.getDescription().equals("Llave agua") && talisman.equals("Talisman agua")){
                GameStatus.getInstance().getParty().getActivePartyMember().addNewItem(item);
                deleteTalisman(talisman);
            } else if (item.getDescription().equals("Llave viento") && talisman.equals("Talisman viento")){
                GameStatus.getInstance().getParty().getActivePartyMember().addNewItem(item);
                deleteTalisman(talisman);
            } else if (item.getDescription().equals("Llave fuego") && talisman.equals("Talisman fuego")){
                GameStatus.getInstance().getParty().getActivePartyMember().addNewItem(item);
                deleteTalisman(talisman);
            }
        }
    }

    @Override
    public String getObserverName() {
        return observerName;
    }

    @Override
    public void actionTrigger(AbstractCharacter playerCharacter, Body currentBody, String currentMapObject) {
        Dialog.updateSeaker("Rey");
        if (verifyAccess(playerCharacter)) {
            provideKey(playerCharacter);
            Dialog.updateDialog("Aqui tienes tus llaves");
        } else {
            Dialog.updateDialog("Necesitas un talisman para entregarte las llaves");
        }
    }
}