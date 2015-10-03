package org.lab1;

/**
 * Created by Strelchenko on 21.09.2015.
 */

import java.sql.*;


public class DataBase {
    public static Connection conn;
    public static Statement statmt;
    public static ResultSet resSet;

    public static void Conn() {
        conn = null;
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:db/database.s3db");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("База Подключена!");
    }

    public static void WriteDB(String... info) {
        try {
            statmt = conn.createStatement();
            String sqlS = "";
            for (int i = 1; i < info.length; i++) {
                sqlS += ",\"" + info[i] + "\"";
            }

                    statmt.execute("INSERT INTO " + info[0] + " VALUES (NULL " + sqlS + ");");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Данные добавлены");
    }

    public static void DeleteDB(String db, int id) {
        try {
            statmt = conn.createStatement();
            PreparedStatement statement = conn.prepareStatement("DELETE  FROM " + db + " WHERE id=?");
            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Данные удалены");
    }

    public static void UpdateDB(String db, String FIO, int id) {
        try {
            statmt = conn.createStatement();
            PreparedStatement statement = conn.prepareStatement("UPDATE " + db + " SET FIO=? WHERE id =?");
            statement.setString(1, FIO);
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Данные обновленны");
    }


    public static void ReadDB(String db, String FIO) {
        try {
            statmt = conn.createStatement();
            resSet = statmt.executeQuery("SELECT * FROM " + db + " WHERE FIO=\"" + FIO + "\";");
            ResultSetMetaData data = resSet.getMetaData();
            int tableColumn = data.getColumnCount();
            int column = 1;
            while (resSet.next()) {
                System.out.print(resSet.getString(column) + "\t");
                if(column != tableColumn){
                    column++;
                } else {
                    column = 1;
                    System.out.println();
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("Таблица выведена");
    }

    public static void ReadDB(String db) {
        try {
            statmt = conn.createStatement();
            resSet = statmt.executeQuery("SELECT * FROM " + db + ";");
            ResultSetMetaData data = resSet.getMetaData();
            int column = 1;
            int tableColumn = data.getColumnCount();
            while (resSet.next()) {
                System.out.print(resSet.getString(column) + "\t");
                if(column != tableColumn){
                    column++;
                } else {
                    column = 1;
                    System.out.println();
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("Таблица выведена");
    }

    public static void CloseDB() throws ClassNotFoundException, SQLException {
        try {
            conn.close();
            statmt.close();
            resSet.close();
        }catch (NullPointerException e){
//            System.err.println(e);
        }
        System.out.println("Соединения закрыты");
    }

}
