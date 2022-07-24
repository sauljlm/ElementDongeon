package com.avengers.rpgame.logic.entities.character.components.skin;

import com.avengers.rpgame.logic.entities.character.components.skin.components.AnimationAssets;

import static com.avengers.rpgame.utils.FileManager.loadTexture;
import static com.avengers.rpgame.utils.FileManager.loadTextureAtlas;
import static com.avengers.rpgame.utils.Resources.*;

public class ChiefWaterSkin extends ISkin{
    public ChiefWaterSkin() {
        setUp(new AnimationAssets(loadTexture(resourceChiefWaterSkeletonTextureUp), loadTextureAtlas(resourceChiefWaterSkeletonTextureMapUp), "up"));
        setDown(new AnimationAssets(loadTexture(resourceChiefWaterSkeletonTextureDown), loadTextureAtlas(resourceChiefWaterSkeletonTextureMapDown), "down"));
        setLeft(new AnimationAssets(loadTexture(resourceChiefWaterSkeletonTextureLeft), loadTextureAtlas(resourceChiefWaterSkeletonTextureMapLeft), "left"));
        setRight(new AnimationAssets(loadTexture(resourceChiefWaterSkeletonTextureRight), loadTextureAtlas(resourceChiefWaterSkeletonTextureMapRight), "right"));
    }
}
