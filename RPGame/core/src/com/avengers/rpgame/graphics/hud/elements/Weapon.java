package com.avengers.rpgame.graphics.hud.elements;

import com.avengers.rpgame.game.GameConfig;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

import static com.avengers.rpgame.utils.FileManager.loadTexture;
import static com.avengers.rpgame.utils.Resources.*;

public class Weapon {
    private Texture _texture;
    private Sprite _sprite;
    private int characterClass;
    private int playerLevel;
    private String weaponPath;

    public Weapon() {
        this.setPlayerLevel(0);
        this.chooseWeapon();
        this.createWeapon();
    }

    public Weapon(int playerLevel, int characterClass) {
        this.setPlayerLevel(playerLevel);
        this.setCharacterClass(characterClass);
        this.chooseWeapon();
        this.createWeapon();
    }

    public Texture get_texture() {
        return _texture;
    }

    public void set_texture(Texture _texture) {
        this._texture = _texture;
    }

    public Sprite get_sprite() {
        return _sprite;
    }

    public void set_sprite(Sprite _sprite) {
        this._sprite = _sprite;
    }

    public int getCharacterClass() {
        return characterClass;
    }

    public void setCharacterClass(int characterClass) {
        this.characterClass = characterClass;
    }

    public float getPotionX() {
        return this._sprite.getX();
    }

    public void setPotionX(int PotionX) {
        this.get_sprite().setX(PotionX);
    }

    public float getPotionY() {
        return this._sprite.getY();
    }

    public void setPotionY(int PotionY) {
        this.get_sprite().setY(PotionY);
    }

    public void setSize(float width, float height) {
        this.get_sprite().setSize(width, height);
    }

    public float getHeight() {
        return this.get_sprite().getHeight();
    }

    public float getWidth() {
        return this.get_sprite().getWidth();
    }

    public int getPlayerLevel() {
        return playerLevel;
    }

    public void setPlayerLevel(int playerLevel) {
        this.playerLevel = playerLevel;
    }

    public String getWeaponPath() {
        return weaponPath;
    }

    public void setWeaponPath(String weaponPath) {
        this.weaponPath = weaponPath;
    }

    private void chooseWeapon() {
        String finalWeaponPath = null;
        if (this.characterClass == 1) {
            if(this.playerLevel <3) {
                finalWeaponPath = HUDSword;
            } else if(this.playerLevel >= 3 && this.playerLevel <7) {
                finalWeaponPath = HUDSilverSword;
            } else if (this.playerLevel >= 7 && this.playerLevel <9) {
                finalWeaponPath = HUDDoubleHandSword;
            } else if (this.playerLevel >= 9) {
                finalWeaponPath = HUDAxes;
            } else {
                System.out.println("invalid player level");
            }
        } else if (this.getCharacterClass() == 2) {
            if(this.playerLevel <3) {
                finalWeaponPath = HUDArrow;
            } else if(this.playerLevel >= 3 && this.playerLevel <7) {
                finalWeaponPath = HUDDoubleArrow;
            } else if (this.playerLevel >= 7 && this.playerLevel <9) {
                finalWeaponPath = HUDTripleArrow;
            } else if (this.playerLevel >= 9) {
                finalWeaponPath = HUDBombArrow;
            } else {
                System.out.println("invalid player level");
            }
        }  else {
            System.out.println("incorrect character class");
            finalWeaponPath = HUDDefaultWeapon;
        }
        this.setWeaponPath(finalWeaponPath);
    }

    private void createWeapon() {
        GameConfig gameConfig = GameConfig.getInstance();
        Vector2 resolution = new Vector2((float)gameConfig.getResolutionHorizontal(), (float)gameConfig.getResolutionVertical());
        Vector2 ofset =  new Vector2(resolution.x*0.8f, resolution.y*0.08f);
        Vector2 position = new Vector2(resolution.x -ofset.x, resolution.y-ofset.y);
        this.set_texture(loadTexture(this.getWeaponPath()));
        this.set_sprite(new Sprite(this.get_texture()));
        this.setPotionX((int)position.x);
        this.setPotionY((int)position.y);
        this.setSize(60, 60);
    }

    public void dispose(){
//        _texture.dispose();
//        _sprite.getTexture().dispose();
    }
}