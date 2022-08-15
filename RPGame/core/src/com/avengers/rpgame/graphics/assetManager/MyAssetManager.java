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
        load(resourceAndTheJourneyBeginsMusic, musicClass);
        load(resourceStoreThemeMusic, musicClass);
    }

    public void loadBackgrounds(){
        Class<Texture> textureClass = Texture.class;
        load(resourceMainBackground, textureClass);
        load(resourceBlurBackground, textureClass);
        load(resourceElementBackground, textureClass);
        load(resourceBlackWhiteBackground, textureClass);
        load(resourceFireworksBackground, textureClass);
        load(resourceFightBackgroundForest, textureClass);

        load(resourceFightBackgroundEarthDungeon, textureClass);
        load(resourceFightBackgroundWindDungeon, textureClass);
        load(resourceFightBackgroundWaterDungeon, textureClass);
        load(resourceFightBackgroundFireDungeon, textureClass);



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
        load(keyEarth, textureClass);
        load(keyWind, textureClass);
        load(keyFire, textureClass);
        load(keyWater, textureClass);
        load(keyElemental, textureClass);
        load(talismanEarth, textureClass);
        load(talismanWind, textureClass);
        load(talismanFire, textureClass);
        load(talismanWater, textureClass);
        load(skeletonShield, textureClass);
        load(skeletonMegaShield, textureClass);
        load(armor, textureClass);
        load(jewelers, textureClass);
        load(arrow, textureClass);
        load(axe, textureClass);
        load(bombArrow, textureClass);
        load(doubleArrow, textureClass);
        load(doubleHandSword, textureClass);
        load(ironSword, textureClass);
        load(silverSword, textureClass);
        load(tripleArrow, textureClass);
        load(mindBlow, textureClass);
        load(elementalSword, textureClass);
        load(elementalAxe, textureClass);
        load(goldSwordAxe, textureClass);
        load(rainArrows, textureClass);
        load(fireArrow, textureClass);
        load(windWaterArrow, textureClass);
        load(peaceMind, textureClass);
        load(health5, textureClass);
        load(blowIce, textureClass);
        load(throwFlames, textureClass);
        load(sharpBranch, textureClass);
        load(ciclon, textureClass);
        load(health20, textureClass);
        load(mentalClarity, textureClass);
        load(groupVitality, textureClass);
        load(iceBomb, textureClass);
        load(melt, textureClass);
        load(lightHealth, textureClass);
        load(midHealth, textureClass);
        load(totalHealth, textureClass);
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
