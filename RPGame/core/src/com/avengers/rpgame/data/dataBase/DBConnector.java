package com.avengers.rpgame.data.dataBase;

public class DBConnector {
    private static DBAccess dBConnection = null;

    public static DBAccess getConnection() throws Exception {

        //DB string conection + params
        String URL = "jdbc:sqlite:../core/src/com/avengers/rpgame/data/dataBase/rpGameDB.db";

        if (dBConnection == null) {
            dBConnection = new DBAccess(URL);
        }
        return dBConnection;
    }
}
