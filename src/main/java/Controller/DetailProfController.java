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

public class DetailProfController extends StuDeptProfInfoController {
    
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
    private Text detailCode;

    @FXML
    private Text detailDOB;

    @FXML
    private Text detailDept;

    @FXML
    private Text detailEmail;

    @FXML
    private Text detailHiredate;

    @FXML
    private Text detailProfName;

    @FXML
    private Text detailRank;
    
    @FXML
    void cancel(MouseEvent event) {
        try {
            App.setRoot("StuDeptProfInfo");
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
            PreparedStatement preSql = this.conn.prepareStatement("SELECT *\n" +
                "FROM professor INNER JOIN employee\n" +
                "ON professor.PROF_NUM = employee.EMP_NUM WHERE PROF_NUM = ?;");
            preSql.setInt(1, this.prof_detail.getPROF_NUM());
            ResultSet prof_set = this.getData(preSql);
            if(prof_set.next())
            {
                Calendar cal = Calendar.getInstance();
                cal.setTime(prof_set.getDate("EMP_DOB"));
                day = cal.get(Calendar.DAY_OF_MONTH);
                month = cal.get(Calendar.MONTH) + 1;
                year = cal.get(Calendar.YEAR);
                DOB = day + "/" + month + "/" + year;
                detailDOB.setText(DOB);
                
                Calendar cal1 = Calendar.getInstance();
                cal1.setTime(prof_set.getDate("EMP_HIREDATE"));
                day = cal1.get(Calendar.DAY_OF_MONTH);
                month = cal1.get(Calendar.MONTH) + 1;
                year = cal1.get(Calendar.YEAR);
                DOB = day + "/" + month + "/" + year;
                detailHiredate.setText(DOB);
                
                detailProfName.setText(prof_set.getString("EMP_LNAME") + " " + prof_set.getString("EMP_MNAME") + " " + prof_set.getString("EMP_FNAME"));
                detailCode.setText(Integer.toString(prof_set.getInt("PROF_NUM")));
                detailEmail.setText(prof_set.getString("EMP_EMAIL"));
                detailRank.setText(prof_set.getString("PROF_RANK"));
            }
            preSql = this.conn.prepareStatement("SELECT professor.PROF_NUM, DEPT_NAME\n" +
                "FROM department INNER JOIN professor\n" +
                "ON department.DEPT_CODE = professor.DEPT_CODE WHERE professor.PROF_NUM = ?;"
            );
            preSql.setInt(1, prof_detail.getPROF_NUM());
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
