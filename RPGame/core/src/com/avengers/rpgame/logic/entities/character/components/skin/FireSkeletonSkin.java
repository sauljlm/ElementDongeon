package com.avengers.rpgame.logic.entities.character.components.skin;

import com.avengers.rpgame.logic.entities.character.components.skin.components.AnimationAssets;

import static com.avengers.rpgame.utils.FileManager.loadTexture;
import static com.avengers.rpgame.utils.FileManager.loadTextureAtlas;
import static com.avengers.rpgame.utils.Resources.*;

public class FireSkeletonSkin extends ISkin{
    public FireSkeletonSkin() {
        setUp(new AnimationAssets(loadTexture(resourceFireSkeletonTextureUp), loadTextureAtlas(resourceFireSkeletonTextureMapUp), "up"));
        setDown(new AnimationAssets(loadTexture(resourceFireSkeletonTextureDown), loadTextureAtlas(resourceFireSkeletonTextureMapDown), "down"));
        setLeft(new AnimationAssets(loadTexture(resourceFireSkeletonTextureLeft), loadTextureAtlas(resourceFireSkeletonTextureMapLeft), "left"));
        setRight(new AnimationAssets(loadTexture(resourceFireSkeletonTextureRight), loadTextureAtlas(resourceFireSkeletonTextureMapRight), "right"));
    }
}
