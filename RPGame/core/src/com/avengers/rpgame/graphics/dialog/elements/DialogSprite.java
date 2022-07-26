package com.avengers.rpgame.graphics.dialog.elements;

import com.avengers.rpgame.game.GameConfig;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

import static com.avengers.rpgame.utils.Resources.dialog;

public class DialogSprite {
    private Texture _texture;
    private Sprite _sprite;

    public DialogSprite() {
        createSprite();
    }

    public Texture get_texture() {
        return _texture;
    }

    public void set_texture(Texture _texture) {
        this._texture = _texture;
    }

    public float getSpriteX() {
        return this._sprite.getX();
    }

    public void setSpriteX(int SpriteX) {
        this.get_sprite().setX(SpriteX);
    }

    public float getSpriteY() {
        return this._sprite.getY();
    }

    public void setSpriteY(int SpriteY) {
        this.get_sprite().setY(SpriteY);
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

    private void createSprite () {
        GameConfig gameConfig = GameConfig.getInstance();
        Vector2 resolution = new Vector2((float)gameConfig.getResolutionHorizontal(), (float)gameConfig.getResolutionVertical());
        Vector2 ofset =  new Vector2(resolution.x*0.95f, resolution.y*0.95f);
        Vector2 position = new Vector2(resolution.x -ofset.x, resolution.y-ofset.y);
        this.set_texture(new Texture(Gdx.files.internal(dialog)));
        this.set_sprite(new Sprite(this.get_texture()));
        this.setSpriteX((int)position.x);
        this.setSpriteY((int)position.y);
        this.setSize(700, 150);
    }
}
