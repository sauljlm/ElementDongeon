package com.avengers.rpgame.ai.Interfaces;

import com.avengers.rpgame.logic.entities.character.abstractCharacter.AbstractCharacter;

public interface Subject {
	void addObserver(String id, Observer observer);
	void removeObserver(String id);
	void notifyObservers(int index, AbstractCharacter playerCharacter);
}