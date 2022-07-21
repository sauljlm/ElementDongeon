package com.avengers.rpgame.logic.entities.character.components.skin;

import com.avengers.rpgame.logic.entities.character.components.skin.components.AnimationAssets;

import static com.avengers.rpgame.utils.FileManager.loadTexture;
import static com.avengers.rpgame.utils.FileManager.loadTextureAtlas;
import static com.avengers.rpgame.utils.Resources.*;
import static com.avengers.rpgame.utils.Resources.resourceKnightTextureMapRight;

public class KnightSkin extends ISkin{
    public KnightSkin() {
        setUp(new AnimationAssets(loadTexture(resourceKnightTextureUp), loadTextureAtlas(resourceKnightTextureMapUp), "up"));
        setDown(new AnimationAssets(loadTexture(resourceKnightTextureDown), loadTextureAtlas(resourceKnightTextureMapDown), "down"));
        setLeft(new AnimationAssets(loadTexture(resourceKnightTextureLeft), loadTextureAtlas(resourceKnightTextureMapLeft), "left"));
        setRight(new AnimationAssets(loadTexture(resourceKnightTextureRight), loadTextureAtlas(resourceKnightTextureMapRight), "right"));
    }
}
