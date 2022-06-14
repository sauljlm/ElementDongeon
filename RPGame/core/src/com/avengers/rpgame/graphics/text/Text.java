package com.avengers.rpgame.graphics.text;

import com.avengers.rpgame.RPGame;
import com.avengers.rpgame.game.GameConfig;
import com.avengers.rpgame.utils.Resources;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;



public class Text {
    private GameConfig gameConfig;
    private static final Color FONT_COLOR = Color.WHITE;
    private BitmapFont _Font;
    private GlyphLayout _GLayout;
    private Float _X, _Y;
    private int _FontSize;
    private String _Text;
//    private RPGame rpGame;

    //Constructores

    public Text() {
        initLayout();
        generateText(Resources.resourceMainFont,this._FontSize,FONT_COLOR,false);
        this.setCoordinates(100,100f);
        this.setText("");
    }

    public Text(int pFontSize, String pText, Boolean pShadow) {
        initLayout();
        generateText(Resources.resourceMainFont,pFontSize,this.FONT_COLOR,pShadow);
        this.setCoordinates(1f,1f);
        this.setText(pText);
    }

    public Text(String pFontSource, int pFontSize, String pText, Boolean pShadow) {
        gameConfig = GameConfig.getInstance();
        initLayout();
        generateText(pFontSource,pFontSize,this.FONT_COLOR,pShadow);
        this.setCoordinates(1f,1f);
        this.setText(pText);
    }


    public Text(String pFontPath, Float pX, Float pY, int pFontSize, String pText) {
        initLayout();
        generateText(pFontPath,pFontSize,FONT_COLOR,false);
        this.setCoordinates(pX,pY);
        this.setText(pText);
    }

    public Text(String pFontPath, Float pX, Float pY, int pFontSize, String pText, Boolean pShadow) {
        initLayout();
        generateText(pFontPath,pFontSize,FONT_COLOR,pShadow);
        this.setCoordinates(pX,pY);
        this.setText(pText);
    }

    public Text(String pFontPath, Float pX, Float pY, int pFontSize, String pText, Color pColor) {
        initLayout();
        generateText(pFontPath,pFontSize,pColor,false);
        this.setCoordinates(pX,pY);
        this.setText(pText);
    }

    public Text(String pFontPath, Float pX, Float pY, int pFontSize, String pText, Color pColor, Boolean pShadow) {
        initLayout();
        generateText(pFontPath,pFontSize,pColor,pShadow);
        this.setCoordinates(pX,pY);
        this.setText(pText);
    }

    //GETTER y SETTERS
    public BitmapFont getFont() {
        return _Font;
    }

    public void setFont(BitmapFont _Font) {
        this._Font = _Font;
    }

    private GlyphLayout getGLayout() {
        return _GLayout;
    }

    private void setGLayout(GlyphLayout _GLayout) {
        this._GLayout = _GLayout;
    }

    public Float getX() {
        return _X;
    }

    public void setX(Float _X) {
        this._X = _X;
    }

    public Float getY() {
        return _Y;
    }

    public void setY(Float _Y) {
        this._Y = _Y;
    }

    public int getFontSize() {
        return _FontSize;
    }

    public void setFontSize(int _FontSize) {
        this._FontSize = _FontSize;
    }

    public String getText() {
        return _Text;
    }

    public float getWidth() {
        return this.getGLayout().width;
    }

    public float getHeight() {
        return this.getGLayout().height;
    }

    public void setText(String pText) {
        this._Text = pText;
        this._GLayout.setText(this._Font, pText);
    }

    public void setCoordinates (float pX, float pY) {
        this.setX(pX);
        this.setY(pY);
    }

    public void setColor (Color pColor){
        this.getFont().setColor(pColor);
    }

    //Funciones publicas

    public void draw(){
        this._Font.draw(RPGame.batch, this.getText(),this.getX(),this.getY());
    }

//    public void centerTextScreen(){
//        float w = (Resources.HEIGHT/2)-(this.getHeight()/2);
//        float h = (Resources.WIDTH/2)-(this.getWidth()/2);
//        this.setCoordinates(w,h);
//    }

