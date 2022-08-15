package com.avengers.rpgame.data.gameStatus;

import com.avengers.rpgame.data.dataBase.DBConnector;
import com.avengers.rpgame.logic.entities.Attack;
import com.avengers.rpgame.logic.entities.Item;
import com.avengers.rpgame.logic.entities.Party;
import com.avengers.rpgame.logic.entities.Skill;
import com.avengers.rpgame.logic.entities.character.abstractCharacter.AbstractCharacter;
import com.avengers.rpgame.logic.entities.character.components.CharacterClass;
import com.avengers.rpgame.logic.entities.character.concrete.PlayableCharacter;
import com.badlogic.gdx.math.Vector2;

import java.sql.ResultSet;
import java.util.ArrayList;

public class BDGameStatusDAO implements IGameStatusDAO{

    public BDGameStatusDAO() {
    }

    //TODO for future me, refactor this into stored procedures
    @Override
    public void saveGameStatus() {
        GameStatus gameStatus = GameStatus.getInstance();
        int partyMember = 3;
        String timeStamp = String.valueOf(System.currentTimeMillis());
        ArrayList<String> querys = new ArrayList<String>();
        String sqlQuery;
        //Delete previous slot saves
        sqlQuery = "DELETE FROM AbstractCharacter where saveSlot is "+gameStatus.getSaveSlot()+" and timeStamp is NOT "+timeStamp;
        querys.add(sqlQuery);
        sqlQuery = "DELETE FROM Attack where saveSlot is "+gameStatus.getSaveSlot()+" and timeStamp is NOT "+timeStamp;
        querys.add(sqlQuery);
        sqlQuery = "DELETE FROM Item where saveSlot is "+gameStatus.getSaveSlot()+" and timeStamp is NOT "+timeStamp;
        querys.add(sqlQuery);
        sqlQuery = "DELETE FROM Skill where saveSlot is "+gameStatus.getSaveSlot()+" and timeStamp is NOT "+timeStamp;
        querys.add(sqlQuery);
        sqlQuery = "DELETE FROM Enemy where saveSlot is "+gameStatus.getSaveSlot()+" and timeStamp is NOT "+timeStamp;
        querys.add(sqlQuery);
        while (partyMember>0){
            sqlQuery = "INSERT INTO AbstractCharacter "+
                    "(saveSlot,partyMember,idCharacter,name,description,positionX,positionY,level,healthPoints,magicPoints,healthPointsMax,magicPointsMax,strength,speed,magic,resistance,luck,coins,characterClass,experiencePoints,timeStamp)" +
                    "VALUES("+
                    gameStatus.getSaveSlot()+","+
                    partyMember+",'"+
                    gameStatus.getPlayerParty().getPartyMember(partyMember).getIdCharacter()+"','"+
                    gameStatus.getPlayerParty().getPartyMember(partyMember).getName()+"','"+
                    gameStatus.getPlayerParty().getPartyMember(partyMember).getDescription()+"',"+
                    gameStatus.getPlayerParty().getPartyMember(partyMember).getPosition().x+","+
                    gameStatus.getPlayerParty().getPartyMember(partyMember).getPosition().y+","+
                    gameStatus.getPlayerParty().getPartyMember(partyMember).getLevel()+","+
                    gameStatus.getPlayerParty().getPartyMember(partyMember).getHealthPoints()+","+
                    gameStatus.getPlayerParty().getPartyMember(partyMember).getMagicPoints()+","+
                    gameStatus.getPlayerParty().getPartyMember(partyMember).getHealthPointsMax()+","+
                    gameStatus.getPlayerParty().getPartyMember(partyMember).getMagicPointsMax()+","+
                    gameStatus.getPlayerParty().getPartyMember(partyMember).getStrength()+","+
                    gameStatus.getPlayerParty().getPartyMember(partyMember).getSpeed()+","+
                    gameStatus.getPlayerParty().getPartyMember(partyMember).getMagic()+","+
                    gameStatus.getPlayerParty().getPartyMember(partyMember).getResistance()+","+
                    gameStatus.getPlayerParty().getPartyMember(partyMember).getLuck()+","+
                    gameStatus.getPlayerParty().getPartyMember(partyMember).getCoins()+","+
                    gameStatus.getPlayerParty().getPartyMember(partyMember).getCharacterClass().getIdCharacterClass()+","+
                    ((PlayableCharacter)gameStatus.getPlayerParty().getPartyMember(partyMember)).getExperiencePoints()+","+
                    timeStamp+
                    ")";
            querys.add(sqlQuery);

            for (Attack attack : gameStatus.getPlayerParty().getPartyMember(partyMember).getAttacks()){
                sqlQuery = "INSERT INTO Attack "+
                        "(saveSlot,partyMember,id,name,description,price,unlockLevel,imagePath,itemType,mPCost,HPEffect,timeStamp)" +
                        "VALUES("+
                        gameStatus.getSaveSlot()+","+
                        partyMember+","+
                        attack.getId()+",'"+
                        attack.getName()+"','"+
                        attack.getDescription()+"',"+
                        attack.getPrice()+","+
                        attack.getUnlockLevel()+",'"+
                        attack.getImagePath()+"',"+
                        attack.getItemType()+","+
                        attack.getmPCost()+","+
                        attack.getHPEffect()+","+
                        timeStamp+
                        ")";
                        querys.add(sqlQuery);
            }
            for (Item item : gameStatus.getPlayerParty().getPartyMember(partyMember).getItems()){
                sqlQuery = "INSERT INTO Item "+
                        "(saveSlot,partyMember,id,name,description,price,unlockLevel,imagePath,itemType,strengthEffect,speedEffect,magicEffect,resistanceEffect,luckEffect,mPEffect,hPEffect,timeStamp)" +
                        "VALUES("+
                        gameStatus.getSaveSlot()+","+
                        partyMember+","+
                        item.getId()+",'"+
                        item.getName()+"','"+
                        item.getDescription()+"',"+
                        item.getPrice()+","+
                        item.getUnlockLevel()+",'"+
                        item.getImagePath()+"',"+
                        item.getItemType()+","+
                        item.getStrengthEffect()+","+
                        item.getSpeedEffect()+","+
                        item.getMagicEffect()+","+
                        item.getResistanceEffect()+","+
                        item.getLuckEffect()+","+
                        item.getmPEffect()+","+
                        item.gethPEffect()+","+
                        timeStamp+
                        ")";
                querys.add(sqlQuery);
            }
            for (Skill skill : gameStatus.getPlayerParty().getPartyMember(partyMember).getSkills()){
                sqlQuery = "INSERT INTO Skill "+
                        "(saveSlot,partyMember,id,name,description,price,unlockLevel,imagePath,itemType,mPCost,strengthEffect,speedEffect,magicEffect,resistanceEffect,luckEffect,mPEffect,hPEffect,type,timeStamp)" +
                        "VALUES("+
                        gameStatus.getSaveSlot()+","+
                        partyMember+","+
                        skill.getId()+",'"+
                        skill.getName()+"','"+
                        skill.getDescription()+"',"+
                        skill.getPrice()+","+
                        skill.getUnlockLevel()+",'"+
                        skill.getImagePath()+"',"+
                        skill.getItemType()+","+
                        skill.getmPCost()+","+
                        skill.getStrengthEffect()+","+
                        skill.getSpeedEffect()+","+
                        skill.getMagicEffect()+","+
                        skill.getResistanceEffect()+","+
                        skill.getLuckEffect()+","+
                        skill.getmPEffect()+","+
                        skill.gethPEffect()+",'"+
                        skill.getType()+"',"+
                        timeStamp+
                        ")";
                querys.add(sqlQuery);
            }
            partyMember--;
        }
        //Enemies save, This is a multiLine query, there are 40 enemies, doing 1 query and conection/enemy is too slow
        sqlQuery = "INSERT INTO Enemy "+
                "(saveSlot,name,healthPoints,timeStamp)" +
                "VALUES";
        for (int i = 0;i < gameStatus.getEnemies().size(); i++){
            sqlQuery = sqlQuery+"("+
                    gameStatus.getSaveSlot()+",'"+
                    gameStatus.getEnemies().get(i).getName()+"',"+
                    gameStatus.getEnemies().get(i).getHealthPoints()+","+
                    timeStamp+
                    ")";
            if(i != gameStatus.getEnemies().size()-1){
                sqlQuery = sqlQuery+",";
            }
        }
        querys.add(sqlQuery);
        //Run the querys
        for (String query : querys)
        {
            try {
                DBConnector.getConnection().executeQuerySET(query);
            } catch (Exception e) {
                e.printStackTrace();
            };
        }
        try {
            DBConnector.getConnection().closeConnection();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public GameStatus loadGameStatus(int saveSlot) {
        GameStatus gameStatus = GameStatus.getInstance();
        Party party = gameStatus.getPlayerParty();
        ResultSet resultSet = null;
        AbstractCharacter character = party.getActivePartyMember();

        String sqlQuery1 = "SELECT * from AbstractCharacter where saveSlot is "+saveSlot;
        String sqlQuery2 = "SELECT * from Attack where saveSlot is "+saveSlot;
        String sqlQuery3 = "SELECT * from Item where saveSlot is "+saveSlot;
        String sqlQuery4 = "SELECT * from Skill where saveSlot is "+saveSlot;
        String sqlQuery5 = "SELECT * from Enemy where saveSlot is "+saveSlot;
        try {
            resultSet = DBConnector.getConnection().executeQueryGET(sqlQuery1);
            gameStatus.setSaveSlot(Integer.parseInt(resultSet.getString("saveSlot"))); //Sets the saveSlot

            while(resultSet.next()){ //Sets info of all party, query should always return 3 rows (1/ party member)
                character = party.getPartyMember(Integer.parseInt(resultSet.getString("partyMember")));
                character.setIdCharacter(Integer.parseInt(resultSet.getString("idCharacter")));
                character.setName(resultSet.getString("name"));
                character.setDescription(resultSet.getString("description"));
                Vector2 position = new Vector2(Float.parseFloat(resultSet.getString("positionX")), Float.parseFloat(resultSet.getString("positionY")));
                character.setPosition(position);
                character.setLevel(Integer.parseInt(resultSet.getString("level")));
                character.setHealthPoints(Integer.parseInt(resultSet.getString("healthPoints")));
                character.setMagicPoints(Integer.parseInt(resultSet.getString("magicPoints")));
                character.setHealthPointsMax(Integer.parseInt(resultSet.getString("healthPointsMax")));
                character.setMagicPointsMax(Integer.parseInt(resultSet.getString("magicPointsMax")));
                character.setStrength(Integer.parseInt(resultSet.getString("strength")));
                character.setSpeed(Integer.parseInt(resultSet.getString("speed")));
                character.setMagic(Integer.parseInt(resultSet.getString("magic")));
                character.setResistance(Integer.parseInt(resultSet.getString("resistance")));
                character.setLuck(Integer.parseInt(resultSet.getString("luck")));
                character.setCoins(Integer.parseInt(resultSet.getString("coins")));
                CharacterClass characterClass = new CharacterClass();
                character.setCharacterClass(characterClass);
                character.getCharacterClass().setIdCharacterClass(Integer.parseInt(resultSet.getString("characterClass")));
                ((PlayableCharacter)character).setExperiencePoints(Integer.parseInt(resultSet.getString("experiencePoints")));
                character.getAttacks().clear();
                character.getItems().clear();
                character.getSkills().clear();
            }

            resultSet = DBConnector.getConnection().executeQueryGET(sqlQuery2);
            while(resultSet.next()){ //Sets info of all party, query should always return 3 rows (1/ party member)
                character = party.getPartyMember(Integer.parseInt(resultSet.getString("partyMember")));
                Attack attack = new Attack();
                attack.setId(Integer.parseInt(resultSet.getString("id")));
                attack.setName(resultSet.getString("name"));
                attack.setDescription(resultSet.getString("description"));
                attack.setPrice(Integer.parseInt(resultSet.getString("price")));
                attack.setUnlockLevel(Integer.parseInt(resultSet.getString("unlockLevel")));
                attack.setImagePath(resultSet.getString("imagePath"));
                attack.setItemType(Integer.parseInt(resultSet.getString("itemType")));
                attack.setmPCost(Integer.parseInt(resultSet.getString("mPCost")));
                attack.setHPEffect(Integer.parseInt(resultSet.getString("HPEffect")));
                character.getAttacks().add(attack);
            }

            resultSet = DBConnector.getConnection().executeQueryGET(sqlQuery3);
            while(resultSet.next()){ //Sets info of all party, query should always return 3 rows (1/ party member)
                character = party.getPartyMember(Integer.parseInt(resultSet.getString("partyMember")));
                Item item = new Item();
                item.setId(Integer.parseInt(resultSet.getString("id")));
                item.setName(resultSet.getString("name"));
                item.setDescription(resultSet.getString("description"));
                item.setUnlockLevel(Integer.parseInt(resultSet.getString("unlockLevel")));
                item.setImagePath(resultSet.getString("imagePath"));
                item.setItemType(Integer.parseInt(resultSet.getString("itemType")));
                item.setStrengthEffect(Integer.parseInt(resultSet.getString("strengthEffect")));
                item.setSpeedEffect(Integer.parseInt(resultSet.getString("speedEffect")));
                item.setMagicEffect(Integer.parseInt(resultSet.getString("magicEffect")));
                item.setResistanceEffect(Integer.parseInt(resultSet.getString("resistanceEffect")));
                item.setLuckEffect(Integer.parseInt(resultSet.getString("luckEffect")));
                item.setmPEffect(Integer.parseInt(resultSet.getString("mPEffect")));
                item.sethPEffect(Integer.parseInt(resultSet.getString("hPEffect")));
                character.getItems().add(item);
            }

            resultSet = DBConnector.getConnection().executeQueryGET(sqlQuery4);
            while(resultSet.next()){ //Sets info of all party, query should always return 3 rows (1/ party member)
                character = party.getPartyMember(Integer.parseInt(resultSet.getString("partyMember")));
                Skill skill = new Skill();
                skill.setId(Integer.parseInt(resultSet.getString("id")));
                skill.setName(resultSet.getString("name"));
                skill.setDescription(resultSet.getString("description"));
                skill.setPrice(Integer.parseInt(resultSet.getString("price")));
                skill.setUnlockLevel(Integer.parseInt(resultSet.getString("unlockLevel")));
                skill.setImagePath(resultSet.getString("imagePath"));
                skill.setItemType(Integer.parseInt(resultSet.getString("itemType")));
                skill.setmPCost(Integer.parseInt(resultSet.getString("mPCost")));
                skill.setStrengthEffect(Integer.parseInt(resultSet.getString("strengthEffect")));
                skill.setSpeedEffect(Integer.parseInt(resultSet.getString("speedEffect")));
                skill.setMagicEffect(Integer.parseInt(resultSet.getString("magicEffect")));
                skill.setResistanceEffect(Integer.parseInt(resultSet.getString("resistanceEffect")));
                skill.setLuckEffect(Integer.parseInt(resultSet.getString("luckEffect")));
                skill.setmPEffect(Integer.parseInt(resultSet.getString("mPEffect")));
                skill.sethPEffect(Integer.parseInt(resultSet.getString("hPEffect")));
                skill.setType(resultSet.getString("type"));
                character.getSkills().add(skill);
            }

            resultSet = DBConnector.getConnection().executeQueryGET(sqlQuery5);
            gameStatus.getEnemies().clear();
            gameStatus.getEnemiesHealth().clear();
            while(resultSet.next()){
                gameStatus.getEnemiesHealth().put(resultSet.getString("name"), Integer.parseInt(resultSet.getString("healthPoints")));
            }
            DBConnector.getConnection().closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
