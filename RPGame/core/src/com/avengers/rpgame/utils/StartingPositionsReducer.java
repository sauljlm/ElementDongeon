
package com.avengers.rpgame.utils;

import com.avengers.rpgame.data.gameStatus.GameStatus;
import com.avengers.rpgame.game.GameConfig;
import com.badlogic.gdx.math.Vector2;

import java.util.HashMap;

public class StartingPositionsReducer {
    private GameConfig gameConfig;
    private GameStatus gameStatus;
    private String characterId;
    private HashMap<String, Vector2> positions;

    public StartingPositionsReducer() {
        gameConfig = GameConfig.getInstance();
        gameStatus = GameStatus.getInstance();
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
        add("WaterMonster9", 134.45f,127.7f);
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

        add("FireMonster1", 214,191);
        add("FireMonster2", 181,200);
        add("FireMonster3", 170,228);
        add("FireMonster4", 200,222);
        add("FireMonster5", 231,221);
        add("FireMonster6", 253,228);
        add("FireMonster7", 213,225);
        add("FireMonster8", 194,206);
        add("FireMonster9", 221.93f,180);
        add("FireBossMonster", 236.89f,198.61f);
    }

    private Vector2 determinePosition(){
        Vector2 worldPosition = new Vector2();
        if (GameStatus.getInstance().getStatus().equals("newGame")){
            worldPosition = getPosition("startPlayer");
        }
        if (GameStatus.getInstance().getStatus().equals("loadedGame")){
            worldPosition.x =GameStatus.getInstance().getParty().getPartyMember(1).getPosition().x*gameConfig.getPPM();
            worldPosition.y = GameStatus.getInstance().getParty().getPartyMember(1).getPosition().y*gameConfig.getPPM();
        }
        if (GameStatus.getInstance().getStatus().equals("defeated")){
            worldPosition = getPosition("startPlayer");
        }
        if (GameStatus.getInstance().getStatus().equals("gameInProgress")){
            worldPosition.x =GameStatus.getInstance().getParty().getPartyMember(1).getPosition().x*gameConfig.getPPM();
            worldPosition.y = GameStatus.getInstance().getParty().getPartyMember(1).getPosition().y*gameConfig.getPPM();
        }
        return worldPosition;
    }

    public Vector2 getPosition(String id){
        if(id.equals("player")){
            return determinePosition();
        }
        if(positions.get(id) == null){
            return new Vector2(0,0);
        }
        return positions.get(id);
    }
}
