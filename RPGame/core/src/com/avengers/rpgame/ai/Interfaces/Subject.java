package com.avengers.rpgame.ai.Interfaces;

import com.avengers.rpgame.logic.entities.character.abstractCharacter.AbstractCharacter;

public interface Subject {
	void addObserver(Observer o);
	void removeObserver(Observer o);
	void notifyObservers(int index, AbstractCharacter playerCharacter);
}