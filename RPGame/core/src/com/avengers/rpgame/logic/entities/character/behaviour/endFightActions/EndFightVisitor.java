package com.avengers.rpgame.logic.entities.character.behaviour.endFightActions;

import com.avengers.rpgame.logic.entities.character.concrete.NPCharacter;
import com.avengers.rpgame.logic.entities.character.concrete.PlayableCharacter;
import com.avengers.rpgame.logic.entities.reward.AReward;

public class EndFightVisitor implements IVisitor{
    private AReward reward;

    public EndFightVisitor() {
    }

    public EndFightVisitor(AReward reward) {
        this.reward = reward;
    }

    public AReward getReward() {
        return reward;
    }

    public void setReward(AReward reward) {
        this.reward = reward;
    }

    @Override
    public void visitPlayableCharacter(PlayableCharacter playableCharacter) {
        playableCharacter.setCoins(playableCharacter.getCoins()+reward.getCoins());
        playableCharacter.setExperiencePoints(playableCharacter.getExperiencePoints()+reward.getExperiencePoints());
    }

    @Override
    public void visitNonPlayableCharacter(NPCharacter npCharacter) {
        npCharacter.setStatus("defeated");
    }

}
