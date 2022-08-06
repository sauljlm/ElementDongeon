package com.avengers.rpgame.graphics.assetManager;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

import static com.avengers.rpgame.utils.Resources.*;

public class MyAssetManager extends AssetManager{
    private static MyAssetManager instance;

    private MyAssetManager() {
    }
    public void loadEverything(){
        loadMusicAssets();
        loadBackgrounds();
        loadTextures();
        loadSoundEffects();
    }

    public void loadMusicAssets(){
        Class<Music> musicClass = Music.class;
        load(resourceThemeMusic, musicClass);
        load(resourceGameOverMusic, musicClass);
        load(menuSoundEffect, musicClass);
    }

    public void loadBackgrounds(){
        Class<Texture> textureClass = Texture.class;
        load(resourceMainBackground, textureClass);
        load(resourceBlurBackground, textureClass);
        load(resourceElementBackground, textureClass);
        load(resourceBlackWhiteBackground, textureClass);
        load(resourceFireworksBackground, textureClass);
        load(resourceFightBackgroundForest, textureClass);
        load(resourceStoreScreen, textureClass);
    }

    public void loadTextures(){
        Class<Texture> textureClass = Texture.class;
        load(resourceCompanyLogo, textureClass);
        load(knightImage, textureClass);
        load(archerImage, textureClass);
        load(mageImage, textureClass);
        load(dialog, textureClass);
        load(coin, textureClass);
        load(speechBubble, textureClass);
        load(potion, textureClass);
        //Items textures
        load(resourceMageTextureUp, textureClass);
        load(resourceMageTextureDown, textureClass);
        load(resourceMageTextureLeft, textureClass);
        load(resourceMageTextureRight, textureClass);
        load(resourceKnightTextureUp, textureClass);
        load(resourceKnightTextureDown, textureClass);
        load(resourceKnightTextureLeft, textureClass);
        load(resourceKnightTextureRight, textureClass);
        load(resourceArcherTextureUp, textureClass);
        load(resourceArcherTextureDown, textureClass);
        load(resourceArcherTextureLeft, textureClass);
        load(resourceArcherTextureRight, textureClass);
        load(resourceEarthSkeletonTextureUp, textureClass);
        load(resourceEarthSkeletonTextureDown, textureClass);
        load(resourceEarthSkeletonTextureLeft, textureClass);
        load(resourceEarthSkeletonTextureRight, textureClass);
        load(resourceFireSkeletonTextureUp, textureClass);
        load(resourceFireSkeletonTextureDown, textureClass);
        load(resourceFireSkeletonTextureLeft, textureClass);
        load(resourceFireSkeletonTextureRight, textureClass);
        load(resourceWaterSkeletonTextureUp, textureClass);
        load(resourceWaterSkeletonTextureDown, textureClass);
        load(resourceWaterSkeletonTextureLeft, textureClass);
        load(resourceWaterSkeletonTextureRight, textureClass);
        load(resourceWindSkeletonTextureUp, textureClass);
        load(resourceWindSkeletonTextureDown, textureClass);
        load(resourceWindSkeletonTextureLeft, textureClass);
        load(resourceWindSkeletonTextureRight, textureClass);
        load(resourceChiefEarthSkeletonTextureUp, textureClass);
        load(resourceChiefEarthSkeletonTextureDown, textureClass);
        load(resourceChiefEarthSkeletonTextureLeft, textureClass);
        load(resourceChiefEarthSkeletonTextureRight, textureClass);
        load(resourceChiefFireSkeletonTextureUp, textureClass);
        load(resourceChiefFireSkeletonTextureDown, textureClass);
        load(resourceChiefFireSkeletonTextureLeft, textureClass);
        load(resourceChiefFireSkeletonTextureRight, textureClass);
        load(resourceChiefWaterSkeletonTextureUp, textureClass);
        load(resourceChiefWaterSkeletonTextureDown, textureClass);
        load(resourceChiefWaterSkeletonTextureLeft, textureClass);
        load(resourceChiefWaterSkeletonTextureRight, textureClass);
        load(resourceChiefWindSkeletonTextureUp, textureClass);
        load(resourceChiefWindSkeletonTextureDown, textureClass);
        load(resourceChiefWindSkeletonTextureLeft, textureClass);
        load(resourceChiefWindSkeletonTextureRight, textureClass);
        load(resourcePortalTexture, textureClass);
        load(HUDHeart, textureClass);
        load(HUDPotion, textureClass);
        load(HUDShield, textureClass);
        load(HUDDefaultWeapon, textureClass);
        load(HUDSword, textureClass);
        load(HUDSilverSword, textureClass);
        load(HUDDoubleHandSword, textureClass);
        load(HUDAxes, textureClass);
        load(HUDArrow, textureClass);
        load(HUDDoubleArrow, textureClass);
        load(HUDTripleArrow, textureClass);
        load(HUDBombArrow, textureClass);
        load(speechBubble, textureClass);
        load(coin, textureClass);
        load(potion, textureClass);
        load(sword, textureClass);
    }

    private void loadSoundEffects(){
        Class<Sound> soundEffectClass = Sound.class;
//        load(menuSoundEffect, soundEffectClass); Sound effects api does not provide a status so, in order not to reproduce multiple sounds we use Music
    }

    //PATRON Singleton
    public static MyAssetManager getInstance() {
        if (instance == null) {
            instance = new MyAssetManager();
        }
        return instance;
    }
}
