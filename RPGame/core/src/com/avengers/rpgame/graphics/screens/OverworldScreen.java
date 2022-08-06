package com.avengers.rpgame.graphics.screens;

import com.avengers.rpgame.RPGame;
import com.avengers.rpgame.ai.AIManager;
import com.avengers.rpgame.data.gameStatus.GameStatus;
import com.avengers.rpgame.game.GameConfig;
import com.avengers.rpgame.game.io.IOManager;
import com.avengers.rpgame.graphics.dialog.DialogManager;
import com.avengers.rpgame.graphics.graphicManagerMediador.GraphicMediator;
import com.avengers.rpgame.graphics.hud.HUD;
import com.avengers.rpgame.graphics.npc.EnemiesManager;
import com.avengers.rpgame.logic.entities.Party;
import com.avengers.rpgame.logic.entities.character.factory.CharacterFactory;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import java.util.ArrayList;
import static com.avengers.rpgame.utils.FileManager.loadTexture;
import static com.avengers.rpgame.utils.Resources.resourcePortalTexture;

public class OverworldScreen implements Screen {
    final RPGame game;
    private GameStatus gameStatus;
    private GameConfig config;
    private DialogManager dialogManager;
    private IOManager ioManager;
    private AIManager aiManager;
    private Party playerParty;
    private CharacterFactory characterFactory;

    //Graphic manager mediator
    GraphicMediator graphicMediator;

    private HUD hudElements;

    //Interactive map objects
    private ArrayList<Vector2> interactiveObjVectors;
    private Sprite accessPortal;

    //Monsters
    EnemiesManager enemiesManager;

    public OverworldScreen() {
        game = RPGame.getInstance();
        gameStatus = GameStatus.getInstance();
        config = GameConfig.getInstance();
        ioManager = IOManager.getInstance();
        aiManager = AIManager.getInstance();
        dialogManager = DialogManager.getInstance();
        graphicMediator = new GraphicMediator(game);

        playerParty = GameStatus.getInstance().getPlayerParty();

        characterFactory = new CharacterFactory(graphicMediator.getWorld(), game);
        characterFactory.createParty();

        hudElements = new HUD();

        // Monsters
        enemiesManager = new EnemiesManager();
        enemiesManager.createEnemies();

        //Interactive objects
        interactiveObjVectors = new ArrayList<Vector2>();

        interactiveObjVectors = aiManager.getInteractiveObjectsV();

        this.accessPortal = new Sprite(loadTexture(resourcePortalTexture));
        accessPortal.setCenter(interactiveObjVectors.get(0).x,interactiveObjVectors.get(0).y);
        gameStatus.saveOnDB();
    }

    @Override
    public void show() {
    }

    //This method holds the game logic
    public void  logic(float delta){
        aiManager.moveAllies(playerParty.getActivePartyMember(), playerParty.getInactivePartyMember(1), 1);
        aiManager.moveAllies(playerParty.getInactivePartyMember(1), playerParty.getInactivePartyMember(2), 1);
        aiManager.monitorSurroundings(playerParty.getActivePartyMember());
        hudElements.update();
    }


    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1f);
        dialogManager.clean();
        logic(delta);// Separate the game logic from rendering for clarity

        //Graphic manager mediator
        graphicMediator.renderGraphicManagers(delta);

        ioManager.processInput("overworld", delta, playerParty);

        game.batch.begin();//Never add game logic inside render begin, end
        playerParty.getPartyMember1().getAnimatedCharacter().draw(delta);
        playerParty.getPartyMember2().getAnimatedCharacter().draw(delta);
        playerParty.getPartyMember3().getAnimatedCharacter().draw(delta);
        enemiesManager.draw(delta);

        for (int j=0;j<=3;j++) {
            game.batch.draw(accessPortal, (interactiveObjVectors.get(j).x-1.25f) * config.getPPM(), (interactiveObjVectors.get(j).y-1.25f) * config.getPPM());
        }

        game.batch.end();

        // hudElements.update(this.userHealth, this.playerLevel, this.magicLevel, this.experiencePoints);
        graphicMediator.changeProjectionMatrix();
        game.batch.begin();//We can stop render, do something and start again
        hudElements.draw();
        dialogManager.draw();
        game.batch.end();

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

        ioManager.dispose();
        graphicMediator.dispose();
        hudElements.dispose();
    }
}
