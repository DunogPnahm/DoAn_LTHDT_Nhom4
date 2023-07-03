package Controller;

import Model.Cls;
import Model.Crs;
import Model.Dept;
import Model.Task;
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

public class UpdateTaskController extends ProfTaskController {

    private List<Cls> cls_list = new ArrayList<>();
    
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
    private ComboBox<String> cb_cls_task;

    @FXML
    private TextField in_code;

    @FXML
    private TextField in_desc;
    
    @FXML
    private Label lb_err;

    @FXML
    private Text txt_name;

    @FXML
    void cancel(MouseEvent event) {
        try {
            App.setRoot("ProfTask");
        } catch (IOException ex) {
            Logger.getLogger(UpdateCourseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void submit(MouseEvent event) {
        if (in_code.getText()=="" || in_desc.getText()=="" || cb_cls_task.getValue()==null) {
            lb_err.setText("Chưa điền đủ thông tin !");
            lb_err.setVisible(true);
            return;
        } else {
            lb_err.setVisible(false);
        }
        
        PreparedStatement preSql;
        try {
            this.openConnection();
        
            Task task_new = new Task(
                        in_code.getText(),
                        in_desc.getText(),
                        cb_cls_task.getValue()
                );
            this.openConnection();
            preSql = this.conn.prepareStatement("UPDATE task SET "
                    + "TASK_DESC = ?, "
                    + "CLS_CODE = ? "
                    + "WHERE TASK_CODE = ?");
            preSql.setString(1, task_new.getTASK_DESC());
            preSql.setString(2, task_new.getCLS_CODE());
            preSql.setString(3, task_new.getTASK_CODE());

            int result = this.postData(preSql);
            App.setRoot("ProfTask");
        } catch (SQLException | IOException ex) {
            Logger.getLogger(UpdateTaskController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private String deptname;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txt_name.setText(this.usr.getProf().getEMP_LNAME() + " " + this.usr.getProf().getEMP_MNAME() + " " + this.usr.getProf().getEMP_FNAME());
        try {
            this.openConnection();
            this.openConnection();
            PreparedStatement preSql = this.conn.prepareStatement("SELECT DISTINCT professor.PROF_NUM, DEPT_NAME\n" +
                "  FROM department INNER JOIN professor\n" +
                "  ON department.DEPT_CODE = professor.DEPT_CODE WHERE professor.PROF_NUM = ?;"
            );
            preSql.setInt(1, this.usr.getProf().getPROF_NUM());
            ResultSet DeptName = this.getData(preSql);
            if(DeptName.next())
            {
                deptname = DeptName.getString("DEPT_NAME");
            }
            this.closeConnection(preSql);
        } catch (SQLException ex) {
            Logger.getLogger(StuScheController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ProfDept.setText(deptname);
        
        in_code.setText(this.task_edit.getTASK_CODE());
        in_desc.setText(this.task_edit.getTASK_DESC());
        
        List<String> clsCode_list = new ArrayList<>();
        try {
            this.openConnection();
            PreparedStatement preSql = this.conn.prepareStatement("SELECT * FROM class WHERE PROF_NUM = ?");
            preSql.setInt(1, this.usr.getProf().getPROF_NUM());
            ResultSet rs = this.getData(preSql);
            while (rs.next()) {
                Cls cls = new Cls(
                    rs.getString("CLS_CODE"),
                    rs.getString("CLS_TIME"),
                    rs.getString("CRS_CODE"),
                    rs.getString("ROOM_CODE"),
                    rs.getString("SEM_CODE"),
                    rs.getInt("PROF_NUM")
                );
                cls_list.add(cls);
                clsCode_list.add(cls.getCLS_CODE());
            }
            this.closeConnection(preSql);
        } catch (SQLException ex) {
            Logger.getLogger(UpdateTaskController.class.getName()).log(Level.SEVERE, null, ex);
        }
        cb_cls_task.getItems().setAll(clsCode_list);
        for (int i = 0; i < cls_list.size(); i++) {
            if (this.task_edit.getCLS_CODE().compareTo(cls_list.get(i).getCLS_CODE()) == 0) {
                cb_cls_task.getSelectionModel().select(i);
            }
        }
    }
    
    
}
