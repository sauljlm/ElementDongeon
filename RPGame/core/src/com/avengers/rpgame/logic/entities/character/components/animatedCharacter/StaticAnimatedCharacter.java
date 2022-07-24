package com.avengers.rpgame.logic.entities.character.components.animatedCharacter;

import com.avengers.rpgame.RPGame;
import com.avengers.rpgame.logic.entities.character.components.skin.ISkin;
import com.badlogic.gdx.math.Vector2;

public class StaticAnimatedCharacter extends AAnimatedCharacter{
    public StaticAnimatedCharacter(ISkin skin, RPGame rpGame, Vector2 position) {
        super(skin, rpGame);
        super.setTextureScreenLocation(position);
        super.setAction("runningRight");
        super.setSizeX(250);
        super.setSizeY(250);
    }
}
