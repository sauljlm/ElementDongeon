package com.avengers.rpgame.graphics.screens;

import com.avengers.rpgame.RPGame;
import com.avengers.rpgame.game.GameConfig;
import com.avengers.rpgame.game.GameInformation;
import com.avengers.rpgame.game.io.MyInputProcessor;
import com.avengers.rpgame.graphics.screens.MainMenuScreen;
import com.avengers.rpgame.graphics.screens.NewGameScreen;
import com.avengers.rpgame.graphics.text.Text;
import com.avengers.rpgame.logic.entities.character.components.CharacterClass;
import com.avengers.rpgame.utils.Resources;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.ArrayList;

import static com.avengers.rpgame.utils.FileManager.loadTexture;
import static com.avengers.rpgame.utils.Resources.*;

public class CharacterSelectionScreen implements Screen {
    final RPGame game;
    private GameInformation gameInfo;
    private final GameConfig config;
    //private final Music backgroundMusic;
    private final Texture backgroundImage;

    private Stage stage;
    private TextField txtUsername;

    private final ArrayList<Text> menuOptions;
    private final ArrayList<Text> menuCharacters;
    private int actualOption =0;
    private int actualCharacter =0;
    private final float ScreenWidth;
    private final float ScreenHeight;
    private Text inputTitle1;
    private Text inputTitle2;

    private MyInputProcessor input;
    private InputMultiplexer multiplexer;

    CharacterClass dummyMage;
    CharacterClass dummyKnight;
    CharacterClass dummyArcher;
    private ArrayList<Text> mageStats;
    private ArrayList<Text> knightStats;
    private ArrayList<Text> archerStats;
    private Texture knightTexture;
    private Texture archerTexture;
    private Texture mageTexture;

    public CharacterSelectionScreen(final RPGame game) {
        this.game = game;
        gameInfo = new GameInformation();
        input = new MyInputProcessor();
        config = GameConfig.getInstance();

        ScreenWidth = config.getResolutionHorizontal();
        ScreenHeight = config.getResolutionVertical();

        backgroundImage = new Texture(Gdx.files.internal(resourceLoadScreenBackground));

        //backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal(resourceThemeMusic));
        //backgroundMusic.setLooping(true);

        menuOptions = new ArrayList<Text>();
        menuCharacters = new ArrayList<Text>();

        this.inputTitle1 = new Text(Resources.resourceMainFont, Resources.emphasisFontSize,"Nombre de jugador:",true);
        this.inputTitle2 = new Text(Resources.resourceMainFont, Resources.emphasisFontSize,"Seleccione su personaje para iniciar partida:",true);
        this.inputTitle1.centerTextScreenInX( ScreenHeight-100);
        this.inputTitle2.centerTextScreenInX( ScreenHeight-300);

        stage= new Stage();

        // create multiplexer
        multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(input); // set stage as first input processor
        multiplexer.addProcessor(stage);  // set your game input processor as second
        Gdx.input.setInputProcessor(multiplexer); // set multiplexer as input processor

        Skin skin = new Skin(Gdx.files.internal(Resources.skinTextfield));

        txtUsername = new TextField("", skin);
        txtUsername.setPosition((ScreenWidth/2)-txtUsername.getWidth(), ScreenHeight-250);
        txtUsername.setSize(300,60);
        txtUsername.setAlignment(1);

        stage.addActor(txtUsername);

        mageStats = new ArrayList<Text>();
        knightStats = new ArrayList<Text>();
        archerStats = new ArrayList<Text>();
        createDummyCharacters();

        archerTexture = new Texture(archerImage);
        knightTexture = new Texture(knightImage);
        mageTexture = new Texture(mageImage);

    }

    @Override
    public void show() {
        generateOptions();
        generateCharacters();
        //backgroundMusic.play();
        Gdx.input.setInputProcessor(multiplexer);
        generateCharactersStats();
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 0);

        game.batch.begin();

        game.batch.draw(backgroundImage, 0, 0);

        for(Text mTemp : this.menuOptions){
            mTemp.draw();
        }
        for(Text mTemp : this.menuCharacters){
            mTemp.draw();
        }
        for(Text mTemp : this.mageStats){
            mTemp.draw();
        }
        for(Text mTemp : this.knightStats){
            mTemp.draw();
        }
        for(Text mTemp : this.archerStats){
            mTemp.draw();
        }
        this.inputTitle1.draw();
        this.inputTitle2.draw();

        stage.act(delta);
        stage.draw();

        game.batch.draw(archerTexture, ScreenWidth/2-75, ScreenHeight-675);
        game.batch.draw(knightTexture, ScreenWidth/2-600, ScreenHeight-680);
        game.batch.draw(mageTexture, ScreenWidth/2+390, ScreenHeight-680);

        game.batch.end();

