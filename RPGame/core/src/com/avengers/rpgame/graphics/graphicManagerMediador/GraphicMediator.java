package com.avengers.rpgame.graphics.graphicManagerMediador;

import com.avengers.rpgame.RPGame;
import com.avengers.rpgame.graphics.camera.CameraManager;
import com.avengers.rpgame.graphics.map.MapManager;
import com.avengers.rpgame.graphics.physics.PhysicsManager;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import static com.avengers.rpgame.utils.Resources.resourceOverworldMap;

public class GraphicMediator implements iMediador{
    private CameraManager cameraManager;
    private MapManager mapManager;
    private PhysicsManager physicsManager;

    public GraphicMediator(RPGame game) {
        inicializeGraphicManagers(game);
    }

    @Override
    public void inicializeGraphicManagers(RPGame game) {
        this.cameraManager = new CameraManager(game);
        this.mapManager = new MapManager(resourceOverworldMap, cameraManager.getCamera(), game);
        this.physicsManager = new PhysicsManager(new Vector2(0, 0f), mapManager, cameraManager.getCamera(), true);
    }

    @Override
    public void renderGraphicManagers(float delta) {
        mapManager.render();//Render the map first!
        physicsManager.simulate();
        cameraManager.action(delta);
    }

    public World getWorld() {
        return physicsManager.getWorld();
    }

    public void changeProjectionMatrix(){
        cameraManager.changeProjectionMatrix();
    }

    public void dispose(){
//        cameraManager.dispose();
        mapManager.dispose();
//        physicsManager.dispose();
    }

}
