package com.avengers.rpgame.graphics.hud;

import com.avengers.rpgame.game.GameConfig;
import com.avengers.rpgame.graphics.camera.CameraManager;
import com.avengers.rpgame.graphics.text.FontFactory;
import com.avengers.rpgame.logic.entities.Character;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import static com.avengers.rpgame.utils.Resources.resourcePixelFont;

public class HUD {
    private Array<Heart> heartsHB;
    private MP magicPower = new MP();
    private UserLevel levelIcon = new UserLevel();
    private Weapon weapon;
    private int playerLevel;
    private String magicLevel;
    private float userHealth;
    private int experiencePoints;
    private int characterClass;
    private BitmapFont gameFont = FontFactory.createBitMapFont(Gdx.files.internal(resourcePixelFont), 36, Color.WHITE, false, Color.BLACK);
    private BitmapFont lvlFont = FontFactory.createBitMapFont(Gdx.files.internal(resourcePixelFont), 20, Color.WHITE, false, Color.BLACK);
    private BitmapFont expFont = FontFactory.createBitMapFont(Gdx.files.internal(resourcePixelFont), 24, Color.WHITE, false, Color.BLACK);
    private final int maxPoints = 500;
    private GameConfig gameConfig;

    public HUD () {
        gameConfig = GameConfig.getInstance();
        this.setUserHealth(0);
        this.setPlayerLevel(0);
        this.setMagicLevel(String.valueOf(0));
        this.setCharacterClass(0);
        this.setExperiencePoints(0);
        createHearts();
    }

    public HUD (float userHealth, int playerLevel, int magicLevel, int experiencePoints, int characterClass) {
        gameConfig = GameConfig.getInstance();
        this.setUserHealth(userHealth);
        this.setPlayerLevel(playerLevel);
        this.setMagicLevel(String.valueOf(magicLevel));
        this.setCharacterClass(characterClass);
        this.setExperiencePoints(experiencePoints);
        createHearts();
        weapon = new Weapon(this.playerLevel, this.characterClass);
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

    public void update (float userHealth, int playerLevel, int magicLevel, int experiencePoints, int characterClass) {
        this.setUserHealth(userHealth);
        this.setPlayerLevel(playerLevel);
        this.setMagicLevel(String.valueOf(magicLevel));
        this.setCharacterClass(characterClass);
        this.setExperiencePoints(experiencePoints);
        this.createHearts();
    }

    public void draw (SpriteBatch batch) {
        Vector2 resolution = new Vector2((float)gameConfig.getResolutionHorizontal(), (float)gameConfig.getResolutionVertical());
        Vector2 mpLevel = new Vector2(resolution.x, resolution.y);
        Vector2 playerLevel = new Vector2(resolution.x, resolution.y);

        for(Heart heart: heartsHB) {
            heart.get_sprite().draw(batch);
        }
//        if (this.characterClass == 3) {
        if (true) {
            magicPower.get_sprite().draw(batch);
            gameFont.draw(batch, magicLevel, resolution.x*0.3f, resolution.y*0.96f);
        }

        levelIcon.get_sprite().draw(batch);
        gameFont.draw(batch, String.valueOf(this.playerLevel), resolution.x*0.033f, resolution.y*0.945f);
        lvlFont.draw(batch, "Level", resolution.x*0.030f, resolution.y*0.965f);
        expFont.draw(batch, "Exp: " + this.experiencePoints + "/ " + this.maxPoints, resolution.x*0.08f, resolution.y*0.955f);
        weapon.get_sprite().draw(batch);
    }

    private void createHearts() {
        this.heartsHB = new Array<Heart>();
        int health = this.convertValue(this.getUserHealth());
        double lastValue = 0.06;
        for(int i=0; i < health; i++) {
            Heart heart = new Heart(lastValue);
            heartsHB.add(heart);
            lastValue += 0.03;
        }
    }
}
