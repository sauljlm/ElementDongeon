package com.avengers.rpgame.logic.entities.character.abstractCharacter;

import com.avengers.rpgame.audio.SoundEffectsManager;
import com.avengers.rpgame.logic.entities.Attack;
import com.avengers.rpgame.logic.entities.Item;
import com.avengers.rpgame.logic.entities.Skill;
import com.avengers.rpgame.logic.entities.character.behaviour.BattleActions;
import com.avengers.rpgame.logic.entities.character.behaviour.endFightActions.IVisitor;
import com.avengers.rpgame.logic.entities.character.components.CharacterClass;
import com.avengers.rpgame.logic.entities.character.components.animatedCharacter.AAnimatedCharacter;
import com.badlogic.gdx.math.Vector2;

import java.nio.channels.SelectionKey;
import java.util.ArrayList;

import static com.avengers.rpgame.utils.Resources.resourceSwordAttackSound;

/*******************************************************************************
 *Abstract Father class for all types of characters
 *******************************************************************************/
public abstract class AbstractCharacter implements BattleActions {
    private int idCharacter;
    private String name; //Example: King Jorge 9th
    private String description; //Example: King Jorge 9th was the saved princess Leia when he was 20yo.....
    private Vector2 position;
    private double level;
    private int healthPoints;
    private int magicPoints;
    private int healthPointsMax;
    private int magicPointsMax;
    private int strength;
    private int speed;
    private int magic;
    private int resistance; //Affects hPRecoveryRate/mPRecoveryRate by some math formula
    private int luck;
    private int coins;
    private CharacterClass characterClass;
    private AAnimatedCharacter animatedCharacter;
    private ArrayList<Item> items;
    private ArrayList<Attack> attacks;
    private ArrayList<Skill> skills;

    public AbstractCharacter() {
    }

    public AbstractCharacter(int idCharacter, String name, String description, Vector2 position, double level, int healthPoints, int magicPoints, int healthPointsMax, int magicPointsMax, int strength, int speed, int magic, int resistance, int luck, int coins, CharacterClass characterClass, AAnimatedCharacter animatedEntity, ArrayList<Item> items, ArrayList<Attack> attacks, ArrayList<Skill> skills) {
        this.idCharacter = idCharacter;
        this.name = name;
        this.description = description;
        this.position = position;
        this.level = level;
        this.healthPoints = healthPoints;
        this.healthPointsMax = healthPointsMax;
        this.magicPoints = magicPoints;
        this.magicPointsMax = magicPointsMax;
        this.strength = strength;
        this.speed = speed;
        this.magic = magic;
        this.resistance = resistance;
        this.luck = luck;
        this.coins = coins;
        this.characterClass = characterClass;
        this.animatedCharacter = animatedEntity;
        this.items = items;
        this.attacks = attacks;
        this.skills = skills;
    }

    public int getIdCharacter() {
        return idCharacter;
    }

