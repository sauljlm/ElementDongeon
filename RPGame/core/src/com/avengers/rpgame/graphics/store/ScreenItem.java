package com.avengers.rpgame.graphics.store;

import com.avengers.rpgame.game.GameConfig;
import com.avengers.rpgame.graphics.assetManager.MyAssetManager;
import com.avengers.rpgame.logic.entities.Item;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

import static com.avengers.rpgame.utils.FileManager.loadTexture;

public class ScreenItem {
    private MyAssetManager assetManager;
    private GameConfig gameConfig;
    private Texture _texture;
    private Sprite _sprite;
    private Item item;

    public ScreenItem(Item item, double itemX, double itemY) {
        gameConfig = GameConfig.getInstance();
        assetManager = MyAssetManager.getInstance();
        this.setItem(item);
        createScreenItem((float) itemX, (float) itemY, 90);
    }

    public ScreenItem(Item item, double itemX, double itemY, int size) {
        gameConfig = GameConfig.getInstance();
        assetManager = MyAssetManager.getInstance();
        this.setItem(item);
        createScreenItem((float) itemX, (float) itemY, size);
    }

    public Texture get_texture() {
        return _texture;
    }

    public void set_texture(Texture _texture) {
        this._texture = _texture;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
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

    private void createScreenItem (float itemX, float itemY, int itemSize) {
        Vector2 resolution = new Vector2((float)gameConfig.getResolutionHorizontal(), (float)gameConfig.getResolutionVertical());
        Vector2 offset =  new Vector2(resolution.x*itemX, resolution.y*itemY);
        Vector2 position = new Vector2(resolution.x -offset.x, resolution.y-offset.y);
        this.set_texture(loadTexture(item.getImagePath()));
        this.set_sprite(new Sprite(this.get_texture()));
        this.setItemX((int)position.x);
        this.setItemY((int)position.y);
        this.setSize(itemSize, itemSize);
    }
}
