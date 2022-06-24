package com.avengers.rpgame.logic.entities.character.components;

//Defines a class of character, for example: mage, archer, knight, human, elf, etc...
 public class CharacterClass {
    private int idCharacterClass;
    private String name; // Example: Mage
    private String description; // Example: Mages are magical beings, they use special magic to attack their enemies from distance.
    private int initialHealthPoints;
    private int initialMagicPoints;
    private int initialHPRecoveryRate;
    private int initialMPRecoveryRate;
    private int initialStrength;
    private int initialSpeed;
    private int initialMagic;
    private int initialResistance;
    private int initialLuck;

    public CharacterClass() {
    }

    public CharacterClass(int idCharacterClass, String name, String description, int initialHealthPoints, int initialMagicPoints, int initialHPRecoveryRate, int initialMPRecoveryRate, int initialStrength, int initialSpeed, int initialMagic, int initialResistance, int initialLuck) {
       this.idCharacterClass = idCharacterClass;
       this.name = name;
       this.description = description;
       this.initialHealthPoints = initialHealthPoints;
       this.initialMagicPoints = initialMagicPoints;
       this.initialHPRecoveryRate = initialHPRecoveryRate;
       this.initialMPRecoveryRate = initialMPRecoveryRate;
       this.initialStrength = initialStrength;
       this.initialSpeed = initialSpeed;
       this.initialMagic = initialMagic;
       this.initialResistance = initialResistance;
       this.initialLuck = initialLuck;
    }

    public int getIdCharacterClass() {
       return idCharacterClass;
    }

    public void setIdCharacterClass(int idCharacterClass) {
       this.idCharacterClass = idCharacterClass;
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

    public int getInitialHealthPoints() {
       return initialHealthPoints;
    }

    public void setInitialHealthPoints(int initialHealthPoints) {
       this.initialHealthPoints = initialHealthPoints;
    }

    public int getInitialMagicPoints() {
       return initialMagicPoints;
    }

    public void setInitialMagicPoints(int initialMagicPoints) {
       this.initialMagicPoints = initialMagicPoints;
    }

    public int getInitialHPRecoveryRate() {
       return initialHPRecoveryRate;
    }

    public void setInitialHPRecoveryRate(int initialHPRecoveryRate) {
       this.initialHPRecoveryRate = initialHPRecoveryRate;
    }

    public int getInitialMPRecoveryRate() {
       return initialMPRecoveryRate;
    }

    public void setInitialMPRecoveryRate(int initialMPRecoveryRate) {
       this.initialMPRecoveryRate = initialMPRecoveryRate;
    }

    public int getInitialStrength() {
       return initialStrength;
    }

    public void setInitialStrength(int initialStrength) {
       this.initialStrength = initialStrength;
    }

    public int getInitialSpeed() {
       return initialSpeed;
    }

    public void setInitialSpeed(int initialSpeed) {
       this.initialSpeed = initialSpeed;
    }

    public int getInitialMagic() {
       return initialMagic;
    }

    public void setInitialMagic(int initialMagic) {
       this.initialMagic = initialMagic;
    }

    public int getInitialResistance() {
       return initialResistance;
    }

    public void setInitialResistance(int initialResistance) {
       this.initialResistance = initialResistance;
    }

    public int getInitialLuck() {
       return initialLuck;
    }

    public void setInitialLuck(int initialLuck) {
       this.initialLuck = initialLuck;
    }
 }