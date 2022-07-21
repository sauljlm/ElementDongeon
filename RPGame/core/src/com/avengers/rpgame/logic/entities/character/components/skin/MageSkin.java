package com.avengers.rpgame.logic.entities.character.components.skin;

import com.avengers.rpgame.logic.entities.character.components.skin.components.AnimationAssets;

import static com.avengers.rpgame.utils.FileManager.loadTexture;
import static com.avengers.rpgame.utils.FileManager.loadTextureAtlas;
import static com.avengers.rpgame.utils.Resources.*;

public class MageSkin extends ISkin{
    public MageSkin() {
        setUp(new AnimationAssets(loadTexture(resourceMageTextureUp), loadTextureAtlas(resourceMageTextureMapUp), "up"));
        setDown(new AnimationAssets(loadTexture(resourceMageTextureDown), loadTextureAtlas(resourceMageTextureMapDown), "down"));
        setLeft(new AnimationAssets(loadTexture(resourceMageTextureLeft), loadTextureAtlas(resourceMageTextureMapLeft), "left"));
        setRight(new AnimationAssets(loadTexture(resourceMageTextureRight), loadTextureAtlas(resourceMageTextureMapRight), "right"));
    }

}
