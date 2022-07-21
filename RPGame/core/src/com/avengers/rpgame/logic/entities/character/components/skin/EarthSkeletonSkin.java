package com.avengers.rpgame.logic.entities.character.components.skin;

import com.avengers.rpgame.logic.entities.character.components.skin.components.AnimationAssets;

import static com.avengers.rpgame.utils.FileManager.loadTexture;
import static com.avengers.rpgame.utils.FileManager.loadTextureAtlas;
import static com.avengers.rpgame.utils.Resources.*;

public class EarthSkeletonSkin extends ISkin{
    public EarthSkeletonSkin() {
        setUp(new AnimationAssets(loadTexture(resourceEarthSkeletonTextureUp), loadTextureAtlas(resourceEarthSkeletonTextureMapUp), "up"));
        setDown(new AnimationAssets(loadTexture(resourceEarthSkeletonTextureDown), loadTextureAtlas(resourceEarthSkeletonTextureMapDown), "down"));
        setLeft(new AnimationAssets(loadTexture(resourceEarthSkeletonTextureLeft), loadTextureAtlas(resourceEarthSkeletonTextureMapLeft), "left"));
        setRight(new AnimationAssets(loadTexture(resourceEarthSkeletonTextureRight), loadTextureAtlas(resourceEarthSkeletonTextureMapRight), "right"));
    }
}
