package com.avengers.rpgame.graphics.hud.elements;

import com.avengers.rpgame.game.GameConfig;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

import static com.avengers.rpgame.utils.Resources.HUDShield;

public class UserLevel {
    private Texture _texture;
    private Sprite _sprite;

    public UserLevel() {
        createShield();
    }

    public UserLevel(float type){
        if(type == 0){
            createShieldBattlePlayer();
        }
        if(type == 1){
            createShieldBattleEnemy();
        }
    }

    public Texture get_texture() {
        return _texture;
    }

    public void set_texture(Texture _texture) {
        this._texture = _texture;
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

    public Sprite get_sprite() {
        return _sprite;
    }

    public void set_sprite(Sprite _sprite) {
        this._sprite = _sprite;
    }

    private void createShield () {
        GameConfig gameConfig = GameConfig.getInstance();
        Vector2 resolution = new Vector2((float)gameConfig.getResolutionHorizontal(), (float)gameConfig.getResolutionVertical());
        Vector2 ofset =  new Vector2(resolution.x*0.98f, resolution.y*0.1f);
        Vector2 position = new Vector2(resolution.x -ofset.x, resolution.y-ofset.y);
        this.set_texture(new Texture(Gdx.files.internal(HUDShield)));
        this.set_sprite(new Sprite(this.get_texture()));
        this.setPotionX((int)position.x);
        this.setPotionY((int)position.y);
        this.setSize(90, 90);
    }
    private void createShieldBattleEnemy () {
        GameConfig gameConfig = GameConfig.getInstance();
        Vector2 resolution = new Vector2((float)gameConfig.getResolutionHorizontal(), (float)gameConfig.getResolutionVertical());
        Vector2 ofset =  new Vector2(resolution.x/4*0.98f, resolution.y*0.1f);
        Vector2 position = new Vector2(resolution.x -ofset.x, resolution.y-ofset.y);
        this.set_texture(new Texture(Gdx.files.internal(HUDShield)));
        this.set_sprite(new Sprite(this.get_texture()));
        this.setPotionX((int)position.x);
        this.setPotionY((int)position.y);
        this.setSize(90, 90);
    }
    private void createShieldBattlePlayer () {
        GameConfig gameConfig = GameConfig.getInstance();
        Vector2 resolution = new Vector2((float)gameConfig.getResolutionHorizontal(), (float)gameConfig.getResolutionVertical());
        Vector2 ofset =  new Vector2(resolution.x*0.98f, resolution.y*0.1f);
        Vector2 position = new Vector2(resolution.x -ofset.x, resolution.y-ofset.y);
        this.set_texture(new Texture(Gdx.files.internal(HUDShield)));
        this.set_sprite(new Sprite(this.get_texture()));
        this.setPotionX((int)position.x);
        this.setPotionY((int)position.y);
        this.setSize(90, 90);
    }
}
