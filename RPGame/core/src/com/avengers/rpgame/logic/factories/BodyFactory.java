package com.avengers.rpgame.logic.factories;

import com.avengers.rpgame.game.GameConfig;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class BodyFactory {
    private static GameConfig gameConfig = GameConfig.getInstance();

    public static Body createBody(World world, Shape shape, Vector2 position, boolean isStatic, boolean isFixedRotation) {
        Body body;
        BodyDef bodyDef = new BodyDef(); //Creates the physical definition for body

        if(isStatic){
            bodyDef.type = BodyDef.BodyType.StaticBody;
        }
        else{
            bodyDef.type = BodyDef.BodyType.DynamicBody;
            bodyDef.position.set(position.x/gameConfig.getPPM(),position.y/gameConfig.getPPM());
        }

        bodyDef.linearDamping = 1f; //This kind of simulates friction to ground so dinamic bodys don't move forever
        bodyDef.angularDamping = 0.5f;
        bodyDef.fixedRotation = isFixedRotation; //this stops the player from rotating
        body = world.createBody(bodyDef); //this initializes the player body using the def and puts it inside the world

        body.createFixture(shape, 1.0f);
        shape.dispose(); //As the shape is already "used" we can dispose it
        return body;
    }
}