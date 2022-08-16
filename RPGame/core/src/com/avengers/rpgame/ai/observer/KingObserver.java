package com.avengers.rpgame.ai.observer;

import com.avengers.rpgame.data.dataStorage.ProxyDataManager;
import com.avengers.rpgame.data.gameStatus.GameStatus;
import com.avengers.rpgame.graphics.dialog.DialogManager;
import com.avengers.rpgame.logic.entities.Item;
import com.avengers.rpgame.logic.entities.Party;
import com.avengers.rpgame.logic.entities.character.abstractCharacter.AbstractCharacter;
import com.badlogic.gdx.physics.box2d.Body;

import java.util.ArrayList;


public class KingObserver  implements Observer {

    private String observerName;
    private String keyName;
    private ArrayList<Item> dataItems;
    private ProxyDataManager proxyDataManager;
    private DialogManager dialogManager;
    public KingObserver() {
        dialogManager = DialogManager.getInstance();
        proxyDataManager = new ProxyDataManager();
        dataItems = proxyDataManager.getSpecialItemsList();
        observerName="king";
    }

    private boolean verifyAccess(AbstractCharacter playerCharacter) {
        boolean access = false;

        for (Item itemFound: playerCharacter.getItems()){
            if (itemFound.getDescription().equals("Talismán inicial")){
                access = true;
            } else if (itemFound.getDescription().equals("Talismán tierra")) {
                access = true;
            } else if (itemFound.getDescription().equals("Talismán agua")) {
                access = true;
            } else if (itemFound.getDescription().equals("Talismán viento")) {
                access = true;
            } else if (itemFound.getDescription().equals("Talismán fuego")) {
                access = true;
            }
        }

        return access;
    }

    private void deleteTalisman(String talisman) {
        AbstractCharacter character1 = GameStatus.getInstance().getPlayerParty().getPartyMember(1);
        AbstractCharacter character2 = GameStatus.getInstance().getPlayerParty().getPartyMember(2);
        AbstractCharacter character3 = GameStatus.getInstance().getPlayerParty().getPartyMember(3);

        int idToDelete = 0;
        for (Item itemFound: character1.getItems()){
            if (itemFound.getDescription().equals(talisman)) {
                idToDelete = itemFound.getId();
            }
        }
        character1.deleteItem(idToDelete);

        for (Item itemFound: character2.getItems()){
            if (itemFound.getDescription().equals(talisman)) {
                idToDelete = itemFound.getId();
            }
        }
        character2.deleteItem(idToDelete);

        for (Item itemFound: character3.getItems()){
            if (itemFound.getDescription().equals(talisman)) {
                idToDelete = itemFound.getId();
            }
        }
        character3.deleteItem(idToDelete);
    }

    private void provideKey(Party playerParty) {
        String talisman = "";

        for (Item itemFound: playerParty.getActivePartyMember().getItems()){
            if (itemFound.getDescription().equals("Talismán inicial")){
                talisman = itemFound.getDescription();
            } else if (itemFound.getDescription().equals("Talismán tierra")) {
                talisman = itemFound.getDescription();
            } else if (itemFound.getDescription().equals("Talismán agua")) {
                talisman = itemFound.getDescription();
            } else if (itemFound.getDescription().equals("Talismán viento")) {
                talisman = itemFound.getDescription();
            } else if (itemFound.getDescription().equals("Talismán fuego")) {
                talisman = itemFound.getDescription();
            }
        }
        for (Item item: dataItems){
            if (item.getDescription().equals("Llave tierra") && talisman.equals("Talismán inicial")){
                GameStatus.getInstance().getPlayerParty().getPartyMember(1).addNewItem(item);
                GameStatus.getInstance().getPlayerParty().getPartyMember(2).addNewItem(item);
                GameStatus.getInstance().getPlayerParty().getPartyMember(3).addNewItem(item);
                deleteTalisman(talisman);
                this.keyName = "¡Les deseo éxito en su aventura! Aquí tienes la llave del calabozo de tierra. Tráeme el talismán.";
            } else if (item.getDescription().equals("Llave agua") && talisman.equals("Talismán tierra")){
                GameStatus.getInstance().getPlayerParty().getPartyMember(1).addNewItem(item);
                GameStatus.getInstance().getPlayerParty().getPartyMember(2).addNewItem(item);
                GameStatus.getInstance().getPlayerParty().getPartyMember(3).addNewItem(item);
                deleteTalisman(talisman);
                this.keyName = "¡Excelente trabajo! Aquí tienes la llave del calabozo de agua. Tráeme el talismán.";
            } else if (item.getDescription().equals("Llave viento") && talisman.equals("Talismán agua")){
                GameStatus.getInstance().getPlayerParty().getPartyMember(1).addNewItem(item);
                GameStatus.getInstance().getPlayerParty().getPartyMember(2).addNewItem(item);
                GameStatus.getInstance().getPlayerParty().getPartyMember(3).addNewItem(item);
                deleteTalisman(talisman);
                this.keyName = "¡Gracias por la ayuda! Aquí tienes la llave del calabozo de viento. Tráeme el talismán.";
            } else if (item.getDescription().equals("Llave fuego") && talisman.equals("Talismán viento")){
                GameStatus.getInstance().getPlayerParty().getPartyMember(1).addNewItem(item);
                GameStatus.getInstance().getPlayerParty().getPartyMember(2).addNewItem(item);
                GameStatus.getInstance().getPlayerParty().getPartyMember(3).addNewItem(item);
                deleteTalisman(talisman);
                this.keyName = "¡Estupendo, solo falta un talismán! Aquí tienes la llave del calabozo de fuego. Tráeme el talismán.";
            } else if (item.getDescription().equals("Llave elemental") && talisman.equals("Talismán fuego")){
                GameStatus.getInstance().getPlayerParty().getPartyMember(1).addNewItem(item);
                GameStatus.getInstance().getPlayerParty().getPartyMember(2).addNewItem(item);
                GameStatus.getInstance().getPlayerParty().getPartyMember(3).addNewItem(item);
                deleteTalisman(talisman);
                this.keyName = "Ya tenemos los cuatro talismanes. ¡El reino está a salvo! Presiona la tecla 'F' para finalizar la partida.";
            }
        }

    }

    @Override
    public String getObserverName() {
        return observerName;
    }

    @Override
    public void actionTrigger(Party playerParty, Body currentBody, String currentMapObject) {
        dialogManager.updateSpeaker("Rey");
        provideKey(playerParty);
        dialogManager.updateDialog(keyName);
    }
}