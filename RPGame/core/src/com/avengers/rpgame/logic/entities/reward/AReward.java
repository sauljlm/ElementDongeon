package com.avengers.rpgame.logic.entities.reward;

public class AReward {
    private int experiencePoints;
    private int coins;
    private String status;

    public AReward() {
    }

    public AReward(int experiencePoints, int coins, String status) {
        this.experiencePoints = experiencePoints;
        this.coins = coins;
        this.status = status;
    }

    public int getExperiencePoints() {
        return experiencePoints;
    }

    public void setExperiencePoints(int experiencePoints) {
        this.experiencePoints = experiencePoints;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
