package com.avengers.rpgame.logic.entities.character.components.skin;

import com.avengers.rpgame.logic.entities.character.components.skin.components.AnimationAssets;

import static com.avengers.rpgame.utils.FileManager.loadTexture;
import static com.avengers.rpgame.utils.FileManager.loadTextureAtlas;
import static com.avengers.rpgame.utils.Resources.*;

public class ChiefEarthSkin extends ISkin{
    public ChiefEarthSkin() {
        setUp(new AnimationAssets(loadTexture(resourceChiefEarthSkeletonTextureUp), loadTextureAtlas(resourceChiefEarthSkeletonTextureMapUp), "up"));
        setDown(new AnimationAssets(loadTexture(resourceChiefEarthSkeletonTextureDown), loadTextureAtlas(resourceChiefEarthSkeletonTextureMapDown), "down"));
        setLeft(new AnimationAssets(loadTexture(resourceChiefEarthSkeletonTextureLeft), loadTextureAtlas(resourceChiefEarthSkeletonTextureMapLeft), "left"));
        setRight(new AnimationAssets(loadTexture(resourceChiefEarthSkeletonTextureRight), loadTextureAtlas(resourceChiefEarthSkeletonTextureMapRight), "right"));
    }
}
