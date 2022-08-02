package com.avengers.rpgame.ai.concrete;

import com.avengers.rpgame.ai.Interfaces.Observer;
import com.avengers.rpgame.graphics.dialog.DialogManager;
import com.avengers.rpgame.logic.entities.Item;
import com.avengers.rpgame.logic.entities.character.abstractCharacter.AbstractCharacter;
import com.badlogic.gdx.physics.box2d.Body;

public class PortalObserver implements Observer {

    private String observerName;
    private DialogManager dialogManager;

    public PortalObserver() {
        dialogManager = DialogManager.getInstance();
        observerName = "portal";
    }

    private boolean verifyAccess(AbstractCharacter playerCharacter, String currentMapObject) {
        boolean access = false;
//        System.out.println(currentMapObject);

        for (Item itemFound: playerCharacter.getItems()){
//            System.out.println(itemFound.getDescription());
            if(currentMapObject.equals("earthPortal")){
                if (itemFound.getDescription().equalsIgnoreCase("Llave tierra")){
                    access = true;
                }
            } else if(currentMapObject.equals("windPortal")){
                if (itemFound.getDescription().equalsIgnoreCase("Llave viento")){
                    access = true;
                }
            } else if(currentMapObject.equals("waterPortal")){
                if (itemFound.getDescription().equalsIgnoreCase("Llave agua")){
                    access = true;
                }
            } else if(currentMapObject.equals("firePortal")){
                if (itemFound.getDescription().equalsIgnoreCase("Llave fuego")){
                    access = true;
                }
            }
        }

        return access;
    }

    private void destroyPortal(Body currentBody) {
        if(currentBody.isActive()){
            currentBody.getWorld().destroyBody(currentBody);
            currentBody.setActive(false);
        }
    }

    @Override
    public String getObserverName() {
        return observerName;
    }

    @Override
    public void actionTrigger(AbstractCharacter playerCharacter, Body currentBody, String currentMapObject) {

        dialogManager.updateSpeaker("Portal");
        if (verifyAccess(playerCharacter, currentMapObject)) {
            destroyPortal(currentBody);
            dialogManager.updateDialog("portal abierto");
        } else {
            dialogManager.updateDialog("El portal no se puede abrir, debes tener una llave");
        }
    }
}