package com.avengers.rpgame.ai.concrete;

import com.avengers.rpgame.ai.Interfaces.Observer;
import com.avengers.rpgame.data.dataStorage.ProxyDataManager;
import com.avengers.rpgame.data.gameStatus.GameStatus;
import com.avengers.rpgame.graphics.dialog.DialogManager;
import com.avengers.rpgame.logic.entities.Item;
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
            if (itemFound.getDescription().equals("Talisman inicial")){
                access = true;
            } else if (itemFound.getDescription().equals("Talisman tierra")) {
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
        AbstractCharacter character = GameStatus.getInstance().getPlayerParty().getActivePartyMember();
        int idToDelete = 0;
        for (Item itemFound: character.getItems()){
            if (itemFound.getDescription().equals(talisman)) {
                idToDelete = itemFound.getId();
            }
        }
        character.deleteItem(idToDelete);
    }

    private void provideKey(AbstractCharacter playerCharacter) {
        String talisman = "";

        for (Item itemFound: playerCharacter.getItems()){
            if (itemFound.getDescription().equals("Talisman inicial")){
                talisman = itemFound.getDescription();
            } else if (itemFound.getDescription().equals("Talisman tierra")) {
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
            if (item.getDescription().equals("Llave tierra") && talisman.equals("Talisman inicial")){
                GameStatus.getInstance().getPlayerParty().getActivePartyMember().addNewItem(item);
                deleteTalisman(talisman);
                this.keyName = "¡Les deseo éxito en su aventura! Aquí tienes la llave del calabozo de tierra. Tráeme el talisman.";
            } else if (item.getDescription().equals("Llave agua") && talisman.equals("Talisman tierra")){
                GameStatus.getInstance().getPlayerParty().getActivePartyMember().addNewItem(item);
                deleteTalisman(talisman);
                this.keyName = "¡Excelente trabajo! Aquí tienes la llave del calabozo de agua. Tráeme el talisman.";
            } else if (item.getDescription().equals("Llave viento") && talisman.equals("Talisman agua")){
                GameStatus.getInstance().getPlayerParty().getActivePartyMember().addNewItem(item);
                deleteTalisman(talisman);
                this.keyName = "¡Gracias por la ayuda! Aquí tienes la llave del calabozo de viento. Tráeme el talisman.";
            } else if (item.getDescription().equals("Llave fuego") && talisman.equals("Talisman viento")){
                GameStatus.getInstance().getPlayerParty().getActivePartyMember().addNewItem(item);
                deleteTalisman(talisman);
                this.keyName = "¡Estupendo, solo falta un talisman! Aquí tienes la llave del calabozo de fuego. Tráeme el talisman.";
            } else if (item.getDescription().equals("Llave elemental") && talisman.equals("Talisman fuego")){
                GameStatus.getInstance().getPlayerParty().getActivePartyMember().addNewItem(item);
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
    public void actionTrigger(AbstractCharacter playerCharacter, Body currentBody, String currentMapObject) {
        dialogManager.updateSpeaker("Rey");
        if (verifyAccess(playerCharacter)) {
            provideKey(playerCharacter);
            dialogManager.updateDialog(keyName);
        } else {
            dialogManager.updateDialog("Necesitas traerme el talisman del cofre para entregarte la siguiente llave.");
        }
    }
}