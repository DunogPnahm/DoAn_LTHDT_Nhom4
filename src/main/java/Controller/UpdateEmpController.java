package Controller;

import Model.Emp;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class UpdateEmpController extends AdminDeptEmpInfoController {

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
    private ComboBox<String> cb_pos;

    @FXML
    private DatePicker dp_dob;

    @FXML
    private DatePicker dp_hd;

    @FXML
    private TextField in_email;

    @FXML
    private TextField in_fname;

    @FXML
    private TextField in_lname;

    @FXML
    private TextField in_mname;

    @FXML
    private TextField in_code;
    
    @FXML
    private Text txt_name;
    
    @FXML
    private Label lb_err;

    @FXML
    private Label err_dob;
    
    @FXML
    private Label err_hd;
    
    @FXML
    void cancel(MouseEvent event) {
        try {
            App.setRoot("AdminDeptEmpInfo");
        } catch (IOException ex) {
            Logger.getLogger(UpdateEmpController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void submit(MouseEvent event) {
        if (!checkDob() || !checkHd()) return;
        if (in_code.getText()=="" || in_lname.getText()=="" || in_fname.getText()=="" || in_email.getText()=="" || cb_pos.getValue()==null || dp_dob.getValue()==null || dp_hd.getValue()==null) {
            lb_err.setText("Chưa điền đủ thông tin !");
            lb_err.setVisible(true);
            return;
        } else {
            lb_err.setVisible(false);
        }
        
        PreparedStatement preSql;
        try {
            this.openConnection();
            preSql = this.conn.prepareStatement("SELECT * FROM employee WHERE EMP_NUM != ? AND EMP_EMAIL = ?");
            preSql.setInt(1, Integer.parseInt(in_code.getText()));
            preSql.setString(2, in_email.getText());
            
            ResultSet rs = this.getData(preSql);
            if (rs.next()) {
                lb_err.setText("Email đã tồn tại!");
                lb_err.setVisible(true);
                return;
            } else {
                lb_err.setVisible(false);
            }
            
            Emp emp_new = new Emp(
                Integer.parseInt(in_code.getText()), 
                in_lname.getText(), 
                in_fname.getText(), 
                in_mname.getText(), 
                in_email.getText(), 
                cb_pos.getSelectionModel().getSelectedItem(), 
                Date.valueOf(dp_hd.getValue()),
                Date.valueOf(dp_dob.getValue()) 
            );
            this.openConnection();
            preSql = this.conn.prepareStatement("UPDATE employee SET "
                    + "EMP_LNAME = ?, "
                    + "EMP_FNAME = ?, "
                    + "EMP_MNAME = ?, "
                    + "EMP_EMAIL = ?, "
                    + "EMP_JOBCODE = ?, "
                    + "EMP_HIREDATE = ?, "
                    + "EMP_DOB = ? "
                    + "WHERE EMP_NUM = ?");
            preSql.setString(1, emp_new.getEMP_LNAME());
            preSql.setString(2, emp_new.getEMP_FNAME());
            preSql.setString(3, emp_new.getEMP_MNAME());
            preSql.setString(4, emp_new.getEMP_EMAIL());
            preSql.setString(5, emp_new.getEMP_JOBCODE());
            preSql.setDate(6, emp_new.getEMP_HIREDATE());
            preSql.setDate(7, emp_new.getEMP_DOB());
            preSql.setInt(8, emp_new.getEMP_NUM());

            int result = this.postData(preSql);
            if (result > 0) System.out.println("Sửa thành công");
            App.setRoot("AdminDeptEmpInfo");
        } catch (SQLException | IOException ex) {
            Logger.getLogger(StuMainMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txt_name.setText(this.usr.getEmp().getEMP_LNAME() + " " + this.usr.getEmp().getEMP_MNAME() + " " + this.usr.getEmp().getEMP_FNAME());
        String[] pos_list = {"ADMIN", "LIBRA", "JANT"};
        
        in_code.setText(String.valueOf(this.emp_edit.getEMP_NUM()));
        in_lname.setText(this.emp_edit.getEMP_LNAME());
        in_mname.setText(this.emp_edit.getEMP_MNAME());
        in_fname.setText(this.emp_edit.getEMP_FNAME());
        in_email.setText(this.emp_edit.getEMP_EMAIL());
        cb_pos.getItems().setAll(pos_list);
        for (int i = 0; i < pos_list.length; i++) {
            if (this.emp_edit.getEMP_JOBCODE().compareTo(pos_list[i]) == 0) {
                cb_pos.getSelectionModel().select(i);
            }
        }
        dp_dob.setValue(this.emp_edit.getEMP_DOB().toLocalDate());
        dp_hd.setValue(this.emp_edit.getEMP_HIREDATE().toLocalDate());
    }

    @FXML
    public boolean checkDob() {
        if (LocalDate.now().getYear() - dp_dob.getValue().getYear() < 18 || LocalDate.now().getYear() - dp_dob.getValue().getYear() > 150) {
            err_dob.setVisible(true);
            return false;
        } else {
            err_dob.setVisible(false);
            return true;
        }
    }
    
    @FXML
    public boolean checkHd() {
        if (dp_hd.getValue().getYear() - LocalDate.now().getYear() > 1 || LocalDate.now().getYear() - dp_hd.getValue().getYear() > 150) {
            err_hd.setVisible(true);
            return false;
        } else {
            err_hd.setVisible(false);
            return true;
        }
    }
}
