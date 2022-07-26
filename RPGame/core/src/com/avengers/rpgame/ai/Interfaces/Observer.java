package com.avengers.rpgame.ai.Interfaces;

import com.avengers.rpgame.logic.entities.character.abstractCharacter.AbstractCharacter;
import com.badlogic.gdx.physics.box2d.Body;

public interface Observer {
    String getObserverName();
    void actionTrigger(AbstractCharacter playerCharacter, Body currentBody, String currentMapObject);
}