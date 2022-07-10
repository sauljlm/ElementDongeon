package com.avengers.rpgame.graphics.screens;

import com.avengers.rpgame.RPGame;
import com.avengers.rpgame.ai.RefereeBattleAI;
import com.avengers.rpgame.game.GameConfig;
import com.avengers.rpgame.game.io.IOManager;
import com.avengers.rpgame.graphics.camera.CameraManager;
import com.avengers.rpgame.graphics.hud.BattleHUD;
import com.avengers.rpgame.graphics.physics.PhysicsManager;
import com.avengers.rpgame.logic.entities.EntitiesBuilderDirector;
import com.avengers.rpgame.logic.entities.Party;
import com.avengers.rpgame.logic.entities.character.abstractCharacter.AbstractCharacter;
import com.avengers.rpgame.logic.entities.character.builder.CharacterBuilder;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;

import javax.swing.*;

import java.util.ArrayList;

import static com.avengers.rpgame.utils.FileManager.*;
import static com.avengers.rpgame.utils.Resources.*;

public class FightScreen implements Screen {
    final RPGame game;

    private final GameConfig config;
    private final Music backgroundMusic;
    private IOManager ioManager;
    private Texture backgroundImage;
    private RefereeBattleAI refereeBattleAI;
    private Party playerParty;
    private Party enemyParty;
    private CameraManager cameraManager;
    private EntitiesBuilderDirector director;
    private CharacterBuilder characterBuilder;
    private AbstractCharacter partyMemberAnimated1;
    private AbstractCharacter partyMemberAnimated2;
    private AbstractCharacter partyMemberAnimated3;
    private AbstractCharacter enemy;
    private PhysicsManager physicsManager;

    //HUD default values
    private int userHealth = 100;
    private int playerLevel = 34;
    private int magicLevel = 12;
    private int experiencePoints = 0;
    private int characterClass = 2;
    // End HUD values
    private BattleHUD hudPlayer;
    private BattleHUD hudEnemy;

    // UI
    private Skin skin;
    private Stage stage; //General stage for all UI
    private Table table; //General table to fill stage, docs recomend to create the UI inside a table that fills the stage
    private Table fightStage; // Table for top part of ui (HUD + fighting character)
    private Table bottomTable; //Table for bottom part of ui (buttons + message dashboard)

    private Window playerPartyStatus; //Window for player HUD
    private Window enemyStatus; //Window for enemy HUD

    private Window uiWindow;
    private Table uiTable; //Table for buttons
    private Table selectorTable; //Table for leftSide UI selector
    private Table buttonsTable; //Table for attacks,skills,items on right side of UI

    private Window selector1W;
    private Window selector2W;
    private Window selector3W;
    private Window button1W;
    private Window button2W;
    private Window button3W;
    private Window button4W;
    private Window button5W;
    private Window button6W;
    private Window button7W;
    private Window button8W;

    private TextButton selector1; //Attacks
    private TextButton selector2; //Skills
    private TextButton selector3; //Items

    private TextButton button1;
    private TextButton button2;
    private TextButton button3;
    private TextButton button4;
    private TextButton button5;
    private TextButton button6;
    private TextButton button7;
    private TextButton button8;

    private Window messagesWindow;
    private TextArea messageText;
    private String messageString;
    private ArrayList<String> messageStringList;

