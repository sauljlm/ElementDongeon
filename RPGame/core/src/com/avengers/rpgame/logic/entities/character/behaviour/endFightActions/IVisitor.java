package com.avengers.rpgame.logic.entities.character.behaviour.endFightActions;

import com.avengers.rpgame.logic.entities.character.concrete.NPCharacter;
import com.avengers.rpgame.logic.entities.character.concrete.PlayableCharacter;

public interface IVisitor {
    void visitPlayableCharacter(PlayableCharacter playableCharacter);
    void visitNonPlayableCharacter(NPCharacter npCharacter);
}
