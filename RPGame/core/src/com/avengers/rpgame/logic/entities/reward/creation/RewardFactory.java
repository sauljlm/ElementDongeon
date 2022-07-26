package com.avengers.rpgame.logic.entities.reward.creation;

import com.avengers.rpgame.logic.entities.character.abstractCharacter.AbstractCharacter;
import com.avengers.rpgame.logic.entities.character.concrete.NPCharacter;
import com.avengers.rpgame.logic.entities.reward.AReward;
import com.avengers.rpgame.logic.entities.reward.BattleReward;

public class RewardFactory implements IRewardFactory{
    @Override
    public AReward createReward(AbstractCharacter monster) {
        AReward reward = new BattleReward(0,0,"null");
        if(true) {
//        if(monster instanceof NPCharacter) {
            String monsterName = monster.getCharacterClass().getName();
            //Earth
            if (monsterName.equals("EarthSkeleton")) {
                reward.setCoins(100);
                reward.setExperiencePoints(2);
            }
            if (monsterName.equals("EarthChief")) {
                reward.setCoins(1000);
                reward.setExperiencePoints(40);
            }
            //Water
            if (monsterName.equals("WaterSkeleton")) {
                reward.setCoins(100);
                reward.setExperiencePoints(100);
            }
            if (monsterName.equals("WaterChief")) {
                reward.setCoins(1000);
                reward.setExperiencePoints(1700);
            }
            //Wind
            if (monsterName.equals("WindSkeleton")) {
                reward.setCoins(100);
                reward.setExperiencePoints(1500);
            }
            if (monsterName.equals("WindChief")) {
                reward.setCoins(1000);
                reward.setExperiencePoints(30500);
            }
            //Fire
            if (monsterName.equals("FireSkeleton")) {
                reward.setCoins(100);
                reward.setExperiencePoints(30000);
            }
            if (monsterName.equals("FireChief")) {
                reward.setCoins(1000);
                reward.setExperiencePoints(750000);
            }
        }
        return reward;
    }
}
