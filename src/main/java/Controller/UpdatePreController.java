package Controller;

import Model.Crs;
import Model.Dept;
import Model.Pre;
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

public class UpdatePreController extends AdminScheController {

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
    private ComboBox<Crs> cb_pre;

    @FXML
    private Label lb_err;

    @FXML
    private Text txt_name;

    String preTake;
    
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
        if (cb_pre.getValue()==null || cb_crs_crs.getValue()==null) {
            lb_err.setText("Chưa điền đủ thông tin !");
            lb_err.setVisible(true);
            return;
        } else {
            lb_err.setVisible(false);
        }
        
        PreparedStatement preSql;
        try {
            this.openConnection();
        
            Pre pre_new = new Pre(                      
                cb_pre.getValue().getCRS_CODE(),
                cb_crs_crs.getValue().getCRS_CODE()
            );
            this.openConnection();
            preSql = this.conn.prepareStatement("UPDATE prerequisite SET "
                    + "PRE_TAKE = ? "
                    + "WHERE CRS_REQ_PRE = ? AND PRE_TAKE = ?");
            preSql.setString(1, pre_new.getPRE_TAKE());
            preSql.setString(2, pre_new.getCRS_REQ_PRE());
            preSql.setString(3, preTake);

            int result = this.postData(preSql);
            App.setRoot("AdminSche");
        } catch (SQLException | IOException ex) {
            Logger.getLogger(UpdateCourseController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(UpdateCourseController.class.getName()).log(Level.SEVERE, null, ex);
        }
        cb_crs_crs.getItems().setAll(crs_list);
        for (int i = 0; i < crs_list.size(); i++) {
            if (this.pre_edit.getCRS_REQ_PRE().compareTo(crs_list.get(i).getCRS_CODE()) == 0) {
                cb_crs_crs.getSelectionModel().select(i);
            }
        }
        
        cb_pre.getItems().setAll(crs_list);
        
        for (int i = 0; i < crs_list.size(); i++) {
            if (this.pre_edit.getPRE_TAKE().compareTo(crs_list.get(i).getCRS_CODE()) == 0) {
                cb_pre.getSelectionModel().select(i);
            }
        }
        preTake = cb_pre.getValue().getCRS_CODE();
    }
    
}
