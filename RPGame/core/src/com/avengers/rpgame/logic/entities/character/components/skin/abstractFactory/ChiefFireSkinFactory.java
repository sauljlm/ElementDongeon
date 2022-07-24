package com.avengers.rpgame.logic.entities.character.components.skin.abstractFactory;

import com.avengers.rpgame.logic.entities.character.components.skin.ChiefFireSkin;
import com.avengers.rpgame.logic.entities.character.components.skin.ISkin;

public class ChiefFireSkinFactory implements ISkinFactory {

    @Override
    public ISkin createSkin() {
        ChiefFireSkin skin = new ChiefFireSkin();
        return skin;
    }
}
