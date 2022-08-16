package com.avengers.rpgame.ai.observer;

import com.avengers.rpgame.graphics.dialog.DialogManager;
import com.avengers.rpgame.logic.entities.Party;
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
    public void actionTrigger(Party playerParty, Body currentBody, String currentMapObject) {
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
        } else if (currentMapObject.contains("Store")) {
            dialogManager.updateSpeaker("Tienda");
            message = "Tienda oficial del Reino Camlot. Presiona la tecla 'T' en tu teclado para ingresar.";
        } else if (currentMapObject.contains("Walk")) {
            dialogManager.updateSpeaker("Importante");
            message = "Presiona las teclas 'AWSD' o las flechas en tu teclado para desplazarte en el mapa.";
        } else if (currentMapObject.contains("Pause")) {
            dialogManager.updateSpeaker("Importante");
            message = "Presiona la tecla 'ESC' en tu teclado para pausar o guardar la partida.";
        } else if (currentMapObject.contains("Battle")) {
            dialogManager.updateSpeaker("Importante");
            message = "Durante una batalla usa el ratón para hacer clic en el ataque, habilidad o item para usarlo.";
        } else if (currentMapObject.contains("Rotate")) {
            dialogManager.updateSpeaker("Importante");
            message = "Mantén a tus compañeros cerca, presiona la tecla 'TAB' para cambiar de personaje.";
        } else if (currentMapObject.contains("Inventory")) {
            dialogManager.updateSpeaker("Importante");
            message = "Presiona la tecla 'I' en tu teclado para ver y ocultar el inventario.";
        }
        dialogManager.updateDialog(message);
    }
}