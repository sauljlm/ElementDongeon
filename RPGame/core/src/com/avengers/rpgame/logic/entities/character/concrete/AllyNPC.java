package com.avengers.rpgame.logic.entities.character.concrete;

import com.avengers.rpgame.logic.entities.character.abstractCharacter.AbstractCharacter;
import com.avengers.rpgame.logic.entities.character.behaviour.endFightActions.IVisitor;

////Class to contain any specific information for an Ally NPC, this might end up being a subclass of NPC, but for now lets keep it
//An ally NPC are friends that follow the Main character on his Jorney
class AllyNPC extends AbstractCharacter {
    private int experiencePoints;


    @Override
    public void accept(IVisitor visitor) {

    }
}
