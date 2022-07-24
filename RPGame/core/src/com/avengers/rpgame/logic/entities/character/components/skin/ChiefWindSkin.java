package com.avengers.rpgame.logic.entities.character.components.skin;

import com.avengers.rpgame.logic.entities.character.components.skin.components.AnimationAssets;

import static com.avengers.rpgame.utils.FileManager.loadTexture;
import static com.avengers.rpgame.utils.FileManager.loadTextureAtlas;
import static com.avengers.rpgame.utils.Resources.*;

public class ChiefWindSkin extends ISkin{
    public ChiefWindSkin() {
        setUp(new AnimationAssets(loadTexture(resourceChiefWindSkeletonTextureUp), loadTextureAtlas(resourceChiefWindSkeletonTextureMapUp), "up"));
        setDown(new AnimationAssets(loadTexture(resourceChiefWindSkeletonTextureDown), loadTextureAtlas(resourceChiefWindSkeletonTextureMapDown), "down"));
        setLeft(new AnimationAssets(loadTexture(resourceChiefWindSkeletonTextureLeft), loadTextureAtlas(resourceChiefWindSkeletonTextureMapLeft), "left"));
        setRight(new AnimationAssets(loadTexture(resourceChiefWindSkeletonTextureRight), loadTextureAtlas(resourceChiefWindSkeletonTextureMapRight), "right"));
    }
}
