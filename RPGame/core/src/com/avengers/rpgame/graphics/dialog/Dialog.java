package com.avengers.rpgame.graphics.dialog;

import com.avengers.rpgame.RPGame;
import com.avengers.rpgame.game.GameConfig;
import com.avengers.rpgame.graphics.dialog.elements.DialogSprite;
import com.avengers.rpgame.graphics.text.FontFactory;
import com.avengers.rpgame.utils.Resources;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;

public class Dialog {
    private RPGame rpGame;
    private GameConfig gameConfig;
    private String dialogSpeaker;
    private String dialogMessage;
    private DialogSprite dialogSprite;
    private BitmapFont dialogFont;


    protected Dialog () {
        rpGame = RPGame.getInstance();
        gameConfig = GameConfig.getInstance();
        dialogSpeaker= "Ayuda";
        dialogMessage = "El reino te necesita aventurero";
        dialogSprite = new DialogSprite(200);
        dialogFont = FontFactory.createBitMapFont(Gdx.files.internal(Resources.resourceMainFont), Resources.generalDialogFontSize, Color.WHITE, false, Color.BLACK);
    }

    public String getDialogSpeaker() {
        return dialogSpeaker;
    }

    public void setDialogSpeaker(String dialogSpeaker) {
        this.dialogSpeaker = dialogSpeaker;
    }

    public String getDialogMessage() {
        return dialogMessage;
    }

    public void setDialogMessage(String dialogMessage) {
        this.dialogMessage = dialogMessage;
    }

    public DialogSprite getDialogSprite() {
        return dialogSprite;
    }

    public void setDialogSprite(DialogSprite dialogSprite) {
        this.dialogSprite = dialogSprite;
    }

    public BitmapFont getDialogFont() {
        return dialogFont;
    }

    public void setDialogFont(BitmapFont dialogFont) {
        this.dialogFont = dialogFont;
    }
}
