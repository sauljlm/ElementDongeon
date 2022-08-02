package com.avengers.rpgame.logic.entities.character.components.skin.components;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

//An animation needs some textures, the best way is to join the frames inside a texture and find them with a TextureAtlas to mark the positions.
public class AnimationAssets {
    private Texture texture;
    private TextureAtlas animationAtlas;
    private String animationName;


    public AnimationAssets() {
    }

    public AnimationAssets(Texture upTexture, TextureAtlas upAnimationAtlas, String animationName) {
        this.texture = upTexture;
        this.animationAtlas = upAnimationAtlas;
        this.animationName = animationName;
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public TextureAtlas getAnimationAtlas() {
        return animationAtlas;
    }

    public void setAnimationAtlas(TextureAtlas animationAtlas) {
        this.animationAtlas = animationAtlas;
    }

    public String getAnimationName() {
        return animationName;
    }

    public void setAnimationName(String animationName) {
        this.animationName = animationName;
    }

    public void dispose() {
        texture.dispose();
        animationAtlas.dispose();
    }
}
