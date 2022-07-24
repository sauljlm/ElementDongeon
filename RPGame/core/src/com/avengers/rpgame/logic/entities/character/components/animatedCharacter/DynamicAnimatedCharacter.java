package com.avengers.rpgame.logic.entities.character.components.animatedCharacter;

import com.avengers.rpgame.RPGame;
import com.avengers.rpgame.data.gameStatus.GameStatus;
import com.avengers.rpgame.game.GameConfig;
import com.avengers.rpgame.logic.entities.character.components.skin.ISkin;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class DynamicAnimatedCharacter extends AAnimatedCharacter{
    private Body player;
    private GameConfig gameConfig;
    private World world;
    private Vector2 worldPosition;

    public DynamicAnimatedCharacter(ISkin skin, RPGame rpGame) {
        super(skin, rpGame);
        gameConfig = GameConfig.getInstance();
        world = GameStatus.getInstance().getWorld();
        worldPosition = new Vector2();
        determinePosition();
        this.player = createBox((int)worldPosition.x, (int) worldPosition.y, 12, 12, false, false);
        super.setTextureScreenLocation(player.getPosition());
    }

    public Body getPlayer() {
        return player;
    }

    public void setPlayer(Body player) {
        this.player = player;
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public void recreateBody(){
        this.world = GameStatus.getInstance().getWorld();
        Vector2 pos = new Vector2();
        GameStatus.getInstance().updateLocation();
        pos.x =GameStatus.getInstance().getParty().getPartyMember(1).getPosition().x*gameConfig.getPPM();
        pos.y = GameStatus.getInstance().getParty().getPartyMember(1).getPosition().y*gameConfig.getPPM();
        this.player = createBox((int)pos.x, (int) pos.y, 12, 12, false, false);
    }

    private void determinePosition(){
        if (GameStatus.getInstance().getStatus().equals("newGame")){
            worldPosition.x = gameConfig.getResolutionHorizontal() * 12 / 5;
            worldPosition.y = gameConfig.getResolutionVertical() / 5;
        }
        if (GameStatus.getInstance().getStatus().equals("loadedGame")){
            worldPosition.x =GameStatus.getInstance().getParty().getPartyMember(1).getPosition().x*gameConfig.getPPM();
            worldPosition.y = GameStatus.getInstance().getParty().getPartyMember(1).getPosition().y*gameConfig.getPPM();
        }
        if (GameStatus.getInstance().getStatus().equals("gameInProgress")){
            worldPosition.x =GameStatus.getInstance().getParty().getPartyMember(1).getPosition().x*gameConfig.getPPM();
            worldPosition.y = GameStatus.getInstance().getParty().getPartyMember(1).getPosition().y*gameConfig.getPPM();
        }
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
    @Override
    public void draw(float delta){
        super.setTextureScreenLocation(player.getPosition());
        super.draw(delta);
    }
}
