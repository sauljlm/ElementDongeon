package com.avengers.rpgame.logic.entities.character.components.skin.abstractFactory;

import com.avengers.rpgame.logic.entities.character.components.skin.ISkin;
import com.avengers.rpgame.logic.entities.character.components.skin.WindSkeletonSkin;

public class WindSkeletonSkinFactory implements ISkinFactory {

    @Override
    public ISkin createSkin() {
        WindSkeletonSkin skin = new WindSkeletonSkin();
        return skin;
    }
}
