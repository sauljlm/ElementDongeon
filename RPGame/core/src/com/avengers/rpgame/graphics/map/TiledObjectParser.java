package com.avengers.rpgame.graphics.map;

import com.avengers.rpgame.ai.AIManager;
import com.avengers.rpgame.game.GameConfig;
import com.avengers.rpgame.graphics.map.mapObjectParsers.IMapObjectParser;
import com.avengers.rpgame.graphics.map.mapObjectParsers.RectangleParser;
import com.avengers.rpgame.logic.factories.BodyFactory;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;

/*******************************************************************************
 *This class is going to receive all the objects coming from the tiled map
 * layer and parse them by type/class
 *******************************************************************************/
//The other way to do this is by assigning properties to every object created on tiled (to much work..) this is faster for dev, maybe more CPU consuming though
public class TiledObjectParser {
    private static GameConfig gameConfig = GameConfig.getInstance();
    private static IMapObjectParser parser = new RectangleParser();

    public static  void parseTiledObjectLayer(World world, MapObjects mapObject){
        boolean isStatic = true;
        for(MapObject object : mapObject) {
            Shape shape = parser.parseObject(object);

// This is failing because we are not sending the position to bodyFactory, but dynamic map objects are not being used for now
//            if(object instanceof EllipseMapObject)isStatic = false;

            BodyFactory.createBody(world, shape, new Vector2(0,0),isStatic, false);
        }
    }

    public static  void parseTiledInteractiveLayer(World world, MapObjects mapObject){
        boolean isStatic = true;
        Shape shape = null;
        Vector2 pos = new Vector2();
        for(MapObject object : mapObject) {
            AIManager aiManager = AIManager.getInstance();
            shape = parser.parseObject(object);

            //For all rectangles add the coordinates to the tracking array
            if(object instanceof RectangleMapObject){
                aiManager.addInteractiveMapObject(object);
                Vector2 center = new Vector2((((RectangleMapObject)object).getRectangle().x + ((RectangleMapObject)object).getRectangle().width / 2) / gameConfig.getPPM(),
                        (((RectangleMapObject)object).getRectangle().y + ((RectangleMapObject)object).getRectangle().height / 2) /gameConfig.getPPM());
                aiManager.addInteractiveObjectV(center); //Add the object coordinates for AI monitoring
            }
            Body body = BodyFactory.createBody(world, shape, new Vector2(0,0),true, true);
            aiManager.addInteractiveObject(body); //Adds the body for AI possible interactions
        }
    }
}
