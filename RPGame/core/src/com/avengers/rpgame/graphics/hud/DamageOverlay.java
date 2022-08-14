package com.avengers.rpgame.graphics.hud;

import com.avengers.rpgame.RPGame;
import com.avengers.rpgame.data.gameStatus.GameStatus;
import com.avengers.rpgame.game.GameConfig;
import com.avengers.rpgame.utils.Utils;
import com.badlogic.gdx.graphics.g2d.Sprite;

import static com.avengers.rpgame.utils.FileManager.loadTexture;
import static com.avengers.rpgame.utils.Resources.damageOverlayResource;
import static com.avengers.rpgame.utils.Resources.dungeonEarthOverlayResource;

public class DamageOverlay {
    private GameConfig gameConfig;
    private GameStatus gameStatus;
    private RPGame rpGame;
    private Sprite damageOverlay;

    private String location;

    private boolean lastState;
    private float alpha;
    private int healthMemento;

    public DamageOverlay() {
        gameConfig = GameConfig.getInstance();
        gameStatus = GameStatus.getInstance();
        rpGame = RPGame.getInstance();
        damageOverlay = new Sprite(loadTexture(damageOverlayResource));
        healthMemento = gameStatus.getPlayerParty().getActivePartyMember().getHealthPoints();
        lastState = false;
    }

    public void draw() {
        if(gameStatus.getDamageStatus().equals("takingDamage")){
            if(!lastState){
                alpha =0;
            }
            if(alpha+0.02<1){
                alpha +=0.02;
            } else {
                alpha =1;
            }
            damageOverlay.setAlpha(alpha);
            lastState = true;
            damageOverlay.draw(rpGame.batch);
        }
        if(gameStatus.getDamageStatus().equals("ok")){
            if(lastState){
                alpha =1;
            }
            if(alpha-0.02>0){
                alpha -=0.02;
            } else {
                alpha =0;
            }
            damageOverlay.setAlpha(alpha);
            lastState = false;
            damageOverlay.draw(rpGame.batch);
        }



//        damageOverlay.draw(rpGame.batch);
//        if(lastState){
//            if(lastState){
//                alpha =1;
//            }
//            if(alpha-0.02>0){
//                alpha -=0.02;
//            } else {
//                alpha =0;
//            }
//            damageOverlay.setAlpha(alpha);
//            lastState = false;
//            damageOverlay.draw(rpGame.batch);
//        }
    }
}
