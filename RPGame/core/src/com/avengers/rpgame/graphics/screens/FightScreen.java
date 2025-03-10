package com.avengers.rpgame.graphics.screens;

import com.avengers.rpgame.RPGame;
import com.avengers.rpgame.ai.AIManager;
import com.avengers.rpgame.ai.RefereeBattleAI;
import com.avengers.rpgame.data.gameStatus.GameStatus;
import com.avengers.rpgame.game.GameConfig;
import com.avengers.rpgame.game.io.IOManager;
import com.avengers.rpgame.graphics.hud.BattleHUD;
import com.avengers.rpgame.logic.entities.*;
import com.avengers.rpgame.logic.entities.character.abstractCharacter.AbstractCharacter;
import com.avengers.rpgame.logic.entities.character.behaviour.endFightActions.EndFightVisitor;
import com.avengers.rpgame.logic.entities.character.behaviour.endFightActions.HealVisitor;
import com.avengers.rpgame.logic.entities.character.behaviour.endFightActions.IVisitor;
import com.avengers.rpgame.logic.entities.character.behaviour.endFightActions.UpdateLevelVisitor;
import com.avengers.rpgame.logic.entities.character.builder.CharacterBuilder;
import com.avengers.rpgame.logic.entities.character.components.animatedCharacter.DynamicAnimatedCharacter;
import com.avengers.rpgame.logic.entities.reward.AReward;
import com.avengers.rpgame.logic.entities.reward.creation.RewardFactory;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.Timer;

import java.util.ArrayList;
import java.util.Locale;

import static com.avengers.rpgame.utils.FileManager.*;
import static com.avengers.rpgame.utils.Resources.*;

public class FightScreen implements Screen {
    private final RPGame game;

    private GameStatus gameStatus;
    private final Timer timer;

    private final GameConfig config;
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
    private RewardFactory rewardFactory;

    private Sprite activePartyMemberIcon;

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

    private Sprite backgroundOverlay;

    private ArrayList<Disposable> disponsables;

