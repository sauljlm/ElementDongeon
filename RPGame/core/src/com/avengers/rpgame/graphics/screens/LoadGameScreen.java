package com.avengers.rpgame.graphics.screens;

import com.avengers.rpgame.RPGame;
import com.avengers.rpgame.game.GameInformation;
import com.avengers.rpgame.graphics.text.Text;
import com.avengers.rpgame.game.GameConfig;
import com.avengers.rpgame.game.io.MyInputProcessor;
import com.avengers.rpgame.utils.Resources;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.ArrayList;

import static com.avengers.rpgame.utils.Resources.resourceLoadScreenBackground;
import static com.avengers.rpgame.utils.Resources.resourceThemeMusic;

public class LoadGameScreen implements Screen {
    final RPGame game;
    private GameInformation gameInfo;
    private final GameConfig config;
    private final Music backgroundMusic;
    private final Texture backgroundImage;

    private final ArrayList<Text> loadOptions;
    private int actualSelection =0;
    private final float ScreenWidth;
    private final float ScreenHeight;
    private Text inputTitle;
    private MyInputProcessor input;

    public LoadGameScreen(final RPGame game) {
        this.game = game;
        input = new MyInputProcessor();
        config = GameConfig.getInstance();

        ScreenWidth = config.getResolutionHorizontal();
        ScreenHeight = config.getResolutionVertical();

        backgroundImage = new Texture(Gdx.files.internal(resourceLoadScreenBackground));

        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal(resourceThemeMusic));
        backgroundMusic.setLooping(true);

        loadOptions = new ArrayList<Text>();
        this.inputTitle = new Text(Resources.resourceMainFont, 100,"Juegos guardados",true);
        this.inputTitle.centerTextScreenInX( ScreenHeight-150);
    }

    @Override
    public void show() {
        generateLoadGames();
        backgroundMusic.play();
        Gdx.input.setInputProcessor(input);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);

        game.batch.begin();
        game.batch.draw(backgroundImage, 0, 0);
        for(Text mTemp : this.loadOptions){
            mTemp.draw();
        }
        this.inputTitle.draw();
        game.batch.end();

        validateKeys();
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
        backgroundMusic.dispose();
        backgroundImage.dispose();
    }

    private void generateLoadGames(){
        int mFontSize = 70;
        float mNextY = 0;
        int mRest = 70;

        this.loadOptions.add(new Text(Resources.resourceMainFont,mFontSize, "Juego1",true));
        this.loadOptions.add(new Text(Resources.resourceMainFont,mFontSize, "Juego2",true));
        this.loadOptions.add(new Text(Resources.resourceMainFont,mFontSize, "Juego3",true));
        this.loadOptions.add(new Text(Resources.resourceMainFont,mFontSize, "Volver a menú principal",true));

        this.loadOptions.get(0).centerTextScreen();
        mNextY = this.loadOptions.get(0).getY();

        for(Text mTemp : this.loadOptions){
            mTemp.centerTextScreen();
            mNextY -=mRest;
            mTemp.setY(mNextY);
        }

        changeOptionColor(0);
    }

    private void changeOptionColor(int pId){
        for (Text mTemp: this.loadOptions)
            mTemp.setColor(Color.WHITE);
        if(pId>=0)
            this.loadOptions.get(pId).setColor(Color.FIREBRICK);
        this.actualSelection = pId;
    }

    private void validateKeys() {
        try{
            int mTime = (int) (config.getFrameRate()*3.5);
            if(this.input.isMoveDown()){
                this.actualSelection++;
                if(this.actualSelection >3)
                    this.actualSelection =0;
                Thread.sleep(mTime);
                changeOptionColor(this.actualSelection);
            }
            if(this.input.isMoveUp()){
                this.actualSelection--;
                if(this.actualSelection <0)
                    this.actualSelection =3;
                Thread.sleep(mTime);
                changeOptionColor(this.actualSelection);
            }
            if(this.input.isEnter()){
                executeAction();
            }
        } catch (InterruptedException e){
            game.print(e.toString());
        }
    }

    //Nota: aqui se debe ajustar para la carga correspondiente de los juegos guardados por usuarios
    private void executeAction() {
        switch (this.actualSelection){
            case 0:
                game.setScreen(new OverworldScreen(game, gameInfo));
                dispose();
                break;
            case 1:
                game.setScreen(new OverworldScreen(game, gameInfo));
                dispose();
                break;
            case 2:
                game.setScreen(new OverworldScreen(game, gameInfo));
                dispose();
                break;
            case 3:
                game.setScreen(new MainMenuScreen(game));
                dispose();
                break;
        }
    }

}
