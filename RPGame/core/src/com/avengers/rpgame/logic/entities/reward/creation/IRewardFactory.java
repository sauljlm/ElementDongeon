package com.avengers.rpgame.logic.entities.reward.creation;

import com.avengers.rpgame.logic.entities.character.abstractCharacter.AbstractCharacter;
import com.avengers.rpgame.logic.entities.reward.AReward;

public interface IRewardFactory {
    public AReward createReward(AbstractCharacter monster);
}
