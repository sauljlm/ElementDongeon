package com.avengers.rpgame.graphics.hud.elements;

import com.avengers.rpgame.game.GameConfig;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

import static com.avengers.rpgame.utils.Resources.HUDHeart;

public class Heart {
    private Texture _texture;
    private Sprite _sprite;

    public Heart() {
        createHeart(1);
    }

    public Heart(double heartX) {
        createHeart((float) heartX);
    }
    public Heart(double heartX, float type){
        if(type == 0){
            createHeartBattlePlayer((float) heartX);
        }
        if(type == 1){
            createHeartBattleEnemy((float) heartX);
        }
    }

    public Texture get_texture() {
        return _texture;
    }

    public void set_texture(Texture _texture) {
        this._texture = _texture;
    }

    public float getHeartX() {
        return this._sprite.getX();
    }

    public void setHeartX(int heartX) {
        this.get_sprite().setX(heartX);
    }

    public float getHeartY() {
        return this._sprite.getY();
    }

    public void setHeartY(int heartY) {
        this.get_sprite().setY(heartY);
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

    private void createHeart (float heartX) {
        GameConfig gameConfig = GameConfig.getInstance();
        Vector2 resolution = new Vector2((float)gameConfig.getResolutionHorizontal(), (float)gameConfig.getResolutionVertical());
        Vector2 ofset =  new Vector2(resolution.x*heartX, resolution.y*0.07f);
        Vector2 position = new Vector2(resolution.x -ofset.x, resolution.y-ofset.y);
        this.set_texture(new Texture(Gdx.files.internal(HUDHeart)));
        this.set_sprite(new Sprite(this.get_texture()));
        this.setHeartX((int)position.x);
        this.setHeartY((int)position.y);
        this.setSize(50, 50);
    }
    private void createHeartBattleEnemy (float heartX) {
        GameConfig gameConfig = GameConfig.getInstance();
        Vector2 resolution = new Vector2((float)gameConfig.getResolutionHorizontal(), (float)gameConfig.getResolutionVertical());
        Vector2 ofset =  new Vector2(resolution.x*heartX/1.4f, resolution.y*0.17f);
        Vector2 position = new Vector2(resolution.x -ofset.x, resolution.y-ofset.y);
        this.set_texture(new Texture(Gdx.files.internal(HUDHeart)));
        this.set_sprite(new Sprite(this.get_texture()));
        this.setHeartX((int)position.x);
        this.setHeartY((int)position.y);
        this.setSize(50, 50);
    }
    private void createHeartBattlePlayer (float heartX) {
        GameConfig gameConfig = GameConfig.getInstance();
        Vector2 resolution = new Vector2((float)gameConfig.getResolutionHorizontal(), (float)gameConfig.getResolutionVertical());
        Vector2 ofset =  new Vector2(resolution.x*heartX/1.4f, resolution.y*0.17f);
        Vector2 position = new Vector2(resolution.x/4 -ofset.x, resolution.y-ofset.y);
        this.set_texture(new Texture(Gdx.files.internal(HUDHeart)));
        this.set_sprite(new Sprite(this.get_texture()));
        this.setHeartX((int)position.x);
        this.setHeartY((int)position.y);
        this.setSize(50, 50);
    }

    public void dispose() {
        _texture.dispose();
        _sprite.getTexture().dispose();
    }
}
