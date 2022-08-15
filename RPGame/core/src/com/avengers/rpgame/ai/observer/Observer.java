package com.avengers.rpgame.ai.observer;

import com.avengers.rpgame.logic.entities.Party;
import com.avengers.rpgame.logic.entities.character.abstractCharacter.AbstractCharacter;
import com.badlogic.gdx.physics.box2d.Body;

public interface Observer {
    String getObserverName();
    void actionTrigger(Party playerParty, Body currentBody, String currentMapObject);
}