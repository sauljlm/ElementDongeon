package com.avengers.rpgame.graphics.screens;

import com.avengers.rpgame.RPGame;
import com.avengers.rpgame.ai.AIManager;
import com.avengers.rpgame.ai.RefereeBattleAI;
import com.avengers.rpgame.game.GameConfig;
import com.avengers.rpgame.game.io.IOManager;
import com.avengers.rpgame.graphics.camera.CameraManager;
import com.avengers.rpgame.graphics.hud.BattleHUD;
import com.avengers.rpgame.logic.entities.*;
import com.avengers.rpgame.logic.entities.character.abstractCharacter.AbstractCharacter;
import com.avengers.rpgame.logic.entities.character.builder.CharacterBuilder;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.Timer;

import java.util.ArrayList;

import static com.avengers.rpgame.utils.FileManager.*;
import static com.avengers.rpgame.utils.Resources.*;

public class FightScreen implements Screen {
    private final RPGame game;
    private final Timer timer;

    private final GameConfig config;
    private final Music backgroundMusic;
    private IOManager ioManager;
    private Texture backgroundImage;
    private RefereeBattleAI refereeBattleAI;
    private AIManager aiManager;
    private Party playerParty;
    private Party enemyParty;
    private EntitiesBuilderDirector director;
    private CharacterBuilder characterBuilder;
    private AbstractCharacter partyMemberAnimated1;
    private AbstractCharacter partyMemberAnimated2;
    private AbstractCharacter partyMemberAnimated3;
    private AbstractCharacter enemy;

    //HUD default values
    private int userHealth = 100;
    private int playerLevel = 94;
    private int magicLevel = 12;
    private int experiencePoints = 0;
    private int characterClass = 2;
    // End HUD values
    private BattleHUD hudPlayer;
    private BattleHUD hudEnemy;

    // UI
    private Skin skin;
    private Skin skin2;
    private Stage stage; //General stage for all UI
    private Table table; //General table to fill stage, docs recomend to create the UI inside a table that fills the stage
    private Table fightStage; // Table for top part of ui (HUD + fighting character)
    private Table statusTable; // Table for top part of ui (HUD + fighting character)
    private Table timerTable; // Table for top part of ui (HUD + fighting character)
    private Table bottomTable; //Table for bottom part of ui (buttons + message dashboard)

    private Window playerPartyStatus; //Window for player HUD
    private Window enemyStatus; //Window for enemy HUD

    private ProgressBar playerTimer;
    private ProgressBar enemyTimer;
    private Label playerTimerLabel;
    private Label enemyTimerLabel;

    private Window uiWindow;
    private Table uiTable; //Table for buttons
    private Table selectorTable; //Table for leftSide UI selector
    private ScrollPane buttonsScroll;
    private Table buttonsTable; //Table for attacks,skills,items on right side of UI

    private Window selector1W;
    private Window selector2W;
    private Window selector3W;

    private TextButton selector1; //Attacks
    private TextButton selector2; //Skills
    private TextButton selector3; //Items

    private Window messagesWindow;
    private TextArea messageText;
    private String messageString;
    private ArrayList<String> messageStringList;

