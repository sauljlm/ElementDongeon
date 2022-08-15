package com.avengers.rpgame.ai.observer;

import com.avengers.rpgame.graphics.dialog.DialogManager;
import com.avengers.rpgame.logic.entities.Party;
import com.avengers.rpgame.logic.entities.character.abstractCharacter.AbstractCharacter;
import com.badlogic.gdx.physics.box2d.Body;

public class RandomNPCObserver implements Observer {
    private String observerName;
    private DialogManager dialogManager;

    public RandomNPCObserver() {
        dialogManager = DialogManager.getInstance();
        observerName="randomNPC";
    }

    @Override
    public String getObserverName() {
        return observerName;
    }

    @Override
    public void actionTrigger(Party playerParty, Body currentBody, String currentMapObject) {
        dialogManager.updateSpeaker("Aldeano");
        dialogManager.updateDialog("Deberías de hablar con el Rey.");
    }
}