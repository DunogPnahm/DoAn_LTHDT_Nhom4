package Controller;

import static Controller.AdminScheController.mgtcls_edit;
import Model.Emp;
import Model.Mgtcls;
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

public class UpdateMgtclsController extends AdminScheController {

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
    private ComboBox<Sem> cb_sem;

    @FXML
    private TextField in_code;
    
    @FXML
    private Text txt_name;
    
    @FXML
    private Label lb_err;

    @FXML
    void cancel(MouseEvent event) {
        try {
            App.setRoot("AdminSche");
        } catch (IOException ex) {
            Logger.getLogger(UpdateMgtclsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void submit(MouseEvent event) {
        if (in_code.getText()=="" || cb_prof.getValue()==null || cb_sem.getValue()==null) {
            lb_err.setText("Chưa điền đủ thông tin !");
            lb_err.setVisible(true);
            return;
        } else {
            lb_err.setVisible(false);
        }
        
        Mgtcls mgtcls_new = new Mgtcls(
            in_code.getText(), 
            cb_prof.getSelectionModel().getSelectedItem().getEMP_NUM(), 
            cb_sem.getSelectionModel().getSelectedItem().getSEM_CODE()
        );
        
        PreparedStatement preSql;
        try {
            this.openConnection();
            preSql = this.conn.prepareStatement("UPDATE management_class SET "
                    + "PROF_NUM = ?, "
                    + "SEM_CODE = ?, "
                    + "WHERE MGTCLS_CODE = ?");
            preSql.setInt(1, mgtcls_new.getPROF_NUM());
            preSql.setString(2, mgtcls_new.getSEM_CODE());
            preSql.setString(3, mgtcls_new.getMGTCLS_CODE());

            int result = this.postData(preSql);
            if (result > 0) System.out.println("Sửa thành công");
            App.setRoot("AdminSche");
        } catch (SQLException | IOException ex) {
            Logger.getLogger(UpdateStudentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txt_name.setText(this.usr.getEmp().getEMP_LNAME() + " " + this.usr.getEmp().getEMP_MNAME() + " " + this.usr.getEmp().getEMP_FNAME());
        in_code.setText(this.mgtcls_edit.getMGTCLS_CODE());
        
        List<Emp> emp_list = new ArrayList<>();
        PreparedStatement preSql;
        try {
            this.openConnection();
            preSql = this.conn.prepareStatement("SELECT * FROM employee WHERE EMP_NUM IN " +
                                                "(SELECT PROF_NUM FROM professor " +
                                                "WHERE DEPT_CODE IN (SELECT DEPT_CODE FROM professor " +
                                                "                   WHERE PROF_NUM = ?))");
            preSql.setInt(1, this.mgtcls_edit.getPROF_NUM());
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
            Logger.getLogger(UpdateMgtclsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        cb_prof.getItems().setAll(emp_list);
        for (int i = 0; i < emp_list.size(); i++) {
            if (this.mgtcls_edit.getPROF_NUM()== (emp_list.get(i).getEMP_NUM())) {
                cb_prof.getSelectionModel().select(i);
            }
        }
        
        List<Sem> sem_list = new ArrayList<>();
        try {
            this.openConnection();
            preSql = this.conn.prepareStatement("SELECT * FROM semester");
            ResultSet sem_set = this.getData(preSql);
            while (sem_set.next()) {
                Sem sem = new Sem(
                        sem_set.getString("SEM_CODE"),
                        sem_set.getInt("SEM_YEAR"),
                        sem_set.getDate("SEM_START_DATE"),
                        sem_set.getDate("SEM_END_DATE")
                );
                sem_list.add(sem);
            }
            this.closeConnection(preSql);
        } catch (SQLException ex) {
            Logger.getLogger(UpdateMgtclsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        cb_sem.getItems().setAll(sem_list);
        for (int i = 0; i < sem_list.size(); i++) {
            if (this.mgtcls_edit.getSEM_CODE().compareTo(sem_list.get(i).getSEM_CODE()) == 0) {
                cb_sem.getSelectionModel().select(i);
            }
        }
    }
    
    

}
