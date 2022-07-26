package com.avengers.rpgame.graphics.hud;

import com.avengers.rpgame.data.gameStatus.GameStatus;
import com.avengers.rpgame.game.GameConfig;
import com.avengers.rpgame.graphics.hud.elements.Heart;
import com.avengers.rpgame.graphics.hud.elements.MP;
import com.avengers.rpgame.graphics.hud.elements.UserLevel;
import com.avengers.rpgame.graphics.hud.elements.Weapon;
import com.avengers.rpgame.graphics.text.FontFactory;
import com.avengers.rpgame.logic.entities.Party;
import com.avengers.rpgame.logic.entities.character.abstractCharacter.AbstractCharacter;
import com.avengers.rpgame.logic.entities.character.concrete.PlayableCharacter;
import com.avengers.rpgame.utils.Resources;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class HUD {
    private Array<Heart> heartsHB;
    private MP magicPower = new MP();
    private UserLevel levelIcon = new UserLevel();
    private Weapon weapon;
    private BitmapFont gameFont = FontFactory.createBitMapFont(Gdx.files.internal(Resources.resourceMainFont), Resources.generalHUDFontSize, Color.WHITE, false, Color.BLACK);
    private BitmapFont lvlFont = FontFactory.createBitMapFont(Gdx.files.internal(Resources.resourceMainFont), Resources.levelHUDFontSize, Color.WHITE, false, Color.BLACK);
    private final int maxPoints = ((PlayableCharacter)GameStatus.getInstance().getParty().getActivePartyMember()).getExperiencePointsMax();
    private GameConfig gameConfig;
    private AbstractCharacter character;

    public HUD (Party playerParty) {
        gameConfig = GameConfig.getInstance();
        this.character = playerParty.getActivePartyMember();
        createHearts();
        weapon = new Weapon((int) this.character.getLevel(), this.character.getCharacterClass().getIdCharacterClass());
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
        Party party = GameStatus.getInstance().getParty();
        this.character.setHealthPoints(party.getActivePartyMember().getHealthPoints());
        this.character.setLevel(party.getActivePartyMember().getLevel());
        this.character.setMagicPoints(party.getActivePartyMember().getMagicPoints());
//        this.character.(((PlayableCharacter)party.getActivePartyMember()).getExperiencePoints());
        this.createHearts();
    }

//    public void update (Party party) {
//        this.setUserHealth(party.getActivePartyMember().getHealthPoints());
//        this.setPlayerLevel((int)party.getPartyMember1().getLevel());
//        this.setMagicLevel("HelloCat");
//        this.setCharacterClass(characterClass);
//        this.setExperiencePoints(experiencePoints);
//        this.createHearts(type);
//    }

    public void draw (SpriteBatch batch) {
        Vector2 resolution = new Vector2((float)gameConfig.getResolutionHorizontal(), (float)gameConfig.getResolutionVertical());

        for(Heart heart: heartsHB) {
            heart.get_sprite().draw(batch);
        }
        if (this.character.getCharacterClass().getIdCharacterClass() == 3) {
            magicPower.get_sprite().draw(batch);
            gameFont.draw(batch, String.valueOf(this.character.getMagicPoints()), resolution.x*0.3f, resolution.y*0.96f);
        }

        levelIcon.get_sprite().draw(batch);
        gameFont.draw(batch, String.valueOf((int)this.character.getLevel()), resolution.x*0.036f, resolution.y*0.945f);
        lvlFont.draw(batch, "Nivel", resolution.x*0.032f, resolution.y*0.965f);
        gameFont.draw(batch, "Exp: " + (int)((PlayableCharacter)this.character).getExperiencePoints() + "/" + this.maxPoints, resolution.x*0.08f, resolution.y*0.96f);
        weapon.get_sprite().draw(batch);
    }

    private void createHearts() {
        this.heartsHB = new Array<Heart>();
        int health = this.convertValue(this.character.getHealthPoints());
        double lastValue = 0.06;
        for(int i=0; i < health; i++) {
            Heart heart = new Heart(lastValue);
            heartsHB.add(heart);
            lastValue += 0.03;
        }
    }
}
