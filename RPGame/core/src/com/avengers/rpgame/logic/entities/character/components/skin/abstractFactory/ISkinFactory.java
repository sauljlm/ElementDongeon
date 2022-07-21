package com.avengers.rpgame.logic.entities.character.components.skin.abstractFactory;

import com.avengers.rpgame.logic.entities.character.components.skin.ISkin;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public interface ISkinFactory {
    public ISkin createSkin();
}
