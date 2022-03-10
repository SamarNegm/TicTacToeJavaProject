/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Shared;

import DataBase.DataBase;
import Models.Player;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MrMr
 */
public abstract class DataBaseHandler {

    public static boolean isDBChanged() {
        //save the value of the flag
        boolean result = isDBChanged;

        //reset the flag because of this check
        resetIsDBChanged();

        return result;
    }

    private static void resetIsDBChanged() {
        isDBChanged = false;
    }

    public static void setIsDBChanged(boolean isDBChanged) {
        DataBaseHandler.isDBChanged = isDBChanged;
    }

    DataBase db = new DataBase();
    private static boolean isDBChanged = false;

 
}
