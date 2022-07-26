package com.avengers.rpgame.logic.entities.character.components.skin;

import com.avengers.rpgame.logic.entities.character.components.skin.components.AnimationAssets;

import static com.avengers.rpgame.utils.FileManager.loadTexture;
import static com.avengers.rpgame.utils.FileManager.loadTextureAtlas;
import static com.avengers.rpgame.utils.Resources.*;

public class ArcherSkin extends ISkin{
    public ArcherSkin() {
       setUp(new AnimationAssets(loadTexture(resourceArcherTextureUp), loadTextureAtlas(resourceArcherTextureMapUp), "up"));
       setDown(new AnimationAssets(loadTexture(resourceArcherTextureDown), loadTextureAtlas(resourceArcherTextureMapDown), "down"));
       setLeft(new AnimationAssets(loadTexture(resourceArcherTextureLeft), loadTextureAtlas(resourceArcherTextureMapLeft), "left"));
       setRight(new AnimationAssets(loadTexture(resourceArcherTextureRight), loadTextureAtlas(resourceArcherTextureMapRight), "right"));
    }
}
