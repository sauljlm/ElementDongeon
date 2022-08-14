package com.avengers.rpgame.graphics.hud;

import com.avengers.rpgame.RPGame;
import com.avengers.rpgame.data.gameStatus.GameStatus;
import com.avengers.rpgame.game.GameConfig;
import com.badlogic.gdx.graphics.g2d.Sprite;
import static com.avengers.rpgame.utils.FileManager.loadTexture;
import static com.avengers.rpgame.utils.Resources.dungeonEarthOverlayResource;

public class DungeonOverlay {
    private GameConfig gameConfig;
    private GameStatus gameStatus;
    private RPGame rpGame;
    private Sprite dungeonOverlay;

    private String location;

    private boolean lastState;
    private float alpha;

    public DungeonOverlay() {
        gameConfig = GameConfig.getInstance();
        gameStatus = GameStatus.getInstance();
        rpGame = RPGame.getInstance();
        dungeonOverlay = new Sprite(loadTexture(dungeonEarthOverlayResource));
    }

    public void draw() {
        location = gameStatus.getCurrentLocation();
        switch (location) {
            case "overworld":
                lastState = false;
            case "earthDungeon":
                dungeonOverlay = new Sprite(loadTexture(dungeonEarthOverlayResource));
                break;
            default:
                break;
        }
        if(!location.contains("overworld")){
            if(!lastState){
                alpha =0;
            }
            if(alpha+0.02<1){
                alpha +=0.02;
            } else {
                alpha =1;
            }
            dungeonOverlay.setAlpha(alpha);
            lastState = true;
            dungeonOverlay.draw(rpGame.batch);
        }
        if(location.contains("overworld")){
            if(lastState){
                alpha =1;
            }
            if(alpha-0.02>0){
                alpha -=0.02;
            } else {
                alpha =0;
            }
            dungeonOverlay.setAlpha(alpha);
            lastState = false;
            dungeonOverlay.draw(rpGame.batch);
        }
    }


}
