package Controller;

import Model.Crs;
import Model.Dept;
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

public class AddCourseController extends AdminScheController {

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
    private ComboBox<String> cb_com;

    @FXML
    private ComboBox<String> cb_cre;

    @FXML
    private ComboBox<Dept> cb_dept_crs;

    @FXML
    private TextField in_code;

    @FXML
    private TextField in_desc;

    @FXML
    private TextField in_name;

    @FXML
    private Label lb_err;

    @FXML
    private Text txt_name;

    @FXML
    void cancel(MouseEvent event) {
        try {
            App.setRoot("AdminSche");
        } catch (IOException ex) {
            Logger.getLogger(UpdateCourseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void submit(MouseEvent event) {
        if (in_code.getText()=="" || in_name.getText()=="" || in_desc.getText()=="" || cb_dept_crs.getValue()==null || cb_cre.getValue()==null || cb_com.getValue()==null) {
            lb_err.setText("Chưa điền đủ thông tin !");
            lb_err.setVisible(true);
            return;
        } else {
            lb_err.setVisible(false);
        }
        
        PreparedStatement preSql;
        try {
            this.openConnection();
            preSql = this.conn.prepareStatement("SELECT * FROM course WHERE CRS_CODE = ?;");
            preSql.setString(1, in_code.getText());
            
            ResultSet rs = this.getData(preSql);
            if (rs.next()) {
                lb_err.setText("Mã môn học đã tồn tại!");   
                lb_err.setVisible(true);
                return;
            } else {
                lb_err.setVisible(false);
            }
            
            Crs crs_new = new Crs(                      
                in_code.getText(),
                cb_dept_crs.getSelectionModel().getSelectedItem().getDEPT_CODE(),
                in_name.getText(),
                Integer.parseInt(cb_cre.getSelectionModel().getSelectedItem()),
                in_desc.getText(),
                cb_com.getSelectionModel().getSelectedItem()
            );
            this.openConnection();
            preSql = this.conn.prepareStatement("INSERT INTO course(CRS_CODE, DEPT_CODE, CRS_TITLE, CRS_CREDIT, CRS_COMPULSORY, CRS_DESC) VALUES "
                    + "(?, ?, ?, ?, ?, ?)");
            preSql.setString(1, crs_new.getCRS_CODE());
            preSql.setString(2, crs_new.getDEPT_CODE());
            preSql.setString(3, crs_new.getCRS_TITLE());
            preSql.setInt(4, crs_new.getCRS_CREDIT());
            preSql.setString(5, crs_new.getCRS_COMPULSORY());
            preSql.setString(6, crs_new.getCRS_DESC());

            int result = this.postData(preSql);
            App.setRoot("AdminSche");
        } catch (SQLException | IOException ex) {
            Logger.getLogger(UpdateCourseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txt_name.setText(this.usr.getEmp().getEMP_LNAME() + " " + this.usr.getEmp().getEMP_MNAME() + " " + this.usr.getEmp().getEMP_FNAME());
        String[] cre_list = {"1", "2", "3"};
        String[] com_list = {"YES", "NO"};
        
        cb_cre.getItems().setAll(cre_list);
        
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
            Logger.getLogger(UpdateCourseController.class.getName()).log(Level.SEVERE, null, ex);
        }
        cb_dept_crs.getItems().setAll(dept_list);
        
        cb_com.getItems().setAll(com_list);
    }
    
}
