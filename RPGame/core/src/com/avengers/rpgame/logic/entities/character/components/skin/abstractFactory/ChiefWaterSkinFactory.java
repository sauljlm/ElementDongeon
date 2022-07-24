package com.avengers.rpgame.logic.entities.character.components.skin.abstractFactory;

import com.avengers.rpgame.logic.entities.character.components.skin.ChiefWaterSkin;
import com.avengers.rpgame.logic.entities.character.components.skin.ISkin;

public class ChiefWaterSkinFactory implements ISkinFactory {

    @Override
    public ISkin createSkin() {
        ChiefWaterSkin skin = new ChiefWaterSkin();
        return skin;
    }
}
