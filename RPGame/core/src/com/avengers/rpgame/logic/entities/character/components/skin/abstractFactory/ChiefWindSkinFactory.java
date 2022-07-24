package com.avengers.rpgame.logic.entities.character.components.skin.abstractFactory;

import com.avengers.rpgame.logic.entities.character.components.skin.ChiefWindSkin;
import com.avengers.rpgame.logic.entities.character.components.skin.ISkin;

public class ChiefWindSkinFactory implements ISkinFactory {

    @Override
    public ISkin createSkin() {
        ChiefWindSkin skin = new ChiefWindSkin();
        return skin;
    }
}
