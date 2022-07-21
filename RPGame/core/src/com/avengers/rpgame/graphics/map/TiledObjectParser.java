package com.avengers.rpgame.graphics.map;
import com.avengers.rpgame.ai.AIManager;
import com.avengers.rpgame.game.GameConfig;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.*;
import com.badlogic.gdx.math.Ellipse;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

//This class is going to receive all the objects coming from the tiled map layer and parse them by type/class
//The other way to do this is by assigning properties to every object created on tiled (to much work..) this is faster for dev, maybe more CPU consuming though
public class TiledObjectParser {

    private static GameConfig gameConfig = GameConfig.getInstance();
//    private AIManager aiManager = new AIManager();


    public static  void parseTiledObjectLayer(World world, MapObjects mapObject){
        boolean isStatic = true;
        Shape shape = null;
        for(MapObject object : mapObject) {
            if (object instanceof RectangleMapObject) {
                shape = createRectangle((RectangleMapObject) object);
            } else
                if (object instanceof PolygonMapObject) {
                    shape = createPolygon((PolygonMapObject) object);
                } else
                    if (object instanceof EllipseMapObject) {
                        shape = createCircle((EllipseMapObject) object);
                    } else
                        {
                            continue;
                        }
            //Create box has this same code, probably this can be abstracted
            Body body;
            BodyDef bodyDef = new BodyDef(); //Creates the physical definition for body
            if (isStatic) {
                bodyDef.type = BodyDef.BodyType.StaticBody;
            }
            if(object instanceof EllipseMapObject) {
                bodyDef.type = BodyDef.BodyType.DynamicBody;
            }
            bodyDef.fixedRotation = false; //this stops the object from rotating
            bodyDef.linearDamping = 1f; //This kind of simulates friction to ground so dinamic bodys don't move forever
            bodyDef.angularDamping = 0.5f;
            body = world.createBody(bodyDef); //this initialices the object body using the def and puts it inside the world
            body.createFixture(shape, 5.0f);
        }
        shape.dispose();
    }
    public static  void parseTiledInteractiveLayer(World world, MapObjects mapObject){
        boolean isStatic = true;
        Shape shape = null;
        Vector2 pos = new Vector2();
        for(MapObject object : mapObject) {
            AIManager aiManager = AIManager.getInstance();

            if (object instanceof RectangleMapObject) {
                Rectangle rectangle = ((RectangleMapObject)object).getRectangle();
                Vector2 center = new Vector2((rectangle.x + rectangle.width / 2) / gameConfig.getPPM(),
                        (rectangle.y + rectangle.height / 2) /gameConfig.getPPM());
                pos = center;

                aiManager.addInteractiveMapObject(object);
                aiManager.addInteractiveObjectV(center); //Add the object coordinates for AI monitoring

                shape = createRectangle((RectangleMapObject) object);
            } else
            {
                continue;
            }
            //Create box has this same code, probably this can be abstracted
            Body body;
            BodyDef bodyDef = new BodyDef(); //Creates the physical definition for body
            bodyDef.type = BodyDef.BodyType.StaticBody;
            bodyDef.fixedRotation = true; //this stops the object from rotating
            //bodyDef.position.set(pos.x,pos.y);
            bodyDef.linearDamping = 1f; //This' kind of simulates friction to ground so dinamic bodys don't move forever
            bodyDef.angularDamping = 0.5f;
            body = world.createBody(bodyDef); //this initialices the object body using the def and puts it inside the world
            body.createFixture(shape, 5.0f);
            aiManager.addInteractiveObject(body); //Adds the body for AI possible interactions
        }
        shape.dispose();
    }

    //creates a PolygonShape object from a tiled RectangleMapObject
    private static PolygonShape createRectangle(RectangleMapObject rectangleObject) {

        Rectangle rectangle = rectangleObject.getRectangle();
        PolygonShape polygon = new PolygonShape();
        Vector2 size = new Vector2((rectangle.x + rectangle.width / 2) / gameConfig.getPPM(),
                (rectangle.y + rectangle.height / 2) /gameConfig.getPPM());
        polygon.setAsBox(rectangle.width / 2/gameConfig.getPPM(),
                rectangle.height /2 / gameConfig.getPPM(),
                size,
                0.0f);
        return polygon;
    }

    //Polygons are restricted to 8 vertices, the workaround is to turn a polygon into a chainshape, I have no idea of the performance impact of this
    //The restriction is here https://github.com/openai/box2d-py/blob/b273568a0e57d51ce41d20d012571186a1839620/Box2D/Collision/Shapes/b2PolygonShape.cpp#L122

    //creates a ChainShape object from a tiled PolygonMapObject
    private static ChainShape createPolygon(PolygonMapObject polygonObject) {
        float[] vertices = polygonObject.getPolygon().getTransformedVertices();
        int chainLenght= (vertices.length / 2)+1;
        Vector2[] worldVertices = new Vector2[chainLenght];
        for (int i = 0; i < (vertices.length / 2); ++i) {
            worldVertices[i] = new Vector2();
            worldVertices[i].x = vertices[i * 2] / gameConfig.getPPM();
            worldVertices[i].y = vertices[i * 2 + 1] / gameConfig.getPPM();
        }
        //This adds a final vertice with the location of the first one so the chainshape/polygon get's closed
        worldVertices[vertices.length / 2] = new Vector2();
        worldVertices[vertices.length / 2].x = vertices[0] / gameConfig.getPPM();
        worldVertices[vertices.length / 2].y = vertices[1] / gameConfig.getPPM();

        ChainShape chain = new ChainShape();
        chain.createChain(worldVertices);
        return chain;
    }

    //creates a CircleShape object from a tiled EllipseMapObject, circle takes the horizontal radio of the ellipse
    private static CircleShape createCircle(EllipseMapObject circleObject) {
        Ellipse ellipse = circleObject.getEllipse();
        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(ellipse.width / gameConfig.getPPM());
        circleShape.setPosition(new Vector2(ellipse.x / gameConfig.getPPM(), ellipse.y / gameConfig.getPPM()));
        return circleShape;
    }
}
