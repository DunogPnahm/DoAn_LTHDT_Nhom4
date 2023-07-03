package Controller;

import Model.Cls;
import Model.Rem;
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

public class UpdateRemController extends AdminRemController {

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
            App.setRoot("AdminRem");
        } catch (IOException ex) {
            Logger.getLogger(UpdateCourseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void submit(MouseEvent event) {
        if (in_code.getText()=="" || in_desc.getText()=="") {
            lb_err.setText("Chưa điền đủ thông tin !");
            lb_err.setVisible(true);
            return;
        } else {
            lb_err.setVisible(false);
        }
        
        PreparedStatement preSql;
        try {
            this.openConnection();
        
            Rem rem_new = new Rem(
                        Integer.parseInt(in_code.getText()),
                        in_desc.getText(),
                        this.usr.getEmp().getEMP_NUM()
                );
            this.openConnection();
            preSql = this.conn.prepareStatement("UPDATE reminder SET "
                    + "REM_DESC = ?, "
                    + "EMP_NUM = ? "
                    + "WHERE REM_NUM = ?");
            preSql.setString(1, rem_new.getREM_DESC());
            preSql.setInt(2, rem_new.getEMP_NUM());
            preSql.setInt(3, rem_new.getREM_NUM());

            int result = this.postData(preSql);
            App.setRoot("AdminRem");
        } catch (SQLException | IOException ex) {
            Logger.getLogger(UpdateRemController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txt_name.setText(this.usr.getEmp().getEMP_LNAME() + " " + this.usr.getEmp().getEMP_MNAME() + " " + this.usr.getEmp().getEMP_FNAME());
        
        in_code.setText(Integer.toString(this.rem_edit.getREM_NUM()));
        in_desc.setText(this.rem_edit.getREM_DESC());
        
    }
    
    
}
