package com.avengers.rpgame.ai;

import com.avengers.rpgame.ai.concrete.ChestObserver;
import com.avengers.rpgame.ai.concrete.KingObserver;
import com.avengers.rpgame.ai.concrete.PortalObserver;
import com.avengers.rpgame.ai.concrete.RandomNPCObserver;
import com.avengers.rpgame.logic.entities.character.abstractCharacter.AbstractCharacter;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

import java.util.ArrayList;


public class AIManager {
    private static AIManager instance;
    private AlliesMovementAI allysMovement;
    private InteractionMonitor interactionMonitor;
    private MonsterBattleAI monsterBattleAI;

    private AIManager() {
        allysMovement = new AlliesMovementAI();
        interactionMonitor = new InteractionMonitor();
        monsterBattleAI = new MonsterBattleAI();

        interactionMonitor.addObserver(new PortalObserver("portal"));
        interactionMonitor.addObserver(new KingObserver("king"));
        interactionMonitor.addObserver(new RandomNPCObserver("randomNPC"));
        interactionMonitor.addObserver(new ChestObserver("chest"));
    }


    public void moveAllies(AbstractCharacter playerCharacter, AbstractCharacter ally1Character, float partyDistance){
        allysMovement.setAllyPosition(playerCharacter, ally1Character, partyDistance );
    }

    public void monitorSurroundings(AbstractCharacter playerCharacter){
        interactionMonitor.monitorSurroundings(playerCharacter);
    }

    public void addInteractiveObjectV(Vector2 object){
        interactionMonitor.addInteractiveObjectV(object);
    }

    public void addInteractiveObject(Body object){
        interactionMonitor.addInteractiveObject(object);
    }

    public void addInteractiveMapObject(MapObject object){
        interactionMonitor.addInteractiveMapObject(object);
    }

    public ArrayList<Vector2> getInteractiveObjectsV(){
        return interactionMonitor.getInteractiveObjectsV();
    }

    public ArrayList<Body> getInteractiveObjects(){
        return interactionMonitor.getInteractiveObjects();
    }

    public ArrayList<MapObject> getInteractiveMapObjects(){
        return interactionMonitor.getInteractiveMapObjects();
    }

    public MonsterBattleAI accessMonsterBattleAI(){
        return monsterBattleAI;
    }

    //PATRON Singleton
    public static AIManager getInstance() {
        if (instance == null) {
            instance = new AIManager();
        }
        return instance;
    }
}
