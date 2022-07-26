package com.avengers.rpgame.graphics.store;

import com.avengers.rpgame.game.GameConfig;
import com.avengers.rpgame.logic.entities.Item;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class ScreenItem {
    private Texture _texture;
    private Sprite _sprite;
    private Item item;

    public ScreenItem(Item item, double itemX, double itemY) {
        this.setItem(item);
        createScreenItem((float) itemX, (float) itemY, 90);
    }

    public ScreenItem(Item item, double itemX, double itemY, int size) {
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
        GameConfig gameConfig = GameConfig.getInstance();
        Vector2 resolution = new Vector2((float)gameConfig.getResolutionHorizontal(), (float)gameConfig.getResolutionVertical());
        Vector2 ofset =  new Vector2(resolution.x*itemX, resolution.y*itemY);
        Vector2 position = new Vector2(resolution.x -ofset.x, resolution.y-ofset.y);
        this.set_texture(new Texture(Gdx.files.internal(item.getImagePath())));
        this.set_sprite(new Sprite(this.get_texture()));
        this.setItemX((int)position.x);
        this.setItemY((int)position.y);
        this.setSize(itemSize, itemSize);
    }
}
