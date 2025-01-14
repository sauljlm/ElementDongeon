package com.avengers.rpgame.graphics.hud;

import com.avengers.rpgame.data.gameStatus.GameStatus;
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
    private final int maxPoints = ((PlayableCharacter) GameStatus.getInstance().getPlayerParty().getActivePartyMember()).getExperiencePointsMax();
    private GameConfig gameConfig;
    private float type;

    public BattleHUD(float type, Party party) {
        gameConfig = GameConfig.getInstance();
        this.setUserHealth(party.getActivePartyMember().getHealthPoints());
        this.levelIcon = new UserLevel(type);
        this.setPlayerLevel((int)(party.getActivePartyMember().getLevel()));
        this.setMagicLevel(String.valueOf(magicLevel));
        this.setCharacterClass(1);
        heartsHB = new Array<Heart>();
        //        this.setCharacterClass(party.getPartyMember1().getCharacterClass());
        this.setExperiencePoints(((PlayableCharacter)party.getActivePartyMember()).getExperiencePoints());
        this.type = type;
        createHearts(type);
        weapon = new Weapon((int)(party.getActivePartyMember().getLevel()), this.characterClass);
        magicPower.get_sprite().setCenterX((float)gameConfig.getResolutionHorizontal()*0.09f);
        magicPower.get_sprite().setCenterY((float)gameConfig.getResolutionVertical()*0.91f);
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
        this.setUserHealth(party.getActivePartyMember().getHealthPoints());
        this.setPlayerLevel((int)party.getActivePartyMember().getLevel());
        this.setMagicLevel(String.valueOf(party.getActivePartyMember().getMagicPoints()));
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
        if (GameStatus.getInstance().getPlayerParty().getActivePartyMember().getCharacterClass().getIdCharacterClass() == 3) {
            magicPower.get_sprite().draw(batch);
            gameFont.draw(batch, String.valueOf(GameStatus.getInstance().getPlayerParty().getActivePartyMember().getMagicPoints()), resolution.x*0.12f, resolution.y*0.915f);
        }

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
            gameFont.draw(batch,GameStatus.getInstance().getEnemyParty().getPartyMember(1).getDescription(), resolution.x-resolution.x*0.190f, resolution.y*0.96f);
        }

    }

    private void createHearts(float type) {
        for (Heart heart:heartsHB) {
            heart.dispose();
        }
        heartsHB.clear();

        int health = this.convertValue(this.getUserHealth());
        double lastValue = 0.06;
        for(int i=0; i < health; i++) {
            Heart heart = new Heart(lastValue, type);
            heartsHB.add(heart);
            lastValue += 0.03;
        }
    }

    public void dispose(){
        for (Heart heart:heartsHB) {
//            heart.dispose();
        }
//        gameFont.dispose();
//        lvlFont.dispose();
//        weapon.dispose();
//        levelIcon.dispose();
//        magicPower.dispose();
    }
}
