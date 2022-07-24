package com.avengers.rpgame.graphics.map.mapObjectParsers;

import com.avengers.rpgame.game.GameConfig;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.Shape;

public class PolylineParser implements IMapObjectParser{
    private GameConfig gameConfig = GameConfig.getInstance();
    private IMapObjectParser nextParser = new LastDummyParser();

    @Override
    public Shape parseObject(MapObject mapObject) {
        if (mapObject instanceof PolygonMapObject) {
            float[] vertices = ((PolygonMapObject) mapObject).getPolygon().getTransformedVertices();
            int chainLenght = (vertices.length / 2) + 1;
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
        return nextParser.parseObject(mapObject);
    }
}
