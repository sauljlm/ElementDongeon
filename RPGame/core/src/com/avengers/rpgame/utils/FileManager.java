package com.avengers.rpgame.utils;

import com.avengers.rpgame.game.GameConfig;
import com.avengers.rpgame.graphics.assetManager.MyAssetManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import java.io.IOError;


//Patron facade?
public class FileManager {
    private static GameConfig config = GameConfig.getInstance();
    private static MyAssetManager assetManager = MyAssetManager.getInstance();

    public static FileHandle loadFile(String fileLocation){
        return Gdx.files.internal(fileLocation);
    }

    public static Music loadMusic(String fileLocation){
        if(!assetManager.isLoaded(fileLocation)) {
            assetManager.load(fileLocation, Music.class);
            while (!assetManager.update()){
            }
            System.out.println("WARNING, Asset loaded in runtime: "+fileLocation+" "+assetManager.getProgress()*100+" %");
        }
        return assetManager.get(fileLocation);
    }

    public static Sound loadSound(String fileLocation){
        if(!assetManager.isLoaded(fileLocation)) {
            assetManager.load(fileLocation, Sound.class);
            while (!assetManager.update()){
            }
            System.out.println("WARNING, Asset loaded in runtime: "+fileLocation+" "+assetManager.getProgress()*100+" %");
        }
        return assetManager.get(fileLocation);
    }

    public static Texture loadTexture(String fileLocation){
        if(!assetManager.isLoaded(fileLocation)) {
            assetManager.load(fileLocation, Texture.class);
            while (!assetManager.update()){
            }
            System.out.println("WARNING, Asset loaded in runtime: "+fileLocation+" "+assetManager.getProgress()*100+" %");
        }
            return assetManager.get(fileLocation);
    }

    public static TextureAtlas loadTextureAtlas(String fileLocation){ return new TextureAtlas(loadFile(fileLocation));}

    public static Skin loadUISkin(String fileLocation){
        return new Skin(Gdx.files.internal(fileLocation));
    }
}
