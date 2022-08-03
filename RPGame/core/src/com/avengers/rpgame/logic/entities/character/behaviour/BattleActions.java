package com.avengers.rpgame.logic.entities.character.behaviour;

import com.avengers.rpgame.logic.entities.Attack;
import com.avengers.rpgame.logic.entities.Item;
import com.avengers.rpgame.logic.entities.Skill;
import com.avengers.rpgame.logic.entities.character.abstractCharacter.AbstractCharacter;


//Another way to implement this would be to not pass the whole Skill, Attack. But the index so you just go find it inside the character.
//The current way is less efficient but probably simpler to just make it works
public interface BattleActions {
    //Attack other character
    String attackOther(Attack attack, AbstractCharacter targetCharacter);
    //Receive attack
    String receiveAttack(int hpDamage);

    //Use skill on other character
    String skillOther(Skill attack, AbstractCharacter targetCharacter);
    //Use/receive skill on oneself
    void receiveSkill(Skill attack);

    //Use item on other character
    void itemOther(Item item, AbstractCharacter targetCharacter);
    //Use/receive item on oneself
    void receiveItem(Item item);
}