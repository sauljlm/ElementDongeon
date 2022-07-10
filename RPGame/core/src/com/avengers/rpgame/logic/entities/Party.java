package com.avengers.rpgame.logic.entities;

import com.avengers.rpgame.logic.entities.character.abstractCharacter.AbstractCharacter;

public class Party {
    private AbstractCharacter partyMember1;
    private AbstractCharacter partyMember2;
    private AbstractCharacter partyMember3;
    private boolean partyMember1Status;
    private boolean partyMember2Status;
    private boolean partyMember3Status;
    private int activePartyMember;

    public Party() {
    }

    public Party(AbstractCharacter partyMember1, AbstractCharacter partyMember2, AbstractCharacter partyMember3, boolean partyMember1Status, boolean partyMember2Status, boolean partyMember3Status, int activePartyMember) {
        this.partyMember1 = partyMember1;
        this.partyMember2 = partyMember2;
        this.partyMember3 = partyMember3;
        this.partyMember1Status = partyMember1Status;
        this.partyMember2Status = partyMember2Status;
        this.partyMember3Status = partyMember3Status;
        this.activePartyMember = activePartyMember;
    }

    public AbstractCharacter getPartyMember1() {
        return partyMember1;
    }

    public void setPartyMember1(AbstractCharacter partyMember1) {
        this.partyMember1 = partyMember1;
    }

    public AbstractCharacter getPartyMember2() {
        return partyMember2;
    }

    public void setPartyMember2(AbstractCharacter partyMember2) {
        this.partyMember2 = partyMember2;
    }

    public AbstractCharacter getPartyMember3() {
        return partyMember3;
    }

    public void setPartyMember3(AbstractCharacter partyMember3) {
        this.partyMember3 = partyMember3;
    }

    public boolean isPartyMember1Status() {
        return partyMember1Status;
    }

    public void setPartyMember1Status(boolean partyMember1Status) {
        this.partyMember1Status = partyMember1Status;
    }

    public boolean isPartyMember2Status() {
        return partyMember2Status;
    }

    public void setPartyMember2Status(boolean partyMember2Status) {
        this.partyMember2Status = partyMember2Status;
    }

    public boolean isPartyMember3Status() {
        return partyMember3Status;
    }

    public void setPartyMember3Status(boolean partyMember3Status) {
        this.partyMember3Status = partyMember3Status;
    }

    public int getActivePartyMember() {
        return activePartyMember;
    }

    public void setActivePartyMember(int activePartyMember) {
        this.activePartyMember = activePartyMember;
    }
}
