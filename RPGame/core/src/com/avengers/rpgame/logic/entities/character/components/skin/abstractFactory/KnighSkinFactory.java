package com.avengers.rpgame.logic.entities.character.components.skin.abstractFactory;

import com.avengers.rpgame.logic.entities.character.components.skin.ISkin;
import com.avengers.rpgame.logic.entities.character.components.skin.KnightSkin;

public class KnighSkinFactory implements ISkinFactory {

    @Override
    public ISkin createSkin() {
        KnightSkin skin = new KnightSkin();
        return skin;
    }
}
