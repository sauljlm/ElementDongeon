package com.avengers.rpgame.graphics.map.mapObjectParsers;

import com.avengers.rpgame.game.GameConfig;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Shape;

//If all parsers are failing, this parser will just create a dummy circle at 0,0 to avoid null exceptions
public class LastDummyParser implements IMapObjectParser{
    private GameConfig gameConfig = GameConfig.getInstance();

    @Override
    public Shape parseObject(MapObject mapObject) {
        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(10 / gameConfig.getPPM());
        circleShape.setPosition(new Vector2(0 / gameConfig.getPPM(), 0 / gameConfig.getPPM()));
        System.out.println("Parser Chain missed an object");
        System.out.println(mapObject.toString());
        return circleShape;
    }
}
