package com.avengers.rpgame.graphics.camera;

import com.avengers.rpgame.RPGame;
import com.avengers.rpgame.data.gameStatus.GameStatus;
import com.avengers.rpgame.game.GameConfig;
import com.avengers.rpgame.graphics.graphicManagerMediador.iGraphicManager;
import com.avengers.rpgame.logic.entities.character.components.animatedCharacter.DynamicAnimatedCharacter;
import com.avengers.rpgame.utils.StartingPositionsReducer;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class CameraManager extends iGraphicManager {
    private GameConfig gameConfig;

    private GameStatus gameStatus;
    private RPGame rpGame;
    private OrthographicCamera camera;
    private Vector3 position;

    private Vector2 distance;

    private Vector2 positionTransition;
    private int lastActivePartyMember;

    public CameraManager(RPGame rpGame) {
        this.rpGame = rpGame;
        gameConfig = GameConfig.getInstance();
        gameStatus = GameStatus.getInstance();
        positionTransition = new Vector2();
        distance = new Vector2();
        lastActivePartyMember =gameStatus.getPlayerParty().getActivePartyMemberId();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, gameConfig.getResolutionHorizontal()/gameConfig.getScale(), gameConfig.getResolutionVertical()/gameConfig.getScale() );
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    //This method centers the camera on the player when he moves
    private void cameraUpdate(float delta) {
        Vector2 positionOld = new Vector2(((DynamicAnimatedCharacter)gameStatus.getPlayerParty().getPartyMember(lastActivePartyMember).getAnimatedCharacter()).getPlayer().getPosition().x, ((DynamicAnimatedCharacter)gameStatus.getPlayerParty().getPartyMember(lastActivePartyMember).getAnimatedCharacter()).getPlayer().getPosition().y);
        Vector2 positionNew = new Vector2(((DynamicAnimatedCharacter)gameStatus.getPlayerParty().getActivePartyMember().getAnimatedCharacter()).getPlayer().getPosition().x, ((DynamicAnimatedCharacter)gameStatus.getPlayerParty().getActivePartyMember().getAnimatedCharacter()).getPlayer().getPosition().y);
        position = new Vector3(positionNew.x,positionNew.y, 0);
        distance = new Vector2(positionNew);

        if(lastActivePartyMember != gameStatus.getPlayerParty().getActivePartyMemberId()){
            distance.sub(positionTransition);
            if(positionTransition.x == 0){
                positionTransition.x = positionOld.x;
                positionTransition.y = positionOld.y;
            }

            positionTransition.x = positionTransition.x + (distance.x/Math.abs(distance.x)*0.07f);
            positionTransition.y = positionTransition.y + (distance.y/Math.abs(distance.y)*0.07f);
            position.x = positionTransition.x;
            position.y = positionTransition.y;
            if(Math.abs(distance.x) < 0.05f && Math.abs(distance.y) < 0.07f){
                lastActivePartyMember = gameStatus.getPlayerParty().getActivePartyMemberId();
                positionTransition.x = 0;
            }
        }
        position.x = position.x * gameConfig.getPPM();
        position.y = position.y * gameConfig.getPPM();
        if (gameConfig.isCameraMode()){
            position = new Vector3(gameStatus.getCameraPosition().x* gameConfig.getPPM(), gameStatus.getCameraPosition().y* gameConfig.getPPM(), 0);
        }
        camera.position.set(position);
        camera.update();
    }

    //The camera needs a character to follow him
    //Anytime we get info from box2D "world" we need to multiply by PPM to go to regular units
    public void action(float delta){
        cameraUpdate(delta);
        rpGame.batch.setProjectionMatrix(camera.combined);
    }

    public void changeProjectionMatrix(){
        Matrix4 uiMatrix = camera.combined.cpy();
        uiMatrix.setToOrtho2D(0, 0, gameConfig.getResolutionHorizontal(), gameConfig.getResolutionVertical());
        rpGame.batch.setProjectionMatrix(uiMatrix);
    }

    public void  dispose(){
    }
}
