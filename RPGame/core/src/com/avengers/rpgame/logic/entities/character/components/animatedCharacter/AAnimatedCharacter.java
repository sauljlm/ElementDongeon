package com.avengers.rpgame.logic.entities.character.components.animatedCharacter;

import com.avengers.rpgame.RPGame;
import com.avengers.rpgame.game.GameConfig;
import com.avengers.rpgame.logic.entities.character.components.skin.ISkin;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.Vector2;

//Contains the information and methods of the graphical entity of the character that is actually rendered and visible to the player
public abstract class AAnimatedCharacter extends Sprite {
    private final GameConfig gameConfig;
    private RPGame rpGame;
    private String textureFileLocation;
    // currentTexture and currentAnimationAtlas contain only the current information for the character. The assets are loaded in memory inside the Skin object
    private Texture currentTexture;
    private TextureAtlas currentAnimationAtlas;
    private Animation<TextureAtlas.AtlasRegion> currentAnimation;
    private Sprite sprite;
    private ISkin skin;
    private SpriteBatch spriteBatch;
    private float elapsedTime;
    private String action;
    private Vector2 textureScreenLocation;
    private int sizeX;
    private int sizeY;

    public AAnimatedCharacter(ISkin skin, RPGame rpGame) {
        this.rpGame = rpGame;
        this.gameConfig = GameConfig.getInstance();
        this.skin = skin;
        this.action = "runningUp";
        this.currentAnimationAtlas = skin.getUp().getAnimationAtlas();
        this.currentTexture = skin.getUp().getTexture();
        this.currentAnimation = new Animation<>(gameConfig.getFrameTime(), currentAnimationAtlas.findRegions(skin.getUp().getAnimationName()));
        this.currentAnimation.setFrameDuration(gameConfig.getFrameTime());
        this.sizeX = 50;
        this.sizeY = 50;
    }

    public Vector2 getTextureScreenLocation() {
        return textureScreenLocation;
    }

    public void setTextureScreenLocation(Vector2 textureScreenLocation) {
        this.textureScreenLocation = textureScreenLocation;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Texture getCurrentTexture() {
        return currentTexture;
    }

    public void setCurrentTexture(Texture currentTexture) {
        this.currentTexture = currentTexture;
    }

    public TextureAtlas getCurrentAnimationAtlas() {
        return currentAnimationAtlas;
    }

    public void setCurrentAnimationAtlas(TextureAtlas currentAnimationAtlas) {
        this.currentAnimationAtlas = currentAnimationAtlas;
    }

    public Animation<TextureAtlas.AtlasRegion> getCurrentAnimation() {
        return currentAnimation;
    }

    public void setCurrentAnimation(Animation<TextureAtlas.AtlasRegion> currentAnimation) {
        this.currentAnimation = currentAnimation;
    }

    public RPGame getGame() {
        return rpGame;
    }

    public void setGame(RPGame game) {
        this.rpGame = game;
    }

    public String getTextureFileLocation() {
        return textureFileLocation;
    }

    public void setTextureFileLocation(String textureFileLocation) {
        this.textureFileLocation = textureFileLocation;
    }

    public Texture getTexture() {
        return currentTexture;
    }

    public void setTexture(Texture texture) {
        this.currentTexture = texture;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public int getSizeX() {
        return sizeX;
    }

    public void setSizeX(int sizeX) {
        this.sizeX = sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public void setSizeY(int sizeY) {
        this.sizeY = sizeY;
    }

    public ISkin getSkin() {
        return skin;
    }

    public void setSkin(ISkin skin) {
        this.skin = skin;
    }

    //sets the current atlas and texture for each frame based on the current action
    private void setCurrentFrame(){
        if(action.equals("runningUp")){
            currentAnimationAtlas = skin.getUp().getAnimationAtlas();
            setCurrentAnimation(new Animation<>(gameConfig.getFrameTime(), currentAnimationAtlas.findRegions(skin.getUp().getAnimationName())));
        }
        if(action.equals("runningDown")){
            currentAnimationAtlas = skin.getDown().getAnimationAtlas();
            setCurrentAnimation(new Animation<>(gameConfig.getFrameTime(), currentAnimationAtlas.findRegions(skin.getDown().getAnimationName())));
        }
        if(action.equals("runningLeft")){
            currentAnimationAtlas = skin.getLeft().getAnimationAtlas();
            setCurrentAnimation(new Animation<>(gameConfig.getFrameTime(), currentAnimationAtlas.findRegions(skin.getLeft().getAnimationName())));
        }
        if(action.equals("runningRight")){
            currentAnimationAtlas = skin.getRight().getAnimationAtlas();
            setCurrentAnimation(new Animation<>(gameConfig.getFrameTime(), currentAnimationAtlas.findRegions(skin.getRight().getAnimationName())));
        }
    }

    public void draw(float delta){
        elapsedTime += delta/1.5f;
        setCurrentFrame();
        TextureRegion currentFrame = new TextureRegion();
        currentFrame = currentAnimation.getKeyFrame(elapsedTime, true);
        //The factor used to divide the texture size is related to the dimension of the full sprite (number of images in each sprite), this might be an issue if we use sprites of different sizes
        //If the sizes of sprites are different or the factor is wrong the texture will not be on the expected possition.
        rpGame.batch.draw(currentFrame, textureScreenLocation.x * gameConfig.getPPM() - currentTexture.getWidth()/currentFrame.getTexture().getWidth()*25, textureScreenLocation.y*gameConfig.getPPM()- currentTexture.getHeight()/currentFrame.getTexture().getHeight()*2.5f,sizeX,sizeY);
    }

    public void dispose() {
        currentTexture.dispose();
        skin.dispose();
    }
}