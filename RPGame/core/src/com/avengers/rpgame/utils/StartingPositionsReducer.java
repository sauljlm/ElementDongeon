
package com.avengers.rpgame.utils;

import com.avengers.rpgame.data.gameStatus.GameStatus;
import com.avengers.rpgame.game.GameConfig;
import com.avengers.rpgame.logic.entities.character.abstractCharacter.AbstractCharacter;
import com.badlogic.gdx.math.Vector2;

import java.util.HashMap;
import java.util.Random;

public class StartingPositionsReducer {
    private GameConfig gameConfig;
    private GameStatus gameStatus;
    private String characterId;
    private HashMap<String, Vector2> positions;
    private Random random;

    public StartingPositionsReducer() {
        gameConfig = GameConfig.getInstance();
        gameStatus = GameStatus.getInstance();
        random = new Random();

        characterId = "start";
        positions = new HashMap<String, Vector2>();
        setPositions();
    }
    private void add(String position, float x, float y){
        positions.put(position, new Vector2(x*gameConfig.getPPM(),y*gameConfig.getPPM()));
    }

    private void setPositions(){
        add("startPlayer", 338,44);
        add("defeatedEarth", 338,44);
        add("defeatedWind", 338,44);
        add("defeatedWater", 338,44);
        add("defeatedFire", 338,44);
        add("EarthMonster1", 240.77f,81.52f);
        add("EarthMonster2", 219.84f,87.30f);
        add("EarthMonster3", 221.84f,65.08f);
        add("EarthMonster4", 193.48f,82.64f);
        add("EarthMonster5", 158.04f,82.45f);
        add("EarthMonster6", 161.91f,61.48f);
        add("EarthMonster7", 187.50f,43.68f);
        add("EarthMonster8", 207.20f,55.37f);
        add("EarthMonster9", 194.29f,33.30f);
        add("EarthBossMonster", 234.95f,38.76f);

        add("WaterMonster1", 115,134);
        add("WaterMonster2", 120.6f,156);
        add("WaterMonster3", 51.9f,147.9f);
        add("WaterMonster4", 89.9f,157.3f);
        add("WaterMonster5", 70.7f,141f);
        add("WaterMonster6", 71.97f,110f);
        add("WaterMonster7", 73.6f,126.8f);
        add("WaterMonster8", 71.47f,151.8f);
        add("WaterMonster9", 99f,135f);
        add("WaterBossMonster", 60.61f,110.8f);

        add("WindMonster1", 282.7f,112.40f);
        add("WindMonster2", 291.17f,127.71f);
        add("WindMonster3", 289f,167f);
        add("WindMonster4", 302.48f,161f);
        add("WindMonster5", 303.04f,134f);
        add("WindMonster6", 321.91f,109f);
        add("WindMonster7", 332.02f,130.52f);
        add("WindMonster8", 341.4f,158.5f);
        add("WindMonster9", 349.86f,138f);
        add("WindBossMonster", 367.57f,156.1f);

        add("FireMonster1", 214,195);
        add("FireMonster2", 181,200);
        add("FireMonster3", 170,228);
        add("FireMonster4", 200,222);
        add("FireMonster5", 231,221);
        add("FireMonster6", 253,228);
        add("FireMonster7", 213,225);
        add("FireMonster8", 194,206);
        add("FireMonster9", 213,190);
        add("FireBossMonster", 236.89f,198.61f);
    }

    private Vector2 determinePosition(){
        Vector2 worldPosition = new Vector2();
        if (GameStatus.getInstance().getStatus().equals("newGame")){
            worldPosition = getPosition("startPlayer");
        }
        if (GameStatus.getInstance().getStatus().equals("loadedGame")){
            worldPosition.x =GameStatus.getInstance().getPlayerParty().getPartyMember(1).getPosition().x*gameConfig.getPPM();
            worldPosition.y = GameStatus.getInstance().getPlayerParty().getPartyMember(1).getPosition().y*gameConfig.getPPM();
        }
        if (GameStatus.getInstance().getStatus().equals("defeated")){
            worldPosition = getPosition("startPlayer");
        }
        if (GameStatus.getInstance().getStatus().equals("defeated")){
            worldPosition = getPosition("startPlayer");
        }
        if (GameStatus.getInstance().getStatus().equals("gameInProgress")){
            worldPosition.x =GameStatus.getInstance().getPlayerParty().getPartyMember(1).getPosition().x*gameConfig.getPPM();
            worldPosition.y = GameStatus.getInstance().getPlayerParty().getPartyMember(1).getPosition().y*gameConfig.getPPM();
        }
        return worldPosition;
    }

    public Vector2 getPosition(String id){
        if(id.equals("player")){
            return determinePosition();
        }
        if(id.contains("randomMonster")){
            return getRandomMonsterCoordinates();
//            return new Vector2(280*gameConfig.getPPM(),64*gameConfig.getPPM());
        }
        if(positions.get(id) == null){
            return new Vector2(0,0);
        }
        return positions.get(id);
    }

    public int getRandom(int min, int max) {
        return random.nextInt(max - min) + min;
    }

    //Returns a random location for monsters, forest have 4x time probability to respawn
    private Vector2 getRandomMonsterCoordinates(){
        int area = random.nextInt(11);
        int x = 0, y=0;

        //Forest left corner
        if(area<4) {
            x = getRandom(47,140);
            y = getRandom(37,90);
        }
        //forest right corner
        if(area<8 && area>=4) {
            x = getRandom(257,361);
            y = getRandom(176,234);
        }
        //Front Castle
        if(area == 8) {
            x = getRandom(147,256);
            y = getRandom(97,106);
        }
        //Behind Castle
        if(area==9) {
            x = getRandom(155,260);
            y = getRandom(164,174);
        }
        return new Vector2(x*gameConfig.getPPM(),y*gameConfig.getPPM());
    }
}
