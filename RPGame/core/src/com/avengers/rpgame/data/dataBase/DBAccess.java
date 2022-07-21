package com.avengers.rpgame.data.dataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBAccess {
    private Connection conn = null;
    private Statement stmt = null;

    public DBAccess(String URL) throws Exception {
        conn = DriverManager.getConnection(URL);
    }

    //Method for INSERT, UPDATE, DELETE
    public void executeQuerySET(String query) throws Exception{
        stmt = conn.createStatement();
        stmt.executeUpdate(query);
    }

    //Method for GET, Selects
    public ResultSet executeQueryGET(String query) throws Exception{
        ResultSet rs;
        stmt = conn.createStatement();
        rs = stmt.executeQuery(query);
        return rs;
    }
}
