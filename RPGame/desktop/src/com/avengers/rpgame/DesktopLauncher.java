package com.avengers.rpgame;

import com.avengers.rpgame.game.GameConfig;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

import static com.avengers.rpgame.utils.Resources.resourceGameIcon;
import static com.avengers.rpgame.utils.Resources.resourceGameTitle;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		GameConfig gameConfig = autoGameConfig();
		config.setTitle(resourceGameTitle);
		config.setWindowIcon(resourceGameIcon);
		config.setTransparentFramebuffer(true);
		config.useVsync(gameConfig.isvSync());
		config.setWindowedMode((int)gameConfig.getResolutionHorizontal(), (int)gameConfig.getResolutionVertical());
		config.setForegroundFPS(Lwjgl3ApplicationConfiguration.getDisplayMode().refreshRate);
		if(gameConfig.isFullScreen()) {
			config.setFullscreenMode(Lwjgl3ApplicationConfiguration.getDisplayMode());
			gameConfig.setResolutionHorizontal(Lwjgl3ApplicationConfiguration.getDisplayMode().width);
			gameConfig.setResolutionVertical(Lwjgl3ApplicationConfiguration.getDisplayMode().height);
		}
		new Lwjgl3Application(RPGame.getInstance(), config);
	}

	//Gets an instance of GameConfig and sets the configuration for the game
	private static GameConfig autoGameConfig(){
		GameConfig gameConfig = GameConfig.getInstance();
		gameConfig.setFrameRate(Lwjgl3ApplicationConfiguration.getDisplayMode().refreshRate);
		gameConfig.setPPM(16); //Sets the size of items for box2D physics simulation
		gameConfig.setScale(2.0f); //Sets the scale of items for box2D physics simulation
		gameConfig.setvSync(true);
		gameConfig.setFullScreen(true);
		gameConfig.setResolutionHorizontal(1920); //If full screen is true resolution is got automatically from monitor/openGl stuff
		gameConfig.setResolutionVertical(1080);
		gameConfig.setGodMode(true);
		gameConfig.setSkipIntro(true);
		gameConfig.setMapTileSize(16);
		gameConfig.setMusicVolume(0.0f);
		gameConfig.setSoundEffectsVolume(1f);
		gameConfig.setFrameTime(1 / 15f);//Value based on docs
		gameConfig.setDebugPhysics(false);
		return gameConfig;
	}
}
