package com.avengers.rpgame.graphics.dialog;

import com.avengers.rpgame.graphics.dialog.RandomDialogs.ARandomDialogHelper;
import com.avengers.rpgame.graphics.dialog.RandomDialogs.MonsterDefeatedDialog;
import com.avengers.rpgame.graphics.dialog.RandomDialogs.MonsterFightDialog;
import java.util.HashMap;

public class DialogsFactory {
    private HashMap<String, ARandomDialogHelper> randomDialogHelperHashMap;

    public DialogsFactory() {
        randomDialogHelperHashMap = new HashMap<>();
        createDialogs();
    }

    public ARandomDialogHelper getRandomDialogHelper(String key){
        return randomDialogHelperHashMap.get(key);
    }

    private void createDialogs() {
        randomDialogHelperHashMap.put("monsterFight", new MonsterFightDialog());
        randomDialogHelperHashMap.put("monsterDefeated", new MonsterDefeatedDialog());
    }
}
