package com.avengers.rpgame.game;

import com.avengers.rpgame.game.io.InputControl;

public class GameConfig {
    private static GameConfig instance;
    private static int resolutionVertical;
    private static int resolutionHorizontal;
    private int frameRate;
    private boolean vSync;
    private boolean fullScreen;
    private boolean skipIntro;
    private boolean godMode;
    private boolean debugPhysics;
    private InputControl inputControls;
    private float PPM; //Pixels per meter for Box2D
    private float scale; //Scale for for BOX2D (performance reasons)
    private float musicVolume;
    private float soundEffectsVolume;
    private int mapTileSize;
    private float frameTime;

    private boolean cameraMode;
//    private int cameraHeight;

    private GameConfig() {
    }

    public static void setInstance(GameConfig instance) {
        GameConfig.instance = instance;
    }

    public boolean isDebugPhysics() {
        return debugPhysics;
    }

    public void setDebugPhysics(boolean debugPhysics) {
        this.debugPhysics = debugPhysics;
    }

    public float getFrameTime() {
        return frameTime;
    }

    public void setFrameTime(float frameTime) {
        this.frameTime = frameTime;
    }

    public int getResolutionVertical() {
        return resolutionVertical;
    }

    public void setResolutionVertical(int resolutionVertical) {
        this.resolutionVertical = resolutionVertical;
    }

    public int getResolutionHorizontal() {
        return resolutionHorizontal;
    }

    public void setResolutionHorizontal(int resolutionHorizontal) {
        this.resolutionHorizontal = resolutionHorizontal;
    }

    public int getFrameRate() {
        return frameRate;
    }

    public void setFrameRate(int frameRate) {
        this.frameRate = frameRate;
    }

    public boolean isvSync() {
        return vSync;
    }

    public void setvSync(boolean vSync) {
        this.vSync = vSync;
    }

    public boolean isFullScreen() {
        return fullScreen;
    }

    public void setFullScreen(boolean fullScreen) {
        this.fullScreen = fullScreen;
    }

    public boolean isSkipIntro() {
        return skipIntro;
    }

    public void setSkipIntro(boolean skipIntro) {
        this.skipIntro = skipIntro;
    }

    public boolean isGodMode() {
        return godMode;
    }

    public void setGodMode(boolean godMode) {
        this.godMode = godMode;
    }

    public InputControl getInputControls() {
        return inputControls;
    }

    public void setInputControls(InputControl inputControls) {
        this.inputControls = inputControls;
    }

    public float getPPM() {
        return PPM;
    }

    public void setPPM(float PPM) {
        this.PPM = PPM;
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public float getMusicVolume() {
        return musicVolume;
    }

    public void setMusicVolume(float musicVolume) {
        this.musicVolume = musicVolume;
    }

    public float getSoundEffectsVolume() {
        return soundEffectsVolume;
    }

    public void setSoundEffectsVolume(float soundEffectsVolume) {
        this.soundEffectsVolume = soundEffectsVolume;
    }

    public int getMapTileSize() {
        return mapTileSize;
    }

    public void setMapTileSize(int mapTileSize) {
        this.mapTileSize = mapTileSize;
    }

    public boolean isCameraMode() {
        return cameraMode;
    }

    public void setCameraMode(boolean cameraMode) {
        this.cameraMode = cameraMode;
    }

    //PATRON Singleton
    public static GameConfig getInstance() {
        if (instance == null) {
            instance = new GameConfig();
        }
        return instance;
    }
}
