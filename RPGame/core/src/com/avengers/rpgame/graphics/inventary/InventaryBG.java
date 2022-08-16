package com.avengers.rpgame.graphics.inventary;

import com.avengers.rpgame.game.GameConfig;
import com.avengers.rpgame.graphics.assetManager.MyAssetManager;
import com.avengers.rpgame.utils.Resources;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

import static com.avengers.rpgame.utils.FileManager.loadTexture;

public class InventaryBG {
    private MyAssetManager assetManager;
    private GameConfig gameConfig;
    private Texture _texture;
    private Sprite _sprite;

    public InventaryBG(double itemX, double itemY) {
        gameConfig = GameConfig.getInstance();
        assetManager = MyAssetManager.getInstance();
        createScreenItem((float) itemX, (float) itemY);
    }

    public Texture get_texture() {
        return _texture;
    }

    public void set_texture(Texture _texture) {
        this._texture = _texture;
    }

    public float getItemX() {
        return this._sprite.getX();
    }

    public void setItemX(int ItemX) {
        this.get_sprite().setX(ItemX);
    }

    public float getItemY() {
        return this._sprite.getY();
    }

    public void setItemY(int ItemY) {
        this.get_sprite().setY(ItemY);
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

    private void createScreenItem (float itemX, float itemY) {
        Vector2 resolution = new Vector2((float)gameConfig.getResolutionHorizontal(), (float)gameConfig.getResolutionVertical());
        Vector2 offset =  new Vector2(resolution.x*itemX, resolution.y*itemY);
        Vector2 position = new Vector2(resolution.x -offset.x, resolution.y-offset.y);
        this.set_texture(loadTexture(Resources.resourceInventaryBackground));
        this.set_sprite(new Sprite(this.get_texture()));
        this.setItemX((int)position.x);
        this.setItemY((int)position.y);
        this.setSize(580, 900);
    }
}