    public FightScreen(final RPGame game, Party playerParty) {
        this.game = game;
        this.config = GameConfig.getInstance();
        this.playerParty = playerParty;
        Party enemyPartyNew = new Party();
        enemyPartyNew = playerParty;
        this.enemyParty = enemyPartyNew;
        ioManager = new IOManager(game);
        cameraManager = new CameraManager(game);
        director = new EntitiesBuilderDirector();
        characterBuilder = new CharacterBuilder();
        hudPlayer = new BattleHUD(0, this.userHealth, this.playerLevel, this.magicLevel, this.experiencePoints,this.characterClass);
        hudEnemy = new BattleHUD(1, this.userHealth, this.playerLevel, this.magicLevel, this.experiencePoints,this.characterClass);

        director.buildKnightDummy(characterBuilder, game);
        partyMemberAnimated1 = characterBuilder.getResult();
        partyMemberAnimated1.getAnimatedCharacter().setTextureScreenLocation(new Vector2(config.getResolutionHorizontal()/9.5f/16, config.getResolutionVertical()/3*1.14f/16));

        director.buildArcherDummy(characterBuilder, game);
        partyMemberAnimated2 = characterBuilder.getResult();
        partyMemberAnimated2.getAnimatedCharacter().setTextureScreenLocation(new Vector2(config.getResolutionHorizontal()/9.5f/16*2, config.getResolutionVertical()/3*1.1f/16));

        director.buildMageDummy(characterBuilder, game);
        partyMemberAnimated3 = characterBuilder.getResult();
        partyMemberAnimated3.getAnimatedCharacter().setTextureScreenLocation(new Vector2(config.getResolutionHorizontal()/9.5f/16*3, config.getResolutionVertical()/3*1.1f/16));

        director.buildEarthSkeletonDummy(characterBuilder, game);
        enemy = characterBuilder.getResult();
        enemy.getAnimatedCharacter().setTextureScreenLocation(new Vector2(config.getResolutionHorizontal()/4f/16*3, config.getResolutionVertical()/3*1.1f/16));
        enemy.getAnimatedCharacter().setAction("runningLeft");

        backgroundMusic = loadMusic(resourceFightMusic);;
        backgroundMusic.setLooping(true);
        backgroundImage = loadTexture(resourceFightBackgroundForest);

        refereeBattleAI = new RefereeBattleAI();
        skin = loadUISkin(resourceSkin3);

        //UI STUFF - Initiallize them all
        //General UI
        stage= new Stage();
        table = new Table();
        table.setFillParent(true);
        stage.addActor(table);
        table.debug();

        // Widgets
        fightStage = new Table();
        bottomTable = new Table();

        playerPartyStatus = new Window("", skin);
        enemyStatus = new Window("", skin);

        uiWindow = new Window("Attacks", skin, "window-player");
        uiTable = new Table();

        selectorTable = new Table();
        buttonsTable = new Table();

        selector1 = new TextButton("Attacks", skin);
        selector2 = new TextButton("Skills", skin);
        selector3 = new TextButton("Items", skin);

        button1 = new TextButton("Attack1", skin);
        button2 = new TextButton("Attack2", skin);
        button3 = new TextButton("Attack3", skin);
        button4 = new TextButton("Attack4", skin);
        button5 = new TextButton("Attack5", skin);
        button6 = new TextButton("Attack6", skin);
        button7 = new TextButton("Attack7", skin);
        button8 = new TextButton("Attack8", skin);

        selector1W = new Window("", skin);
        selector2W = new Window("", skin);
        selector3W = new Window("", skin);
        button1W = new Window("", skin);
        button2W = new Window("", skin);
        button3W = new Window("", skin);
        button4W = new Window("", skin);
        button5W = new Window("", skin);
        button6W = new Window("", skin);
        button7W = new Window("", skin);
        button8W = new Window("", skin);

        messagesWindow = new Window("", skin);
        messageString = "Que empiece la232323 1batalla a muerte 1dafkjh gakjgdhkajgdfhakjhlgf alkshjfgaslkjhf gajkhlfgasklhfgakjfhlafkjlhasgf";
        messageText = new TextArea(messageString, skin);

        //Hierarchy
        //Upper section
        table.add(fightStage).fill().expandX().height(config.getResolutionVertical()/3*2);
        fightStage.add(playerPartyStatus).expand().width(config.getResolutionHorizontal()/4).height(config.getResolutionVertical()/5).left().top();
        fightStage.add(enemyStatus).expand().width(config.getResolutionHorizontal()/4).height(config.getResolutionVertical()/5).right().top();
        table.row();
        //Bottom section
        table.add(bottomTable).fill().expandX().height(config.getResolutionVertical()/3*1);
        bottomTable.add(uiWindow).fill().expand().fill();

        //Bottom left side

        uiWindow.add(uiTable).fill().expand();
        uiTable.debug();
        uiTable.add(selectorTable).expand().fill().left().width(config.getResolutionHorizontal()/10);

        selectorTable.add(selector1W).fill().expand();
        selector1W.add(selector1).fill().expand();

        selectorTable.row();
        selectorTable.add(selector2W).fill().expand();
        selector2W.add(selector2).fill().expand();

        selectorTable.row();
        selectorTable.add(selector3W).fill().expand();
        selector3W.add(selector3).fill().expand();

        uiTable.add(buttonsTable).fill().expand();

        buttonsTable.add(button1W).fill().expand();
        button1W.add(button1).fill().expand();

        buttonsTable.add(button2W).fill().expand();
        button2W.add(button2).fill().expand();

        buttonsTable.add(button3W).fill().expand();
        button3W.add(button3).fill().expand();

        buttonsTable.add(button4W).fill().expand();
        button4W.add(button4).fill().expand();

        buttonsTable.row();
        buttonsTable.add(button5W).fill().expand();
        button5W.add(button5).fill().expand();

        buttonsTable.add(button6W).fill().expand();
        button6W.add(button6).fill().expand();

        buttonsTable.add(button7W).fill().expand();
        button7W.add(button7).fill().expand();

        buttonsTable.add(button8W).fill().expand();
        button8W.add(button8).fill().expand();

        //Bottom right side
        bottomTable.add(messagesWindow).fill().expand().fill();
        messageText.setColor(new Color(0,255,0, 1));
//        messageText.setAlignment();
        messagesWindow.add(messageText).fill().expand();

        //Controls
        selector1.setBounds(0, 0, selector1.getWidth(), selector1.getHeight());

        selector1.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("down");
                return true;
            }

            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("up");
            }
        });

    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        backgroundMusic.play();
    }

    //This method holds the game logic
    public void  logic(float delta){
        refereeBattleAI.manageBattle(playerParty, enemyParty);
    }

    @Override
    public void render(float delta) {
        logic(delta);// Separate the game logic from rendering for clarity
        ScreenUtils.clear(0, 0, 0, 1);

        ioManager.processInput("battle", delta, playerParty);

//        cameraManager.action(delta, playerParty.getPartyMember1());
        hudPlayer.update(this.userHealth, this.playerLevel, this.magicLevel, this.experiencePoints,this.characterClass);
        hudEnemy.update(this.userHealth, this.playerLevel, this.magicLevel, this.experiencePoints,this.characterClass);
        game.batch.begin();
        game.batch.draw(backgroundImage, 0, 0);
        partyMemberAnimated1.getAnimatedCharacter().draw(delta/2f);
        partyMemberAnimated2.getAnimatedCharacter().draw(delta/2f);
        partyMemberAnimated3.getAnimatedCharacter().draw(delta/2f);
        enemy.getAnimatedCharacter().draw(delta/2f);
        game.batch.end();
        stage.act(delta);
        stage.draw();
        game.batch.begin();
        hudPlayer.draw(game.batch);
        hudEnemy.draw(game.batch);
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
        backgroundMusic.play();
    }

    @Override
    public void hide() {
        backgroundMusic.pause();
    }

    @Override
    public void dispose() {
        this.pause();
        this.hide();
        stage.dispose();
    }
}
