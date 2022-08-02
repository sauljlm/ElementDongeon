package com.avengers.rpgame.logic.entities.character.components.animatedCharacter;

import com.avengers.rpgame.RPGame;
import com.avengers.rpgame.data.gameStatus.GameStatus;
import com.avengers.rpgame.game.GameConfig;
import com.avengers.rpgame.logic.entities.character.components.skin.ISkin;
import com.avengers.rpgame.logic.factories.BodyFactory;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class DynamicAnimatedCharacter extends AAnimatedCharacter{
    private Body player;
    private GameConfig gameConfig;
    private World world;
    private Vector2 worldPosition;

    public DynamicAnimatedCharacter(ISkin skin, RPGame rpGame, Vector2 worldPosition) {
        super(skin, rpGame);
        gameConfig = GameConfig.getInstance();
        world = GameStatus.getInstance().getWorld();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(12 / 2f / gameConfig.getPPM(), 12 / 2f / gameConfig.getPPM()); // /2 cause box2D counts stuff from center, so 32 x 32 would be 64, /PPM to scale down into box2D units

        this.player = BodyFactory.createBody(world, shape, worldPosition,false, false);
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

    public Vector2 getWorldPosition() {
        return worldPosition;
    }

    public void setWorldPosition(Vector2 worldPosition) {
        this.worldPosition = worldPosition;
    }

    public void recreateBody(){
        this.world = GameStatus.getInstance().getWorld();
        Vector2 pos = new Vector2();
//        GameStatus.getInstance().updateLocation();
        pos.x = this.getPlayer().getPosition().x*gameConfig.getPPM();
        pos.y = this.getPlayer().getPosition().y*gameConfig.getPPM();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(12 / 2f / gameConfig.getPPM(), 12 / 2f / gameConfig.getPPM()); // /2 cause box2D counts stuff from center, so 32 x 32 would be 64, /PPM to scale down into box2D units
        this.player = BodyFactory.createBody(world, shape, pos, false, false);
    }

    @Override
    public void draw(float delta){
        super.setTextureScreenLocation(player.getPosition());
        super.draw(delta);
    }
}
