package com.avengers.rpgame.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;


//Patron facade?
public class FileManager {

    public static FileHandle loadFile(String fileLocation){
        return Gdx.files.internal(fileLocation);
    }

    public static Music loadMusic(String fileLocation){
        return Gdx.audio.newMusic(Gdx.files.internal(fileLocation));
    }

    public static Sound loadSound(String fileLocation){
        return Gdx.audio.newSound(Gdx.files.internal(fileLocation));
    }

    public static Texture loadTexture(String fileLocation){
        return new Texture(Gdx.files.internal(fileLocation));
    }

    public static TextureAtlas loadTextureAtlas(String fileLocation){ return new TextureAtlas(loadFile(fileLocation));}

}
