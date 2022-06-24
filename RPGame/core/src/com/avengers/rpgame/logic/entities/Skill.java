package com.avengers.rpgame.logic.entities;

public abstract class Skill {
    private String name;
    private String description;
    private int unlockLevel;
    private int mPCost; //MP cost of using this skill
    private int strengthEffect; //Effects can be positives for Buff or negative for Nerf/Debuff
    private int speedEffect;
    private int magicEffect;
    private int resistanceEffect;
    private int luckEffect;
}
