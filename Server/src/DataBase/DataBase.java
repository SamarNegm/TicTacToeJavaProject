/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBase;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MrMr
 */
public class DataBase {

    static Connection con = null;
    static Statement stmt = null;
    static PreparedStatement preparedStmt = null;

    public DataBase() {
        Connet();
    }

    public static Connection getCon() {
        return con;
    }

    public static void setCon(Connection con) {
        DataBase.con = con;
    }

    public static Statement getStmt() {
        return stmt;
    }

    public static void setStmt(Statement stmt) {
        DataBase.stmt = stmt;
    }

    public static PreparedStatement getPreparedStmt() {
        return preparedStmt;
    }

    public static void setPreparedStmt(PreparedStatement preparedStmt) {
        DataBase.preparedStmt = preparedStmt;
    }

    public static Connection Connet() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gamedb", "root", "1234");
            System.out.println("conected..");

        } catch (Exception e) {

            System.out.println(e);

        }
        return con;
    }







}
