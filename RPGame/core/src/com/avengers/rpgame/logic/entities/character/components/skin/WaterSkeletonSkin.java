package com.avengers.rpgame.logic.entities.character.components.skin;

import com.avengers.rpgame.logic.entities.character.components.skin.components.AnimationAssets;

import static com.avengers.rpgame.utils.FileManager.loadTexture;
import static com.avengers.rpgame.utils.FileManager.loadTextureAtlas;
import static com.avengers.rpgame.utils.Resources.*;

public class WaterSkeletonSkin extends ISkin{
    public WaterSkeletonSkin() {
        setUp(new AnimationAssets(loadTexture(resourceWaterSkeletonTextureUp), loadTextureAtlas(resourceWaterSkeletonTextureMapUp), "up"));
        setDown(new AnimationAssets(loadTexture(resourceWaterSkeletonTextureDown), loadTextureAtlas(resourceWaterSkeletonTextureMapDown), "down"));
        setLeft(new AnimationAssets(loadTexture(resourceWaterSkeletonTextureLeft), loadTextureAtlas(resourceWaterSkeletonTextureMapLeft), "left"));
        setRight(new AnimationAssets(loadTexture(resourceWaterSkeletonTextureRight), loadTextureAtlas(resourceWaterSkeletonTextureMapRight), "right"));
    }
}
