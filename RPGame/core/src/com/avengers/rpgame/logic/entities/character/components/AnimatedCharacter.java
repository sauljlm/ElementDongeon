package com.avengers.rpgame.logic.entities.character.components;

import com.avengers.rpgame.RPGame;
import com.avengers.rpgame.game.GameConfig;
import com.avengers.rpgame.logic.entities.character.components.skin.Skin;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import static com.avengers.rpgame.utils.FileManager.loadFile;
import static com.avengers.rpgame.utils.FileManager.loadTexture;

//Contains the information and methods of the grpahical entity of the character that is actually rendered and visible to the player
public class AnimatedCharacter extends Sprite {
    private GameConfig gameConfig;
    private RPGame rpGame;
    private String textureFileLocation;
    private Body player;
    // currentTexture and currentAnimationAtlas contain only the current information for the character. The assets are loaded on memory inside the Skin object
    private Texture currentTexture;
    private TextureAtlas currentAnimationAtlas;
    private Animation<TextureAtlas.AtlasRegion> currentAnimation;
    private Sprite sprite;
    private World world;
    private Skin skin;
    private SpriteBatch spriteBatch;
    private float elapsedTime;
    private String action;

    public AnimatedCharacter() {
    }

    public AnimatedCharacter(Skin skin, World world, RPGame rpGame) {
        this.rpGame = rpGame;
        this.gameConfig = GameConfig.getInstance();
        this.world = world;
        this.skin = skin;
        this.action = "runningUp";
        this.currentAnimationAtlas = skin.getUp().getAnimationAtlas();
        this.currentTexture = skin.getUp().getTexture();
        this.currentAnimation = new Animation<>(gameConfig.getFrameTime(), currentAnimationAtlas.findRegions(skin.getUp().getAnimationName()));
        this.currentAnimation.setFrameDuration(gameConfig.getFrameTime());
        this.player = createBox((int)gameConfig.getResolutionHorizontal()*8/5, (int) gameConfig.getResolutionVertical() /5, 16, 16, false, true);
    }

    public AnimatedCharacter(String textureFileLocation,String textureAtlas) {
        this.gameConfig = GameConfig.getInstance();
        this.currentAnimationAtlas = new TextureAtlas(loadFile(textureAtlas));
        this.currentTexture = loadTexture(textureFileLocation);
        this.currentAnimation = new Animation<>(gameConfig.getFrameTime(), currentAnimationAtlas.findRegions("run"));
        this.currentAnimation.setFrameDuration(gameConfig.getFrameTime());
        this.player = createBox((int)gameConfig.getResolutionHorizontal()/2, (int) gameConfig.getResolutionVertical() /2, 16, 16, false, true);
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

    public Body getPlayer() {
        return player;
    }

    public void setPlayer(Body player) {
        this.player = player;
    }

    @Override
    public Texture getTexture() {
        return currentTexture;
    }

    @Override
    public void setTexture(Texture texture) {
        this.currentTexture = texture;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    private Body createBox(int posX, int posY, int width, int height, boolean isStatic, boolean isFixedRotation) {
        Body body;
        BodyDef bodyDef = new BodyDef(); //Creates the physical definition for body
        if(isStatic)
            bodyDef.type = BodyDef.BodyType.StaticBody;
        else
            bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(posX/gameConfig.getPPM(),posY/gameConfig.getPPM());
        bodyDef.fixedRotation = isFixedRotation; //this stops the player from rotating
        body = world.createBody(bodyDef); //this initializes the player body using the def and puts it inside the world

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width / 2f / gameConfig.getPPM(), height / 2f / gameConfig.getPPM()); // /2 cause box2D counts stuff from center, so 32 x 32 would be 64, /PPM to scale down into box2D units

        body.createFixture(shape, 1.0f);
        shape.dispose(); //As the shape is already "used" we can dispose it
        return body;
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
        elapsedTime += delta;
        setCurrentFrame();
        TextureRegion currentFrame = new TextureRegion();
        currentFrame = currentAnimation.getKeyFrame(elapsedTime, true);
        //The factor used to divide the texture size is related to the dimension of the full sprite (number of images in each sprite), this might be an issue if we use sprites of different sizes
        //If the sizes of sprites are different or the factor is wrong the texture will not be on the expected possition.
        rpGame.batch.draw(currentFrame, player.getPosition().x * gameConfig.getPPM() - currentTexture.getWidth()/8.3f, player.getPosition().y*gameConfig.getPPM()- currentTexture.getHeight()/30f);
    }
}
