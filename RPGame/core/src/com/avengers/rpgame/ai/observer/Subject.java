package com.avengers.rpgame.ai.observer;

import com.avengers.rpgame.ai.observer.Observer;
import com.avengers.rpgame.logic.entities.Party;
import com.avengers.rpgame.logic.entities.character.abstractCharacter.AbstractCharacter;

public interface Subject {
	void addObserver(String id, Observer observer);
	void removeObserver(String id);
	void notifyObservers(int index, Party playerParty);
}