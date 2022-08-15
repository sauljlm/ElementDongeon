package com.avengers.rpgame.graphics.screens;

import com.avengers.rpgame.RPGame;
import com.avengers.rpgame.ai.AIManager;
import com.avengers.rpgame.ai.OneForAllAI;
import com.avengers.rpgame.data.gameStatus.GameStatus;
import com.avengers.rpgame.game.GameConfig;
import com.avengers.rpgame.game.io.IOManager;
import com.avengers.rpgame.graphics.dialog.DialogManager;
import com.avengers.rpgame.graphics.graphicManagerMediador.GraphicMediator;
import com.avengers.rpgame.graphics.hud.DamageOverlay;
import com.avengers.rpgame.graphics.hud.DungeonOverlay;
import com.avengers.rpgame.graphics.hud.HUD;
import com.avengers.rpgame.graphics.npc.EnemiesManager;
import com.avengers.rpgame.logic.entities.Party;
import com.avengers.rpgame.logic.entities.character.components.animatedCharacter.DynamicAnimatedCharacter;
import com.avengers.rpgame.logic.entities.character.factory.CharacterFactory;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import java.util.ArrayList;
import static com.avengers.rpgame.utils.FileManager.loadTexture;
import static com.avengers.rpgame.utils.Resources.*;

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

    private DungeonOverlay dungeonOverlay;

    private DamageOverlay damageOverlay;

    //Interactive map objects
    private ArrayList<Vector2> interactiveObjVectors;
    private Sprite accessPortal;

    private Sprite activePartyMemberIcon;

    private OneForAllAI oneForAllAI;

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
        oneForAllAI = new OneForAllAI();

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

        activePartyMemberIcon = new Sprite(loadTexture(activePartyMemberResource));
        activePartyMemberIcon.setCenter(((DynamicAnimatedCharacter)gameStatus.getPlayerParty().getActivePartyMember().getAnimatedCharacter()).getPlayer().getPosition().x,((DynamicAnimatedCharacter)gameStatus.getPlayerParty().getActivePartyMember().getAnimatedCharacter()).getPlayer().getPosition().y);
        dungeonOverlay = new DungeonOverlay();
        damageOverlay = new DamageOverlay();

        gameStatus.saveOnDB();
    }

    @Override
    public void show() {
    }

    //This method holds the game logic
    public void  logic(float delta){
        //Graphic manager mediator
        graphicMediator.renderGraphicManagers(delta);
        //Move allies closer to active party member
        aiManager.moveAllies(playerParty.getActivePartyMember(), playerParty.getInactivePartyMember(1), 1);
        aiManager.moveAllies(playerParty.getInactivePartyMember(1), playerParty.getInactivePartyMember(2), 1);
        aiManager.monitorSurroundings(playerParty);
        //Process input to move character normally
        ioManager.processInput("overworld", delta, playerParty);
        oneForAllAI.areFriendsClose();
        hudElements.update();
    }


    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1f);
        dialogManager.clean();
        logic(delta);// Separate the game logic from rendering for clarity

        game.batch.begin();//Never add game logic inside render begin, end
        playerParty.getPartyMember(1).getAnimatedCharacter().draw(delta);
        playerParty.getPartyMember(2).getAnimatedCharacter().draw(delta);
        playerParty.getPartyMember(3).getAnimatedCharacter().draw(delta);
        game.batch.draw(activePartyMemberIcon, ((DynamicAnimatedCharacter)gameStatus.getPlayerParty().getActivePartyMember().getAnimatedCharacter()).getPlayer().getPosition().x* config.getPPM() -activePartyMemberIcon.getWidth()/2,((DynamicAnimatedCharacter)gameStatus.getPlayerParty().getActivePartyMember().getAnimatedCharacter()).getPlayer().getPosition().y* config.getPPM()+activePartyMemberIcon.getHeight()*2.7f);
//        game.batch.draw(activePartyMemberIcon, (interactiveObjVectors.get(0).x-1.25f) * config.getPPM(), (interactiveObjVectors.get(0).y-1.25f) * config.getPPM());
        enemiesManager.draw(delta);

        for (int j=0;j<=3;j++) {
            game.batch.draw(accessPortal, (interactiveObjVectors.get(j).x-1.25f) * config.getPPM(), (interactiveObjVectors.get(j).y-1.25f) * config.getPPM());
        }

        game.batch.end();
        // hudElements.update(this.userHealth, this.playerLevel, this.magicLevel, this.experiencePoints);
        graphicMediator.changeProjectionMatrix();
        game.batch.begin();//We can stop render, do something and start again
        dungeonOverlay.draw();
        damageOverlay.draw();
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
