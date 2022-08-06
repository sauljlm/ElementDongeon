package com.avengers.rpgame.audio;

import com.avengers.rpgame.game.GameConfig;
import com.avengers.rpgame.graphics.assetManager.MyAssetManager;
import com.badlogic.gdx.audio.Music;

import java.util.HashMap;

import static com.avengers.rpgame.utils.FileManager.loadMusic;
import static com.avengers.rpgame.utils.Resources.menuSoundEffect;

public class SoundEffectsManager {
    private static SoundEffectsManager instance;
    private MyAssetManager assetManager;

    private HashMap<String, Music> sounds;

    private SoundEffectsManager() {
        assetManager = MyAssetManager.getInstance();
        sounds = new HashMap<>();
    }

    public void play(String soundID, boolean loop){
        if(sounds.get(soundID) == null){
            sounds.put(soundID, loadMusic(soundID));
        }
        if(!sounds.get(soundID).isPlaying()){
            sounds.get(soundID).setLooping(loop);
            sounds.get(soundID).setVolume(GameConfig.getInstance().getSoundEffectsVolume());
            sounds.get(soundID).play();
        }
    }

    public void stop(String soundID){
        if(sounds.get(soundID) == null){
            sounds.put(soundID, loadMusic(soundID));
        }
        if(sounds.get(soundID).isPlaying()){
            sounds.get(soundID).stop();
        }
    }

    public static SoundEffectsManager getInstance() {
        if (instance == null) {
            instance = new SoundEffectsManager();
        }
        return instance;
    }
}
