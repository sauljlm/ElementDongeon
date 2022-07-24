package com.avengers.rpgame.logic.entities.character.components.skin.abstractFactory;

import com.avengers.rpgame.logic.entities.character.components.skin.ISkin;
import com.avengers.rpgame.logic.entities.character.components.skin.WaterSkeletonSkin;

public class WaterSkeletonSkinFactory implements ISkinFactory {

    @Override
    public ISkin createSkin() {
        WaterSkeletonSkin skin = new WaterSkeletonSkin();
        return skin;
    }
}
