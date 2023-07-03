    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;
/**
 *
 * @author nguye
 */
import Model.Emp;
import Model.Prof;
import Model.Stu;
import Model.User;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
public class BaseController implements Initializable {
    private static String url = Config.HOST + Config.DATABASE;
    private static String user = Config.USERNAME;
    private static String password = Config.PASSWORD;
    public static Connection conn = null;
    protected static User usr = null;
    
    protected static Stu stu_detail;
    
    protected static String page;
    
    @FXML
    private JFXButton ClsButton;
            
    @FXML
    protected Text StuCls;

    @FXML
    protected Text StuName;
    
    @FXML
    protected Text ProfDept;

    @FXML
    protected Text ProfName;
    
    @FXML
    protected Text AdminName;
            
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
    
    @FXML
    public void ExitApp(MouseEvent event) {
        javafx.application.Platform.exit();
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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
}
