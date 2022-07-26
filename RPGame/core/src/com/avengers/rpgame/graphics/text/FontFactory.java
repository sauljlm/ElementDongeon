//libgdx needs bitmapfonts, the easiest way is to generate them from the ttf font file using freetype plugin on the fly
//this has some advantages at the small cost of some CPU cycles when the game is loading
//more info here https://libgdx.com/wiki/extensions/gdx-freetype

package com.avengers.rpgame.graphics.text;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class FontFactory {
    //ignore the warnings, gonna keep this here in case we need another method
    private static FreeTypeFontGenerator generator;
    private static FreeTypeFontGenerator.FreeTypeFontParameter parameter;
    private static BitmapFont font;

    //Creates a bitmap font from a ttf file
    public static BitmapFont createBitMapFont(FileHandle fontFile, int size, Color color) {
        generator = new FreeTypeFontGenerator(fontFile);
        parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = size;
        font = generator.generateFont(parameter); // font size 12 pixels
        font.setColor(color);

        return font;
    }

    public static BitmapFont createBitMapFont(FileHandle fontFile, int size, Color color, boolean shadow, Color shadowColor) {
        generator = new FreeTypeFontGenerator(fontFile);
        parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = size;
        if(shadow){
            parameter.shadowOffsetY = 5 ;
            parameter.shadowOffsetX = 5;
            parameter.shadowColor =shadowColor;

        }
        font = generator.generateFont(parameter); // font size 12 pixels
        font.setColor(color);

        return font;
    }
}