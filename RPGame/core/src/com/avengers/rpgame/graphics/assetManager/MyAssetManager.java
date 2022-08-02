package com.avengers.rpgame.graphics.assetManager;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

public class MyAssetManager{
    private AssetManager manager;

    private static MyAssetManager instance;

    private MyAssetManager() {
        manager = new AssetManager();
    }

    public void loadVisualAssets(){
        manager.load("graphics/sprites/actors/mage/TexturePackage/mageUp.png", Texture.class);
    }

    //PATRON Singleton
    public static MyAssetManager getInstance() {
        if (instance == null) {
            instance = new MyAssetManager();
        }
        return instance;
    }
}
