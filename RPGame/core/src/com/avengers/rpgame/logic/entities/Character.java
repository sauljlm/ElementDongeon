package com.avengers.rpgame.logic.entities;

import com.avengers.rpgame.RPGame;
import com.avengers.rpgame.game.GameConfig;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import static com.avengers.rpgame.utils.FileManager.loadFile;
import static com.avengers.rpgame.utils.FileManager.loadTexture;

public class Character extends Sprite {
    private GameConfig gameConfig;
    private RPGame rpGame;
    private int idPlayer;
    private String name;
    private int level;
    private int subLevel;
    private double healthPoint;
    private double magicPoints;
    private int recoveryRate;
    private int strength;
    private int speed;
    private int resistance;
    private String textureFileLocation;
    private Body player;
    private Texture texture;
    private Sprite sprite;
    private World world;
    private Animation<TextureAtlas.AtlasRegion> upAnimation;
    private TextureAtlas runAtlas;
    private SpriteBatch spriteBatch;
    private float elapsedTime;

    public Character() {
    }

    public Character(String textureFileLocation, World world, RPGame rpGame) {
        this.rpGame = rpGame;
        this.gameConfig = GameConfig.getInstance();
        this.world = world;
        this.runAtlas = new TextureAtlas(loadFile("graphics/sprites/actors/TexturePackage/run.atlas"));
        this.texture = loadTexture(textureFileLocation);
        this.upAnimation = new Animation<>(gameConfig.getFrameTime(), runAtlas.findRegions("run"));
        this.upAnimation.setFrameDuration(gameConfig.getFrameTime());
        this.player = createBox((int)gameConfig.getResolutionHorizontal()*8/5, (int) gameConfig.getResolutionVertical() /5, 16, 16, false, true);
    }

    public Character(int idPlayer, String name, int level, int subLevel, double healthPoint, double magicPoints, int recoveryRate, int strenght, int speed, int resistance, String textureFileLocation, World world) {
        this.idPlayer = idPlayer;
        this.name = name;
        this.level = level;
        this.subLevel = subLevel;
        this.healthPoint = healthPoint;
        this.magicPoints = magicPoints;
        this.recoveryRate = recoveryRate;
        this.strength = strenght;
        this.speed = speed;
        this.resistance = resistance;
        //
        this.world = world;
        this.texture = loadTexture(textureFileLocation);
    }

    public RPGame getGame() {
        return rpGame;
    }

    public void setGame(RPGame game) {
        this.rpGame = game;
    }

    public int getIdPlayer() {
        return idPlayer;
    }

    public void setIdPlayer(int idPlayer) {
        this.idPlayer = idPlayer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getSubLevel() {
        return subLevel;
    }

    public void setSubLevel(int subLevel) {
        this.subLevel = subLevel;
    }

    public double getHealthPoint() {
        return healthPoint;
    }

    public void setHealthPoint(double healthPoint) {
        this.healthPoint = healthPoint;
    }

    public double getMagicPoints() {
        return magicPoints;
    }

    public void setMagicPoints(double magicPoints) {
        this.magicPoints = magicPoints;
    }

    public int getRecoveryRate() {
        return recoveryRate;
    }

    public void setRecoveryRate(int recoveryRate) {
        this.recoveryRate = recoveryRate;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getResistance() {
        return resistance;
    }

    public void setResistance(int resistance) {
        this.resistance = resistance;
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
        return texture;
    }

    @Override
    public void setTexture(Texture texture) {
        this.texture = texture;
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

    public void draw(float delta){
//        rpGame.batch.draw(this.texture, 600, 600);
        elapsedTime += delta;
        TextureRegion currentFrame = upAnimation.getKeyFrame(elapsedTime, true);
        rpGame.batch.draw(currentFrame, player.getPosition().x * gameConfig.getPPM() - texture.getWidth()/2f, player.getPosition().y*gameConfig.getPPM()-texture.getHeight()/3.5f);
//        rpGame.batch.draw(texture, player.getPosition().x * gameConfig.getPPM() - texture.getWidth()/2f, player.getPosition().y*gameConfig.getPPM()-texture.getHeight()/3.5f);
    }
}
