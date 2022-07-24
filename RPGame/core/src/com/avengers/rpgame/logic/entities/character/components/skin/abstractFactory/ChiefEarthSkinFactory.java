package com.avengers.rpgame.logic.entities.character.components.skin.abstractFactory;

import com.avengers.rpgame.logic.entities.character.components.skin.ChiefEarthSkin;
import com.avengers.rpgame.logic.entities.character.components.skin.ISkin;

public class ChiefEarthSkinFactory implements ISkinFactory {

    @Override
    public ISkin createSkin() {
        ChiefEarthSkin skin = new ChiefEarthSkin();
        return skin;
    }
}
