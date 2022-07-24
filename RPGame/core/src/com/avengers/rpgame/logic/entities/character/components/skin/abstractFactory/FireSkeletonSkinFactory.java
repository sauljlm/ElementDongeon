package com.avengers.rpgame.logic.entities.character.components.skin.abstractFactory;

import com.avengers.rpgame.logic.entities.character.components.skin.FireSkeletonSkin;
import com.avengers.rpgame.logic.entities.character.components.skin.ISkin;

public class FireSkeletonSkinFactory implements ISkinFactory {

    @Override
    public ISkin createSkin() {
        FireSkeletonSkin skin = new FireSkeletonSkin();
        return skin;
    }
}
