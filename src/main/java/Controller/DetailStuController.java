package Controller;

import static Controller.BaseController.usr;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
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

public class DetailStuController extends BaseController {
    
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
    private Text detailCls;

    @FXML
    private Text detailCode;

    @FXML
    private Text detailDOB;

    @FXML
    private Text detailDept;

    @FXML
    private Text detailEmail;

    @FXML
    private Text detailGender;

    @FXML
    private Text detailStuName;
    
    @FXML
    void cancel(MouseEvent event) {
        try {
            if(this.page == "StuSche")
            {
                App.setRoot("StuSche");
            }
            else
            {
                App.setRoot("StuDeptProfInfo");
            }
        } catch (IOException ex) {
            Logger.getLogger(DetailStuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private String DOB;
    private int day, month, year;
    private String deptname;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            this.openConnection();
            PreparedStatement preSql = this.conn.prepareStatement("SELECT * FROM student WHERE STU_NUM = ?");
            preSql.setInt(1, this.stu_detail.getSTU_NUM());
            ResultSet stu_set = this.getData(preSql);
            if(stu_set.next())
            {
                Calendar cal = Calendar.getInstance();
                cal.setTime(stu_set.getDate("STU_DOB"));
                day = cal.get(Calendar.DAY_OF_MONTH);
                month = cal.get(Calendar.MONTH) + 1;
                year = cal.get(Calendar.YEAR);
                DOB = day + "/" + month + "/" + year;
                detailDOB.setText(DOB);
                
                detailStuName.setText(stu_set.getString("STU_LNAME") + " " + stu_set.getString("STU_MNAME") + " " + stu_set.getString("STU_FNAME"));
                detailCode.setText(Integer.toString(stu_set.getInt("STU_NUM")));
                detailCls.setText(stu_set.getString("MGTCLS_CODE"));
                detailEmail.setText(stu_set.getString("STU_EMAIL"));
                detailGender.setText(stu_set.getString("STU_GENDER"));
            }
            preSql = this.conn.prepareStatement("SELECT STU_NUM, DEPT_NAME\n" +
                "FROM department INNER JOIN student\n" +
                "ON department.DEPT_CODE = student.DEPT_CODE WHERE STU_NUM = ?;"
            );
            preSql.setInt(1, stu_detail.getSTU_NUM());
            ResultSet DeptName = this.getData(preSql);
            if(DeptName.next())
            {
                deptname = DeptName.getString("DEPT_NAME");
            }

        } catch (SQLException ex) {
            Logger.getLogger(StuMainMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
        StuName.setText(usr.getStu().getSTU_LNAME() + " " + usr.getStu().getSTU_MNAME() + " " + usr.getStu().getSTU_FNAME());
        StuCls.setText(usr.getStu().getMGTCLS_CODE());
        detailDept.setText(deptname);

    }
    
}
