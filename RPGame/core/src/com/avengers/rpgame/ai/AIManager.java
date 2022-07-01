package com.avengers.rpgame.ai;

import com.avengers.rpgame.logic.entities.character.abstractCharacter.AbstractCharacter;

public class AIManager {
    private AlliesMovement allysMovement;

    public AIManager() {
        allysMovement = new AlliesMovement();
    }


    public void moveAllies(AbstractCharacter playerCharacter, AbstractCharacter ally1Character, float partyDistance){
        allysMovement.setAllyPosition(playerCharacter, ally1Character, partyDistance );
    }
}
