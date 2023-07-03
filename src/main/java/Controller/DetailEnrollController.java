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

public class DetailEnrollController extends StuMainMenuController {
    
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
    private Text detailMark;

    @FXML
    private JFXButton Logout_btn;

    @FXML
    private JFXButton Logout_btn1;

    @FXML
    private Pane UsrBckgr;

    @FXML
    private AnchorPane body;
    
    @FXML
    private Text detailAVG;

    @FXML
    private Text detailAssess;

    @FXML
    private Text detailCrsTitle;
    
    @FXML
    private Text detailCls;

    @FXML
    private Text detailCode;

    @FXML
    private Text detailCre;

    @FXML
    private Text detailFinal;

    @FXML
    private Text detailMidterm;

    @FXML
    private Text detailTime;
    
    @FXML
    private Text detailCom;
    
    @FXML
    void cancel(MouseEvent event) {
        try {
            App.setRoot("StuMainMenu");
        } catch (IOException ex) {
            Logger.getLogger(UpdateEnrollController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private String enrolldate;
    private int day, month, year;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            this.openConnection();
            PreparedStatement preSql = this.conn.prepareStatement("SELECT A.*, course.CRS_CREDIT, course.CRS_TITLE, course.CRS_COMPULSORY, ROUND((ENROLL_FINAL*0.7 + ENROLL_ASSESS*0.15 + ENROLL_MIDTERM*0.15),2) AS TB\n" +
                "FROM (SELECT enroll.*, CRS_CODE \n" +
                "FROM enroll INNER JOIN class\n" +
                "ON enroll.CLS_CODE = class.CLS_CODE\n" +
                "WHERE STU_NUM = ? AND enroll.CLS_CODE = ?) AS A INNER JOIN course\n" +
                "ON A.CRS_CODE = course.CRS_CODE;");
            preSql.setInt(1, this.usr.getStu().getSTU_NUM());
            preSql.setString(2, this.enroll_detail.getCLS_CODE());
            ResultSet rs = this.getData(preSql);
            if(rs.next())
            {
                detailCrsTitle.setText(rs.getString("CRS_TITLE"));
                detailCode.setText(Integer.toString(rs.getInt("STU_NUM")));
                detailCls.setText(rs.getString("CLS_CODE"));
                
                Calendar cal = Calendar.getInstance();
                cal.setTime(rs.getDate("ENROLL_DATE"));
                day = cal.get(Calendar.DAY_OF_MONTH);
                month = cal.get(Calendar.MONTH) + 1;
                year = cal.get(Calendar.YEAR);
                enrolldate = day + "/" + month + "/" + year; 
                
                detailTime.setText(enrolldate);
                
                if(rs.getFloat("TB") < 4)
                {
                    detailMark.setText("F");
                }
                else if(rs.getFloat("TB") < 5)
                {
                    detailMark.setText("D");
                }
                else if(rs.getFloat("TB") < 5.5)
                {
                    detailMark.setText("D+");
                }
                else if(rs.getFloat("TB") < 6.5)
                {
                    detailMark.setText("C");
                }
                else if(rs.getFloat("TB") < 7)
                {
                    detailMark.setText("C+");
                }
                else if(rs.getFloat("TB") < 8)
                {
                    detailMark.setText("B");
                }
                else if(rs.getFloat("TB") < 8.5)
                {
                    detailMark.setText("B+");
                }
                else
                {
                    detailMark.setText("A");
                }
                detailAssess.setText(Float.toString(rs.getFloat("ENROLL_ASSESS")));
                detailMidterm.setText(Float.toString(rs.getFloat("ENROLL_MIDTERM")));
                detailFinal.setText(Float.toString(rs.getFloat("ENROLL_FINAL")));
                detailAVG.setText(Float.toString(rs.getFloat("TB")));
                detailCre.setText(Integer.toString(rs.getInt("CRS_CREDIT")));
                detailCom.setText(rs.getString("CRS_COMPULSORY"));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(StuMainMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
        StuName.setText(usr.getStu().getSTU_LNAME() + " " + usr.getStu().getSTU_MNAME() + " " + usr.getStu().getSTU_FNAME());
        StuCls.setText(usr.getStu().getMGTCLS_CODE());
    }
    
}