    public void centerTextScreen(){
        float w = ((float)gameConfig.getResolutionHorizontal()/2)-(this.getWidth()/2);
        float h = ((float)gameConfig.getResolutionVertical()/2)-(this.getHeight()/2);
        this.setCoordinates(w,h);
    }

    public void centerTextScreenInX(float pHeight){
        float w = ((float)gameConfig.getResolutionHorizontal()/2)-(this.getWidth()/2);
        float h = (pHeight);
        this.setCoordinates(w,h);
    }

    public void centerTextScreenInY(float pWidth){
        float w = (pWidth);
        float h = ((float)gameConfig.getResolutionVertical()/2)-(this.getHeight()/2);
        this.setCoordinates(w,h);
    }

    public void customPositionTextScreen(float pWidth, float pHeight){
        this.setCoordinates(pWidth,pHeight);
    }

    //Funciones privadas

    private void generateText(String pFontPath, int pFontSize, Color pColor, Boolean pShadow){
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(pFontPath));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = pFontSize;
        parameter.color = pColor;
        if(pShadow){
            parameter.shadowOffsetX =  5;
            parameter.shadowOffsetY = 5;
            parameter.shadowColor = com.badlogic.gdx.graphics.Color.BLACK;
        }
        this.setFontSize(pFontSize);
        this._Font = generator.generateFont(parameter);

    }

    private void initLayout(){
        this.setGLayout(new GlyphLayout());
    }
}





















//import com.badlogic.gdx.files.FileHandle;
//import com.badlogic.gdx.graphics.Color;
//import com.badlogic.gdx.graphics.g2d.BitmapFont;
//import com.badlogic.gdx.graphics.g2d.GlyphLayout;
//import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
//
////import java.util.ArrayList;
//
//public class Text {
//    private Color fontColor;
//    private BitmapFont bitmapFont;
//    private GlyphLayout gLayout;
//    private int posX;
//    private int posY;
//    private String textString;
//    private FileHandle font;
//
//    public Text() {
//    }
//
//    public Text(Color fontColor, int posX, int posY, String textString, FileHandle font) {
//        this.fontColor = fontColor;
//        this.posX = posX;
//        this.posY = posY;
//        this.textString = textString;
//        this.font = font;
//    }
//
//    public Color getFontColor() {
//        return fontColor;
//    }
//
//    public BitmapFont getBitmapFont() {
//        return bitmapFont;
//    }
//
//    public GlyphLayout getgLayout() {
//        return gLayout;
//    }
//
//    public int getPosX() {
//        return posX;
//    }
//
//    public int getPosY() {
//        return posY;
//    }
//
//    public String getTextString() {
//        return textString;
//    }
//
//    public void setFontColor(Color fontColor) {
//        this.fontColor = fontColor;
//    }
//
//    public void setBitmapFont(BitmapFont bitmapFont) {
//        this.bitmapFont = bitmapFont;
//    }
//
//    public void setgLayout(GlyphLayout gLayout) {
//        this.gLayout = gLayout;
//    }
//
//    public void setPosX(int posX) {
//        this.posX = posX;
//    }
//
//    public void setPosY(int posY) {
//        this.posY = posY;
//    }
//
//    public void setTextString(String textString) {
//        this.textString = textString;
//    }
//
//    public static Text createText(FileHandle fontFile, String textString, int size, Color color, boolean shadow, int posX, int posY) {
//        GlyphLayout glyphLayout = new GlyphLayout();
//
//        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(fontFile);
//        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
//        parameter.size = size;
//        BitmapFont font = generator.generateFont(parameter);
//
//        Text text = new Text();
//        text.setBitmapFont(font);
//        text.setFontColor(color);
//        text.setgLayout(glyphLayout);
//        text.setPosX(posX);
//        text.setPosY(posY);
//        text.setTextString(textString);
//        return text;
//    }
//
//}
