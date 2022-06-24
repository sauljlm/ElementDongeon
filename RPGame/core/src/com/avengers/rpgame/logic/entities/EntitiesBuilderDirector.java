package com.avengers.rpgame.logic.entities;

import com.avengers.rpgame.RPGame;
import com.avengers.rpgame.logic.entities.character.builder.CharacterBuilder;
import com.avengers.rpgame.logic.entities.character.builder.ICharacterDirector;
import com.avengers.rpgame.logic.entities.character.components.AnimatedCharacter;
import com.avengers.rpgame.logic.entities.character.components.skin.Skin;
import com.avengers.rpgame.logic.entities.character.components.skin.AnimationAssets;
import com.badlogic.gdx.physics.box2d.World;

import static com.avengers.rpgame.utils.FileManager.*;
import static com.avengers.rpgame.utils.Resources.*;

public class EntitiesBuilderDirector implements ICharacterDirector {

    //DummyPlayer only contains the animatedCharacter data for it to render on the game. This is only for developing/debugging
    public void buildDummyPlayer(CharacterBuilder builder, World world, RPGame rpGame){
        //TODO Skin needs to be abstracted into a abstractFactory /Factory
        Skin skin = new Skin();
        skin.setUp(new AnimationAssets(loadTexture(resourceMageTextureUp), loadTextureAtlas(resourceMageTextureMapUp), "up"));
        skin.setDown(new AnimationAssets(loadTexture(resourceMageTextureDown), loadTextureAtlas(resourceMageTextureMapDown), "down"));
        skin.setLeft(new AnimationAssets(loadTexture(resourceMageTextureLeft), loadTextureAtlas(resourceMageTextureMapLeft), "left"));
        skin.setRight(new AnimationAssets(loadTexture(resourceMageTextureRight), loadTextureAtlas(resourceMageTextureMapRight), "right"));
        AnimatedCharacter animatedCharacter = new AnimatedCharacter(skin, world, rpGame);
        builder.setAnimatedCharacter(animatedCharacter);
    }
}