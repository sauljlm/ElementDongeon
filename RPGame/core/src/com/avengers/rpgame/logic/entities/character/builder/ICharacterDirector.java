package com.avengers.rpgame.logic.entities.character.builder;

import com.avengers.rpgame.RPGame;
import com.badlogic.gdx.physics.box2d.World;

//Interface to define the different characters we can create with the BuilderDirector
public interface ICharacterDirector {
    public void buildKnightDummy(CharacterBuilder builder, RPGame rpGame);
    public void buildKnight(CharacterBuilder builder, World world, RPGame rpGame, String playerName);
    public void buildArcher(CharacterBuilder builder, World world, RPGame rpGame, String playerName);
    public void buildMage(CharacterBuilder builder, World world, RPGame rpGame, String playerName);
}