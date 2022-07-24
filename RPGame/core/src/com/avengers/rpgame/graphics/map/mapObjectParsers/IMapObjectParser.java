package com.avengers.rpgame.graphics.map.mapObjectParsers;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.physics.box2d.Shape;

public interface IMapObjectParser {
    public Shape parseObject(MapObject mapObject);
}
