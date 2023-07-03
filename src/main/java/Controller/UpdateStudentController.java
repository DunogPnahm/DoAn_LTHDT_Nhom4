package Controller;

import Model.Dept;
import Model.Mgtcls;
import Model.Stu;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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

public class UpdateStudentController extends AdminScheController {

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
    private ComboBox<Dept> cb_dept_stu;

    @FXML
    private ComboBox<String> cb_gender;

    @FXML
    private ComboBox<Mgtcls> cb_mgtcls;

    @FXML
    private DatePicker dp_dob;

    @FXML
    private TextField in_email;

    @FXML
    private TextField in_fname;

    @FXML
    private TextField in_lname;

    @FXML
    private TextField in_mname;

    @FXML
    private TextField in_msv;
    
    @FXML
    private Text txt_name;
    
    @FXML
    private Label lb_err;

    @FXML
    private Label err_dob;
    
    @FXML
    void cancel(MouseEvent event) {
        try {
            App.setRoot("AdminSche");
        } catch (IOException ex) {
            Logger.getLogger(UpdateStudentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void submit(MouseEvent event) {
        if (!checkDob()) return;
        if (in_msv.getText()=="" || in_lname.getText()=="" || in_fname.getText()=="" || in_email.getText()=="" || dp_dob.getValue()==null || cb_gender.getValue()==null || cb_dept_stu.getValue()==null || cb_mgtcls.getValue()==null) {
            lb_err.setText("Chưa điền đủ thông tin!");
            lb_err.setVisible(true);
            return;
        } else {
            lb_err.setVisible(false);
        }
        
        PreparedStatement preSql;
        try {
            this.openConnection();
            preSql = this.conn.prepareStatement("SELECT * FROM student WHERE STU_NUM != ? AND STU_EMAIL = ?");
            preSql.setInt(1, Integer.parseInt(in_msv.getText()));
            preSql.setString(2, in_email.getText());
            
            ResultSet rs = this.getData(preSql);
            if (rs.next()) {
                lb_err.setText("Mã sinh viên hoặc Email đã tồn tại!");
                lb_err.setVisible(true);
                return;
            } else {
                lb_err.setVisible(false);
            }
        
            Stu stu_new = new Stu(
                Integer.parseInt(in_msv.getText()), 
                in_lname.getText(), 
                in_fname.getText(), 
                in_mname.getText(), 
                in_email.getText(), 
                Date.valueOf(dp_dob.getValue()),
                cb_gender.getSelectionModel().getSelectedItem(), 
                cb_dept_stu.getSelectionModel().getSelectedItem().getDEPT_CODE(),
                cb_mgtcls.getSelectionModel().getSelectedItem().getMGTCLS_CODE()
            );
            this.openConnection();
            preSql = this.conn.prepareStatement("UPDATE student SET "
                    + "STU_LNAME = ?, "
                    + "STU_FNAME = ?, "
                    + "STU_MNAME = ?, "
                    + "STU_EMAIL = ?, "
                    + "STU_DOB = ?, "
                    + "STU_GENDER = ?, "
                    + "DEPT_CODE = ?, "
                    + "MGTCLS_CODE = ? "
                    + "WHERE STU_NUM = ?");
            preSql.setString(1, stu_new.getSTU_LNAME());
            preSql.setString(2, stu_new.getSTU_FNAME());
            preSql.setString(3, stu_new.getSTU_MNAME());
            preSql.setString(4, stu_new.getSTU_EMAIL());
            preSql.setDate(5, stu_new.getSTU_DOB());
            preSql.setString(6, stu_new.getSTU_GENDER());
            preSql.setString(7, stu_new.getDEPT_CODE());
            preSql.setString(8, stu_new.getMGTCLS_CODE());
            preSql.setInt(9, stu_new.getSTU_NUM());

            int result = this.postData(preSql);
            App.setRoot("AdminSche");
        } catch (SQLException | IOException ex) {
            Logger.getLogger(UpdateStudentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    void DeptChosen(ActionEvent event) {
        List<Mgtcls> mgtcls_list = new ArrayList<>();
        try {
            this.openConnection();
            PreparedStatement preSql = this.conn.prepareStatement("SELECT * FROM `management_class`\n" +
                                                                  "WHERE PROF_NUM IN (SELECT PROF_NUM FROM professor\n" +
                                                                  "                   WHERE DEPT_CODE = ?)");
            preSql.setString(1, cb_dept_stu.getSelectionModel().getSelectedItem().getDEPT_CODE());
            ResultSet rs = this.getData(preSql);
            while (rs.next()) {
                Mgtcls mgtcls = new Mgtcls(
                        rs.getString("MGTCLS_CODE"),                        
                        rs.getInt("PROF_NUM"),
                        rs.getString("SEM_CODE")
                );
                mgtcls_list.add(mgtcls);
            }
            this.closeConnection(preSql);
        } catch (SQLException ex) {
            Logger.getLogger(UpdateStudentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        cb_mgtcls.getItems().setAll(mgtcls_list);
        for (int i = 0; i < mgtcls_list.size(); i++) {
            if (this.stu_edit.getMGTCLS_CODE().compareTo(mgtcls_list.get(i).getMGTCLS_CODE()) == 0) {
                cb_mgtcls.getSelectionModel().select(i);
            }
        }
        
        cb_mgtcls.setDisable(false);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txt_name.setText(this.usr.getEmp().getEMP_LNAME() + " " + this.usr.getEmp().getEMP_MNAME() + " " + this.usr.getEmp().getEMP_FNAME());
        String[] gender_list = {"Nam", "Nữ"};

        in_msv.setText(String.valueOf(this.stu_edit.getSTU_NUM()));
        in_lname.setText(this.stu_edit.getSTU_LNAME());
        in_mname.setText(this.stu_edit.getSTU_MNAME());
        in_fname.setText(this.stu_edit.getSTU_FNAME());
        in_email.setText(this.stu_edit.getSTU_EMAIL());
        dp_dob.setValue(this.stu_edit.getSTU_DOB().toLocalDate());
        cb_gender.getItems().setAll(gender_list);
        for (int i = 0; i < gender_list.length; i++) {
            if (this.stu_edit.getSTU_GENDER().compareTo(gender_list[i]) == 0) {
                cb_gender.getSelectionModel().select(i);
            }
        }
        
        List<Dept> dept_list = new ArrayList<>();
        try {
            this.openConnection();
            PreparedStatement preSql = this.conn.prepareStatement("SELECT * FROM department");
            ResultSet rs = this.getData(preSql);
            while (rs.next()) {
                Dept dept = new Dept(
                        rs.getString("DEPT_CODE"),
                        rs.getString("DEPT_NAME"),
                        rs.getInt("PROF_NUM")
                );
                dept_list.add(dept);
            }
            this.closeConnection(preSql);
        } catch (SQLException ex) {
            Logger.getLogger(UpdateStudentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        cb_dept_stu.getItems().setAll(dept_list);
        for (int i = 0; i < dept_list.size(); i++) {
            if (this.stu_edit.getDEPT_CODE().compareTo(dept_list.get(i).getDEPT_CODE()) == 0) {
                cb_dept_stu.getSelectionModel().select(i);
            }
        }
        
        List<Mgtcls> mgtcls_list = new ArrayList<>();
        try {
            this.openConnection();
            PreparedStatement preSql = this.conn.prepareStatement("SELECT * FROM `management_class`\n" +
                                                                  "WHERE PROF_NUM IN (SELECT PROF_NUM FROM professor\n" +
                                                                  "                   WHERE DEPT_CODE = ?)");
            preSql.setString(1, this.stu_edit.getDEPT_CODE());
            ResultSet rs = this.getData(preSql);
            while (rs.next()) {
                Mgtcls mgtcls = new Mgtcls(
                        rs.getString("MGTCLS_CODE"),                        
                        rs.getInt("PROF_NUM"),
                        rs.getString("SEM_CODE")
                );
                mgtcls_list.add(mgtcls);
            }
            this.closeConnection(preSql);
        } catch (SQLException ex) {
            Logger.getLogger(UpdateStudentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        cb_mgtcls.getItems().setAll(mgtcls_list);
        
        for (int i = 0; i < mgtcls_list.size(); i++) {
            if (this.stu_edit.getMGTCLS_CODE().compareTo(mgtcls_list.get(i).getMGTCLS_CODE()) == 0) {
                cb_mgtcls.getSelectionModel().select(i);
            }
        }
    }
    
    @FXML
    public boolean checkDob() {
        if (LocalDate.now().getYear() - dp_dob.getValue().getYear() < 8 || LocalDate.now().getYear() - dp_dob.getValue().getYear() > 150) {
            err_dob.setVisible(true);
            return false;
        } else {
            err_dob.setVisible(false);
            return true;
        }
    }
}
