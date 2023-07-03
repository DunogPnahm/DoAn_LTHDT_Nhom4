package Controller;

import Model.Dept;
import Model.Emp;
import Model.Prof;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class AddProfController extends AdminDeptEmpInfoController {

    @FXML
    private Pane Cancel_Ic;

    @FXML
    private Pane Confirm_Ic;

    @FXML
    private JFXButton Logout_btn;

    @FXML
    private JFXButton Logout_btn1;

    @FXML
    private ComboBox<Dept> cb_dept_prof;

    @FXML
    private ComboBox<String> cb_pos;

    @FXML
    private TextField in_rank;

    @FXML
    private TextField in_spec;

    @FXML
    private DatePicker dp_dob;

    @FXML
    private DatePicker dp_hd;

    @FXML
    private TextField in_code;

    @FXML
    private TextField in_email;

    @FXML
    private TextField in_fname;

    @FXML
    private TextField in_lname;

    @FXML
    private TextField in_mname;

    @FXML
    private Label lb_err;

    @FXML
    private Text txt_name;

    @FXML
    void cancel(MouseEvent event) {
        try {
            App.setRoot("AdminDeptEmpInfo");
        } catch (IOException ex) {
            Logger.getLogger(UpdateProfController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void submit(MouseEvent event) {
        if (in_code.getText()=="" || in_lname.getText()=="" || in_fname.getText()=="" || in_email.getText()=="" || cb_pos.getValue()==null || cb_dept_prof.getValue()==null || in_rank.getText()=="" || in_spec.getText()=="" || dp_dob.getValue()==null || dp_hd.getValue()==null) {
            lb_err.setText("Chưa điền đủ thông tin !");
            lb_err.setVisible(true);
            return;
        } else {
            lb_err.setVisible(false);
        }
        
        PreparedStatement preSql;
        try {
            this.openConnection();
            preSql = this.conn.prepareStatement("SELECT *\n" +
                "FROM professor INNER JOIN employee\n" +
                "ON professor.PROF_NUM = employee.EMP_NUM\n" +
                "WHERE PROF_NUM = ? OR EMP_EMAIL = ?;");
            preSql.setInt(1, Integer.parseInt(in_code.getText()));
            preSql.setString(2, in_email.getText());
            
            ResultSet rs = this.getData(preSql);
            if (rs.next()) {
                lb_err.setText("Mã giảng viên hoặc email đã tồn tại!");
                lb_err.setVisible(true);
                return;
            } else {
                lb_err.setVisible(false);
            }
            
            Prof prof_new = new Prof(
                Integer.parseInt(in_code.getText()), 
                in_spec.getText(),
                cb_dept_prof.getSelectionModel().getSelectedItem().getDEPT_CODE(),
                in_rank.getText(),
                in_lname.getText(), 
                in_fname.getText(), 
                in_mname.getText(), 
                cb_pos.getSelectionModel().getSelectedItem(), 
                Date.valueOf(dp_hd.getValue()),
                Date.valueOf(dp_dob.getValue()), 
                in_email.getText()
            );
            this.openConnection();
            preSql = this.conn.prepareStatement("INSERT INTO employee (EMP_NUM, EMP_LNAME, EMP_FNAME, EMP_MNAME, EMP_EMAIL, EMP_JOBCODE, EMP_HIREDATE, EMP_DOB) VALUES "
                    + "(?, ?, ?, ?, ?, ?, ?, ?)");
            preSql.setInt(1, prof_new.getEMP_NUM());
            preSql.setString(2, prof_new.getEMP_LNAME());
            preSql.setString(3, prof_new.getEMP_FNAME());
            preSql.setString(4, prof_new.getEMP_MNAME());
            preSql.setString(5, prof_new.getEMP_EMAIL());
            preSql.setString(6, prof_new.getEMP_JOBCODE());
            preSql.setDate(7, prof_new.getEMP_HIREDATE());
            preSql.setDate(8, prof_new.getEMP_DOB());

            int result = this.postData(preSql);
            this.closeConnection(preSql);
            
            this.openConnection();
            preSql = this.conn.prepareStatement("INSERT INTO professor (PROF_SPECIALTY, PROF_RANK, DEPT_CODE, PROF_NUM) VALUES "
                    + "(?, ?, ?, ?)");
            preSql.setString(1, prof_new.getPROF_SPECIALTY());
            preSql.setString(2, prof_new.getPROF_RANK());
            preSql.setString(3, prof_new.getDEPT_CODE());
            preSql.setInt(4, prof_new.getPROF_NUM());

            int result1 = this.postData(preSql);
            this.closeConnection(preSql);
            App.setRoot("AdminDeptEmpInfo");
        } catch (SQLException | IOException ex) {
            Logger.getLogger(StuMainMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txt_name.setText(this.usr.getEmp().getEMP_LNAME() + " " + this.usr.getEmp().getEMP_MNAME() + " " + this.usr.getEmp().getEMP_FNAME());
        String[] pos_list = {"ADMIN", "PROF", "LIBRA", "JANT"};
        
        cb_pos.getItems().setAll(pos_list);
        cb_pos.getSelectionModel().select(1);
        
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
        cb_dept_prof.getItems().setAll(dept_list);
    }

    
}
