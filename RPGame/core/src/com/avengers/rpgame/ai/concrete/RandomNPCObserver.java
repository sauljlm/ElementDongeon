package com.avengers.rpgame.ai.concrete;

import com.avengers.rpgame.ai.Interfaces.Observer;
import com.avengers.rpgame.graphics.dialog.Dialog;
import com.avengers.rpgame.logic.entities.character.abstractCharacter.AbstractCharacter;
import com.badlogic.gdx.physics.box2d.Body;

public class RandomNPCObserver implements Observer {

    private String observerName;

    public RandomNPCObserver(String pN) {
        observerName=pN;
    }

    @Override
    public String getObserverName() {
        return observerName;
    }

    @Override
    public void actionTrigger(AbstractCharacter playerCharacter, Body currentBody, String currentMapObject) {
        Dialog.updateSeaker("Aldeano");
        Dialog.updateDialog("Deberias de hablar con el Rey!");
    }
}