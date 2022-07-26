package com.avengers.rpgame.logic.entities.character.components.skin.abstractFactory;

import com.avengers.rpgame.logic.entities.character.components.skin.ISkin;
import com.avengers.rpgame.logic.entities.character.components.skin.MageSkin;

public class MageSkinFactory implements ISkinFactory {

    @Override
    public ISkin createSkin() {
        MageSkin skin = new MageSkin();
        return skin;
    }
}
