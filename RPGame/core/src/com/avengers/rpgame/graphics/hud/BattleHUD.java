package com.avengers.rpgame.graphics.hud;

import com.avengers.rpgame.game.GameConfig;
import com.avengers.rpgame.graphics.hud.elements.Heart;
import com.avengers.rpgame.graphics.hud.elements.MP;
import com.avengers.rpgame.graphics.hud.elements.UserLevel;
import com.avengers.rpgame.graphics.hud.elements.Weapon;
import com.avengers.rpgame.graphics.text.FontFactory;
import com.avengers.rpgame.logic.entities.Party;
import com.avengers.rpgame.logic.entities.character.concrete.PlayableCharacter;
import com.avengers.rpgame.utils.Resources;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class BattleHUD {
    private Array<Heart> heartsHB;
    private MP magicPower = new MP();
    private UserLevel levelIcon;
    private Weapon weapon;
    private int playerLevel;
    private String magicLevel;
    private float userHealth;
    private int experiencePoints;
    private int characterClass;
    private BitmapFont gameFont = FontFactory.createBitMapFont(Gdx.files.internal(Resources.resourceMainFont), Resources.generalHUDFontSize, Color.WHITE, false, Color.BLACK);
    private BitmapFont lvlFont = FontFactory.createBitMapFont(Gdx.files.internal(Resources.resourceMainFont), Resources.levelHUDFontSize, Color.WHITE, false, Color.BLACK);
    private final int maxPoints = 500;
    private GameConfig gameConfig;
    private float type;

    public BattleHUD() {
        gameConfig = GameConfig.getInstance();
        this.setUserHealth(0);
        this.setPlayerLevel(0);
        this.setMagicLevel(String.valueOf(0));
        this.setCharacterClass(0);
        this.setExperiencePoints(0);
        createHearts(0);
    }

    public BattleHUD(float type, float userHealth, int playerLevel, int magicLevel, int experiencePoints, int characterClass) {
        gameConfig = GameConfig.getInstance();
        this.setUserHealth(userHealth);
        this.levelIcon = new UserLevel(type);
        this.setPlayerLevel(playerLevel);
        this.setMagicLevel(String.valueOf(magicLevel));
        this.setCharacterClass(characterClass);
        this.setExperiencePoints(experiencePoints);
        this.type = type;
        createHearts(type);
        weapon = new Weapon(this.playerLevel, this.characterClass);
    }
    public BattleHUD(float type, Party party) {
        gameConfig = GameConfig.getInstance();
        this.setUserHealth(party.getPartyMember1().getHealthPoints());
        this.levelIcon = new UserLevel(type);
        this.setPlayerLevel((int)(party.getPartyMember1().getLevel()));
        this.setMagicLevel(String.valueOf(magicLevel));
        this.setCharacterClass(1);
        //        this.setCharacterClass(party.getPartyMember1().getCharacterClass());
        this.setExperiencePoints(((PlayableCharacter)party.getPartyMember1()).getExperiencePoints());
        this.type = type;
        createHearts(type);
        weapon = new Weapon((int)(party.getPartyMember1().getLevel()), this.characterClass);
    }

    public Array<Heart> getHeartsHB() {
        return heartsHB;
    }

    public void setHeartsHB(Array<Heart> heartsHB) {
        this.heartsHB = heartsHB;
    }

    public MP getMagicPower() {
        return magicPower;
    }

    public void setMagicPower(MP magicPower) {
        this.magicPower = magicPower;
    }

    public int getPlayerLevel() {
        return playerLevel;
    }

    public void setPlayerLevel(int userLevel) {
        this.playerLevel = userLevel;
    }

    public String getMagicLevel() {
        return magicLevel;
    }

    public void setMagicLevel(String magicLevel) {
        this.magicLevel = magicLevel;
    }

    public float getUserHealth() {
        return userHealth;
    }

    public void setUserHealth(float userHealth) {
        this.userHealth = userHealth;
    }

    public int getExperiencePoints() {
        return experiencePoints;
    }

    public void setExperiencePoints(int experiencePoints) {
        this.experiencePoints = experiencePoints;
    }

    public int getCharacterClass() {
        return characterClass;
    }

    public void setCharacterClass(int characterClass) {
        this.characterClass = characterClass;
    }

    private int convertValue (float healthValue) {
        int finalValue = (int) Math.floor(healthValue/10);
        return finalValue;
    }

    public void update (Party party) {
        this.setUserHealth(party.getPartyMember1().getHealthPoints());
        this.setPlayerLevel((int)party.getPartyMember1().getLevel());
        this.setMagicLevel("HelloCat");
        this.setCharacterClass(characterClass);
        this.setExperiencePoints(experiencePoints);
        this.createHearts(type);
    }

    public void draw (SpriteBatch batch) {
        Vector2 resolution = new Vector2((float)gameConfig.getResolutionHorizontal(), (float)gameConfig.getResolutionVertical());
        Vector2 mpLevel = new Vector2(resolution.x, resolution.y);
        Vector2 playerLevel = new Vector2(resolution.x, resolution.y);
        Vector2 levelPlayer = new Vector2();
        Vector2 levelEnemy = new Vector2();
        Vector2 levelLabelPlayer  = new Vector2();
        Vector2 levelLabelEnemy  = new Vector2();

        for(Heart heart: heartsHB) {
            heart.get_sprite().draw(batch);
        }
//        if (this.characterClass == 3) {
//        if (true) {
////            magicPower.get_sprite().draw(batch);
//            gameFont.draw(batch, magicLevel, resolution.x-resolution.x*0.235f, resolution.y*0.96f);
//        }

        levelIcon.get_sprite().draw(batch);
        if(type == 0){
            gameFont.draw(batch, String.valueOf(this.playerLevel), resolution.x*0.036f, resolution.y*0.945f);
            lvlFont.draw(batch, "Nivel", resolution.x*0.032f, resolution.y*0.965f);
            gameFont.draw(batch, "Exp: " + this.experiencePoints + "/" + this.maxPoints, resolution.x*0.08f, resolution.y*0.96f);
//            weapon.get_sprite().draw(batch);
        }
        if(type == 1){
            gameFont.draw(batch, String.valueOf(this.playerLevel), resolution.x-resolution.x*0.230f, resolution.y*0.945f);
            lvlFont.draw(batch, "Nivel", resolution.x-resolution.x*0.235f, resolution.y*0.965f);
            gameFont.draw(batch, "Esqueleto de tierra", resolution.x-resolution.x*0.190f, resolution.y*0.96f);
        }

    }

    private void createHearts(float type) {
        this.heartsHB = new Array<Heart>();
        int health = this.convertValue(this.getUserHealth());
        double lastValue = 0.06;
        for(int i=0; i < health; i++) {
            Heart heart = new Heart(lastValue, type);
            heartsHB.add(heart);
            lastValue += 0.03;
        }
    }
}
