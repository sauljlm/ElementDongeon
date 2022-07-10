package com.avengers.rpgame.data;

import com.avengers.rpgame.logic.entities.Party;
import com.avengers.rpgame.logic.entities.character.builder.CharacterBuilder;
import com.avengers.rpgame.logic.entities.character.components.CharacterClass;
import com.avengers.rpgame.utils.Resources;








import com.badlogic.gdx.math.Vector2;

import java.sql.*;

public class DatabaseAccess {
    private static Connection connection;
    private static Statement statement;

    private static void connect(String url){
        //"jdbc:sqlite:C:/Users/XSF/Documents/TestDatabases/SaveData.db";
        try {
            connection = DriverManager.getConnection(url);
            if(connection != null){
                DatabaseMetaData meta = connection.getMetaData();
                switch (url) {
                    case Resources.SAVEDATA:
                                String sql = "CREATE TABLE IF NOT EXISTS SavedFiles (\n" +
                                    "        Id integer PRIMARY KEY,\n" +
                                        "    UserName Text, \n" +
                                        ");";

                                    statement = connection.createStatement();
                                    statement.execute(sql);

                                    sql = " CREATE TABLE IF NOT EXISTS CharacterAttributes(\n" +
                                            "                       IdCharacter integer PRIMARY KEY,\n" +
                                            "                       HP integer,\n" +
                                            "                       Level float,\n" +
                                            "                       MP integer,\n" +
                                            "                       Strength integer,\n" +
                                            "                       Speed integer, \n" +
                                            "                       Magic integer,\n" +
                                            "                       Resistance integer,\n" +
                                            "                       Luck integer,\n" +
                                            "                       Name Text,\n" +
                                            "                       X float,\n" +
                                            "                       Y float,\n" +
                                            "                       IdFile Integer NOT NULL,\n" +
                                            "                       CharacterClass Text,\n" +
                                            "                       FOREIGN KEY (IdFile)\n" +
                                            "                       REFERENCES SavedFiles (Id));";

                                    statement = connection.createStatement();
                                    statement.execute(sql);

                                    sql =  "      CREATE TABLE IF NOT EXISTS Items(\n" +
                                            "         Id Integer PRIMARY KEY,\n" +
                                            "         IdFile Integer NOT NULL,\n" +
                                            "         FOREIGN KEY (IdFile)\n" +
                                            "              REFERENCES SavedFiles(Id));\n";
                                    statement = connection.createStatement();
                                    statement.execute(sql);
                                    sql =  "      CREATE TABLE IF NOT EXISTS Skills(\n" +
                                            "         Id Integer PRIMARY KEY,\n" +
                                            "         IdFile Integer NOT NULL,\n" +
                                            "         FOREIGN KEY (IdFile)\n" +
                                            "              REFERENCES SavedFiles(Id));\n";
                                    statement = connection.createStatement();
                                    statement.execute(sql);

                                    sql =   "      CREATE TABLE IF NOT EXISTS Goals(\n" +
                                            "         Id Integer PRIMARY KEY,\n" +
                                            "         Status Boolean,\n" +
                                            "         IdFile Integer NOT NULL,\n" +
                                            "         FOREIGN KEY (IdFile)\n" +
                                            "               REFERENCES SavedFiles(Id));";
                                    statement = connection.createStatement();
                                    statement.execute(sql);

                                    sql = "INSERT INTO SavedFiles (Id) VALUES(?);";
                                    PreparedStatement preparedStatement = connection.prepareStatement(sql);
                                    preparedStatement.setInt(1, 1);
                                    preparedStatement.executeUpdate();
                                    preparedStatement = connection.prepareStatement(sql);
                                    preparedStatement.setInt(1, 2);
                                    preparedStatement.executeUpdate();
                                    preparedStatement = connection.prepareStatement(sql);
                                    preparedStatement.setInt(1, 3);
                                    preparedStatement.executeUpdate();

                                    sql = "INSERT INTO CharacterAttributes (IdFile, X, Y, CharacterClass) VALUES(?,?,?,?);";
                                    preparedStatement = connection.prepareStatement(sql);
                                    preparedStatement.setInt(1, 1);
                                    preparedStatement.setFloat(2, -1);
                                    preparedStatement.setFloat(3, -1);
                                    preparedStatement.setInt(4, -1);
                                    preparedStatement.executeUpdate();
                                    preparedStatement = connection.prepareStatement(sql);
                                    preparedStatement.setInt(1, 2);
                                    preparedStatement.setFloat(2, -1);
                                    preparedStatement.setFloat(3, -1);
                                    preparedStatement.setInt(4, -1);
                                    preparedStatement.executeUpdate();
                                    preparedStatement = connection.prepareStatement(sql);
                                    preparedStatement.setInt(1, 3);
                                    preparedStatement.setFloat(2, -1);
                                    preparedStatement.setFloat(3, -1);
                                    preparedStatement.setInt(4, -1);
                                    preparedStatement.executeUpdate();

                        break;

                    default:
                        break;
                }

            }
            System.out.println("Connection to SQLite has been established");
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }


    public static void saveFile(int id, SavedFile savedFile){

        try{
            DatabaseAccess.connect(Resources.SAVEDATA);

            String sql = "UPDATE CharacterAttributes SET X = ?, Y = ?, CharacterClass = ?," +
                    " HP = ?, Level = ?, MP = ?, Strength = ?, Speed = ?, Magic = ?, Resistance = ?, Luck = ?, Name = ?," +
                    "IdCharacter = ? WHERE IdFile = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setFloat(1, savedFile.getCharacterInfo().getAnimatedCharacter().getPlayer().getPosition().x);
            preparedStatement.setFloat(2, savedFile.getCharacterInfo().getAnimatedCharacter().getPlayer().getPosition().y);
            preparedStatement.setInt(3, savedFile.getCharacterInfo().getCharacterClass().getIdCharacterClass());
            preparedStatement.setInt(4, savedFile.getCharacterInfo().getHealthPoints());
            preparedStatement.setFloat(5, (float)savedFile.getCharacterInfo().getLevel());
            preparedStatement.setInt(6, savedFile.getCharacterInfo().getMagicPoints());
            preparedStatement.setInt(7, savedFile.getCharacterInfo().getStrength());
            preparedStatement.setInt(8, savedFile.getCharacterInfo().getSpeed());
            preparedStatement.setInt(9, savedFile.getCharacterInfo().getMagic());
            preparedStatement.setInt(10, savedFile.getCharacterInfo().getResistance());
            preparedStatement.setInt(11, savedFile.getCharacterInfo().getLuck());
            preparedStatement.setString(12, savedFile.getCharacterInfo().getName());
            preparedStatement.setInt(13, savedFile.getCharacterInfo().getIdCharacter());

            preparedStatement.setInt(14, id);
            preparedStatement.executeUpdate();

            sql = "UPDATE SavedFiles SET UserName = ? WHERE Id = ?;";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, savedFile.getUsername());
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        }
        catch(SQLException exc){
            System.out.println(exc.getMessage());
        }
        finally {
            try{
                if(connection != null){
                    connection.close();
                }
            }catch (SQLException exception){
                System.out.println(exception.getMessage());
            }
        }
    }

