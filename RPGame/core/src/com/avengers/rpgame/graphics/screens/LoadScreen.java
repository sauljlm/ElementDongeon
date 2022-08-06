package com.avengers.rpgame.graphics.screens;

import com.avengers.rpgame.RPGame;
import com.avengers.rpgame.graphics.cutScenes.ICutScene;
import com.avengers.rpgame.graphics.cutScenes.GameIntroCutScene;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.ScreenUtils;

public class LoadScreen implements Screen {
    private final RPGame game;
    private ICutScene introCutScene;

    public LoadScreen() {
        this.game = RPGame.getInstance();
        introCutScene = new GameIntroCutScene(game);
    }

    @Override
    public void show() {
       introCutScene.show();
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        introCutScene.draw(delta);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
