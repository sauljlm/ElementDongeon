package com.avengers.rpgame.ai.observer;

import com.avengers.rpgame.data.dataStorage.ProxyDataManager;
import com.avengers.rpgame.data.gameStatus.GameStatus;
import com.avengers.rpgame.graphics.dialog.DialogManager;
import com.avengers.rpgame.logic.entities.Item;
import com.avengers.rpgame.logic.entities.Party;
import com.avengers.rpgame.logic.entities.character.abstractCharacter.AbstractCharacter;
import com.badlogic.gdx.physics.box2d.Body;

import java.util.ArrayList;

public class ChestObserver implements Observer {
    private GameStatus gameStatus;
    private String observerName;
    private ArrayList<Item> dataItems;
    private ProxyDataManager proxyDataManager;
    private DialogManager dialogManager;
    public ChestObserver() {
        gameStatus = GameStatus.getInstance();
        dialogManager = DialogManager.getInstance();
        proxyDataManager = new ProxyDataManager();
        dataItems = proxyDataManager.getSpecialItemsList();
        observerName = "chest";
    }

    //Verifies the corresponding boss monster of the dungeon is defeated, returns true if he is defeated
    private boolean verifyAccess(AbstractCharacter playerCharacter, String currentMapObject) {
        int bossHP = 100;
        String bossName = getBossName();
        for (AbstractCharacter enemy: gameStatus.getEnemies()) {
            if(enemy.getName().equals(bossName)){
                return enemy.getHealthPoints() <= 0;
            }
        }
        return false;
    }

    //Get the bossName for the current Dungeon
    private String getBossName(){
        if(gameStatus.getCurrentLocation().contains("earth")){
            return  "EarthBossMonster";
        }
        if(gameStatus.getCurrentLocation().contains("wind")){
            return "WindBossMonster";
        }
        if(gameStatus.getCurrentLocation().contains("water")){
            return "WaterBossMonster";
        }
        if(gameStatus.getCurrentLocation().contains("fire")){
            return "FireBossMonster";
        }
        return "";
    }

    private void provideTalisman(Party playerParty, String currentMapObject) {
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
            if (item.getDescription().equals("Talismán tierra") && currentMapObject.contains("earth") && talisman.equals("")) {
                GameStatus.getInstance().getPlayerParty().getPartyMember(1).addNewItem(item);
                GameStatus.getInstance().getPlayerParty().getPartyMember(2).addNewItem(item);
                GameStatus.getInstance().getPlayerParty().getPartyMember(3).addNewItem(item);

            } else if (item.getDescription().equals("Talismán agua") && currentMapObject.contains("water") && talisman.equals("")) {
                GameStatus.getInstance().getPlayerParty().getPartyMember(1).addNewItem(item);
                GameStatus.getInstance().getPlayerParty().getPartyMember(2).addNewItem(item);
                GameStatus.getInstance().getPlayerParty().getPartyMember(3).addNewItem(item);

            } else if (item.getDescription().equals("Talismán viento") && currentMapObject.contains("wind") && talisman.equals("")) {
                GameStatus.getInstance().getPlayerParty().getPartyMember(1).addNewItem(item);
                GameStatus.getInstance().getPlayerParty().getPartyMember(2).addNewItem(item);
                GameStatus.getInstance().getPlayerParty().getPartyMember(3).addNewItem(item);

            } else if (item.getDescription().equals("Talismán fuego") && currentMapObject.contains("fire") && talisman.equals("")) {
                GameStatus.getInstance().getPlayerParty().getPartyMember(1).addNewItem(item);
                GameStatus.getInstance().getPlayerParty().getPartyMember(2).addNewItem(item);
                GameStatus.getInstance().getPlayerParty().getPartyMember(3).addNewItem(item);

            }
        }
    }

    @Override
    public String getObserverName() {
        return observerName;
    }

    @Override
    public void actionTrigger(Party playerParty, Body currentBody, String currentMapObject) {
        dialogManager.updateSpeaker("Cofre");
        if (verifyAccess(playerParty.getActivePartyMember(), currentMapObject)) {
            provideTalisman(playerParty, currentMapObject);
            dialogManager.updateDialog("Has obtenido un talismán, ve donde el Rey.");
        } else {
            dialogManager.updateDialog("Debes vencer al jefe para abrir el cofre.");
        }
    }
}