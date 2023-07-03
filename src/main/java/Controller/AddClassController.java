package Controller;

import Model.Cls;
import Model.Crs;
import Model.Dept;
import Model.Mgtcls;
import Model.Prof;
import Model.Room;
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

public class AddClassController extends AdminScheController {

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
    private ComboBox<Crs> cb_crs_crs;

    @FXML
    private ComboBox<String> cb_prof;

    @FXML
    private ComboBox<String> cb_room;

    @FXML
    private ComboBox<Sem> cb_sem;

    @FXML
    private TextField in_code;

    @FXML
    private TextField in_time;
    
    @FXML
    private Text txt_name;
    
    @FXML
    private Label lb_err;

    @FXML
    void cancel(MouseEvent event) {
        try {
            App.setRoot("AdminSche");
        } catch (IOException ex) {
            Logger.getLogger(UpdateClassController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void submit(MouseEvent event) {
        if (in_code.getText()=="" || in_time.getText()=="" || cb_crs_crs.getValue()==null || cb_room.getValue()==null || cb_sem.getValue()==null || cb_prof.getValue()==null) {
            lb_err.setText("Chưa điền đủ thông tin!");
            lb_err.setVisible(true);
            return;
        } else {
            lb_err.setVisible(false);
        }
        
        PreparedStatement preSql;
        try {
            this.openConnection();
            preSql = this.conn.prepareStatement("SELECT * \n" +
                "FROM class\n" +
                "WHERE CLS_CODE = ? OR ((ROOM_CODE = ? AND CLS_TIME = ? AND SEM_CODE = ?) OR (PROF_NUM = ? AND CLS_TIME = ? AND SEM_CODE = ?));");
            preSql.setString(1, in_code.getText());
            preSql.setString(2, cb_room.getValue());
            preSql.setString(3, in_time.getText());
            preSql.setString(4, cb_sem.getValue().getSEM_CODE());
            preSql.setInt(5, Integer.parseInt(cb_prof.getValue()));
            preSql.setString(6, in_time.getText());
            preSql.setString(7, cb_sem.getValue().getSEM_CODE());
            
            ResultSet rs = this.getData(preSql);
            if (rs.next()) {
                lb_err.setText("Mã lớp, phòng học, giảng viên hoặc giờ học không hợp lệ!");
                lb_err.setVisible(true);
                return;
            } else {
                lb_err.setVisible(false);
            }
        
            Cls cls_new = new Cls(
                in_code.getText(),
                in_time.getText(),
                cb_crs_crs.getValue().getCRS_CODE(),
                cb_room.getValue(),
                cb_sem.getValue().getSEM_CODE(),
                Integer.parseInt(cb_prof.getValue())
            );
            this.openConnection();
            preSql = this.conn.prepareStatement("INSERT INTO class(PROF_NUM, CLS_TIME, CRS_CODE, ROOM_CODE, SEM_CODE, CLS_CODE) VALUES "
                    + "(?, ?, ?, ?, ?, ?)");
            preSql.setInt(1, cls_new.getPROF_NUM());
            preSql.setString(2, cls_new.getCLS_TIME());
            preSql.setString(3, cls_new.getCRS_CODE());
            preSql.setString(4, cls_new.getROOM_CODE());
            preSql.setString(5, cls_new.getSEM_CODE());
            preSql.setString(6, cls_new.getCLS_CODE());

            int result = this.postData(preSql);
            App.setRoot("AdminSche");
        } catch (SQLException | IOException ex) {
            Logger.getLogger(UpdateClassController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txt_name.setText(this.usr.getEmp().getEMP_LNAME() + " " + this.usr.getEmp().getEMP_MNAME() + " " + this.usr.getEmp().getEMP_FNAME());
        
        List<Crs> crs_list = new ArrayList<>();
        try {
            this.openConnection();
            PreparedStatement preSql = this.conn.prepareStatement("SELECT * FROM course");
            ResultSet rs = this.getData(preSql);
            while (rs.next()) {
                Crs course = new Crs(                      
                        rs.getString("CRS_CODE"),
                        rs.getString("DEPT_CODE"),
                        rs.getString("CRS_TITLE"),
                        rs.getInt("CRS_CREDIT"),
                        rs.getString("CRS_DESC"),
                        rs.getString("CRS_COMPULSORY")
                );
                crs_list.add(course);
            }
            this.closeConnection(preSql);
        } catch (SQLException ex) {
            Logger.getLogger(UpdateClassController.class.getName()).log(Level.SEVERE, null, ex);
        }
        cb_crs_crs.getItems().setAll(crs_list);
        
        List<String> room_list = new ArrayList<>();
        try {
            this.openConnection();
            PreparedStatement preSql = this.conn.prepareStatement("SELECT * FROM room;");
            ResultSet rs = this.getData(preSql);
            while (rs.next()) {
                room_list.add(rs.getString("ROOM_CODE"));
            }
            this.closeConnection(preSql);
        } catch (SQLException ex) {
            Logger.getLogger(UpdateClassController.class.getName()).log(Level.SEVERE, null, ex);
        }
        cb_room.getItems().setAll(room_list);
        
        List<Sem> sem_list = new ArrayList<>();
        try {
            this.openConnection();
            PreparedStatement preSql = this.conn.prepareStatement("SELECT * FROM semester;");
            ResultSet rs = this.getData(preSql);
            while (rs.next()) {
                Sem sem = new Sem(
                        rs.getString("SEM_CODE"),
                        rs.getInt("SEM_YEAR"),
                        rs.getDate("SEM_START_DATE"),
                        rs.getDate("SEM_END_DATE")
                );
                sem_list.add(sem);
            }
            this.closeConnection(preSql);
        } catch (SQLException ex) {
            Logger.getLogger(UpdateClassController.class.getName()).log(Level.SEVERE, null, ex);
        }
        cb_sem.getItems().setAll(sem_list);
        
        List<String> profcode_list = new ArrayList<>();
        try {
            this.openConnection();
            PreparedStatement preSql = this.conn.prepareStatement("SELECT * FROM professor;");
            ResultSet rs = this.getData(preSql);
            while (rs.next()) {
                profcode_list.add(Integer.toString(rs.getInt("PROF_NUM")));
            }
            this.closeConnection(preSql);
        } catch (SQLException ex) {
            Logger.getLogger(UpdateClassController.class.getName()).log(Level.SEVERE, null, ex);
        }
        cb_prof.getItems().setAll(profcode_list);
    }
}
