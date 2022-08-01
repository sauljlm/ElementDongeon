
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
        add("startPlayer", 298,14);
        add("EarthMonster1", 200.77f,51.52f);
        add("EarthMonster2", 179.84f,57.30f);
        add("EarthMonster3", 181.84f,35.08f);
        add("EarthMonster4", 153.48f,52.64f);
        add("EarthMonster5", 118.04f,52.45f);
        add("EarthMonster6", 121.91f,31.48f);
        add("EarthMonster7", 127.50f,13.68f);
        add("EarthMonster8", 167.20f,25.37f);
        add("EarthMonster9", 154.29f,3.30f);
        add("EarthBossMonster", 194.95f,8.76f);

        add("WaterMonster1", 75,104);
        add("WaterMonster2", 80.6f,126);
        add("WaterMonster3", 57.9f,86.9f);
        add("WaterMonster4", 49.9f,127.3f);
        add("WaterMonster5", 29.7f,111f);
        add("WaterMonster6", 11.97f,117f);
        add("WaterMonster7", 32.6f,96.8f);
        add("WaterMonster8", 25.47f,131.8f);
        add("WaterMonster9", 94.45f,97.7f);
        add("WaterBossMonster", 21.61f,80.8f);

        add("WindMonster1", 242.7f,82.40f);
        add("WindMonster2", 251.17f,97.71f);
        add("WindMonster3", 249f,137f);
        add("WindMonster4", 262.48f,131f);
        add("WindMonster5", 263.04f,104f);
        add("WindMonster6", 281.91f,79f);
        add("WindMonster7", 292.02f,100.52f);
        add("WindMonster8", 301.4f,128.5f);
        add("WindMonster9", 309.86f,108f);
        add("WindBossMonster", 327.57f,126.1f);

        add("FireMonster1", 174,161);
        add("FireMonster2", 141,170);
        add("FireMonster3", 130,198);
        add("FireMonster4", 160,192);
        add("FireMonster5", 191,191);
        add("FireMonster6", 213,198);
        add("FireMonster7", 173,195);
        add("FireMonster8", 154,176);
        add("FireMonster9", 181.93f,150);
        add("FireBossMonster", 196.89f,168.61f);
    }

    private Vector2 determinePosition(){
        Vector2 worldPosition = new Vector2();
        if (GameStatus.getInstance().getStatus().equals("newGame")){
            worldPosition.x = 298*gameConfig.getPPM();
            worldPosition.y = 14*gameConfig.getPPM();
        }
        if (GameStatus.getInstance().getStatus().equals("loadedGame")){
            worldPosition.x =GameStatus.getInstance().getParty().getPartyMember(1).getPosition().x*gameConfig.getPPM();
            worldPosition.y = GameStatus.getInstance().getParty().getPartyMember(1).getPosition().y*gameConfig.getPPM();
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
