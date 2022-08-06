package com.avengers.rpgame.graphics.hud.elements;

import com.avengers.rpgame.game.GameConfig;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

import static com.avengers.rpgame.utils.FileManager.loadTexture;
import static com.avengers.rpgame.utils.Resources.HUDPotion;

public class MP {
    private Texture _texture;
    private Sprite _sprite;

    public MP() {
        createMagicPower();
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

    private void createMagicPower () {
        GameConfig gameConfig = GameConfig.getInstance();
        Vector2 resolution = new Vector2((float)gameConfig.getResolutionHorizontal(), (float)gameConfig.getResolutionVertical());
        Vector2 ofset =  new Vector2(resolution.x*0.74f, resolution.y*0.07f);
        Vector2 position = new Vector2(resolution.x -ofset.x, resolution.y-ofset.y);
        this.set_texture(loadTexture(HUDPotion));
        this.set_sprite(new Sprite(this.get_texture()));
        this.setPotionX((int)position.x);
        this.setPotionY((int)position.y);
        this.setSize(50, 50);
    }

    public void dispose(){
//        _texture.dispose();
//        _sprite.getTexture().dispose();
    }
}
