package com.avengers.rpgame.graphics.dialog;

import com.avengers.rpgame.RPGame;
import com.avengers.rpgame.audio.SoundEffectsManager;
import com.avengers.rpgame.game.GameConfig;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Align;

import static com.avengers.rpgame.utils.Resources.*;

public class DialogManager {
    private static DialogManager instance;
    private Dialog dialog;
    private GameConfig gameConfig;
    private RPGame rpGame;

    private String messageMemento;

    private boolean active;

    private DialogManager() {
        dialog = new Dialog();
        gameConfig = GameConfig.getInstance();
        rpGame = RPGame.getInstance();
        active = false;
    }

    public void updateDialog(String message) {
        if(!message.equals("")){
            dialog.setDialogMessage(message);
            if(!message.equals(messageMemento)){
                SoundEffectsManager.getInstance().play(popUp, false);
                messageMemento = message;
            }
        }
    }

    public void updateSpeaker(String name) {
        if(dialog.getDialogMessage().isEmpty()){
            dialog.setDialogSpeaker(name);
        }
    }

    public void clean(){
        dialog.setDialogMessage("");
    }

    public boolean isEmpty(){
        return dialog.getDialogMessage().isEmpty();
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void draw () {
        if(!dialog.getDialogMessage().isEmpty()){
            Vector2 resolution = new Vector2((float)gameConfig.getResolutionHorizontal(), (float)gameConfig.getResolutionVertical());
            dialog.getDialogSprite().get_sprite().draw(rpGame.batch);
            dialog.getDialogFont().draw(rpGame.batch, dialog.getDialogSpeaker(), resolution.x*0.11f, resolution.y*0.21f);
            GlyphLayout text = new GlyphLayout(dialog.getDialogFont(), dialog.getDialogMessage(), Color.BROWN, 600, Align.left, true);
            dialog.getDialogFont().draw(rpGame.batch, text, resolution.x*0.07f, resolution.y*0.17f);
        } else {
            messageMemento = "";
        }
    }

    public static DialogManager getInstance() {
        if (instance == null) {
            instance = new DialogManager();
        }
        return instance;
    }
}
