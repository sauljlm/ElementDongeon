package com.avengers.rpgame.ai.concrete;

import com.avengers.rpgame.ai.Interfaces.Observer;
import com.avengers.rpgame.graphics.dialog.DialogManager;
import com.avengers.rpgame.logic.entities.character.abstractCharacter.AbstractCharacter;
import com.badlogic.gdx.physics.box2d.Body;

public class SignObserver implements Observer {
    private String observerName;
    private String message;
    private DialogManager dialogManager;
    public SignObserver() {
        dialogManager = DialogManager.getInstance();
        observerName="sign";
        message = "";
    }

    @Override
    public String getObserverName() {
        return observerName;
    }

    @Override
    public void actionTrigger(AbstractCharacter playerCharacter, Body currentBody, String currentMapObject) {
        dialogManager.updateSpeaker("Calabozo");
        if (currentMapObject.contains("Earth")) {
            message = "Bienvenido al calabozo de tierra, vence al Jefe de Tierra y recupera el talisman de su cofre.";
        } else if (currentMapObject.contains("Water")) {
            message = "Bienvenido al calabozo de agua, vence al Jefe de Agua y recupera el talisman de su cofre.";
        } else if (currentMapObject.contains("Wind")) {
            message = "Bienvenido al calabozo de viento, vence al Jefe de Viento y recupera el talisman de su cofre.";
        } else if (currentMapObject.contains("Fire")) {
            message = "Bienvenido al calabozo de fuego, vence al Jefe de Fuego y recupera el talisman de su cofre.";
        } else if (currentMapObject.contains("Capital")) {
            message = "Bienvenido a la Ciudad Central del reino Camlot.";
        } else if (currentMapObject.contains("House")) {
            dialogManager.updateSpeaker("Hogar");
            message = "Objetivo: Recuperar los talismanes y llevarlos donde el Rey. ¡Grandes aventuras te esperan!";
        }
        dialogManager.updateDialog(message);
    }
}