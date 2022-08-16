package com.avengers.rpgame.ai;

import com.avengers.rpgame.RPGame;
import com.avengers.rpgame.data.gameStatus.GameStatus;
import com.avengers.rpgame.game.GameConfig;
import com.avengers.rpgame.logic.entities.Party;
import com.avengers.rpgame.logic.entities.character.abstractCharacter.AbstractCharacter;

public class RefereeBattleAI {
    private int turn;
    private boolean winner;
    private GameConfig config = GameConfig.getInstance();
    private int partyTurn;
    private boolean partyTurnCompleted;
    private RPGame game;

    public RefereeBattleAI(RPGame game) {
        this.turn = 0;
        this.winner = false;
        this.game = game;
    }

    public void startBattle(Party party1, Party party2){
    };

    public void finishBattle(Party winnerParty){

    }

    public String manageBattle(Party playerParty , Party npcParty){
        if(turn==0){
            startBattle(playerParty, npcParty);
        }


        if(!checkPartyStatus(playerParty)){
            finishBattle(playerParty);
            GameStatus.getInstance().setStatus("gameInProgress");
            return "EnemyWins";

        };
        if(!checkPartyStatus(npcParty)){
            finishBattle(npcParty);
            GameStatus.getInstance().setStatus("gameInProgress");
            return "PlayerWins";
        }

        this.turn = turn++;
        return "catsAreGreat";
    }

    public boolean checkPartyStatus(Party party){
        boolean partyMember1 = true;
        boolean partyMember2 = true;
        boolean partyMember3 = true;

        if(!checkAlive(party.getActivePartyMember())){ //If not alive set it as inactive/death
            party.setPartyMember1Status(false);
            partyMember1 =false;
        }

        //Code commented because only active party member fights
//        if(!checkAlive(party.getPartyMember(2))){ //If not alive set it as inactive/death
//            party.setPartyMember2Status(false);
//            partyMember2 =false;
//        }
//        if(!checkAlive(party.getPartyMember(3))){ //If not alive set it as inactive/death
//            party.setPartyMember3Status(false);
//            partyMember3 =false;
//        }

        return partyMember1; //returns false for death party, true if any member is still alive
//        return partyMember1 || partyMember2 || partyMember3; //returns false for death party, true if any member is still alive
    }

    public boolean checkAlive(AbstractCharacter character){
        return character.getHealthPoints() > 0; //Returns true for alive character
    }

}
