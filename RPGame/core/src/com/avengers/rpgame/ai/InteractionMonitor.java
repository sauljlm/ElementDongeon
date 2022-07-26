package com.avengers.rpgame.ai;

import com.avengers.rpgame.ai.Interfaces.Observer;
import com.avengers.rpgame.ai.Interfaces.Subject;
import com.avengers.rpgame.game.GameConfig;
import com.avengers.rpgame.graphics.dialog.Dialog;
import com.avengers.rpgame.logic.entities.Item;
import com.avengers.rpgame.logic.entities.character.abstractCharacter.AbstractCharacter;
import com.avengers.rpgame.logic.entities.character.components.animatedCharacter.DynamicAnimatedCharacter;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

import java.util.ArrayList;
import java.util.List;

public class InteractionMonitor implements Subject {
    private GameConfig config;

    private ArrayList<Vector2> interactiveObjectsV = new ArrayList<Vector2>();
    private ArrayList<Body> interactiveObjects = new ArrayList<Body>();
    private ArrayList<MapObject> interactiveMapObjects = new ArrayList<MapObject>();
    private List<Observer> observers = new ArrayList<Observer>();
    private float maxDistance = 3;
    private GameConfig gameConfig = GameConfig.getInstance();

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

    public void monitorSurroundings(AbstractCharacter playerCharacter) {
        Vector2 characterC = new Vector2(((DynamicAnimatedCharacter)playerCharacter.getAnimatedCharacter()).getPlayer().getPosition());
        boolean objectFound = false;
        int index=0;
        for(Vector2 object : interactiveObjectsV) {
            objectFound=itIsClose(characterC, object, maxDistance);
            if (objectFound){
                notifyObservers(index,playerCharacter);
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
        config = GameConfig.getInstance();

        Vector2 object = new Vector2();
        object.set(intObject.x * config.getPPM(),intObject.y * config.getPPM());

        Vector2 distance = new Vector2();
        distance.set(intObject.x-character.x, intObject.y-character.y);
        //Vector2 distance = character.sub(intObject);

//        System.out.println("IntObject = "+intObject);
//        System.out.println("Object = "+object);
//        System.out.println("character = "+character);
//        System.out.println("distance = "+distance);


        if(Math.abs(distance.x) < Math.abs(maxDistance) & Math.abs(distance.y) < Math.abs(maxDistance)){
            // System.out.println("Close encounter!");
            return true;
        }
        return false;
    }

    @Override
    public void addObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {}

    @Override
    public void notifyObservers(int index, AbstractCharacter playerCharacter) {
        for(Observer o : observers){
            Body currentBody = interactiveObjects.get(index);
            String currentMapObject = interactiveMapObjects.get(index).getName();
            if (interactiveMapObjects.get(index).getName().contains("Portal") && o.getObserverName().equals("portal")) {
                o.actionTrigger(playerCharacter, currentBody, currentMapObject);
            } else if (interactiveMapObjects.get(index).getName().contains("kingNPC") && o.getObserverName().equals("king")) {
                o.actionTrigger(playerCharacter, currentBody, currentMapObject);
            } else if (interactiveMapObjects.get(index).getName().contains("npcCharacter") && o.getObserverName().equals("randomNPC")) {
                o.actionTrigger(playerCharacter, currentBody, currentMapObject);
            } else if (interactiveMapObjects.get(index).getName().contains("Treasure") && o.getObserverName().equals("chest")) {
                o.actionTrigger(playerCharacter, currentBody, currentMapObject);
            }
        }
    }
}
