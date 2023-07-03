package Controller;

import static Controller.AdminScheController.mgtcls_edit;
import Model.Emp;
import Model.Dept;
import Model.Sem;
import Model.Stu;
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.util.Callback;

public class AddDeptController extends AdminDeptEmpInfoController {

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
    private ComboBox<Emp> cb_prof;

    @FXML
    private TextField in_code;

    @FXML
    private TextField in_name;
    
    @FXML
    private Text txt_name;

    @FXML
    private Label lb_err;
    
    @FXML
    void cancel(MouseEvent event) {
        try {
            App.setRoot("AdminDeptEmpInfo");
        } catch (IOException ex) {
            Logger.getLogger(AddDeptController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void submit(MouseEvent event) {  
        if (in_code.getText()=="" || in_name.getText()=="" || cb_prof.getValue()==null) {
            lb_err.setText("Chưa điền đủ thông tin !");
            lb_err.setVisible(true);
            return;
        } else {
            lb_err.setVisible(false);
        }
        
        PreparedStatement preSql;
        try {
            this.openConnection();
            preSql = this.conn.prepareStatement("SELECT * FROM department WHERE DEPT_CODE = ?");
            preSql.setInt(1, Integer.parseInt(in_code.getText()));
            
            ResultSet rs = this.getData(preSql);
            if (rs.next()) {
                lb_err.setText("Mã khoa đã tồn tại !");
                lb_err.setVisible(true);
                return;
            } else {
                lb_err.setVisible(false);
            }
        
            Dept dept_new = new Dept(
                in_code.getText(), 
                in_name.getText(),
                cb_prof.getSelectionModel().getSelectedItem().getEMP_NUM()
            );
            this.openConnection();
            preSql = this.conn.prepareStatement("INSERT INTO department (DEPT_CODE, DEPT_NAME, PROF_NUM) VALUES "
                                                + "(?, ?, ?)");
            preSql.setString(1, dept_new.getDEPT_CODE());
            preSql.setString(2, dept_new.getDEPT_NAME());
            preSql.setInt(3, dept_new.getPROF_NUM());

            int result = this.postData(preSql);
            if (result > 0) System.out.println("Thêm thành công");
            App.setRoot("AdminDeptEmpInfo");
        } catch (SQLException | IOException ex) {
            Logger.getLogger(AddDeptController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txt_name.setText(this.usr.getEmp().getEMP_LNAME() + " " + this.usr.getEmp().getEMP_MNAME() + " " + this.usr.getEmp().getEMP_FNAME());
        List<Emp> emp_list = new ArrayList<>();
        PreparedStatement preSql;
        try {
            this.openConnection();
            preSql = this.conn.prepareStatement("SELECT * FROM employee WHERE EMP_NUM IN " +
                                                "(SELECT PROF_NUM FROM professor)");
            ResultSet emp_set = this.getData(preSql);
            while (emp_set.next()) {
                Emp emp = new Emp(
                        emp_set.getInt("EMP_NUM"),
                        emp_set.getString("EMP_LNAME"),
                        emp_set.getString("EMP_FNAME"),
                        emp_set.getString("EMP_MNAME"),
                        emp_set.getString("EMP_EMAIL"),
                        emp_set.getString("EMP_JOBCODE"),
                        emp_set.getDate("EMP_HIREDATE"),
                        emp_set.getDate("EMP_DOB")
                );
                emp_list.add(emp);
            }
            this.closeConnection(preSql);
        } catch (SQLException ex) {
            Logger.getLogger(AddDeptController.class.getName()).log(Level.SEVERE, null, ex);
        }
        cb_prof.getItems().setAll(emp_list);
    }
    
    

}
