package com.avengers.rpgame.ai.concrete;

import com.avengers.rpgame.ai.Interfaces.Observer;
import com.avengers.rpgame.graphics.dialog.DialogManager;
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
    public void actionTrigger(AbstractCharacter playerCharacter, Body currentBody, String currentMapObject) {
        dialogManager.updateSpeaker("Aldeano");
        dialogManager.updateDialog("Deberias de hablar con el Rey!");
    }
}