package com.avengers.rpgame.utils;

import com.avengers.rpgame.graphics.screens.ScreeenManager;
import com.badlogic.gdx.Gdx;

public class Utils {
    private static Utils instance;
    private float frameSkipperCounter;

    //This method allows to skip executing an action everyFrame, returns a boolean. Just wrap the action you want to skip on a if(skipFrames()){do something}
    public boolean skipFrames(){
        frameSkipperCounter = frameSkipperCounter+ Gdx.graphics.getDeltaTime();
        if(frameSkipperCounter >= Gdx.graphics.getDeltaTime()*15){
            frameSkipperCounter = 0;
            return true;
        }
        return false;
    }

    public static Utils getInstance() {
        if (instance == null) {
            instance = new Utils();
        }
        return instance;
    }
}
