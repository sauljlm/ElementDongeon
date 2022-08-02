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
            message = "Bienvenido al calabozo de tierra, ve e intenta vencer al Jefe de tierra que guarda el talisman de agua";
        } else if (currentMapObject.contains("Water")) {
            message = "Bienvenido al calabozo de agua, ve e intenta vencer al Jefe de agua que guarda el talisman de viento";
        } else if (currentMapObject.contains("Wind")) {
            message = "Bienvenido al calabozo de viento, ve e intenta vencer al Jefe de viento que guarda el talisman de fuego";
        } else if (currentMapObject.contains("Fire")) {
            message = "Bienvenido al calabozo de fuego, ve e intenta vencer al Jefe de fuego";
        } else if (currentMapObject.contains("Capital")) {
            message = "Bienvenido a la ciudad central del reino";
        } else if (currentMapObject.contains("House")) {
            dialogManager.updateSpeaker("Hola");
            message = "Debes proteger el reino y vencer a los jefes de los calabozos, grandes aventuras te esperan!";
        }
        dialogManager.updateDialog(message);
    }
}