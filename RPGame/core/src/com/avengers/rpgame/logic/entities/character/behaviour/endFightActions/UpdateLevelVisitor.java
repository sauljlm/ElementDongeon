package com.avengers.rpgame.logic.entities.character.behaviour.endFightActions;

import com.avengers.rpgame.logic.entities.character.concrete.NPCharacter;
import com.avengers.rpgame.logic.entities.character.concrete.PlayableCharacter;

public class UpdateLevelVisitor implements IVisitor{
    @Override
    public void visitPlayableCharacter(PlayableCharacter playableCharacter) {
        if(playableCharacter.getExperiencePoints()>=playableCharacter.getExperiencePointsMax()){
            playableCharacter.setLevel(playableCharacter.getLevel()+1);
            playableCharacter.setExperiencePointsMax(playableCharacter.getExperienceProgression()[(int) playableCharacter.getLevel()]);
        }
    }

    @Override
    public void visitNonPlayableCharacter(NPCharacter npCharacter) {

    }
}
