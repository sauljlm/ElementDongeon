package com.avengers.rpgame.data.dataBase;

import java.sql.*;

public class DBAccess {
    private Connection conn = null;
    private Statement stmt = null;

    private String url;

    public DBAccess(String uRL) throws Exception {
        url = uRL;
    }

    //Method for INSERT, UPDATE, DELETE
    public void executeQuerySET(String query) throws Exception{
        try {
            conn = DriverManager.getConnection(url);
            stmt = conn.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.close();
        }
    }

    //Method for GET, Selects
    public ResultSet executeQueryGET(String query) throws Exception{
        try {
            conn = DriverManager.getConnection(url);
            ResultSet rs;
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void closeConnection() throws Exception {
        conn = DriverManager.getConnection(url);
        conn.close();
    }
}
