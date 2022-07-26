package com.avengers.rpgame.logic.entities.mapObject;

import com.avengers.rpgame.logic.entities.Attack;
import com.avengers.rpgame.logic.entities.Item;
import com.avengers.rpgame.logic.entities.Skill;
import com.avengers.rpgame.logic.entities.character.abstractCharacter.AbstractCharacter;
import com.avengers.rpgame.logic.entities.character.behaviour.endFightActions.IVisitor;

//Placeholder for map objects that have a graphic entity but also a logic entity, kind of similar to character
public class MapObject  extends AbstractCharacter {


    @Override
    public void accept(IVisitor visitor) {

    }

    @Override
    public void attackOther(Attack attack, AbstractCharacter targetCharacter) {

    }

    @Override
    public void receiveAttack(Attack attack) {

    }

    @Override
    public void skillOther(Skill attack, AbstractCharacter targetCharacter) {

    }

    @Override
    public void receiveSkill(Skill attack) {

    }

    @Override
    public void itemOther(Item item, AbstractCharacter targetCharacter) {

    }

    @Override
    public void receiveItem(Item item) {

    }
}