    public static SavedFile loadFile(int id){

        try{
            DatabaseAccess.connect(Resources.SAVEDATA);

            String sql = "SELECT sf.Id, sf.UserName, ca.X, ca.Y, ca.CharacterClass, ca.Level, ca.HP, ca.MP, ca.Strength, ca.Speed, ca.Magic, ca.Resistance, ca.Luck\n" +
                    " FROM SavedFiles as sf\n" +
                    "JOIN CharacterAttributes as ca ON sf.Id = ca.IdFile  \n" +
                    "WHERE Id = ?";

            SavedFile savedFile = SavedFile.getInstance();

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            CharacterBuilder builder = new CharacterBuilder();
            while(rs.next()){
                float x = rs.getFloat("X");
                float y = rs.getFloat("Y");
                int hp = rs.getInt("HP");
                int mp = rs.getInt("MP");
                int strength = rs.getInt("Strength");
                int speed = rs.getInt("Speed");
                int magic = rs.getInt("Magic");
                int resistance = rs.getInt("Resistance");
                int luck = rs.getInt("Luck");
                float level = rs.getFloat("Level");
                String username = rs.getString("UserName");

                int characterClass = rs.getInt("CharacterClass");

                builder.setCharacterBasicInfo(1,username,
                        "", new Vector2(x,y), level,hp,mp,0);

                builder.setCharacterAttributes(strength,speed,magic,resistance,luck);
                savedFile.setCharacterInfo(builder.getResult());
                savedFile.setIdCharacterClass(characterClass);
                savedFile.setUsername(username);
                Party party = new Party();
                party.setPartyMember1(savedFile.getCharacterInfo());
                savedFile.setPlayerParty(party);

            }
            return savedFile;
        }
        catch(SQLException exc){
            System.out.println(exc.getMessage());
        }
        finally {
            try{
                if(connection != null){
                    connection.close();
                }
            }catch (SQLException exception){
                System.out.println(exception.getMessage());
            }
        }
        return null;
    }

}
