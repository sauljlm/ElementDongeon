package com.avengers.rpgame.logic.entities.character.behaviour.endFightActions;

import com.avengers.rpgame.logic.entities.character.concrete.NPCharacter;
import com.avengers.rpgame.logic.entities.character.concrete.PlayableCharacter;

public class HealVisitor implements IVisitor{

    @Override
    public void visitPlayableCharacter(PlayableCharacter playableCharacter) {
        playableCharacter.setHealthPoints(playableCharacter.getHealthPointsMax());
        playableCharacter.setMagicPoints(playableCharacter.getHealthPointsMax());
    }

    @Override
    public void visitNonPlayableCharacter(NPCharacter npCharacter) {
        npCharacter.setHealthPoints(npCharacter.getHealthPointsMax());
        npCharacter.setMagicPoints(npCharacter.getHealthPointsMax());
    }
}
