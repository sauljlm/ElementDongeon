package com.avengers.rpgame.graphics.dialog.RandomDialogs;

import java.util.ArrayList;
import java.util.Random;

public abstract class ARandomDialogHelper {
    private ArrayList<String> messages;
    private String currentMessage;

    private int lastUpdate1;

    private int random;

    private Random randomGen;

    public ARandomDialogHelper() {
        messages = new ArrayList<>();
        randomGen = new Random();
        currentMessage="";
        lastUpdate1 = 0;
    }

    public void add(String message){
        messages.add(message);
    }

    public void clearMessages(){
        messages.clear();
    }

    public String getRandomMessage(){
        int minute = (int) ((System.currentTimeMillis() / (1000*10)) % 10);
        if(minute != lastUpdate1){
            random = randomGen.nextInt(messages.size());
            lastUpdate1 = minute;
        }
        return messages.get(random);
    }
}
