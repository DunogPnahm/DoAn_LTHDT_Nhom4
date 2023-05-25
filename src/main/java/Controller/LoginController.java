package Controller;

import Controller.BaseController;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.security.MessageDigest;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

public class LoginController extends BaseController implements Initializable {

    @FXML
    private JFXButton btn_log;

    @FXML
    private ChoiceBox<String> themeButton;
    
    @FXML
    private Text chooseTheme;
    
    @FXML
    private Pane body;
    
    @FXML
    private Button btn_reg;

    @FXML
    private PasswordField tf_pwd;

    @FXML
    private TextField tf_usr;
    
    @FXML
    private Text txt_err;

    private String[] theme = {"background 1","background 2","background 3","background 4","background 5","background 6"};
    
    public void login() {
        if (tf_usr.getText() == null || tf_usr.getText().isBlank()) {
            tf_usr.requestFocus();
            return;
        }
        if (tf_pwd.getText() == null || tf_pwd.getText().isBlank()) {
            tf_pwd.requestFocus();
            return;
        }
        
        try {
            this.openConnection();
            PreparedStatement preSql = this.conn.prepareStatement("SELECT * FROM USER WHERE USER_ACC = ? AND USER_PSWD = ?");
            preSql.setString(1, tf_usr.getText());
            preSql.setString(2, tf_pwd.getText());
            ResultSet rs = this.getData(preSql);
            if (rs.next()) {
                App.setRoot("StuMainMenu");
            } else {
                txt_err.setVisible(true);
            }
            this.closeConnection(preSql);
        } catch (SQLException | IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public void hideErr() {
        txt_err.setVisible(false);
    }
    
    @FXML
    public void changeTheme(ActionEvent event)
    {
        chooseTheme.setVisible(false);
        switch(themeButton.getValue())
        {
            case "background 1": body.setStyle("-fx-background-image: url('./images/bg1.jpg');");
                break;
            case "background 2": body.setStyle("-fx-background-image: url('./images/bg2.jpg');");
                break;
            case "background 3": body.setStyle("-fx-background-image: url('./images/bg3.jpg');");
                break;
            case "background 4": body.setStyle("-fx-background-image: url('./images/bg4.jpg');");
                break;
            case "background 5": body.setStyle("-fx-background-image: url('./images/bg5.jpg');");
                break;
            case "background 6": body.setStyle("-fx-background-image: url('./images/bg6.jpg');");
                break;
            default: break;
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        hideErr();
        themeButton.getItems().addAll(theme);
        themeButton.setOnAction(this::changeTheme);
    }
}
