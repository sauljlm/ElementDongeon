package com.avengers.rpgame.logic.entities.character.components.skin.abstractFactory;

import com.avengers.rpgame.logic.entities.character.components.skin.*;

public class RandomSkeletonSkinFactory implements ISkinFactory {

    @Override
    public ISkin createSkin() {
        int random = (int) ((Math.random() * (4 - 1)) + 1);
        switch (random){
            case 1:
                return new EarthSkeletonSkin();
            case 2:
                return new WaterSkeletonSkin();
            case 3:
                return new WindSkeletonSkin();
            case 4:
                return new FireSkeletonSkin();
        }
        return new FireSkeletonSkin();
    }
}
