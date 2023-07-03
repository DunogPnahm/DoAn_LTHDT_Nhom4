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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class UpdatePasswordController extends BaseController {

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
    private PasswordField in_confirm;

    @FXML
    private PasswordField in_new;

    @FXML
    private PasswordField in_old;

    @FXML
    private Label lb_err;

    @FXML
    private Text txt_cls_dept;

    @FXML
    private Text txt_name;

    @FXML
    void cancel(MouseEvent event) {
        try {
            if(usr.getUSER_ROLE() == "STU")
            {
                App.setRoot("StuMainMenu");
            }
            if(usr.getUSER_ROLE() == "PROF")
            {
                App.setRoot("ProfMainMenu");
            }
            if(usr.getUSER_ROLE() == "ADMIN")
            {
                App.setRoot("AdminMainMenu");
            }
        } catch (IOException ex) {
            Logger.getLogger(UpdateCourseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    void submit(MouseEvent event) {
        String oldpswd = in_old.getText();
        String newpswd = in_new.getText();
        String confirm = in_confirm.getText();
        if (oldpswd.isBlank() || newpswd.isBlank() || confirm.isBlank()) 
        {
            lb_err.setText("Chưa điền đủ thông tin!");
            lb_err.setVisible(true);
            return;
        } 
        else if(!oldpswd.equals(usr.getUSER_PSWD()))
        {
            lb_err.setText("Mật khẩu hiện tại không đúng!");
            lb_err.setVisible(true);
            return;
        }
        else if(newpswd.equals(oldpswd))
        {
            lb_err.setText("Mật khẩu mới không được giống mật khẩu cũ!");
            lb_err.setVisible(true);
            return;
        }
        else if(!confirm.equals(newpswd))
        {
            lb_err.setText("Mật khẩu xác nhận không khớp!");
            lb_err.setVisible(true);
            return;
        }
        else 
        {
            lb_err.setVisible(false);
        }
        
        PreparedStatement preSql;
        try {
            this.openConnection();
            preSql = this.conn.prepareStatement("UPDATE user SET "
                    + "USER_PSWD = ? "
                    + "WHERE USER_ACC = ?");
            preSql.setString(1, newpswd);
            preSql.setString(2, usr.getUSER_ACC());

            int result = this.postData(preSql);
            if(usr.getUSER_ROLE().compareTo(Config.STUDENT_ROLE) == 0)
            {
                App.setRoot("StuMainMenu");
            }
            if(usr.getUSER_ROLE().compareTo(Config.PROFESSOR_ROLE) == 0)
            {
                App.setRoot("ProfMainMenu");
            }
            if(usr.getUSER_ROLE().compareTo(Config.ADMIN_ROLE) == 0)
            {
                App.setRoot("AdminMainMenu");
            }
        } catch (SQLException | IOException ex) {
            Logger.getLogger(UpdateCourseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if(usr.getUSER_ROLE() == "STU")
        {
            txt_name.setText(this.usr.getStu().getSTU_LNAME() + " " + this.usr.getStu().getSTU_MNAME() + " " + this.usr.getStu().getSTU_FNAME());
            txt_cls_dept.setText(usr.getStu().getMGTCLS_CODE());
        }
        if(usr.getUSER_ROLE() == "PROF")
        {
            txt_name.setText(this.usr.getProf().getEMP_LNAME() + " " + this.usr.getProf().getEMP_MNAME() + " " + this.usr.getProf().getEMP_FNAME());
            txt_cls_dept.setText(usr.getProf().getDEPT_CODE());
        }
        if(usr.getUSER_ROLE() == "ADMIN")
        {
            txt_name.setText(this.usr.getEmp().getEMP_LNAME() + " " + this.usr.getEmp().getEMP_MNAME() + " " + this.usr.getEmp().getEMP_FNAME());
        }
    }
    
}
