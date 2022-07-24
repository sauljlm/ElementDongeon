package com.avengers.rpgame.graphics.map.mapObjectParsers;

import com.avengers.rpgame.game.GameConfig;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;

public class RectangleParser implements IMapObjectParser{
    private GameConfig gameConfig = GameConfig.getInstance();
    private IMapObjectParser nextParser = new PolygonParser();

    //creates a PolygonShape object from a tiled RectangleMapObject
    @Override
    public Shape parseObject(MapObject mapObject) {
        if(mapObject instanceof RectangleMapObject){
            Rectangle rectangle = ((RectangleMapObject) mapObject).getRectangle();
            PolygonShape polygon = new PolygonShape();
            Vector2 size = new Vector2((rectangle.x + rectangle.width / 2) / gameConfig.getPPM(),
                    (rectangle.y + rectangle.height / 2) /gameConfig.getPPM());
            polygon.setAsBox(rectangle.width / 2/gameConfig.getPPM(),
                    rectangle.height /2 / gameConfig.getPPM(),
                    size,
                    0.0f);
            return polygon;
        }
        return nextParser.parseObject(mapObject);
    }
}