    public FightScreen() {
        this.game = RPGame.getInstance();
        this.config = GameConfig.getInstance();
        this.gameStatus = GameStatus.getInstance();
        this.timer = new Timer();
        this.playerParty = gameStatus.getPlayerParty();
        this.enemyParty = gameStatus.getEnemyParty();
        activePartyMemberIcon = new Sprite(loadTexture(activePartyMemberResource));
        activePartyMemberIcon.setCenter(((DynamicAnimatedCharacter)gameStatus.getPlayerParty().getActivePartyMember().getAnimatedCharacter()).getPlayer().getPosition().x,((DynamicAnimatedCharacter)gameStatus.getPlayerParty().getActivePartyMember().getAnimatedCharacter()).getPlayer().getPosition().y);
        disponsables =new ArrayList<>();
        messageStringList = new ArrayList<String>();
        aiManager = AIManager.getInstance();
        ioManager = IOManager.getInstance();
        director = new EntitiesBuilderDirector();
        characterBuilder = new CharacterBuilder();
        hudPlayer = new BattleHUD(0, playerParty);
        hudEnemy = new BattleHUD(1, enemyParty);
        backgroundOverlay = new Sprite(loadTexture(battleBackgroundOverlay));
        backgroundOverlay.setAlpha(0.91f);
//        hudEnemy = new BattleHUD(1, this.userHealth, this.playerLevel, this.magicLevel, this.experiencePoints,this.characterClass);
        rewardFactory = new RewardFactory();

        director.buildBattleDummy(characterBuilder, game, playerParty.getInactivePartyMember(1));
        partyMemberAnimated1 = characterBuilder.getResult();
        partyMemberAnimated1.getAnimatedCharacter().setTextureScreenLocation(new Vector2(config.getResolutionHorizontal()/9.5f/16, config.getResolutionVertical()/3*1.22f/16));

        director.buildBattleDummy(characterBuilder, game, playerParty.getInactivePartyMember(2));
        partyMemberAnimated2 = characterBuilder.getResult();
        partyMemberAnimated2.getAnimatedCharacter().setTextureScreenLocation(new Vector2(config.getResolutionHorizontal()/9.5f/16*2, config.getResolutionVertical()/3*1.2f/16));

        director.buildBattleDummy(characterBuilder, game, playerParty.getActivePartyMember());
        partyMemberAnimated3 = characterBuilder.getResult();
        partyMemberAnimated3.getAnimatedCharacter().setTextureScreenLocation(new Vector2(config.getResolutionHorizontal()/9.5f/16*3, config.getResolutionVertical()/3*1.2f/16));

        director.buildBattleDummy(characterBuilder, game, enemyParty.getActivePartyMember());

        enemy = characterBuilder.getResult();
        enemy.getAnimatedCharacter().setTextureScreenLocation(new Vector2(config.getResolutionHorizontal()/4f/16*3, config.getResolutionVertical()/3*1.23f/16));
        enemy.getAnimatedCharacter().setAction("runningLeft");
        String location = gameStatus.getCurrentLocation();
        String dungeonBackgroundTexture = "";
        switch (location){
            case "earthDungeon":
                dungeonBackgroundTexture = resourceFightBackgroundEarthDungeon;
                break;
            case "windDungeon":
                dungeonBackgroundTexture = resourceFightBackgroundWindDungeon;
                break;
            case "waterDungeon":
                dungeonBackgroundTexture = resourceFightBackgroundWaterDungeon;
                break;
            case "fireDungeon":
                dungeonBackgroundTexture = resourceFightBackgroundFireDungeon;
                break;
            default:
                dungeonBackgroundTexture = resourceFightBackgroundForest;
                break;
        }
        backgroundImage = loadTexture(dungeonBackgroundTexture);

        refereeBattleAI = new RefereeBattleAI(game);
        skin = loadUISkin(resourceSkin3);
        skin2 = loadUISkin(resourceSkin4);

        //UI STUFF - Initiallize them all
        //General UI
        stage= new Stage();
        table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        // Widgets
        fightStage = new Table();
        statusTable = new Table();
        timerTable = new Table();
        bottomTable = new Table();

        playerPartyStatus = new Window("", skin);
        enemyStatus = new Window("", skin);

        //Change MAX for faster debugging, default is 600
        playerTimer = new ProgressBar(0,200,1,false,skin2);
        enemyTimer = new ProgressBar(0,200,1,false,skin2);
        playerTimerLabel = new Label("Cargando . . .",skin);
        enemyTimerLabel = new Label("Cargando . . .",skin);

        uiWindow = new Window("", skin);
        uiTable = new Table();

        selectorTable = new Table();
        buttonsTable = new Table(skin);
        buttonsScroll = new ScrollPane(buttonsTable);
        buttonsScroll.setLayoutEnabled(true);
        buttonsScroll.layout();

        selector1 = new TextButton("Ataques", skin);
        selector2 = new TextButton("Habilidades", skin);
        selector3 = new TextButton("Items", skin);

        selector1W = new Window("", skin);
        selector2W = new Window("", skin);
        selector3W = new Window("", skin);

        messagesWindow = new Window("", skin);
        messageString = "\n"+"\n"+"Que empiece la batalla a muerte de los heroes contra los monstruos elementales que atentan contra la paz de la isla oculta entre la neblina";
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
        timerTable.add(playerTimerLabel).expandX().bottom().left().pad(20);
        playerTimerLabel.setFontScale(1.4f);
        enemyTimerLabel.setFontScale(1.4f);
        timerTable.add(enemyTimerLabel).bottom().right().pad(20);
        timerTable.add(enemyTimer).bottom().right().height(70).width(200).pad(10);

        table.row();

        //Bottom section
        table.add(bottomTable).fill().expandX().height(config.getResolutionVertical()/3*1);
        bottomTable.add(uiWindow).fill().expand();

        //Bottom left side
        uiWindow.add(uiTable).fill().expand();
        uiTable.add(selectorTable).expandY().fill().left().width(config.getResolutionHorizontal()/10);

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
        messagesWindow.add(messageText).fill().expand().center().pad(10).padTop(10);

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
                //changeMessageBoard("Seleccionar un ataque");
            }
        });

        selector2.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                setActivePlayerSkills();
                //changeMessageBoard("Seleccionar un skill");
            }
        });

        selector3.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                setActivePlayerItems();
                //changeMessageBoard("Seleccionar un item");
            }
        });
    }

    //Custom methods
    private void changeMessageBoard(String text){
        messageStringList.add(text);
        if(messageStringList.size() >=7){
            messageStringList.remove(0);
        }
        messageString = "";
        for(int i = messageStringList.size()-1;i>=0;i--){
            messageString = messageString+"\n"+"\n"+messageStringList.get(i);
        }
        this.messageText.setText(messageString);
    }

    private void fillTimer(final ProgressBar progressBar, final int bar){
//        this.timer.clear();
        this.timer.scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                if(progressBar.getValue() < progressBar.getMaxValue()){
                    progressBar.setValue(progressBar.getValue()+progressBar.getStepSize());
                    if(bar==1) playerTimerLabel.setText("Cargando . . .");
                    if(bar==2) enemyTimerLabel.setText("Cargando . . .");
                }
                if(progressBar.getValue() == progressBar.getMaxValue()){
                    if(bar==1) playerTimerLabel.setText("Listo para atacar !");
                    if(bar==2) enemyTimerLabel.setText("Listo para atacar !");
                }
            }
        }, 0.2f);
    }

    private void setActivePlayerAttacks(){
        int index = 0;
        buttonsTable.clear();
        for (final Attack attack:playerParty.getActivePartyMember().getAttacks()) {
            Table buttonW = new Table(skin);
            buttonW.setBackground("window-player");
            TextButton buttonA = new TextButton("Ataque "+index, skin);
            Image attackTexture = new Image(loadTexture(attack.getImagePath()));
//            disponsables.add(attackTexture);
            buttonsTable.row();
            buttonsTable.add(buttonW).fillX().expandX().height(90).pad(2).padLeft(50).padRight(150);
            buttonW.add(attackTexture).height(80).width(80);
            buttonW.add(buttonA).fill().expandX().align(1);
            buttonA.setText(attack.getDescription());
            index++;

            final int finalIndex = index;
            buttonA.addListener(new InputListener() {
                public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                    return true;
                }

                public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                    if(playerTimer.getValue() == playerTimer.getMaxValue()){
                        String hpValue = playerParty.getActivePartyMember().attackOther(playerParty.getActivePartyMember().getAttacks().get(finalIndex-1), enemyParty.getActivePartyMember());
                        playerTimer.setValue(playerTimer.getMinValue());
                        changeMessageBoard(playerParty.getActivePartyMember().getName() + " ha utilizado el ataque " + attack.getDescription().toUpperCase(Locale.ROOT)+ " (" + hpValue + " HP)");
                    }
                }
            });
        }
    }

    private void setActivePlayerSkills(){
        int index = 0;
        buttonsTable.clear();
        for (final Skill skill:playerParty.getActivePartyMember().getSkills()) {
            Table buttonW = new Table(skin);
            buttonW.setBackground("window-player");
            TextButton buttonA = new TextButton("Poder "+index, skin);
            Image skillTexture = new Image(loadTexture(skill.getImagePath()));
            buttonsTable.row();
            buttonsTable.add(buttonW).fillX().expandX().height(90).pad(2).padLeft(50).padRight(150);
            buttonW.add(skillTexture).height(80).width(80);
            buttonW.add(buttonA).fill().expandX().align(1);
            buttonA.setText(skill.getDescription());
            index++;

            final int finalIndex = index;
            buttonA.addListener(new InputListener() {
                public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                    return true;
                }

                public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                    if(playerTimer.getValue() == playerTimer.getMaxValue()){
                        String hpValue = playerParty.getActivePartyMember().skillOther(playerParty.getActivePartyMember().getSkills().get(finalIndex-1), enemyParty.getActivePartyMember());
                        playerTimer.setValue(playerTimer.getMinValue());
                        changeMessageBoard(playerParty.getActivePartyMember().getName() + " ha utilizado el poder " + skill.getDescription()+ " (" +hpValue+ " HP)");
                    }
                }
            });
        }
    }

    private void setActivePlayerItems(){
        int index = 0;
        buttonsTable.clear();
        for (final Item item:playerParty.getActivePartyMember().getItems()) {
            Table buttonW = new Table(skin);
            buttonW.setBackground("window-player");
            TextButton buttonA = new TextButton("Item "+index, skin);
            Image itemTexture = new Image(loadTexture(item.getImagePath()));
            buttonsTable.row();
            buttonsTable.add(buttonW).fillX().expandX().height(90).pad(2).padLeft(50).padRight(150);
            buttonW.add(itemTexture).height(80).width(80);
            buttonW.add(buttonA).fill().expandX().align(1);
            buttonA.setText(item.getDescription());
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
                        changeMessageBoard(playerParty.getActivePartyMember().getName() + " ha utilizado el item " + item.getDescription()+ " (" +item.gethPEffect()+" HP)");
                        setActivePlayerItems();
                    }
                }
            });
        }
    }

    private void enemyPartyAction(){
        float delay = MathUtils.random(500);
        if(enemyTimer.getValue() == enemyTimer.getMaxValue() && delay >492){
            String message = aiManager.accessMonsterBattleAI().selectAction(enemyParty.getActivePartyMember(), playerParty.getActivePartyMember());
            changeMessageBoard(enemyParty.getActivePartyMember().getDescription()+ " ha utilizado "+message);
            enemyTimer.setValue(enemyTimer.getMinValue());
        }
    }

    //____________________________________________________________________

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    //This method holds the game logic
    public void  logic(float delta){
        String result = refereeBattleAI.manageBattle(playerParty, enemyParty);
        if(result.equals("EnemyWins")){
            this.dispose();
            ScreeenManager.getInstance().changeScreen("GameOverScreen");
        }
        if(result.equals("PlayerWins")){
            gameStatus.getWorld().destroyBody(((DynamicAnimatedCharacter)enemyParty.getActivePartyMember().getAnimatedCharacter()).getPlayer());
            //TODO Improve this, maybe use visitor pattern
            AReward reward = rewardFactory.createReward(enemyParty.getActivePartyMember());
            IVisitor visitor = new EndFightVisitor(reward);

            GameStatus.getInstance().getPlayerParty().getPartyMember(1).accept(visitor);
            GameStatus.getInstance().getPlayerParty().getPartyMember(2).accept(visitor);
            GameStatus.getInstance().getPlayerParty().getPartyMember(3).accept(visitor);

            IVisitor visitor2 = new HealVisitor();
            GameStatus.getInstance().getPlayerParty().getPartyMember(1).accept(visitor2);
            GameStatus.getInstance().getPlayerParty().getPartyMember(2).accept(visitor2);
            GameStatus.getInstance().getPlayerParty().getPartyMember(3).accept(visitor2);

            IVisitor visitor3 = new UpdateLevelVisitor();
            GameStatus.getInstance().getPlayerParty().getPartyMember(1).accept(visitor3);
            GameStatus.getInstance().getPlayerParty().getPartyMember(2).accept(visitor3);
            GameStatus.getInstance().getPlayerParty().getPartyMember(3).accept(visitor3);

            GameStatus.getInstance().setStatus("gameInProgress");
            this.dispose();
            ScreeenManager.getInstance().changeScreen("OverworldScreen");
        }
        fillTimer(playerTimer, 1);
        fillTimer(enemyTimer, 2);
        enemyPartyAction();
    }

    private void renderSelectorIcon(){
        Vector2 position = new Vector2();
            position.x =config.getResolutionHorizontal()/8.3f/16*3;
            position.y = config.getResolutionVertical()/3f*1.8f/16;

        game.batch.draw(activePartyMemberIcon, (position.x*GameConfig.getInstance().getPPM()), position.y*GameConfig.getInstance().getPPM(),50,50);
    }

    @Override
    public void render(float delta) {
        logic(delta);// Separate the game logic from rendering for clarity
        ScreenUtils.clear(0, 0, 0, 1);

        ioManager.processInput("battle", delta, playerParty);
        hudPlayer.update(playerParty);
        hudEnemy.update(enemyParty);
        game.batch.begin();
        game.batch.draw(backgroundImage, 0, 0);
        backgroundOverlay.draw(game.batch);
        partyMemberAnimated1.getAnimatedCharacter().draw(delta/1.5f);
        partyMemberAnimated2.getAnimatedCharacter().draw(delta/1.5f);
        partyMemberAnimated3.getAnimatedCharacter().draw(delta/1.5f);
        enemy.getAnimatedCharacter().draw(delta/1.5f);
        game.batch.end();
        stage.act(delta);
        stage.draw();
        game.batch.begin();
        hudPlayer.draw(game.batch);
        hudEnemy.draw(game.batch);
        renderSelectorIcon();
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
        for (Disposable disposable:disponsables) {
            disposable.dispose();
        }
        hudPlayer.dispose();
        hudEnemy.dispose();
    }
}
