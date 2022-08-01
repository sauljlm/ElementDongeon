package com.avengers.rpgame.graphics.npc;

import com.avengers.rpgame.RPGame;
import com.avengers.rpgame.ai.MonsterMovement;
import com.avengers.rpgame.data.gameStatus.GameStatus;
import com.avengers.rpgame.game.GameConfig;
import com.avengers.rpgame.logic.entities.EntitiesBuilderDirector;
import com.avengers.rpgame.logic.entities.character.abstractCharacter.AbstractCharacter;
import com.avengers.rpgame.logic.entities.character.builder.CharacterBuilder;
import com.avengers.rpgame.logic.entities.character.components.animatedCharacter.DynamicAnimatedCharacter;
import com.avengers.rpgame.utils.StartingPositionsReducer;
import java.util.ArrayList;
import java.util.Arrays;

public class EnemiesManager {
    private GameStatus gameStatus;
    private GameConfig gameConfig;
    private RPGame rpGame;
    private ArrayList<String> enemiesList = new ArrayList<>();
    private ArrayList<AbstractCharacter> enemies = new ArrayList<>();
    private CharacterBuilder characterBuilder;
    private EntitiesBuilderDirector entitiesBuilderDirector;
    private StartingPositionsReducer startingPositionsReducer;

    private MonsterMovement monsterMovement;

    public EnemiesManager() {
        gameStatus = GameStatus.getInstance();
        gameConfig = GameConfig.getInstance();
        rpGame = RPGame.getInstance();
        monsterMovement = new MonsterMovement();
        characterBuilder = new CharacterBuilder();
        entitiesBuilderDirector = new EntitiesBuilderDirector();
        startingPositionsReducer = new StartingPositionsReducer();

        String[] earthEnemiesIds = {"EarthMonster1", "EarthMonster2", "EarthMonster3", "EarthMonster4","EarthMonster5", "EarthMonster6", "EarthMonster7", "EarthMonster8", "EarthMonster9"};
        enemiesList.addAll(Arrays.asList(earthEnemiesIds));
        String[] waterEnemiesIds = {"WaterMonster1", "WaterMonster2", "WaterMonster3", "WaterMonster4","WaterMonster5", "WaterMonster6", "WaterMonster7", "WaterMonster8", "WaterMonster9"};
        enemiesList.addAll(Arrays.asList(waterEnemiesIds));
        String[] windEnemiesIds = {"WindMonster1", "WindMonster2", "WindMonster3", "WindMonster4","WindMonster5", "WindMonster6", "WindMonster7", "WindMonster8", "WindMonster9"};
        enemiesList.addAll(Arrays.asList(windEnemiesIds));
        String[] fireEnemiesIds = {"FireMonster1", "FireMonster2", "FireMonster3", "FireMonster4","FireMonster5", "FireMonster6", "FireMonster7", "FireMonster8", "FireMonster9"};
        enemiesList.addAll(Arrays.asList(fireEnemiesIds));
        String[] bossesEnemiesIds = {"EarthBossMonster", "WaterBossMonster", "WindBossMonster", "FireBossMonster"};
        enemiesList.addAll(Arrays.asList(bossesEnemiesIds));
    }

    public void createEnemies(){
        if (gameStatus.getStatus().equals("newGame")){
            createNewEnemies();
            gameStatus.setEnemies(enemies);
        }
        if (gameStatus.getStatus().equals("loadedGame")){
            createNewEnemies();
            gameStatus.setEnemies(enemies);
            restoreHealth();
        }
        if (gameStatus.getStatus().equals("gameInProgress")){
            enemies = gameStatus.getEnemies();
            restoreEnemies();
            System.out.println("done");
        }
    }

    public void draw(float delta){
        for (AbstractCharacter enemy:enemies) {
            enemy.getAnimatedCharacter().draw(delta);
            monsterMovement.chaseActivePlayer(enemy);
        }
    }

    private void restoreEnemies() {
        for (AbstractCharacter enemy:enemies) {
            ((DynamicAnimatedCharacter)enemy.getAnimatedCharacter()).recreateBody();
        }
    }

    private void createNewEnemies(){
        for (String id: enemiesList) {
            if(id.contains("EarthMonster")){
                entitiesBuilderDirector.buildEarthSkeleton(characterBuilder, GameStatus.getInstance().getWorld(), rpGame, id);
            }
            if(id.contains("WaterMonster")){
                entitiesBuilderDirector.buildWaterSkeleton(characterBuilder, GameStatus.getInstance().getWorld(), rpGame, id);
            }
            if(id.contains("WindMonster")){
                entitiesBuilderDirector.buildWindSkeleton(characterBuilder, GameStatus.getInstance().getWorld(), rpGame, id);
            }
            if(id.contains("FireMonster")){
                entitiesBuilderDirector.buildFireSkeleton(characterBuilder, GameStatus.getInstance().getWorld(), rpGame, id);
            }
            if(id.contains("EarthBossMonster")) {
                entitiesBuilderDirector.buildChiefEarth(characterBuilder, GameStatus.getInstance().getWorld(), rpGame, id);
            }
            if(id.contains("WaterBossMonster")) {
                entitiesBuilderDirector.buildChiefWater(characterBuilder, GameStatus.getInstance().getWorld(), rpGame, id);
            }
            if(id.contains("WindBossMonster")) {
                entitiesBuilderDirector.buildChiefWind(characterBuilder, GameStatus.getInstance().getWorld(), rpGame, id);
            }
            if(id.contains("FireBossMonster")) {
                entitiesBuilderDirector.buildChiefFire(characterBuilder, GameStatus.getInstance().getWorld(), rpGame, id);
            }
            AbstractCharacter enemy = characterBuilder.getResult();
            enemies.add(enemy);
        }
    }

    private void restoreHealth(){
        for(AbstractCharacter enemy: enemies){
            enemy.setHealthPoints(gameStatus.getEnemiesHealth().get(enemy.getName()));

        }
    }
}