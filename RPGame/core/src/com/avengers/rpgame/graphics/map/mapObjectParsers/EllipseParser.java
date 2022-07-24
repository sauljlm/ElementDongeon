package com.avengers.rpgame.graphics.map.mapObjectParsers;

import com.avengers.rpgame.game.GameConfig;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.EllipseMapObject;
import com.badlogic.gdx.math.Ellipse;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Shape;

public class EllipseParser implements IMapObjectParser{
    private GameConfig gameConfig = GameConfig.getInstance();
    private IMapObjectParser nextParser = new LastDummyParser();

    //creates a CircleShape object from a tiled EllipseMapObject, circle takes the horizontal radio of the ellipse
    @Override
    public Shape parseObject(MapObject mapObject) {
        if(mapObject instanceof EllipseMapObject){
            Ellipse ellipse = ((EllipseMapObject)mapObject).getEllipse();
            CircleShape circleShape = new CircleShape();
            circleShape.setRadius(ellipse.width / gameConfig.getPPM());
            circleShape.setPosition(new Vector2(ellipse.x / gameConfig.getPPM(), ellipse.y / gameConfig.getPPM()));
            return circleShape;
        }
        return nextParser.parseObject(mapObject);
    }
}
