package com.avengers.rpgame.graphics.screens;

import com.avengers.rpgame.RPGame;
import com.avengers.rpgame.data.gameStatus.GameStatus;
import com.avengers.rpgame.game.GameConfig;
import com.avengers.rpgame.game.io.IOManager;
import com.avengers.rpgame.graphics.assetManager.MyAssetManager;
import com.avengers.rpgame.graphics.graphicManagerMediador.GraphicMediator;
import com.avengers.rpgame.logic.entities.Party;
import com.avengers.rpgame.logic.entities.character.factory.CharacterFactory;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.ScreenUtils;

public class LoadingOverworldScreen implements Screen {
    final RPGame game;
    private GameStatus gameStatus;
    private GameConfig config;

    private IOManager ioManager;
    private Party playerParty;
    private CharacterFactory characterFactory;

    private MyAssetManager assetManager;

    //Graphic manager mediator
    GraphicMediator graphicMediator;


    public LoadingOverworldScreen() {
        this.game = RPGame.getInstance();
        gameStatus = GameStatus.getInstance();
        config = GameConfig.getInstance();
        assetManager = MyAssetManager.getInstance();
        assetManager.loadEverything();
        //Graphic manager mediator
        graphicMediator = new GraphicMediator(game);

        playerParty = GameStatus.getInstance().getPlayerParty();
        characterFactory = new CharacterFactory(graphicMediator.getWorld(), game);
        characterFactory.createParty();
    }

    @Override
    public void show() {
    }


    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1f);

        //Graphic manager mediator
//        graphicMediator.renderGraphicManagers(delta);

//        ioManager.processInput("overworld", delta, playerParty);

        game.batch.begin();//Never add game logic inside render begin, end
//        playerParty.getPartyMember1().getAnimatedCharacter().draw(delta);
//        playerParty.getPartyMember2().getAnimatedCharacter().draw(delta);
//        playerParty.getPartyMember3().getAnimatedCharacter().draw(delta);
        game.batch.end();

        // hudElements.update(this.userHealth, this.playerLevel, this.magicLevel, this.experiencePoints);
        graphicMediator.changeProjectionMatrix();
        game.batch.begin();//We can stop render, do something and start again
        game.batch.end();

        System.out.println(assetManager.getProgress());
        if(assetManager.update()){
            ScreeenManager.getInstance().changeScreen("MainMenuScreen");
        }
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
        graphicMediator.dispose();
        ioManager.dispose();
    }
}
