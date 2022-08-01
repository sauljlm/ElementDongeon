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

    //TODO for future me, refactor this into stored procedures
    @Override
    public void saveGameStatus(GameStatus gameStatus) {
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
                    gameStatus.getParty().getPartyMember(partyMember).getIdCharacter()+"','"+
                    gameStatus.getParty().getPartyMember(partyMember).getName()+"','"+
                    gameStatus.getParty().getPartyMember(partyMember).getDescription()+"',"+
                    gameStatus.getParty().getPartyMember(partyMember).getPosition().x+","+
                    gameStatus.getParty().getPartyMember(partyMember).getPosition().y+","+
                    gameStatus.getParty().getPartyMember(partyMember).getLevel()+","+
                    gameStatus.getParty().getPartyMember(partyMember).getHealthPoints()+","+
                    gameStatus.getParty().getPartyMember(partyMember).getMagicPoints()+","+
                    gameStatus.getParty().getPartyMember(partyMember).getHealthPointsMax()+","+
                    gameStatus.getParty().getPartyMember(partyMember).getMagicPointsMax()+","+
                    gameStatus.getParty().getPartyMember(partyMember).getStrength()+","+
                    gameStatus.getParty().getPartyMember(partyMember).getSpeed()+","+
                    gameStatus.getParty().getPartyMember(partyMember).getMagic()+","+
                    gameStatus.getParty().getPartyMember(partyMember).getResistance()+","+
                    gameStatus.getParty().getPartyMember(partyMember).getLuck()+","+
                    gameStatus.getParty().getPartyMember(partyMember).getCoins()+","+
                    gameStatus.getParty().getPartyMember(partyMember).getCharacterClass().getIdCharacterClass()+","+
                    ((PlayableCharacter)gameStatus.getParty().getPartyMember(partyMember)).getExperiencePoints()+","+
                    timeStamp+
                    ")";
            querys.add(sqlQuery);

            for (Attack attack : gameStatus.getParty().getPartyMember(partyMember).getAttacks()){
                sqlQuery = "INSERT INTO Attack "+
                        "(saveSlot,partyMember,name,description,unlockLevel,mPCost,HPEffect,timeStamp)" +
                        "VALUES("+
                        gameStatus.getSaveSlot()+","+
                        partyMember+",'"+
                        attack.getName()+"','"+
                        attack.getDescription()+"',"+
                        attack.getUnlockLevel()+","+
                        attack.getmPCost()+","+
                        attack.getHPEffect()+","+
                        timeStamp+
                        ")";
                        querys.add(sqlQuery);
            }
            for (Item item : gameStatus.getParty().getPartyMember(partyMember).getItems()){
                sqlQuery = "INSERT INTO Item "+
                        "(saveSlot,partyMember,id,name,description,price,unlockLevel,imagePath,itemType,strengthEffect,speedEffect,magicEffect,resistanceEffect,luckEffect,mPEffect,hPEffect,timeStamp)" +
                        "VALUES("+
                        gameStatus.getSaveSlot()+","+
                        partyMember+",'"+
                        item.getId()+"','"+
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
            for (Skill skill : gameStatus.getParty().getPartyMember(partyMember).getSkills()){
                sqlQuery = "INSERT INTO Skill "+
                        "(saveSlot,partyMember,name,description,price,unlockLevel,mPCost,strengthEffect,speedEffect,magicEffect,resistanceEffect,luckEffect,mPEffect,hPEffect,timeStamp)" +
                        "VALUES("+
                        gameStatus.getSaveSlot()+","+
                        partyMember+",'"+
                        skill.getName()+"','"+
                        skill.getDescription()+"',"+
                        skill.getPrice()+","+
                        skill.getUnlockLevel()+",'"+
                        skill.getmPCost()+"',"+
                        skill.getStrengthEffect()+","+
                        skill.getSpeedEffect()+","+
                        skill.getMagicEffect()+","+
                        skill.getResistanceEffect()+","+
                        skill.getLuckEffect()+","+
                        skill.getmPEffect()+","+
                        skill.gethPEffect()+","+
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
    }

    @Override
    public GameStatus loadGameStatus(int saveSlot) {
        GameStatus gameStatus = GameStatus.getInstance();
        Party party = gameStatus.getParty();
        ResultSet resultSet;
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
                party.setPartyMember(Integer.parseInt(resultSet.getString("partyMember")), character);
                character.getAttacks().clear();
                character.getItems().clear();
                character.getSkills().clear();
            }

            resultSet = DBConnector.getConnection().executeQueryGET(sqlQuery2);
            while(resultSet.next()){ //Sets info of all party, query should always return 3 rows (1/ party member)
                character = party.getPartyMember(Integer.parseInt(resultSet.getString("partyMember")));
                Attack attack = new Attack();
                attack.setName(resultSet.getString("name"));
                attack.setDescription(resultSet.getString("description"));
                attack.setUnlockLevel(Integer.parseInt(resultSet.getString("unlockLevel")));
                attack.setmPCost(Integer.parseInt(resultSet.getString("mPCost")));
                attack.setHPEffect(Integer.parseInt(resultSet.getString("HPEffect")));
                character.getAttacks().add(attack);
            }

            resultSet = DBConnector.getConnection().executeQueryGET(sqlQuery3);
            while(resultSet.next()){ //Sets info of all party, query should always return 3 rows (1/ party member)
                character = party.getPartyMember(Integer.parseInt(resultSet.getString("partyMember")));
                Item item = new Item();
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
                skill.setName(resultSet.getString("name"));
                skill.setDescription(resultSet.getString("description"));
                skill.setPrice(Integer.parseInt(resultSet.getString("price")));
                skill.setUnlockLevel(Integer.parseInt(resultSet.getString("unlockLevel")));
                skill.setmPCost(Integer.parseInt(resultSet.getString("mPCost")));
                skill.setStrengthEffect(Integer.parseInt(resultSet.getString("strengthEffect")));
                skill.setSpeedEffect(Integer.parseInt(resultSet.getString("speedEffect")));
                skill.setMagicEffect(Integer.parseInt(resultSet.getString("magicEffect")));
                skill.setResistanceEffect(Integer.parseInt(resultSet.getString("resistanceEffect")));
                skill.setLuckEffect(Integer.parseInt(resultSet.getString("luckEffect")));
                skill.setmPEffect(Integer.parseInt(resultSet.getString("mPEffect")));
                skill.sethPEffect(Integer.parseInt(resultSet.getString("hPEffect")));
                character.getSkills().add(skill);
            }

            resultSet = DBConnector.getConnection().executeQueryGET(sqlQuery5);
            gameStatus.getEnemies().clear();
            gameStatus.getEnemiesHealth().clear();
            while(resultSet.next()){
                gameStatus.getEnemiesHealth().put(resultSet.getString("name"), Integer.parseInt(resultSet.getString("healthPoints")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        };
        return null;
    }
}
