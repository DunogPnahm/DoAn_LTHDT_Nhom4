/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;
/**
 *
 * @author nguye
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class BaseController {
    private static String url = Config.HOST + Config.DATABASE;
    private static String user = Config.USERNAME;
    private static String password = Config.PASSWORD;
    public static Connection conn = null;
    
    public void openConnection() {
        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public void closeConnection(PreparedStatement preSql) {
        if (preSql != null) {
            try {
                preSql.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public ResultSet getData(PreparedStatement preSql) {
        try {
            ResultSet rs = preSql.executeQuery();
            return rs;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public int postData(PreparedStatement preSql) {
        try {
            int rowsAffected = preSql.executeUpdate();
            return rowsAffected;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return -1;
    }
    
    public int deleteData(PreparedStatement preSql) {
        try {
            int rowsAffected = preSql.executeUpdate();
            return rowsAffected;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return -1;
    }
}
