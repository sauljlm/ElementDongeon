package com.avengers.rpgame.graphics.screens;

import com.avengers.rpgame.RPGame;
import com.avengers.rpgame.audio.MusicManager;
import com.avengers.rpgame.graphics.assetManager.MyAssetManager;
import com.badlogic.gdx.Screen;

public class ScreeenManager {
    private static ScreeenManager instance;
    private MyAssetManager assetManager;
    private RPGame rpGame;
    private Screen currentScreen;

    private ScreeenManager() {
        rpGame = RPGame.getInstance();
        assetManager = MyAssetManager.getInstance();
    }

    public void changeScreen(String screenName) {
        if(currentScreen != null){
            currentScreen.dispose();
//            assetManager.clear();
            assetManager.loadEverything();
        }
        MusicManager.getInstance().play(screenName);
        switch (screenName) {
            case "CharacterSelectionScreen":
                currentScreen = new CharacterSelectionScreen();
                break;
            case "CreditScreen":
                currentScreen = new CreditScreen();
                break;
            case "FightScreen":
                currentScreen = new FightScreen();
                break;
            case "GameOverScreen":
                currentScreen = new GameOverScreen();
                break;
            case "LoadGameScreen":
                currentScreen = new LoadGameScreen();
                break;
            case "LoadingOverworldScreen":
                currentScreen = new LoadingOverworldScreen();
                break;
            case "LoadScreen":
                currentScreen = new LoadScreen();
                break;
            case "MainMenuScreen":
                currentScreen = new MainMenuScreen();
                break;
            case "NewGameScreen":
                currentScreen = new NewGameScreen();
                break;
            case "OverworldScreen":
                currentScreen = new OverworldScreen();
                break;
            case "PauseScreen":
                currentScreen = new PauseScreen();
                break;
            case "SaveSlotSelectionScreen":
                currentScreen = new SaveSlotSelectionScreen();
                break;
            case "SoundOptionScreen":
                currentScreen = new SoundOptionScreen();
                break;
            case "StoreScreen":
                currentScreen = new StoreScreen();
                break;
            default:
                currentScreen = new MainMenuScreen();
                break;
        }
        rpGame.setScreen(currentScreen);
    }

    public static ScreeenManager getInstance() {
        if (instance == null) {
            instance = new ScreeenManager();
        }
        return instance;
    }
}
