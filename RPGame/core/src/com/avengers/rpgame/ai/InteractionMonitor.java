package com.avengers.rpgame.ai;

import com.avengers.rpgame.ai.observer.Observer;
import com.avengers.rpgame.ai.observer.Subject;
import com.avengers.rpgame.game.GameConfig;
import com.avengers.rpgame.logic.entities.Party;
import com.avengers.rpgame.logic.entities.character.abstractCharacter.AbstractCharacter;
import com.avengers.rpgame.logic.entities.character.components.animatedCharacter.DynamicAnimatedCharacter;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

import java.util.ArrayList;
import java.util.HashMap;

public class InteractionMonitor implements Subject {
    private GameConfig gameConfig = GameConfig.getInstance();

    private ArrayList<Vector2> interactiveObjectsV = new ArrayList<Vector2>();
    private ArrayList<Body> interactiveObjects = new ArrayList<Body>();
    private ArrayList<MapObject> interactiveMapObjects = new ArrayList<MapObject>();
    private HashMap<String,Observer> observers = new HashMap<String,Observer>();
    private float maxDistance = 3;

    private Body currentBody;

    public void setInteractiveObjectsC(ArrayList<Vector2> interactiveObjectsC) {
        this.interactiveObjectsV = interactiveObjectsC;
    }

    public ArrayList<Vector2> getInteractiveObjectsV() {
        return interactiveObjectsV;
    }

    public ArrayList<Body> getInteractiveObjects() {
        return interactiveObjects;
    }

    public ArrayList<MapObject> getInteractiveMapObjects() {
        return interactiveMapObjects;
    }

    public void monitorSurroundings(Party playerParty) {
        Vector2 characterC = new Vector2(((DynamicAnimatedCharacter)playerParty.getActivePartyMember().getAnimatedCharacter()).getPlayer().getPosition());
        boolean objectFound;
        int index=0;
        for(Vector2 object : interactiveObjectsV) {
            objectFound=itIsClose(characterC, object, maxDistance);
            if (objectFound){
                notifyObservers(index,playerParty);
            }
            index++;
        }
    }

    public void addInteractiveObjectV(Vector2 object) {
        interactiveObjectsV.add(object);
    }

    public void addInteractiveObject(Body object) {
        interactiveObjects.add(object);
    }

    public void addInteractiveMapObject(MapObject object) {
        interactiveMapObjects.add(object);
    }

    public boolean itIsClose(Vector2 intObject, Vector2 character, float maxDistance){

        Vector2 distance = new Vector2(intObject.x-character.x, intObject.y-character.y);

        return Math.abs(distance.x) < Math.abs(maxDistance) & Math.abs(distance.y) < Math.abs(maxDistance);
    }

    @Override
    public void addObserver(String id,Observer observer) {
        observers.put(id, observer);
    }

    @Override
    public void removeObserver(String id) {
        observers.remove(id);
    }

    //As part of performance improvements observers was refactored into a hashmap and a for cycle was eliminated
    @Override
    public void notifyObservers(int index, Party playerParty) {
        currentBody = interactiveObjects.get(index);
        String currentMapObject = interactiveMapObjects.get(index).getName();
        if (currentMapObject.contains("Portal")) {
            observers.get("portal").actionTrigger(playerParty, currentBody, currentMapObject);
            return;
        }
        if (currentMapObject.contains("kingNPC")) {
            observers.get("kingNPC").actionTrigger(playerParty, currentBody, currentMapObject);
            return;
        }
        if (currentMapObject.contains("npcCharacter")) {
            observers.get("randomNPC").actionTrigger(playerParty, currentBody, currentMapObject);
            return;
        }
        if (currentMapObject.contains("Treasure")) {
            observers.get("chest").actionTrigger(playerParty, currentBody, currentMapObject);
            return;
        }
        if (currentMapObject.contains("msg")) {
            observers.get("sign").actionTrigger(playerParty, currentBody, currentMapObject);
        }
    }

//    public void notifyObservers(int index, AbstractCharacter playerCharacter) {
//            currentBody = interactiveObjects.get(index);
//            String currentMapObject = interactiveMapObjects.get(index).getName();
//            if (currentMapObject.contains("Portal")) {
//                observers.get("portal").actionTrigger(playerCharacter, currentBody, currentMapObject);
//                return;
//            }
//            if (currentMapObject.contains("kingNPC")) {
//                observers.get("kingNPC").actionTrigger(playerCharacter, currentBody, currentMapObject);
//                return;
//            }
//            if (currentMapObject.contains("npcCharacter")) {
//                observers.get("randomNPC").actionTrigger(playerCharacter, currentBody, currentMapObject);
//                return;
//            }
//            if (currentMapObject.contains("Treasure")) {
//                observers.get("chest").actionTrigger(playerCharacter, currentBody, currentMapObject);
//                return;
//            }
//            if (currentMapObject.contains("msg")) {
//                observers.get("sign").actionTrigger(playerCharacter, currentBody, currentMapObject);
//            }
//    }
}
