package com.avengers.rpgame.graphics.camera;

import com.avengers.rpgame.RPGame;
import com.avengers.rpgame.game.GameConfig;
import com.avengers.rpgame.logic.entities.Character;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;

public class CameraManager {
    private GameConfig gameConfig;
    private RPGame rpGame;
    private OrthographicCamera camera;
    private Vector3 position;

    public CameraManager(RPGame rpGame) {
        this.rpGame = rpGame;
        gameConfig = GameConfig.getInstance();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, gameConfig.getResolutionHorizontal()/gameConfig.getScale(), gameConfig.getResolutionVertical()/gameConfig.getScale() );
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    //This method centers the camera on the player when he moves
    private void cameraUpdate(float delta) {
        position.z = camera.position.z;
        camera.position.set(position);
        camera.update();
    }

    //The camera needs a character to follow him
    //Anytime we get info from box2D "world" we need to multiply by PPM to go to regular units
    public void action(float delta, Character character){
        position = new Vector3(character.getPlayer().getPosition().x * gameConfig.getPPM(), character.getPlayer().getPosition().y * gameConfig.getPPM(), 0);
        cameraUpdate(delta);
        rpGame.batch.setProjectionMatrix(camera.combined);
    }

    public void changeProjectionMatrix(){
        Matrix4 uiMatrix = camera.combined.cpy();
        uiMatrix.setToOrtho2D(0, 0, gameConfig.getResolutionHorizontal(), gameConfig.getResolutionVertical());
        rpGame.batch.setProjectionMatrix(uiMatrix);
    }

    public void  dispose(){
        rpGame.dispose();
    }
}
