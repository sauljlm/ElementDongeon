package com.avengers.rpgame.ai;

import com.avengers.rpgame.RPGame;
import com.avengers.rpgame.data.gameStatus.GameStatus;
import com.avengers.rpgame.game.GameConfig;
import com.avengers.rpgame.graphics.dialog.DialogManager;
import com.avengers.rpgame.graphics.dialog.DialogsFactory;
import com.avengers.rpgame.graphics.dialog.RandomDialogs.ARandomDialogHelper;
import com.avengers.rpgame.graphics.hud.DamageOverlay;
import com.avengers.rpgame.graphics.screens.ScreeenManager;
import com.avengers.rpgame.logic.entities.character.abstractCharacter.AbstractCharacter;
import com.avengers.rpgame.logic.entities.character.behaviour.endFightActions.IVisitor;
import com.avengers.rpgame.logic.entities.character.behaviour.endFightActions.RingDamageVisitor;
import com.avengers.rpgame.logic.entities.character.components.animatedCharacter.DynamicAnimatedCharacter;
import com.avengers.rpgame.utils.Utils;
import com.badlogic.gdx.math.Vector2;

/*******************************************************************************
 *This class contains AI logic for all related to detecting and consequences
 * when the party members are not close
 *******************************************************************************/
public class OneForAllAI {
    private GameConfig gameConfig;
    private GameStatus gameStatus;
    private RPGame rpGame;

    private DialogManager dialogManager;
    private DialogsFactory dialogsFactory;
    private ARandomDialogHelper stickTogetherMessages;

    private DamageOverlay damageOverlay;

    private IVisitor damageGenerator;

    public OneForAllAI() {
        gameConfig = GameConfig.getInstance();
        gameStatus = GameStatus.getInstance();
        rpGame = RPGame.getInstance();
        dialogManager = DialogManager.getInstance();
        dialogsFactory = new DialogsFactory();
        damageOverlay = new DamageOverlay();
        stickTogetherMessages = dialogsFactory.getRandomDialogHelper("stickTogether");
        damageGenerator = new RingDamageVisitor();

    }

    public void areFriendsClose(){
        Vector2 distance = calculateDistance(gameStatus.getPlayerParty().getActivePartyMember(), gameStatus.getPlayerParty().getInactivePartyMember(1));
        Vector2 distance2 = calculateDistance(gameStatus.getPlayerParty().getActivePartyMember(), gameStatus.getPlayerParty().getInactivePartyMember(2));
        int maxDistance = 20;

        if(Math.abs(distance.x)>maxDistance || Math.abs(distance.y) >maxDistance || Math.abs(distance2.x) >maxDistance || Math.abs(distance2.y) > maxDistance) {
            dialogManager.updateSpeaker("Héroe");
            dialogManager.updateDialog(stickTogetherMessages.getRandomMessage());
            if(Utils.getInstance().skipFrames()){
                gameStatus.getPlayerParty().getActivePartyMember().accept(damageGenerator);
            }
            if(gameStatus.getPlayerParty().getActivePartyMember().getHealthPoints() <= 0){
                GameStatus.getInstance().setStatus("gameInProgress");
                ScreeenManager.getInstance().changeScreen("GameOverScreen");
            }
            gameStatus.setDamageStatus("takingDamage");
            return;
        }
        gameStatus.setDamageStatus("ok");
    }

    private void drawDamage(){
        rpGame.batch.begin();
        damageOverlay.draw();
        rpGame.batch.end();
    }

    private Vector2 calculateDistance(AbstractCharacter character1, AbstractCharacter character2){
        Vector2 mainCharacterPos = ((DynamicAnimatedCharacter)character1.getAnimatedCharacter()).getPlayer().getPosition();
        Vector2 followerCharacterPos = ((DynamicAnimatedCharacter)character2.getAnimatedCharacter()).getPlayer().getPosition();
        return mainCharacterPos.sub(followerCharacterPos);
    }
}
