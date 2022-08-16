package com.avengers.rpgame.graphics.hud;

import com.avengers.rpgame.RPGame;
import com.avengers.rpgame.data.gameStatus.GameStatus;
import com.avengers.rpgame.game.GameConfig;
import com.avengers.rpgame.graphics.hud.elements.Heart;
import com.avengers.rpgame.graphics.hud.elements.MP;
import com.avengers.rpgame.graphics.hud.elements.UserLevel;
import com.avengers.rpgame.graphics.hud.elements.Weapon;
import com.avengers.rpgame.graphics.store.Coin;
import com.avengers.rpgame.graphics.text.FontFactory;
import com.avengers.rpgame.logic.entities.Party;
import com.avengers.rpgame.logic.entities.character.abstractCharacter.AbstractCharacter;
import com.avengers.rpgame.logic.entities.character.components.animatedCharacter.DynamicAnimatedCharacter;
import com.avengers.rpgame.logic.entities.character.concrete.PlayableCharacter;
import com.avengers.rpgame.utils.Resources;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class HUD {
    private RPGame rpGame;
    private GameStatus gameStatus;
    private Array<Heart> heartsHB;
    private MP magicPower;
    private UserLevel levelIcon;
    private Weapon weapon;
    private BitmapFont gameFont;
    private BitmapFont lvlFont;
    private int maxPoints;
    private GameConfig gameConfig;
    private AbstractCharacter character;
    private Coin coin;
    private BitmapFont characterCoinsFont;
    private int lastActivePartyMember;

    public HUD () {
        rpGame = RPGame.getInstance();
        gameConfig = GameConfig.getInstance();
        gameStatus = GameStatus.getInstance();
        initializeHud();
    }

    private void initializeHud(){
        character = gameStatus.getPlayerParty().getActivePartyMember();
        if(magicPower !=null){
            magicPower.dispose();
        }
        if(levelIcon != null){
            levelIcon.dispose();
        }
        if(heartsHB !=null){
            heartsHB.clear();
        }
        if(coin != null){
            coin.dispose();
        }
        magicPower = new MP();
        levelIcon = new UserLevel();
        heartsHB = new Array<Heart>();
        coin = new Coin(50, 50, 0.92f, 0.11f);
        lvlFont = FontFactory.createBitMapFont(Gdx.files.internal(Resources.resourceMainFont), Resources.levelHUDFontSize, Color.WHITE, false, Color.BLACK);
        gameFont = FontFactory.createBitMapFont(Gdx.files.internal(Resources.resourceMainFont), Resources.generalHUDFontSize, Color.WHITE, false, Color.BLACK);
        characterCoinsFont = FontFactory.createBitMapFont(Gdx.files.internal(Resources.resourceMainFont), Resources.generalHUDFontSize, Color.WHITE, false, Color.BLACK);
        maxPoints = ((PlayableCharacter)GameStatus.getInstance().getPlayerParty().getActivePartyMember()).getExperiencePointsMax();
        createHearts();
        weapon = new Weapon((int) this.character.getLevel(), this.character.getCharacterClass().getIdCharacterClass());
        lastActivePartyMember = gameStatus.getPlayerParty().getActivePartyMemberId();
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

    private int convertValue (float healthValue) {
        int finalValue = (int) Math.floor(healthValue/10);
        return finalValue;
    }

    public void update () {
        Party party = gameStatus.getPlayerParty();

        //Checks if the active party member changed and we need to re initialize the HUD
        if(party.getActivePartyMemberId() != lastActivePartyMember){
            initializeHud();
        }
        this.character.setHealthPoints(party.getActivePartyMember().getHealthPoints());
        this.character.setLevel(party.getActivePartyMember().getLevel());
        this.character.setMagicPoints(party.getActivePartyMember().getMagicPoints());
//        this.character.(((PlayableCharacter)party.getActivePartyMember()).getExperiencePoints());
        this.createHearts();
    }

    public void draw () {
        Vector2 resolution = new Vector2((float)gameConfig.getResolutionHorizontal(), (float)gameConfig.getResolutionVertical());
        if(gameConfig.isDebugPhysics()){ //Debug print for character coordinates
            gameFont.draw(rpGame.batch, String.valueOf(((DynamicAnimatedCharacter)this.character.getAnimatedCharacter()).getPlayer().getPosition()), resolution.x*0.2f, resolution.y*0.2f);
        }

        for(Heart heart: heartsHB) {
            heart.get_sprite().draw(rpGame.batch);
        }
        if (this.character.getCharacterClass().getIdCharacterClass() == 3) {
            magicPower.get_sprite().draw(rpGame.batch);
            gameFont.draw(rpGame.batch, String.valueOf(this.character.getMagicPoints()), resolution.x*0.11f, resolution.y*0.855f);
        }

        levelIcon.get_sprite().draw(rpGame.batch);
        gameFont.draw(rpGame.batch, String.valueOf((int)this.character.getLevel()), resolution.x*0.036f, resolution.y*0.945f);
        lvlFont.draw(rpGame.batch, "Nivel", resolution.x*0.032f, resolution.y*0.965f);
        gameFont.draw(rpGame.batch, "Exp: " + (int)((PlayableCharacter)this.character).getExperiencePoints() + "/" + this.maxPoints, resolution.x*0.08f, resolution.y*0.96f);
        weapon.get_sprite().draw(rpGame.batch);
        coin.get_sprite().draw(rpGame.batch);
        characterCoinsFont.draw(rpGame.batch, String.valueOf(this.character.getCoins()), resolution.x*0.115f, resolution.y- resolution.y*0.100f+coin.getHeight()/2f);
    }

    private void createHearts() {
        for (Heart heart:heartsHB) {
            heart.dispose();
        }
        heartsHB.clear();
        int health = this.convertValue(this.character.getHealthPoints());
        double lastValue = 0.06;
        for(int i=0; i < health; i++) {
            Heart heart = new Heart(lastValue);
            heartsHB.add(heart);
            lastValue += 0.03;
        }
    }

    public void dispose(){
        for (Heart heart:heartsHB) {
            heart.dispose();
        }
        gameFont.dispose();
        lvlFont.dispose();
        weapon.dispose();
        levelIcon.dispose();
        magicPower.dispose();
    }
}
