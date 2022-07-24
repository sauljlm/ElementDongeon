package com.avengers.rpgame.ai;

import com.avengers.rpgame.game.GameConfig;
import com.avengers.rpgame.logic.entities.character.abstractCharacter.AbstractCharacter;
import com.avengers.rpgame.logic.entities.character.components.animatedCharacter.DynamicAnimatedCharacter;
import com.badlogic.gdx.math.Vector2;

public class AlliesMovement {
    private GameConfig config = GameConfig.getInstance();

    public void setAllyPosition(AbstractCharacter playerCharacter, AbstractCharacter ally1Character, float partyDistance){
        float horizontalForce = 0;
        float verticalForce = 0;
        float movementSpeed = 4f;

        if(config.isGodMode()){
            movementSpeed = 4;
        }
        Vector2 velocity = new Vector2();
        Vector2 mainCharacterPos = new Vector2();
        Vector2 followerCharacterPos = new Vector2();
        Vector2 distance = new Vector2();


        mainCharacterPos = ((DynamicAnimatedCharacter)playerCharacter.getAnimatedCharacter()).getPlayer().getPosition();
        followerCharacterPos = ((DynamicAnimatedCharacter)ally1Character.getAnimatedCharacter()).getPlayer().getPosition();
        distance = mainCharacterPos.sub(followerCharacterPos);

        if(distance.x > partyDistance || distance.x < -partyDistance){
            horizontalForce = movementSpeed;
        }
        if(distance.y > partyDistance || distance.y < -partyDistance) {
            verticalForce = movementSpeed;
        }
        if(distance.x > partyDistance*4 || distance.x < -partyDistance*4){
            horizontalForce = movementSpeed*2;
        }
        if(distance.y > partyDistance*4 || distance.y < -partyDistance*4){
            verticalForce = movementSpeed*2;
        }

        velocity.x = horizontalForce * distance.x;
        velocity.y = verticalForce * distance.y;

        if(Math.abs(velocity.x) > Math.abs(velocity.y)){
            if(velocity.x<0) {
                ally1Character.getAnimatedCharacter().setAction("runningLeft");
            }
            if(velocity.x>0) {
                ally1Character.getAnimatedCharacter().setAction("runningRight");
            }
        }
        if(Math.abs(velocity.x) < Math.abs(velocity.y)) {
            if(velocity.y>0) {
                ally1Character.getAnimatedCharacter().setAction("runningUp");
            }
            if(velocity.y<0) {
                ally1Character.getAnimatedCharacter().setAction("runningDown");
            }
        }

        ((DynamicAnimatedCharacter)ally1Character.getAnimatedCharacter()).getPlayer().setLinearVelocity(velocity.x,velocity.y);
    }
}
