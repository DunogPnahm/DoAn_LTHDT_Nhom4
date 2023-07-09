package Controller;

import Controller.BaseController;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class LoginController extends BaseController implements Initializable {

    @FXML
    private Pane body;

    @FXML
    private Button btn_log;

    @FXML
    private Button btn_reg;

    @FXML
    private PasswordField tf_pwd;

    @FXML
    private TextField tf_usr;
    
    @FXML
    private Text txt_err;

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
                App.setRoot("secondary");
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        hideErr();
    }
}