    public FightScreen(final RPGame game, final Party playerParty, final Party enemyParty) {
        this.game = game;
        this.timer = new Timer();
        this.config = GameConfig.getInstance();
        this.playerParty = playerParty;
        this.enemyParty = enemyParty;
        aiManager = AIManager.getInstance();
        ioManager = new IOManager(game);
        director = new EntitiesBuilderDirector();
        characterBuilder = new CharacterBuilder();
        hudPlayer = new BattleHUD(0, playerParty);
        hudEnemy = new BattleHUD(1, this.userHealth, this.playerLevel, this.magicLevel, this.experiencePoints,this.characterClass);

        director.buildKnightDummy(characterBuilder, game);
        partyMemberAnimated1 = characterBuilder.getResult();
        partyMemberAnimated1.getAnimatedCharacter().setTextureScreenLocation(new Vector2(config.getResolutionHorizontal()/9.5f/16, config.getResolutionVertical()/3*1.32f/16));

        director.buildArcherDummy(characterBuilder, game);
        partyMemberAnimated2 = characterBuilder.getResult();
        partyMemberAnimated2.getAnimatedCharacter().setTextureScreenLocation(new Vector2(config.getResolutionHorizontal()/9.5f/16*2, config.getResolutionVertical()/3*1.3f/16));

        director.buildMageDummy(characterBuilder, game);
        partyMemberAnimated3 = characterBuilder.getResult();
        partyMemberAnimated3.getAnimatedCharacter().setTextureScreenLocation(new Vector2(config.getResolutionHorizontal()/9.5f/16*3, config.getResolutionVertical()/3*1.3f/16));

        director.buildEarthSkeletonDummy(characterBuilder, game);
        enemy = characterBuilder.getResult();
        enemy.getAnimatedCharacter().setTextureScreenLocation(new Vector2(config.getResolutionHorizontal()/4f/16*3, config.getResolutionVertical()/3*1.35f/16));
        enemy.getAnimatedCharacter().setAction("runningLeft");

        backgroundMusic = loadMusic(resourceFightMusic);;
        backgroundMusic.setLooping(true);
        backgroundImage = loadTexture(resourceFightBackgroundForest);

        refereeBattleAI = new RefereeBattleAI(game);
        skin = loadUISkin(resourceSkin3);
        skin2 = loadUISkin(resourceSkin4);

        //UI STUFF - Initiallize them all
        //General UI
        stage= new Stage();
        table = new Table();
        table.setFillParent(true);
        stage.addActor(table);
        table.debug();

        // Widgets
        fightStage = new Table();
        statusTable = new Table();
        timerTable = new Table();
        bottomTable = new Table();

        playerPartyStatus = new Window("", skin);
        enemyStatus = new Window("", skin);

        playerTimer = new ProgressBar(0,600,1,false,skin2);
        enemyTimer = new ProgressBar(0,600,1,false,skin2);
        playerTimerLabel = new Label("Cargando . . .",skin);
        enemyTimerLabel = new Label("Cargando . . .",skin);

        uiWindow = new Window("", skin, "window-player");
        uiTable = new Table();

        selectorTable = new Table();
        buttonsTable = new Table();
        buttonsScroll = new ScrollPane(buttonsTable);
        buttonsScroll.setLayoutEnabled(true);
        buttonsScroll.layout();

        selector1 = new TextButton("Ataques", skin);
        selector2 = new TextButton("Skills", skin);
        selector3 = new TextButton("Items", skin);

        selector1W = new Window("", skin);
        selector2W = new Window("", skin);
        selector3W = new Window("", skin);

        messagesWindow = new Window("", skin);
        messageString = "Que empiece la batalla a muerte de los heroes contra los monstruos elementales que atentan contra la paz de la isla oculta entre la neblina";
        messageText = new TextArea(messageString, skin);

        //Hierarchy
        //Upper section
        table.add(fightStage).fill().expandX().height(config.getResolutionVertical()/3*2);
        fightStage.add(statusTable).expand().fill();
        fightStage.row();
        fightStage.add(timerTable).expand().fill();

        statusTable.add(playerPartyStatus).expand().width(config.getResolutionHorizontal()/4).height(config.getResolutionVertical()/5).left().top();
        statusTable.add(enemyStatus).expand().width(config.getResolutionHorizontal()/4).height(config.getResolutionVertical()/5).right().top();

        timerTable.add(playerTimer).expandY().fill().bottom().left().height(70).width(200).pad(10);
        timerTable.add(playerTimerLabel).bottom().left().pad(20);
        playerTimerLabel.setFontScale(1.4f);
        enemyTimerLabel.setFontScale(1.4f);
        timerTable.add(enemyTimer).expand().fill().bottom().right().height(70).width(200).pad(10);
        timerTable.add(enemyTimerLabel).bottom().right().pad(20);

        table.row();

        //Bottom section
        table.add(bottomTable).fill().expandX().height(config.getResolutionVertical()/3*1);
        bottomTable.add(uiWindow).fill().expand();

        //Bottom left side

        uiWindow.add(uiTable).fill().expand();
        uiTable.add(selectorTable).expandY().fill().left().width(config.getResolutionHorizontal()/10);

        selectorTable.debug();
        selector1W.debug();
        selector1.debug();
        selectorTable.add(selector1W).fill().expand().left();
        selector1W.add(selector1).fill().expand();

        selectorTable.row();
        selectorTable.add(selector2W).fill().expand();
        selector2W.add(selector2).fill().expand();

        selectorTable.row();
        selectorTable.add(selector3W).fill().expand();
        selector3W.add(selector3).fill().expand();


        uiTable.add(buttonsScroll).fill().expand();
        buttonsScroll.layout();

        setActivePlayerAttacks();

        //Bottom right side
        bottomTable.add(messagesWindow).width(config.getResolutionHorizontal()/5*2).expandY().fill();
        messageText.setColor(new Color(0,255,0, 1));
        messageText.setAlignment(1);
        messagesWindow.add(messageText).fill().expand().center().pad(100).padTop(config.getResolutionVertical()/3*1/3);

        //Controls
        selector1.setBounds(0, 0, selector1.getWidth(), selector1.getHeight());
        selector2.setBounds(0, 0, selector2.getWidth(), selector2.getHeight());
        selector2.setBounds(0, 0, selector3.getWidth(), selector3.getHeight());

        selector1.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                setActivePlayerAttacks();
                changeMessageBoard("Seleccionar un ataque");
            }
        });

        selector2.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                setActivePlayerSkills();
                changeMessageBoard("Seleccionar un skill");
            }
        });

        selector3.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                setActivePlayerItems();
                changeMessageBoard("Seleccionar un item");
            }
        });
    }

    //Custom methods
    private void changeMessageBoard(String text){
        this.messageString = text;
        this.messageText.setText(messageString);
    }

    private void fillTimer(final ProgressBar progressBar){
//        this.timer.clear();
        this.timer.scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                if(progressBar.getValue() < progressBar.getMaxValue()){
                    progressBar.setValue(progressBar.getValue()+progressBar.getStepSize());
                    playerTimerLabel.setText("Cargando . . .");
                }
                if(progressBar.getValue() == progressBar.getMaxValue()){
                    playerTimerLabel.setText("Listo para atacar !");
                }
            }
        }, 0.2f);
    }

    private void setActivePlayerAttacks(){
        int index = 0;
        buttonsTable.clear();
        for (final Attack attack:playerParty.getActivePartyMember().getAttacks()) {
            Table buttonW = new Table();
            TextButton buttonA = new TextButton("Ataque "+index, skin);
            buttonsTable.row();
            buttonsTable.add(buttonW).fillX().expandX().height(100);
            buttonW.add(buttonA);
            buttonA.setText(attack.getName() +":  "+ attack.getDescription());
            index++;

            final int finalIndex = index;
            buttonA.addListener(new InputListener() {
                public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                    return true;
                }

                public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                    if(playerTimer.getValue() == playerTimer.getMaxValue()){
                        playerParty.getActivePartyMember().attackOther(playerParty.getActivePartyMember().getAttacks().get(finalIndex-1), enemyParty.getActivePartyMember());
                        playerTimer.setValue(playerTimer.getMinValue());
                        changeMessageBoard(playerParty.getActivePartyMember().getName() + " ha utilizado " + attack.getName());
                    }
                }
            });
        }
    }

    private void setActivePlayerSkills(){
        int index = 0;
        buttonsTable.clear();
        for (final Skill skill:playerParty.getActivePartyMember().getSkills()) {
            Table buttonW = new Table();
            TextButton buttonA = new TextButton("Skill "+index, skin);
            buttonsTable.row();
            buttonsTable.add(buttonW).fillX().expandX().height(100);
            buttonW.add(buttonA);
            buttonA.setText(skill.getName() +":  "+ skill.getDescription());
            index++;

            final int finalIndex = index;
            buttonA.addListener(new InputListener() {
                public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                    return true;
                }

                public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                    if(playerTimer.getValue() == playerTimer.getMaxValue()){
                        playerParty.getActivePartyMember().skillOther(playerParty.getActivePartyMember().getSkills().get(finalIndex-1), enemyParty.getActivePartyMember());
                        playerTimer.setValue(playerTimer.getMinValue());
                        changeMessageBoard(playerParty.getActivePartyMember().getName() + " ha utilizado " + skill.getName());
                    }
                }
            });
        }
    }

    private void setActivePlayerItems(){
        int index = 0;
        buttonsTable.clear();
        for (final Item item:playerParty.getActivePartyMember().getItems()) {
            Table buttonW = new Table();
            TextButton buttonA = new TextButton("Item "+index, skin);
            buttonsTable.row();
            buttonsTable.add(buttonW).fillX().expandX().height(100);
            buttonW.add(buttonA);
            buttonA.setText(item.getName() +":  "+ item.getDescription());
            index++;

            final int finalIndex = index;
            buttonA.addListener(new InputListener() {
                public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                    return true;
                }

                public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                    if(playerTimer.getValue() == playerTimer.getMaxValue()){
                        playerParty.getActivePartyMember().receiveItem(playerParty.getActivePartyMember().getItems().get(finalIndex-1)); //TODO make items and skills dualmode for attack others and self use.
                        playerTimer.setValue(playerTimer.getMinValue());
                        changeMessageBoard(playerParty.getActivePartyMember().getName() + " ha utilizado " + item.getName());
                        setActivePlayerItems();
                    }
                }
            });
        }
    }

    private void enemyPartyAction(){
        if(enemyTimer.getValue() == enemyTimer.getMaxValue()){
            String message = aiManager.accessMonsterBattleAI().selectAction(enemyParty.getActivePartyMember(), playerParty.getActivePartyMember());
            changeMessageBoard(enemyParty.getActivePartyMember().getName()+ " ha utilizado "+message);
            enemyTimer.setValue(enemyTimer.getMinValue());
        }
    }

    //____________________________________________________________________

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        backgroundMusic.play();
    }

    //This method holds the game logic
    public void  logic(float delta){
        refereeBattleAI.manageBattle(playerParty, enemyParty);
        fillTimer(playerTimer);
        fillTimer(enemyTimer);
        enemyPartyAction();
    }

    @Override
    public void render(float delta) {
        logic(delta);// Separate the game logic from rendering for clarity
        ScreenUtils.clear(0, 0, 0, 1);

        ioManager.processInput("battle", delta, playerParty);

//        cameraManager.action(delta, playerParty.getPartyMember1());
        hudPlayer.update(playerParty);
        hudEnemy.update(enemyParty);
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
