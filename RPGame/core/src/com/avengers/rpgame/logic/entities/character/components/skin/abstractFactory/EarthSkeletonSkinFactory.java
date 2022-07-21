package com.avengers.rpgame.logic.entities.character.components.skin.abstractFactory;

import com.avengers.rpgame.logic.entities.character.components.skin.ISkin;
import com.avengers.rpgame.logic.entities.character.components.skin.EarthSkeletonSkin;

public class EarthSkeletonSkinFactory implements ISkinFactory {

    @Override
    public ISkin createSkin() {
        EarthSkeletonSkin skin = new EarthSkeletonSkin();
        return skin;
    }
}
