package com.avengers.rpgame.logic.entities.character.components.skin.abstractFactory;

import com.avengers.rpgame.logic.entities.character.components.skin.ArcherSkin;
import com.avengers.rpgame.logic.entities.character.components.skin.ISkin;
import com.avengers.rpgame.logic.entities.character.components.skin.MageSkin;

public class ArcherSkinFactory implements ISkinFactory {

    @Override
    public ISkin createSkin() {
        ArcherSkin skin = new ArcherSkin();
        return skin;
    }
}
