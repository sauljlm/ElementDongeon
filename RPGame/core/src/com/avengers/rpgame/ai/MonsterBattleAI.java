package com.avengers.rpgame.ai;

import com.avengers.rpgame.game.GameConfig;
import com.avengers.rpgame.logic.entities.Attack;
import com.avengers.rpgame.logic.entities.Item;
import com.avengers.rpgame.logic.entities.Party;
import com.avengers.rpgame.logic.entities.Skill;
import com.avengers.rpgame.logic.entities.character.abstractCharacter.AbstractCharacter;
import com.badlogic.gdx.math.MathUtils;

public class MonsterBattleAI {
    private GameConfig config = GameConfig.getInstance();
    private int difficulty;

    public MonsterBattleAI() {
    }

    public MonsterBattleAI(GameConfig config, int difficulty) {
        this.difficulty = difficulty;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public String selectAction(AbstractCharacter oneSelfCharacter, AbstractCharacter targetCharacter){
        int selectedNumber;
        if(oneSelfCharacter.getItems().size()>0 && oneSelfCharacter.getHealthPoints()<oneSelfCharacter.getHealthPointsMax()){
            selectedNumber = MathUtils.random(1);
        }else{
            selectedNumber = MathUtils.random(0);
        }
        switch (selectedNumber){
            case 0: //Attack enemy
                Attack attack = selectAttack(oneSelfCharacter);
                String hpValue = oneSelfCharacter.attackOther(attack, targetCharacter);
                return attack.getDescription() +" ha infringido " +hpValue + " HP";
            case 1: //UseItem on himself
                Item item = selectItem(oneSelfCharacter);
                oneSelfCharacter.receiveItem(item);
                return (item.getDescription()+" y se ha curado "+ item.gethPEffect()+ " HP");
            case 2: //UNUSED
                Skill skill = selectSkill(oneSelfCharacter);
//                selectSkill(Character);
                return (skill.getDescription());
            default:
                return "Ya no quiero pelear.";
        }
    }

    public Attack selectAttack(AbstractCharacter Character){
        int selectedNumber = Character.getAttacks().size() -1;
        selectedNumber = MathUtils.random(selectedNumber);
        return Character.getAttacks().get(selectedNumber);
    }

    public Skill selectSkill(AbstractCharacter Character){
        int selectedNumber = Character.getSkills().size() -1;
        selectedNumber = MathUtils.random(selectedNumber);
        return Character.getSkills().get(selectedNumber);
    }

    public Item selectItem(AbstractCharacter Character){
        int selectedNumber = Character.getItems().size() -1;
        selectedNumber = MathUtils.random(selectedNumber);
        return Character.getItems().get(selectedNumber);
    }

    public AbstractCharacter selectMonster(Party npcParty){
        int member = MathUtils.random(2);
        switch (member){
            case 0:
                return npcParty.getPartyMember(1);
            case 1:
                return npcParty.getPartyMember(2);
            case 2:
                return npcParty.getPartyMember(3);
            default:
                return npcParty.getPartyMember(1);
        }
    }
}
