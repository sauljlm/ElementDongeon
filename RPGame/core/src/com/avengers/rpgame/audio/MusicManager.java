package com.avengers.rpgame.audio;

import com.avengers.rpgame.RPGame;
import com.avengers.rpgame.game.GameConfig;
import com.avengers.rpgame.graphics.assetManager.MyAssetManager;
import com.avengers.rpgame.graphics.screens.*;
import com.badlogic.gdx.audio.Music;

import java.util.HashMap;

import static com.avengers.rpgame.utils.FileManager.loadMusic;
import static com.avengers.rpgame.utils.Resources.*;

public class MusicManager {
    private static MusicManager instance;

    private MyAssetManager assetManager;

    private Music currentMusic;

    private String currentMusicID;

    public MusicManager() {
        assetManager = MyAssetManager.getInstance();
        currentMusic = loadMusic(resourceAndTheJourneyBeginsMusic);
        currentMusicID = "";
    }
    public void play(String screenId) {
        switch (screenId) {
            case "CharacterSelectionScreen":
                screenId = resourceThemeMusic;
                break;
            case "CreditScreen":
                screenId = credits;
                break;
            case "FightScreen":
                screenId = resourceFightMusic;
                break;
            case "GameOverScreen":
                screenId = resourceGameOverMusic;
                break;
            case "LoadGameScreen":
                screenId = resourceThemeMusic;
                break;
            case "LoadingOverworldScreen":
                screenId = resourceThemeMusic;
                break;
            case "LoadScreen":
                screenId = resourceThemeMusic;
                break;
            case "MainMenuScreen":
                screenId = resourceThemeMusic;
                break;
            case "NewGameScreen":
                screenId = resourceThemeMusic;
                break;
            case "OverworldScreen":
                screenId = resourceAndTheJourneyBeginsMusic;
                break;
            case "PauseScreen":
                screenId = resourceThemeMusic;
                break;
            case "SaveSlotSelectionScreen":
                screenId = resourceThemeMusic;
                break;
            case "SoundOptionScreen":
                screenId = resourceThemeMusic;
                break;
            case "StoreScreen":
                screenId = resourceStoreThemeMusic;
                break;
            default:
                screenId = resourceThemeMusic;
                break;
        }
        if(!currentMusicID.equals(screenId)){
            currentMusic.stop();
            currentMusic = loadMusic(screenId);
            currentMusic.setLooping(true);
            currentMusic.play();
        }
        currentMusicID = screenId;
        currentMusic.setVolume(GameConfig.getInstance().getMusicVolume());
    }

    public void updateVolume (float volume){
        currentMusic.setVolume(volume);
    }

    public static MusicManager getInstance() {
        if (instance == null) {
            instance = new MusicManager();
        }
        return instance;
    }
}