        validateKeys();
        validateMouse();
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
       //backgroundMusic.dispose();
        backgroundImage.dispose();
    }

    private void generateOptions(){
        int mFontSize = 70;
        float mNextY = 0;
        int mRest = 70;

        this.menuOptions.add(new Text(Resources.resourceMainFont,mFontSize, "Iniciar partida",true));
        this.menuOptions.add(new Text(Resources.resourceMainFont,mFontSize, "Regresar",true));

        this.menuOptions.get(0).centerTextScreenInX(300);
        mNextY = this.menuOptions.get(0).getY();

        for(Text mTemp : this.menuOptions){
            mTemp.centerTextScreenInX(300);
            mNextY -=mRest;
            mTemp.setY(mNextY);
        }

        changeOptionColor(0,menuOptions);
    }

    private void generateCharacters(){
        int mFontSize = emphasisFontSize;
        float pHeightText = ScreenHeight-400;
        float mCenterX = 0;
        int mRest = 400;

        this.menuCharacters.add(new Text(Resources.resourceMainFont,mFontSize,"Caballero",true));
        this.menuCharacters.add(new Text(Resources.resourceMainFont,mFontSize,"Arquero",true));
        this.menuCharacters.add(new Text(Resources.resourceMainFont,mFontSize,"Mago",true));

        this.menuCharacters.get(1).customPositionTextScreen((ScreenWidth/2)-(menuCharacters.get(1).getWidth()/2),pHeightText);
        mCenterX = this.menuCharacters.get(1).getX();
        this.menuCharacters.get(0).customPositionTextScreen(mCenterX-(menuCharacters.get(0).getWidth()/2)-mRest+30,pHeightText);
        this.menuCharacters.get(2).customPositionTextScreen(mCenterX+(menuCharacters.get(2).getWidth()/2)+mRest+50,pHeightText);

        changeOptionColor(0,menuCharacters);
    }

    private void changeOptionColor(int pId, ArrayList<Text> menu){
        if(menu.equals(menuOptions)) {
            for (Text mTemp1 : this.menuOptions)
                mTemp1.setColor(Color.WHITE);
            if (pId >= 0)
                this.menuOptions.get(pId).setColor(Color.FIREBRICK);
            this.actualOption = pId;
        }
        if(menu.equals(menuCharacters)) {
            for (Text mTemp2 : this.menuCharacters)
                mTemp2.setColor(Color.GREEN);
            if (pId >= 0)
                this.menuCharacters.get(pId).setColor(Color.FIREBRICK);
            this.actualCharacter = pId;
        }
    }

    private void validateKeys() {
        try{
            int mTime = (int) (config.getFrameRate()*3.5);
            if(this.input.isMoveDownMenu()){
                stage.setKeyboardFocus(null);
                this.actualOption++;
                if(this.actualOption > menuOptions.size()-1)
                    this.actualOption =0;
                Thread.sleep(mTime);
                changeOptionColor(this.actualOption,menuOptions);
            }
            if(this.input.isMoveUpMenu()){
                stage.setKeyboardFocus(null);
                this.actualOption--;
                if(this.actualOption <0)
                    this.actualOption = menuOptions.size()-1;
                Thread.sleep(mTime);
                changeOptionColor(this.actualOption,menuOptions);
            }
            if(this.input.isMoveRightMenu()){
                stage.setKeyboardFocus(null);
                this.actualCharacter++;
                if(this.actualCharacter > menuCharacters.size()-1)
                    this.actualCharacter =0;
                Thread.sleep(mTime);
                changeOptionColor(this.actualCharacter,menuCharacters);
            }
            if(this.input.isMoveLeftMenu()){
                stage.setKeyboardFocus(null);
                this.actualCharacter--;
                if(this.actualCharacter <0)
                    this.actualCharacter = menuCharacters.size()-1;
                Thread.sleep(mTime);
                changeOptionColor(this.actualCharacter,menuCharacters);
            }
            if(this.input.isEnter()){
                executeAction(menuOptions);
            }
        } catch (InterruptedException e){
            game.print(e.toString());
        }
    }

    private void validateMouse() {
        for (int i = 0; i < this.menuOptions.size(); i++) {
            float mX = this.input.getMouseX(), mY = this.input.getMouseY();
            Text mTemp = this.menuOptions.get(i);
            if (mX >= mTemp.getX() && mX <= (mTemp.getX() + mTemp.getWidth()))
                if (mY >= (mTemp.getY() - mTemp.getHeight()) && mY <= mTemp.getY()){
                    changeOptionColor(i,menuOptions);
                    if(this.input.isClickTouch())
                        executeAction(menuOptions);
                }
        }
        for (int i = 0; i < this.menuCharacters.size(); i++) {
            float mX = this.input.getMouseX(), mY = this.input.getMouseY();
            Text mTemp = this.menuCharacters.get(i);
            if (mX >= mTemp.getX() && mX <= (mTemp.getX() + mTemp.getWidth()))
                if (mY >= (mTemp.getY() - mTemp.getHeight()) && mY <= mTemp.getY()){
                    changeOptionColor(i,menuCharacters);
                    if(this.input.isClickTouch())
                        executeAction(menuCharacters);
                }
        }
    }

    private void executeAction(ArrayList<Text> menu) {
        if(menu.equals(menuOptions)) {
            switch (this.actualOption) {
                case 0:
                    //Knight
                    if(this.actualCharacter==0){
                        gameInfo.setIdCharacterClass(1);
                    }
                    //Archer
                    if(this.actualCharacter==1){
                        gameInfo.setIdCharacterClass(2);
                    }
                    //Mage
                    if(this.actualCharacter==2){
                        gameInfo.setIdCharacterClass(3);
                    }

                    if(txtUsername.getText().isEmpty()){
                        gameInfo.setUsername("Unknown");
                    }else{
                        gameInfo.setUsername(txtUsername.getText());
                    }

                    game.setScreen(new NewGameScreen(game,gameInfo));
                    dispose();
                    break;
                case 1:
                    game.setScreen(new MainMenuScreen(game));
                    dispose();
                    break;
            }
        }
    }

    //Esto es temporal
    private void createDummyCharacters() {
        this.dummyKnight = new CharacterClass(1, "Knight", "Caballero", 60, 61, 62, 63, 64, 65, 66, 67, 68);
        this.dummyArcher = new CharacterClass(2, "Archer", "Arquero", 70, 71, 72, 73, 74, 75, 76, 77, 78);
        this.dummyMage = new CharacterClass(3, "Mage", "Mago", 50, 51, 52, 53, 54, 55, 56, 57, 58);
    }

    private ArrayList<String> characterStats (CharacterClass characterClass) {
        ArrayList<String> stats = new ArrayList<String>();
        stats.add(String.valueOf(characterClass.getInitialStrength())+" pts <= Fuerza");
        stats.add(String.valueOf(characterClass.getInitialSpeed())+" pts <= Velocidad");
        stats.add(String.valueOf(characterClass.getInitialResistance())+" pts <= Resistencia");
        return stats;
    }

    private void generateCharactersStats() {
        int mFontSize = storyFontSize;
        Color colorStat = Color.GREEN;
        float mNextY1 = 0;
        float mNextY2 = 0;
        float mNextY3 = 0;
        int mRestIntern = storyFontSize;
        float mCenterX = 0;
        int mRest = 350;
        float pHeightText = ScreenHeight-650;

        this.knightStats.add(new Text(Resources.resourceMainFont,mFontSize,colorStat,characterStats(dummyKnight).get(0),true));
        this.knightStats.add(new Text(Resources.resourceMainFont,mFontSize,colorStat,characterStats(dummyKnight).get(1),true));
        this.knightStats.add(new Text(Resources.resourceMainFont,mFontSize,colorStat,characterStats(dummyKnight).get(2),true));

        this.archerStats.add(new Text(Resources.resourceMainFont,mFontSize,colorStat,characterStats(dummyArcher).get(0),true));
        this.archerStats.add(new Text(Resources.resourceMainFont,mFontSize,colorStat,characterStats(dummyArcher).get(1),true));
        this.archerStats.add(new Text(Resources.resourceMainFont,mFontSize,colorStat,characterStats(dummyArcher).get(2),true));

        this.mageStats.add(new Text(Resources.resourceMainFont,mFontSize,colorStat,characterStats(dummyMage).get(0),true));
        this.mageStats.add(new Text(Resources.resourceMainFont,mFontSize,colorStat,characterStats(dummyMage).get(1),true));
        this.mageStats.add(new Text(Resources.resourceMainFont,mFontSize,colorStat,characterStats(dummyMage).get(2),true));

        this.archerStats.get(0).customPositionTextScreen((ScreenWidth/2)-(archerStats.get(0).getWidth()/2),pHeightText);
        mNextY3 = this.archerStats.get(0).getY();
        mCenterX = this.archerStats.get(0).getX();
        for(Text mTemp : this.archerStats){
            mTemp.customPositionTextScreen((ScreenWidth/2)-(archerStats.get(0).getWidth()/2),pHeightText);
            mNextY3 -=mRestIntern;
            mTemp.setY(mNextY3);
        }

        this.knightStats.get(0).customPositionTextScreen(mCenterX-(knightStats.get(0).getWidth()/2)-mRest,pHeightText);
        mNextY2 = this.knightStats.get(0).getY();
        for(Text mTemp : this.knightStats){
            mTemp.customPositionTextScreen(mCenterX-(knightStats.get(0).getWidth()/2)-mRest,pHeightText);
            mNextY2 -=mRestIntern;
            mTemp.setY(mNextY2);
        }

        this.mageStats.get(0).customPositionTextScreen(mCenterX+(mageStats.get(0).getWidth()/2)+mRest,pHeightText);
        mNextY1 = this.mageStats.get(0).getY();
        for(Text mTemp : this.mageStats){
            mTemp.customPositionTextScreen(mCenterX+(mageStats.get(0).getWidth()/2)+mRest,pHeightText);
            mNextY1 -=mRestIntern;
            mTemp.setY(mNextY1);
        }

    }

}
