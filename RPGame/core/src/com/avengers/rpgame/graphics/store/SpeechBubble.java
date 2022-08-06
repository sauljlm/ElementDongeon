package com.avengers.rpgame.graphics.store;

import com.avengers.rpgame.game.GameConfig;
import com.avengers.rpgame.graphics.assetManager.MyAssetManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

import static com.avengers.rpgame.utils.FileManager.loadTexture;
import static com.avengers.rpgame.utils.Resources.speechBubble;

public class SpeechBubble {
    private MyAssetManager assetManager;
    GameConfig gameConfig;
    private Texture _texture;
    private Sprite _sprite;

    public SpeechBubble(int width, int height, float positionX, float positionY) {
        assetManager = MyAssetManager.getInstance();
        gameConfig = GameConfig.getInstance();
        createBubble(width, height, positionX, positionY);
    }

    public Texture get_texture() {
        return _texture;
    }

    public void set_texture(Texture _texture) {
        this._texture = _texture;
    }

    public float getBubbleX() {
        return this._sprite.getX();
    }

    public void setBubbleX(int PotionX) {
        this.get_sprite().setX(PotionX);
    }

    public float getBubbleY() {
        return this._sprite.getY();
    }

    public void setBubbleY(int PotionY) {
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

    private void createBubble(int width, int height, float positionX, float positionY) {

        Vector2 resolution = new Vector2((float)gameConfig.getResolutionHorizontal(), (float)gameConfig.getResolutionVertical());
        Vector2 ofset =  new Vector2(resolution.x*positionX, resolution.y*positionY);
        Vector2 position = new Vector2(resolution.x -ofset.x, resolution.y-ofset.y);
        this.set_texture(loadTexture(speechBubble));
        this.set_sprite(new Sprite(this.get_texture()));
        this.setBubbleX((int)position.x);
        this.setBubbleY((int)position.y);
        this.setSize(width, height);
    }
}
