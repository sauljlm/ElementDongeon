package com.avengers.rpgame.logic.entities;

import com.avengers.rpgame.logic.entities.character.abstractCharacter.AbstractCharacter;
// import org.graalvm.compiler.core.common.type.ArithmeticOpTable;

public class Party {
    private AbstractCharacter partyMember1;
    private AbstractCharacter partyMember2;
    private AbstractCharacter partyMember3;
    private boolean partyMember1Status;
    private boolean partyMember2Status;
    private boolean partyMember3Status;
    private int activePartyMemberId = 1;

    public Party() {
    }

    public Party(AbstractCharacter partyMember1, AbstractCharacter partyMember2, AbstractCharacter partyMember3, boolean partyMember1Status, boolean partyMember2Status, boolean partyMember3Status, int activePartyMemberId) {
        this.partyMember1 = partyMember1;
        this.partyMember2 = partyMember2;
        this.partyMember3 = partyMember3;
        this.partyMember1Status = partyMember1Status;
        this.partyMember2Status = partyMember2Status;
        this.partyMember3Status = partyMember3Status;
        this.activePartyMemberId = activePartyMemberId;
    }

    private void setPartyMember1(AbstractCharacter partyMember1) {
        this.partyMember1 = partyMember1;
    }

    private AbstractCharacter getPartyMember2() {
        return partyMember2;
    }

    private void setPartyMember2(AbstractCharacter partyMember2) {
        this.partyMember2 = partyMember2;
    }

    private AbstractCharacter getPartyMember3() {
        return partyMember3;
    }

    private void setPartyMember3(AbstractCharacter partyMember3) {
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

    public int getActivePartyMemberId() {
        return activePartyMemberId;
    }

    public void setActivePartyMemberId(int activePartyMemberId) {
        this.activePartyMemberId = activePartyMemberId;
    }

    public AbstractCharacter getActivePartyMember(){
        switch (activePartyMemberId){
            case 1:
                return partyMember1;
            case 2:
                return partyMember2;
            case 3:
                return partyMember3;
            default:
                return partyMember1;
        }
    }
    public AbstractCharacter getInactivePartyMember(int id){
        switch (id){
            case 1:
                if(activePartyMemberId==1){
                    return partyMember2;
                }
                if(activePartyMemberId==2){
                    return partyMember1;
                }
                if(activePartyMemberId==3){
                    return partyMember1;
                }
            case 2:
                if(activePartyMemberId==1){
                    return partyMember3;
                }
                if(activePartyMemberId==2){
                    return partyMember3;
                }
                if(activePartyMemberId==3){
                    return partyMember2;
                }
            default:
                return partyMember2;
        }
    }

    public AbstractCharacter getPartyMember(int memberId){
        switch (memberId){
            case 1:
                return partyMember1;
            case 2:
                return partyMember2;
            case 3:
                return partyMember3;
            default:
                return partyMember1;
        }
    }
    public void setPartyMember(int memberId, AbstractCharacter member){
        switch (memberId){
            case 1:
                setPartyMember1(member);
                break;
            case 2:
                setPartyMember2(member);
                break;
            case 3:
                setPartyMember3(member);
                break;
            default:
        }
    }
}
