package com.avengers.rpgame.graphics.dialog;

import com.avengers.rpgame.RPGame;
import com.avengers.rpgame.game.GameConfig;
import com.avengers.rpgame.graphics.dialog.elements.DialogSprite;
import com.avengers.rpgame.graphics.text.FontFactory;
import com.avengers.rpgame.logic.entities.Party;
import com.avengers.rpgame.logic.entities.character.abstractCharacter.AbstractCharacter;
import com.avengers.rpgame.utils.Resources;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;

public class Dialog {
    private RPGame rpGame;
    private Array<String> dialogMessage = new Array<String>();
    private String dialogSpeaker = "NPC";
    private DialogSprite dialogSprite;
    private BitmapFont dialogFont = FontFactory.createBitMapFont(Gdx.files.internal(Resources.resourceMainFont), Resources.generalDialogFontSize, Color.WHITE, false, Color.BLACK);
    private GameConfig gameConfig;
    private AbstractCharacter character;

    public Dialog () {
        gameConfig = GameConfig.getInstance();
        rpGame = RPGame.getInstance();
        // this.character = playerParty.getActivePartyMember();
    }

    public Array<String> getDialogMessage() {
        return dialogMessage;
    }

    public void updateDialog(String message) {
        RPGame game = RPGame.getInstance();
        dialogMessage = new Array<String>();
        if (!message.equals("")) {
            dialogMessage.add(message);
        }
        if (dialogMessage.size > 0) {
            game.batch.begin();
            draw();
            game.batch.end();
        }
    }

    public void updateSeaker(String name) {
        dialogSpeaker = name;
    }

    public void draw () {
        Vector2 resolution = new Vector2((float)gameConfig.getResolutionHorizontal(), (float)gameConfig.getResolutionVertical());

        for(String message: dialogMessage) {
            if (message.length() > 70) {
                dialogSprite = new DialogSprite(200);
                dialogSprite.get_sprite().draw(rpGame.batch);
                dialogFont.draw(rpGame.batch, dialogSpeaker, resolution.x*0.11f, resolution.y*0.21f);
                GlyphLayout text = new GlyphLayout(dialogFont, message, Color.WHITE, 600, Align.left, true);
                dialogFont.draw(rpGame.batch, text, resolution.x*0.07f, resolution.y*0.17f);
            } else {
                dialogSprite = new DialogSprite(150);
                dialogSprite.get_sprite().draw(rpGame.batch);
                dialogFont.draw(rpGame.batch, dialogSpeaker, resolution.x*0.11f, resolution.y*0.175f);
                GlyphLayout text = new GlyphLayout(dialogFont, message, Color.WHITE, 600, Align.left, true);
                dialogFont.draw(rpGame.batch, text, resolution.x*0.07f, resolution.y*0.135f);
            }
        }
    }
}
