package com.avengers.rpgame.logic.entities.character.components.skin;

import com.avengers.rpgame.logic.entities.character.components.skin.components.AnimationAssets;

import static com.avengers.rpgame.utils.FileManager.loadTexture;
import static com.avengers.rpgame.utils.FileManager.loadTextureAtlas;
import static com.avengers.rpgame.utils.Resources.*;

public class ChiefFireSkin extends ISkin{
    public ChiefFireSkin() {
        setUp(new AnimationAssets(loadTexture(resourceChiefFireSkeletonTextureUp), loadTextureAtlas(resourceChiefFireSkeletonTextureMapUp), "up"));
        setDown(new AnimationAssets(loadTexture(resourceChiefFireSkeletonTextureDown), loadTextureAtlas(resourceChiefFireSkeletonTextureMapDown), "down"));
        setLeft(new AnimationAssets(loadTexture(resourceChiefFireSkeletonTextureLeft), loadTextureAtlas(resourceChiefFireSkeletonTextureMapLeft), "left"));
        setRight(new AnimationAssets(loadTexture(resourceChiefFireSkeletonTextureRight), loadTextureAtlas(resourceChiefFireSkeletonTextureMapRight), "right"));
    }
}