    public void setIdCharacter(int idCharacter) {
        this.idCharacter = idCharacter;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public double getLevel() {
        return level;
    }

    public void setLevel(double level) { this.level = level; }

    public int getHealthPoints() {
        return healthPoints;
    }

    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    public int getMagicPoints() {
        return magicPoints;
    }

    public void setMagicPoints(int magicPoints) {
        this.magicPoints = magicPoints;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getMagic() {
        return magic;
    }

    public void setMagic(int magic) {
        this.magic = magic;
    }

    public int getResistance() {
        return resistance;
    }

    public void setResistance(int resistance) {
        this.resistance = resistance;
    }

    public int getLuck() {
        return luck;
    }

    public void setLuck(int luck) {
        this.luck = luck;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public int getHealthPointsMax() {
        return healthPointsMax;
    }

    public void setHealthPointsMax(int healthPointsMax) {
        this.healthPointsMax = healthPointsMax;
    }

    public int getMagicPointsMax() {
        return magicPointsMax;
    }

    public void setMagicPointsMax(int magicPointsMax) {
        this.magicPointsMax = magicPointsMax;
    }

    public CharacterClass getCharacterClass() {
        return characterClass;
    }

    public void setCharacterClass(CharacterClass characterClass) {
        this.characterClass = characterClass;
    }

    public AAnimatedCharacter getAnimatedCharacter() {
        return animatedCharacter;
    }

    public void setAnimatedCharacter(AAnimatedCharacter animatedCharacter) {
        this.animatedCharacter = animatedCharacter;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public ArrayList<Attack> getAttacks() {
        return attacks;
    }

    public void setAttacks(ArrayList<Attack> attacks) {
        this.attacks = attacks;
    }

    public ArrayList<Skill> getSkills() {
        return skills;
    }

    public void setSkills(ArrayList<Skill> skills) {
        this.skills = skills;
    }

    public void addNewItem (Item item) {
        this.items.add(item);
    }

    public void deleteItem (int id) {
        int idItem = 0;
        for (int i = 0; i < this.items.size(); i++) {
            if (this.items.get(i).getId() == id) {
                idItem = i;
            }
        }
        this.items.remove(idItem);
    }

    public void addNewAttack (Attack attack) {
        this.attacks.add(attack);
    }

    public void deleteAttack (int id) {
        int idItem = 0;
        for (int i = 0; i < this.attacks.size(); i++) {
            if (this.attacks.get(i).getId() == id) {
                idItem = i;
            }
        }
        this.attacks.remove(idItem);
    }

    public void addNewSkill (Skill skill) {
        this.skills.add(skill);
    }

    public void deleteSkill (int id) {
        int idItem = 0;
        for (int i = 0; i < this.skills.size(); i++) {
            if (this.skills.get(i).getId() == id) {
                idItem = i;
            }
        }
        this.skills.remove(idItem);
    }


    //BattleSystem Rewards Visitor pattern
    public abstract void accept(IVisitor visitor);

    //BattleSystem
    //Attack other character
    public String attackOther(Attack attack, AbstractCharacter targetCharacter) {
        String result="";
        if(this.getMagicPoints() >= attack.getmPCost()){
            this.setMagicPoints(this.getMagicPoints()-attack.getmPCost());
            int hpDamage = (int) ((attack.getHPEffect()/2)+(attack.getHPEffect()*((this.strength*0.75+this.speed*0.45+this.luck*0.1+this.magic*0.2)/100)));
//            int hpDamage = attack.getHPEffect();
            result=targetCharacter.receiveAttack(hpDamage);
        } else {
            result="0 (sin magia)";
        }
        return result;
    }

    //Receive attack
    public String receiveAttack(int hpDamage) {
        SoundEffectsManager.getInstance().play(resourceSwordAttackSound, false);
        this.setHealthPoints(this.getHealthPoints()-(hpDamage-(hpDamage/4*this.resistance/100)));
        return String.valueOf(hpDamage-(hpDamage/4*this.resistance/100));
    }

    //Use skill on other character
    public String skillOther(Skill skill, AbstractCharacter targetCharacter) {
        String result="";
        if(this.getMagicPoints() >= skill.getmPCost()){
            this.setMagicPoints(this.getMagicPoints()-skill.getmPCost());
            if(skill.getType().equalsIgnoreCase("internal")) {
                receiveSkill(skill);
                result= String.valueOf(skill.gethPEffect());
            } else {
                int hpDamage = (int) ((skill.gethPEffect()/2)+(skill.gethPEffect()*((this.strength*0.75+this.speed*0.45+this.luck*0.1+this.magic*0.2)/100)));
                result=targetCharacter.receiveAttack(hpDamage);
            }
        }else{
            result="0 (sin magia)";
        }
        return result;
    }

    //Use/receive skill on oneself
    public void receiveSkill(Skill skill) {
        this.setStrength(this.getStrength()+this.getStrength()*skill.getStrengthEffect()/100);
        this.setSpeed(this.getSpeed()+this.getSpeed()*skill.getSpeedEffect()/100);
        this.setMagic(this.getMagic()+this.getMagic()*skill.getMagicEffect()/100);
        this.setResistance(this.getResistance()+this.getResistance()*skill.getResistanceEffect()/100);
        this.setLuck(this.getLuck()+this.getLuck()* skill.getLuckEffect()/100);
        this.setMagicPoints(this.getMagicPoints()+this.getMagicPoints()*skill.getmPEffect()/100);
        this.setHealthPoints(this.getHealthPoints()+this.getHealthPoints()*skill.gethPEffect()/100);
    }

    //Use item on other character
    public void itemOther(Item item, AbstractCharacter targetCharacter) {
        targetCharacter.receiveItem(item);
    }

    //Use/receive item on oneself
    //This assumes the effects are % like 10 So the effect on strength for 10 is to increase base strength in 10%
    public void receiveItem(Item item) {
        this.setStrength(this.getStrength()+this.getStrength()*item.getStrengthEffect()/100);
        this.setSpeed(this.getSpeed()+this.getSpeed()*item.getSpeedEffect()/100);
        this.setMagic(this.getMagic()+this.getMagic()*item.getMagicEffect()/100);
        this.setResistance(this.getResistance()+this.getResistance()*item.getResistanceEffect()/100);
        this.setLuck(this.getLuck()+this.getLuck()* item.getLuckEffect()/100);
        this.setMagicPoints(this.getMagicPoints()+this.getMagicPointsMax()*item.getmPEffect()/100);
        this.setHealthPoints(this.getHealthPoints()+this.getHealthPointsMax()*item.gethPEffect()/100);
        if(healthPoints>healthPointsMax)healthPoints=healthPointsMax;
        if(magicPoints>magicPointsMax)magicPoints=magicPointsMax;
        if(item.getDescription().contains("Poción")){
            items.remove(items.indexOf(item));
        }
    }

    public void restoreHP() {
        this.setHealthPoints(this.getHealthPointsMax());
    }

    public void restoreMP() {
        this.setMagicPoints(this.getMagicPointsMax());
    }

    @Override
    public String toString() {
        return "AbstractCharacter{" +
                "idCharacter=" + idCharacter +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", position=" + position +
                ", level=" + level +
                ", healthPoints=" + healthPoints +
                ", magicPoints=" + magicPoints +
                ", strength=" + strength +
                ", speed=" + speed +
                ", magic=" + magic +
                ", resistance=" + resistance +
                ", luck=" + luck +
                ", coins=" + coins +
                ", characterClass=" + characterClass +
                ", animatedCharacter=" + animatedCharacter +
                ", items=" + items +
                ", attacks=" + attacks +
                ", skills=" + skills +
                '}';
    }

    public void dispose(){
        animatedCharacter.dispose();
    }
}
