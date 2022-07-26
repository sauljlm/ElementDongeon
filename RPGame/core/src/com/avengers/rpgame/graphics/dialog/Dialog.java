package com.avengers.rpgame.graphics.dialog;

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

    private static Array<String> dialogMessage = new Array<String>();
    private static String dialogSpeaker = "NPC";
    private DialogSprite dialogSprite = new DialogSprite();

    private BitmapFont dialogFont = FontFactory.createBitMapFont(Gdx.files.internal(Resources.resourceMainFont), Resources.generalDialogFontSize, Color.WHITE, false, Color.BLACK);
    private GameConfig gameConfig;
    private AbstractCharacter character;

    public Dialog () {
        gameConfig = GameConfig.getInstance();
        // this.character = playerParty.getActivePartyMember();
    }

    public static Array<String> getDialogMessage() {
        return dialogMessage;
    }

    public static void updateDialog(String message) {
        dialogMessage = new Array<String>();
        if (!message.equals("")) {
            dialogMessage.add(message);
        }
    }

    public static void updateSeaker(String name) {
        dialogSpeaker = name;
    }

    public void draw (SpriteBatch batch) {
        Vector2 resolution = new Vector2((float)gameConfig.getResolutionHorizontal(), (float)gameConfig.getResolutionVertical());

        dialogSprite.get_sprite().draw(batch);
        dialogFont.draw(batch, dialogSpeaker, resolution.x*0.11f, resolution.y*0.175f);

        for(String message: dialogMessage) {
            GlyphLayout text = new GlyphLayout(dialogFont, message, Color.WHITE, 600, Align.left, true);
            dialogFont.draw(batch, text, resolution.x*0.07f, resolution.y*0.135f);
        }
    }
}
