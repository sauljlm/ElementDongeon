package com.avengers.rpgame.graphics.physics;

import com.avengers.rpgame.data.gameStatus.GameStatus;
import com.avengers.rpgame.game.GameConfig;
import com.avengers.rpgame.graphics.graphicManagerMediador.iGraphicManager;
import com.avengers.rpgame.graphics.map.MapManager;
import com.avengers.rpgame.graphics.map.TiledObjectParser;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

public class PhysicsManager extends iGraphicManager {
    private World world;
    private Vector2 gravity;
    private Box2DDebugRenderer debugRenderer;
    private MapManager mapManager;
    private GameConfig gameConfig;
    private boolean debug;
    OrthographicCamera camera;

    public PhysicsManager(Vector2 gravityVector, MapManager mapManager, OrthographicCamera camera, boolean debug) {
        this.gravity = gravityVector;
        this.world = new World(gravity, false);
        GameStatus.getInstance().setWorld(this.world);
        this.debugRenderer = new Box2DDebugRenderer();
        this.gameConfig = GameConfig.getInstance();
        this.debug = gameConfig.isDebugPhysics();
        this.camera = camera;
        this.mapManager = mapManager;

        //For performance reasons parsing of objects on map needs to be on constructor
        TiledObjectParser.parseTiledObjectLayer(world, this.mapManager.getMap().getLayers().get("collisionLayer").getObjects());
        TiledObjectParser.parseTiledInteractiveLayer(world, this.mapManager.getMap().getLayers().get("interactionLayer").getObjects());
    }

    public World getWorld() {
        return world;
    }

    public void simulate(){
        world.step((float)1/gameConfig.getFrameRate(), 6, 2); //sets the ticks on the game, how ofter we simulate the world
        if(debug){
            debugRenderer.render(world, camera.combined.scl(gameConfig.getPPM()));
        }
    }

    public void dispose(){
        world.dispose();
        debugRenderer.dispose();
        mapManager.dispose();
    }
}
