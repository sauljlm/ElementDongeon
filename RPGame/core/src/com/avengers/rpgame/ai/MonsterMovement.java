package com.avengers.rpgame.ai;

import com.avengers.rpgame.RPGame;
import com.avengers.rpgame.data.gameStatus.GameStatus;
import com.avengers.rpgame.game.GameConfig;
import com.avengers.rpgame.graphics.dialog.DialogManager;
import com.avengers.rpgame.graphics.dialog.DialogsFactory;
import com.avengers.rpgame.graphics.dialog.RandomDialogs.ARandomDialogHelper;
import com.avengers.rpgame.graphics.screens.FightScreen;
import com.avengers.rpgame.graphics.screens.ScreeenManager;
import com.avengers.rpgame.logic.entities.Party;
import com.avengers.rpgame.logic.entities.character.abstractCharacter.AbstractCharacter;
import com.avengers.rpgame.logic.entities.character.components.animatedCharacter.DynamicAnimatedCharacter;
import com.badlogic.gdx.math.Vector2;

public class MonsterMovement {
    private GameConfig gameConfig;
    private GameStatus gameStatus;
    private DialogManager dialogManager;
    private RPGame rpGame;

    private DialogsFactory dialogsFactory;
    private ARandomDialogHelper fightMessages;
    private ARandomDialogHelper defeatedMessages;

    public MonsterMovement() {
        gameConfig = GameConfig.getInstance();
        gameStatus = GameStatus.getInstance();
        dialogManager = DialogManager.getInstance();
        rpGame = RPGame.getInstance();
        dialogsFactory = new DialogsFactory();
        fightMessages = dialogsFactory.getRandomDialogHelper("monsterFight");
        defeatedMessages = dialogsFactory.getRandomDialogHelper("monsterDefeated");
    }

    public void chaseActivePlayer(AbstractCharacter enemy){
        AbstractCharacter playerCharacter = gameStatus.getPlayerParty().getActivePartyMember();
        float partyDistance = 10;
        float horizontalForce = 0;
        float verticalForce = 0;
        float movementSpeed = 1.5f;
        if(gameConfig.isGodMode()){
            movementSpeed = 2;
        }
        Vector2 velocity = new Vector2();
        Vector2 distance = calculateDistance(playerCharacter, enemy);

        if(Math.abs(distance.x) < 1f && Math.abs(distance.y) <1f && enemy.getHealthPoints()>0){
            enterBattleMode(enemy);
        }

        if(Math.abs(distance.x) < partyDistance && Math.abs(distance.y) < partyDistance && enemy.getHealthPoints() >0){
            horizontalForce = movementSpeed;
            verticalForce = movementSpeed;
            dialogManager.updateSpeaker("Esqueleto");
            dialogManager.updateDialog(fightMessages.getRandomMessage());

        }

        if(Math.abs(distance.x) < 2 && Math.abs(distance.y) < 2 && enemy.getHealthPoints() <=0){
            dialogManager.setActive(true);
            dialogManager.updateSpeaker("Esqueleto");
            dialogManager.updateDialog(defeatedMessages.getRandomMessage());
        } else {
            dialogManager.setActive(false);
        }

        velocity.x = horizontalForce * distance.x/2;
        velocity.y = verticalForce * distance.y/2;

        setActionToCharacter(enemy, velocity);

        ((DynamicAnimatedCharacter)enemy.getAnimatedCharacter()).getPlayer().setLinearVelocity(velocity.x,velocity.y);
    }

    private void setActionToCharacter(AbstractCharacter character, Vector2 velocity){
        if(Math.abs(velocity.x) > Math.abs(velocity.y)){
            if(velocity.x<0) {
                character.getAnimatedCharacter().setAction("runningLeft");
            }
            if(velocity.x>0) {
                character.getAnimatedCharacter().setAction("runningRight");
            }
        }
        if(Math.abs(velocity.x) < Math.abs(velocity.y)) {
            if(velocity.y>0) {
                character.getAnimatedCharacter().setAction("runningUp");
            }
            if(velocity.y<0) {
                character.getAnimatedCharacter().setAction("runningDown");
            }
        }
    }

    private Vector2 calculateDistance(AbstractCharacter character1, AbstractCharacter character2){
        Vector2 mainCharacterPos = ((DynamicAnimatedCharacter)character1.getAnimatedCharacter()).getPlayer().getPosition();
        Vector2 followerCharacterPos = ((DynamicAnimatedCharacter)character2.getAnimatedCharacter()).getPlayer().getPosition();
        return mainCharacterPos.sub(followerCharacterPos);
    }

    private void enterBattleMode(AbstractCharacter enemy){
        gameStatus.saveOnDB();
        Party enemyParty = gameStatus.getEnemyParty();
        enemyParty.setPartyMember(1,enemy);
        enemyParty.setPartyMember(2,enemy);
        enemyParty.setPartyMember(3,enemy);
        ScreeenManager.getInstance().changeScreen("FightScreen");
    }
}
