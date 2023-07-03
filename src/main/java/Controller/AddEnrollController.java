package Controller;

import static Controller.BaseController.usr;
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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class AddEnrollController extends ProfMainMenuController {

    private String deptname;
    
    @FXML
    private Pane Avatar;
    
    @FXML
    private Label lb_crserr;

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
    private Text ProfDept;

    @FXML
    private Text ProfName;

    @FXML
    private Pane UsrBckgr;

    @FXML
    private AnchorPane body;

    @FXML
    private DatePicker dp_time;

    @FXML
    private TextField in_cls;

    @FXML
    private TextField in_stu;

    @FXML
    private Label lb_err;
    
    @FXML
    private Label lb_generr;

    @FXML
    private TextField tf_assess;
    @FXML
    private TextField tf_midterm;
    @FXML
    private TextField tf_final;
    
    @FXML
    private Label err_time;
    
    @FXML
    void cancel(MouseEvent event) {
        try {
            App.setRoot("ProfMainMenu");
        } catch (IOException ex) {
            Logger.getLogger(UpdateEnrollController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void submit(MouseEvent event) {
        if (tf_assess.getText() == "" || tf_midterm.getText() == "" || tf_final.getText() == ""
                || Float.parseFloat(tf_assess.getText()) < 0 || Float.parseFloat(tf_assess.getText()) > 10
                || Float.parseFloat(tf_midterm.getText()) < 0 || Float.parseFloat(tf_midterm.getText()) > 10
                || Float.parseFloat(tf_final.getText()) < 0 || Float.parseFloat(tf_final.getText()) > 10) {
            lb_generr.setVisible(true);
            return;
        } else {
            lb_generr.setVisible(false);
        }
        if (!checkTime()) 
        {
            return;
        }
        
        PreparedStatement preSql;
        try {
            this.openConnection();
            
            preSql = this.conn.prepareStatement("SELECT *\n" +
                "FROM prerequisite INNER JOIN class\n" +
                "ON prerequisite.CRS_REQ_PRE = class.CRS_CODE\n" +
                "WHERE CLS_CODE = ?;");
            preSql.setString(1, in_cls.getText());
            ResultSet rs = this.getData(preSql);
            if(rs.next())
            {
                preSql = this.conn.prepareStatement("SELECT DISTINCT A.*, PRE_TAKE, CRS_REQ_PRE, ROUND(AVG(ENROLL_FINAL*0.7 + ENROLL_ASSESS*0.15 + ENROLL_MIDTERM*0.15),2) AS TB\n" +
                "FROM (SELECT enroll.*, CRS_CODE\n" +
                "FROM ENROLL INNER JOIN class\n" +
                "ON enroll.CLS_CODE = class.CLS_CODE) AS A INNER JOIN prerequisite\n" +
                "ON A.CRS_CODE = prerequisite.PRE_TAKE\n" +
                "WHERE A.STU_NUM = ? AND CRS_REQ_PRE = ?\n" +
                "GROUP BY A.STU_NUM, A.ENROLL_FINAL, A.ENROLL_ASSESS, A.ENROLL_MIDTERM, A.CLS_CODE, A.CRS_CODE, PRE_TAKE, CRS_REQ_PRE\n" +
                "HAVING AVG(ENROLL_FINAL*0.7 + ENROLL_ASSESS*0.15 + ENROLL_MIDTERM*0.15) >= 4;");
                preSql.setInt(1, Integer.parseInt(in_stu.getText()));
                preSql.setString(2, rs.getString("CRS_REQ_PRE"));
                ResultSet pre = this.getData(preSql);
                if(pre.next())
                {
                    lb_crserr.setVisible(true);
                    return;
                }
                else
                {
                    lb_crserr.setVisible(false);
                }
            }
            
            preSql = this.conn.prepareStatement("INSERT INTO enroll (ENROLL_ASSESS, ENROLL_MIDTERM, ENROLL_FINAL, STU_NUM, CLS_CODE, ENROLL_DATE) VALUES "
                    + "(?, ?, ?, ?, ?, ?)");
            preSql.setFloat(1, Float.parseFloat(tf_assess.getText()));
            preSql.setFloat(2, Float.parseFloat(tf_midterm.getText()));
            preSql.setFloat(3, Float.parseFloat(tf_final.getText()));
            preSql.setInt(4, Integer.parseInt(in_stu.getText()));
            preSql.setString(5, in_cls.getText());
            preSql.setDate(6, Date.valueOf(dp_time.getValue()));
            int result = this.postData(preSql);
            App.setRoot("ProfMainMenu");
        } catch (SQLException | IOException ex) {
            Logger.getLogger(AddEnrollController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void checkNumber() {
        if (!tf_assess.getText().matches("\\d*(\\.\\d*)?")) {
            tf_assess.setText(tf_assess.getText().replaceAll("[^\\d.]", ""));
            tf_assess.positionCaret(tf_assess.getLength());
        }
        if (!tf_midterm.getText().matches("\\d*(\\.\\d*)?")) {
            tf_midterm.setText(tf_midterm.getText().replaceAll("[^\\d.]", ""));
            tf_midterm.positionCaret(tf_midterm.getLength());
        }
        if (!tf_final.getText().matches("\\d*(\\.\\d*)?")) {
            tf_final.setText(tf_final.getText().replaceAll("[^\\d.]", ""));
            tf_final.positionCaret(tf_final.getLength());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            this.openConnection();
            PreparedStatement preSql = this.conn.prepareStatement("SELECT DISTINCT professor.PROF_NUM, DEPT_NAME\n" +
                "FROM department INNER JOIN professor\n" +
                "ON department.DEPT_CODE = professor.DEPT_CODE WHERE professor.PROF_NUM = ?;"
            );
            preSql.setInt(1, this.usr.getProf().getPROF_NUM());
            ResultSet DeptName = this.getData(preSql);
            if(DeptName.next())
            {
                deptname = DeptName.getString("DEPT_NAME");
            }
        } catch (SQLException ex) {
            Logger.getLogger(StuMainMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ProfName.setText(usr.getProf().getEMP_LNAME() + " " + usr.getProf().getEMP_MNAME() + " " + usr.getProf().getEMP_FNAME());
        ProfDept.setText(deptname);
    }
    
    @FXML
    public boolean checkTime() {
        if (dp_time.getValue().getYear() - LocalDate.now().getYear() > 1 || dp_time.getValue().getYear() < 1966) {
            err_time.setVisible(true);
            return false;
        } else {
            err_time.setVisible(false);
            return true;
        }
    }
}
