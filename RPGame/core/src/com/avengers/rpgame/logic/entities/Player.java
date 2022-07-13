package com.avengers.rpgame.logic.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Player extends Sprite {
    private Vector2 velocity = new Vector2(); //vector2 holds X and Y coordinates (better than having 2 floats)
    private float speed = 60*2, gravity = 60*1.8f;

    public Player(Sprite sprite){
        super(sprite);
    }

//    @Override
    public void draw(SpriteBatch spriteBatch){
        update(Gdx.graphics.getDeltaTime());
        super.draw(spriteBatch);
    }

    public void update(float delta) {
        //apply gravity. Using delta make's it independent from framerate
        velocity.y += gravity * delta;

        //clamp velocity
        if(velocity.y > speed)
            velocity .y = speed;
        else if(velocity.y < speed)
            velocity.y = -speed;

        //save old position
        float oldX = getX(), oldY = getY();

        //move on X
        setX(getX() + velocity.x * delta);



        //move on y
        setY(getY() + velocity.y * delta);



    }
}