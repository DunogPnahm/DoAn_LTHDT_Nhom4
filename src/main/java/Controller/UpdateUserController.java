package Controller;

import Model.Crs;
import Model.Dept;
import Model.User;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class UpdateUserController extends AdminMainMenuController {

    @FXML
    private Pane Avatar;

    @FXML
    private Pane Cancel_Ic;

    @FXML
    private JFXButton ClsButton;

    @FXML
    private Pane Confirm_Ic;

    @FXML
    private Pane HuceLogo;

    @FXML
    private JFXButton Logout_btn;

    @FXML
    private JFXButton Logout_btn1;

    @FXML
    private Pane UsrBckgr;

    @FXML
    private AnchorPane body;

    @FXML
    private ComboBox<String> cb_code;

    @FXML
    private ComboBox<String> cb_role;

    @FXML
    private TextField in_name;

    @FXML
    private TextField in_pswd;

    @FXML
    private Label lb_err;

    @FXML
    private Text txt_name;

    @FXML
    void cancel(MouseEvent event) {
        try {
            App.setRoot("AdminMainMenu");
        } catch (IOException ex) {
            Logger.getLogger(UpdateCourseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void submit(MouseEvent event) {
        if (in_name.getText()=="" || in_pswd.getText().isBlank() || cb_role.getValue()==null || cb_code.getValue()==null) {
            lb_err.setText("Chưa điền đủ thông tin !");
            lb_err.setVisible(true);
            return;
        } else {
            lb_err.setVisible(false);
        }
        
        PreparedStatement preSql;
        try {
            this.openConnection();
            
            
        
            User usr_new = User.getInstance(
                        in_name.getText(),
                        in_pswd.getText(),
                        cb_role.getValue(),
                        Integer.parseInt(cb_code.getValue())
                );
            this.openConnection();
            preSql = this.conn.prepareStatement("UPDATE user SET "
                    + "USER_ID = ?, "
                    + "USER_PSWD = ?, "
                    + "USER_ROLE = ? "
                    + "WHERE USER_ACC = ?");
            preSql.setInt(1, usr_new.getUSER_ID());
            preSql.setString(2, usr_new.getUSER_PSWD());
            preSql.setString(3, usr_new.getUSER_ROLE());
            preSql.setString(4, usr_new.getUSER_ACC());

            int result = this.postData(preSql);
            App.setRoot("AdminMainMenu");
        } catch (SQLException | IOException ex) {
            Logger.getLogger(UpdateCourseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txt_name.setText(this.usr.getEmp().getEMP_LNAME() + " " + this.usr.getEmp().getEMP_MNAME() + " " + this.usr.getEmp().getEMP_FNAME());
        String[] role_list = {"STU", "PROF", "ADMIN"};
        
        in_name.setText(this.user_edit.getUSER_ACC());
        cb_role.getItems().setAll(role_list);
        for (int i = 0; i < role_list.length; i++) {
            if (this.user_edit.getUSER_ROLE().compareTo(role_list[i]) == 0) {
                cb_role.getSelectionModel().select(i);
            }
        }
        
        List<String> code_list = new ArrayList<>();
        try {
            this.openConnection();
            PreparedStatement preSql;
            ResultSet rs;
            if(cb_role.getValue() == "STU")
            {
                preSql = this.conn.prepareStatement("SELECT * FROM student;");
                rs = this.getData(preSql);
                while (rs.next()) {
                    code_list.add(Integer.toString(rs.getInt("STU_NUM")));
                }
            }
            else if(cb_role.getValue() == "PROF")
            {
                preSql = this.conn.prepareStatement("SELECT * FROM employee WHERE EMP_JOBCODE = 'PROF';");
                rs = this.getData(preSql);
                while (rs.next()) {
                    code_list.add(Integer.toString(rs.getInt("EMP_NUM")));
                }
            }
            else
            {
                preSql = this.conn.prepareStatement("SELECT * FROM employee WHERE EMP_JOBCODE = 'ADMIN'");
                rs = this.getData(preSql);
                while (rs.next()) {
                    code_list.add(Integer.toString(rs.getInt("EMP_NUM")));
                }
            }
            this.closeConnection(preSql);
        } catch (SQLException ex) {
            Logger.getLogger(UpdateCourseController.class.getName()).log(Level.SEVERE, null, ex);
        }
        cb_code.getItems().setAll(code_list);
        for (int i = 0; i < code_list.size(); i++) {
            if (this.user_edit.getUSER_ID() == Integer.parseInt(code_list.get(i))) {
                cb_code.getSelectionModel().select(i);
            }
        }
        
    }
    
}
