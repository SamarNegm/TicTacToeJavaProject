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

    public boolean IsAuthenticated(String uname, String Pass) {
        try {

            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from playerdata");
            while (rs.next()) {
                 System.out.println(rs.getString(2));
                if (rs.getString(2).equals(uname) && rs.getString(3).equals(Pass)) {
                    System.out.println("ok*******");
                    return true;
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("not ok||");
        return false;
    }

    public void checkUserData(String uname, String password) {

        if (exist(uname)) {
            System.out.println("UserName is exist");
        } else {
            signUp(uname, password);
        }

    }

    public void signUp(String uname, String password) {
        try {
            String queryIsert = " insert into playerdata (  Name, Password, Score, Status)"
                    + " values (?, ?, ?,?)";
            preparedStmt = con.prepareStatement(queryIsert);
            preparedStmt.setString(1, uname);
            preparedStmt.setString(2, password);
            preparedStmt.setInt(3, 0);
            preparedStmt.setString(4, "Online");
            System.out.println("iserted....");
            preparedStmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean exist(String uname) {
        try {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from playerdata");
            while (rs.next()) {
                  System.out.println(rs.getString(2));
                if (rs.getString(2).equals(uname)) {
                    System.out.println("ok*******");
                    return true;
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("not ok||");
        return false;
    }

}
