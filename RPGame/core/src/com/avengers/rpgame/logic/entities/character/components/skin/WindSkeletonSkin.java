package com.avengers.rpgame.logic.entities.character.components.skin;

import com.avengers.rpgame.logic.entities.character.components.skin.components.AnimationAssets;

import static com.avengers.rpgame.utils.FileManager.loadTexture;
import static com.avengers.rpgame.utils.FileManager.loadTextureAtlas;
import static com.avengers.rpgame.utils.Resources.*;

public class WindSkeletonSkin extends ISkin{
    public WindSkeletonSkin() {
        setUp(new AnimationAssets(loadTexture(resourceWindSkeletonTextureUp), loadTextureAtlas(resourceWindSkeletonTextureMapUp), "up"));
        setDown(new AnimationAssets(loadTexture(resourceWindSkeletonTextureDown), loadTextureAtlas(resourceWindSkeletonTextureMapDown), "down"));
        setLeft(new AnimationAssets(loadTexture(resourceWindSkeletonTextureLeft), loadTextureAtlas(resourceWindSkeletonTextureMapLeft), "left"));
        setRight(new AnimationAssets(loadTexture(resourceWindSkeletonTextureRight), loadTextureAtlas(resourceWindSkeletonTextureMapRight), "right"));
    }
}
