package com.avengers.rpgame.logic.entities.character.concrete;

import com.avengers.rpgame.logic.entities.AI;
import com.avengers.rpgame.logic.entities.character.abstractCharacter.AbstractCharacter;
import com.avengers.rpgame.logic.entities.character.behaviour.endFightActions.IVisitor;

//Class to contain any specific information for a Non Playable Character
public class NPCharacter extends AbstractCharacter {
    private AI artifitialIntelligence;
    private String status;

    public NPCharacter() {
        this.artifitialIntelligence = null;
        this.status = "ready";
    }

    public AI getArtifitialIntelligence() {
        return artifitialIntelligence;
    }

    public void setArtifitialIntelligence(AI artifitialIntelligence) {
        this.artifitialIntelligence = artifitialIntelligence;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public void accept(IVisitor visitor) {
        visitor.visitNonPlayableCharacter(this);
    }
}
