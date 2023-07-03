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

public class DetailClsController extends StuScheController {
    
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
    private Text detailClsCode;

    @FXML
    private Text detailCom;

    @FXML
    private Text detailCre;

    @FXML
    private Text detailCrsCode;

    @FXML
    private Text detailCrsName;

    @FXML
    private Text detailDept;

    @FXML
    private Text detailDesc;
    
    @FXML
    private Text detailStuSum;

    @FXML
    private Text detailProf;

    @FXML
    private Text detailRoom;

    @FXML
    private Text detailStartDate;

    @FXML
    private Text detailTime;
    
    @FXML
    private Text detailPre;
    
    @FXML
    void cancel(MouseEvent event) {
        try {
            App.setRoot("StuSche");
        } catch (IOException ex) {
            Logger.getLogger(DetailStuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private String startDate;
    private int day, month, year;
    private String deptname;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            this.openConnection();
            PreparedStatement preSql = this.conn.prepareStatement("SELECT C.*, department.DEPT_NAME\n" +
                "FROM (SELECT B.*, EMP_LNAME, EMP_MNAME, EMP_FNAME\n" +
                "FROM (SELECT A.*, semester.SEM_START_DATE\n" +
                "FROM (SELECT class.*, course.CRS_TITLE, course.CRS_CREDIT, course.CRS_COMPULSORY, course.CRS_DESC, course.DEPT_CODE\n" +
                "FROM class INNER JOIN course\n" +
                "ON class.CRS_CODE = course.CRS_CODE) AS A INNER JOIN semester\n" +
                "ON A.SEM_CODE = semester.SEM_CODE) AS B INNER JOIN employee\n" +
                "ON B.PROF_NUM = employee.EMP_NUM) AS C INNER JOIN department\n" +
                "ON C.DEPT_CODE = department.DEPT_CODE WHERE CLS_CODE = ?;");
            preSql.setString(1, this.cls_detail.getCLS_CODE());
            ResultSet cls_set = this.getData(preSql);
            if(cls_set.next())
            {
                Calendar cal = Calendar.getInstance();
                cal.setTime(cls_set.getDate("SEM_START_DATE"));
                day = cal.get(Calendar.DAY_OF_MONTH);
                month = cal.get(Calendar.MONTH) + 1;
                year = cal.get(Calendar.YEAR);
                startDate = day + "/" + month + "/" + year;
                detailStartDate.setText(startDate);
                
                detailProf.setText(cls_set.getString("EMP_LNAME") + " " + cls_set.getString("EMP_MNAME") + " " + cls_set.getString("EMP_FNAME"));
                detailCrsName.setText(cls_set.getString("CRS_TITLE"));
                detailCre.setText(Integer.toString(cls_set.getInt("CRS_CREDIT")));
                detailCom.setText(cls_set.getString("CRS_COMPULSORY"));
                detailDesc.setText(cls_set.getString("CRS_DESC"));
            }
            preSql = this.conn.prepareStatement("SELECT professor.PROF_NUM, DEPT_NAME\n" +
                "FROM department INNER JOIN professor\n" +
                "ON department.DEPT_CODE = professor.DEPT_CODE WHERE professor.PROF_NUM = ?;"
            );
            preSql.setInt(1, cls_detail.getPROF_NUM());
            ResultSet DeptName = this.getData(preSql);
            if(DeptName.next())
            {
                deptname = DeptName.getString("DEPT_NAME");
            }
            
            preSql = this.conn.prepareStatement("(SELECT CLS_CODE, COUNT(STU_NUM) AS StuSum\n" +
                "FROM enroll\n" +
                "WHERE CLS_CODE = ?\n" +
                "GROUP BY CLS_CODE)\n" +
                "\n" +
                "UNION\n" +
                "\n" +
                "(SELECT class.CLS_CODE, (PROF_NUM-PROF_NUM) AS StuSum\n" +
                "FROM class LEFT JOIN enroll\n" +
                "ON class.CLS_CODE = enroll.CLS_CODE\n" +
                "WHERE STU_NUM IS NULL AND class.CLS_CODE = ?\n" +
                "GROUP BY class.CLS_CODE);"
            );
            preSql.setString(1, cls_detail.getCLS_CODE());
            preSql.setString(2, cls_detail.getCLS_CODE());
            ResultSet StuSum = this.getData(preSql);
            if(StuSum.next())
            {
                detailStuSum.setText("Sĩ số lớp: "+Integer.toString(StuSum.getInt("StuSum")));
            }
            preSql = this.conn.prepareStatement("SELECT *\n" +
                "FROM prerequisite INNER JOIN course\n" +
                "ON prerequisite.PRE_TAKE = course.CRS_CODE\n" +
                "WHERE CRS_REQ_PRE = ?;"
            );
            preSql.setString(1, cls_detail.getCRS_CODE());
            ResultSet ReqPre = this.getData(preSql);
            String reqPre = "";
            if(ReqPre.next())
            {
                reqPre = ReqPre.getString("CRS_TITLE") + "(" + ReqPre.getString("PRE_TAKE") + ")";
            }
            else
            {
                reqPre = "Không có";
            }
            while(ReqPre.next())
            {
                reqPre += " || " + ReqPre.getString("CRS_TITLE") + "(" +ReqPre.getString("PRE_TAKE")+ ")";
            }
            detailPre.setText(reqPre);

        } catch (SQLException ex) {
            Logger.getLogger(StuMainMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
        StuName.setText(usr.getStu().getSTU_LNAME() + " " + usr.getStu().getSTU_MNAME() + " " + usr.getStu().getSTU_FNAME());
        StuCls.setText(usr.getStu().getMGTCLS_CODE());
        detailDept.setText(deptname);
        detailClsCode.setText(cls_detail.getCLS_CODE());
        detailCrsCode.setText(cls_detail.getCRS_CODE());
        detailTime.setText(cls_detail.getCLS_TIME());
        detailRoom.setText(cls_detail.getROOM_CODE());
        
    }
    
}
