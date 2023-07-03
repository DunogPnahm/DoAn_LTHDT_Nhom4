package Controller;


import Controller.BaseController;
import Model.Emp;
import Model.Prof;
import Model.Rem;
import Model.Stu;
import Model.User;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.security.MessageDigest;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.util.Duration;
import javafx.animation.PauseTransition;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;


public class LoginController extends BaseController {

    private int i = 0;
    
    @FXML
    private JFXButton btn_log;
    
    @FXML
    private TextArea notif;
    
    @FXML
    private Pane body;

    @FXML
    private PasswordField tf_pwd;

    @FXML
    private TextField tf_usr;

    @FXML
    private Text txt_err;
    
    @FXML
    private Pane pn_err;
    
    @FXML
    private Pane pn_welcome;
    
    @FXML
    private Text txt_wait;

    @FXML
    private Text txt_welcome;
    
    private List<String> rem_list = new ArrayList<>();
    private List<String> official_rem_list = new ArrayList<>();
    
    public void login() {
        if (tf_usr.getText() == null || tf_usr.getText().isBlank()) {
            tf_usr.requestFocus();
            return;
        }
        if (tf_pwd.getText() == null || tf_pwd.getText().isBlank()) {
            tf_pwd.requestFocus();
            return;
        }

        try {
            this.openConnection();
            PreparedStatement preSql = this.conn.prepareStatement("SELECT * FROM USER WHERE USER_ACC = ? AND USER_PSWD = ?");
            preSql.setString(1, tf_usr.getText());
            preSql.setString(2, tf_pwd.getText());
            ResultSet rs = this.getData(preSql);
            if (rs.next()) {
                User usr = User.getInstance(
                        rs.getString("USER_ACC"),
                        rs.getString("USER_PSWD"),
                        rs.getString("USER_ROLE"),
                        rs.getInt("USER_ID")
                );
                this.usr = usr;
                if (usr.getUSER_ROLE().compareTo(Config.STUDENT_ROLE) == 0) {
                    preSql = this.conn.prepareStatement("SELECT * FROM student WHERE STU_NUM = ?");
                    preSql.setInt(1, this.usr.getUSER_ID());
                    ResultSet stu_set = this.getData(preSql);
                    if (stu_set.next()) {
                        Stu stu = new Stu(
                                stu_set.getInt("STU_NUM"),
                                stu_set.getString("STU_LNAME"),
                                stu_set.getString("STU_FNAME"),
                                stu_set.getString("STU_MNAME"),
                                stu_set.getString("STU_EMAIL"),
                                stu_set.getDate("STU_DOB"),
                                stu_set.getString("STU_GENDER"),
                                stu_set.getString("DEPT_CODE"),
                                stu_set.getString("MGTCLS_CODE")
                        );
                        this.usr.setStu(stu);
                    }
                    pn_welcome.setVisible(true);
                    txt_wait.setVisible(true);
                    txt_welcome.setVisible(true);
                    PauseTransition pause = new PauseTransition(Duration.millis(3000));
                    pause.setOnFinished(e -> {
                        try {
                            // Code to be executed after the delay
                            App.setRoot("StuMainMenu");
                        } catch (IOException ex) {
                            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    });
                    pause.play();
                } else if (usr.getUSER_ROLE().compareTo(Config.PROFESSOR_ROLE) == 0) {
                    preSql = this.conn.prepareStatement("SELECT * \n" +
                    "FROM professor INNER JOIN employee\n" +
                    "ON professor.PROF_NUM = employee.EMP_NUM WHERE PROF_NUM = ?;");
                    preSql.setInt(1, this.usr.getUSER_ID());
                    ResultSet prof_set = this.getData(preSql);
                    if (prof_set.next()) {
                        Prof prof = new Prof(
                                prof_set.getInt("PROF_NUM"),
                                prof_set.getString("PROF_SPECIALTY"),
                                prof_set.getString("DEPT_CODE"),
                                prof_set.getString("PROF_RANK"),
                                prof_set.getString("EMP_LNAME"),
                                prof_set.getString("EMP_FNAME"),
                                prof_set.getString("EMP_MNAME"),
                                prof_set.getString("EMP_JOBCODE"),
                                prof_set.getDate("EMP_HIREDATE"),
                                prof_set.getDate("EMP_DOB"),
                                prof_set.getString("EMP_EMAIL")
                        );
                        this.usr.setProf(prof);
                    }
                    pn_welcome.setVisible(true);
                    txt_wait.setVisible(true);
                    txt_welcome.setVisible(true);
                    PauseTransition pause = new PauseTransition(Duration.millis(3000));
                    pause.setOnFinished(e -> {
                        try {
                            // Code to be executed after the delay
                            App.setRoot("ProfMainMenu");
                        } catch (IOException ex) {
                            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    });
                    pause.play();
                } else if (usr.getUSER_ROLE().compareTo(Config.ADMIN_ROLE) == 0) {
                    preSql = this.conn.prepareStatement("SELECT * FROM employee WHERE EMP_NUM = ?");
                    preSql.setInt(1, this.usr.getUSER_ID());
                    ResultSet emp_set = this.getData(preSql);
                    if (emp_set.next()) {
                        Emp emp = new Emp(
                                emp_set.getInt("EMP_NUM"),
                                emp_set.getString("EMP_LNAME"),
                                emp_set.getString("EMP_FNAME"),
                                emp_set.getString("EMP_MNAME"),
                                emp_set.getString("EMP_EMAIL"),
                                emp_set.getString("EMP_JOBCODE"),
                                emp_set.getDate("EMP_HIREDATE"),
                                emp_set.getDate("EMP_DOB")
                        );
                        this.usr.setEmp(emp);
                    }
                    pn_welcome.setVisible(true);
                    txt_wait.setVisible(true);
                    txt_welcome.setVisible(true);
                    PauseTransition pause = new PauseTransition(Duration.millis(3000));
                    pause.setOnFinished(e -> {
                        try {
                            // Code to be executed after the delay
                            App.setRoot("AdminMainMenu");
                        } catch (IOException ex) {
                            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    });
                    pause.play();
                } else App.setRoot("Login");
            } else {
                txt_err.setVisible(true);
                pn_err.setVisible(true);
                FadeTransition fadeTransition = new FadeTransition(Duration.seconds(5), txt_err);
                fadeTransition.setFromValue(1.0); // Fully visible
                fadeTransition.setToValue(0.0); // Completely transparent
                fadeTransition.setOnFinished(event -> {
                    // Code to be executed after the fade out
                    txt_err.setVisible(false);
                });
                fadeTransition.play();
                
                FadeTransition fadeTransition1 = new FadeTransition(Duration.seconds(5), pn_err);
                fadeTransition1.setFromValue(0.4); // Barely visible
                fadeTransition1.setToValue(0.0); // Completely transparent
                fadeTransition1.setOnFinished(event -> {
                    // Code to be executed after the fade out
                    pn_err.setVisible(false);
                });
                fadeTransition1.play();
            }
            this.closeConnection(preSql);
        } catch (SQLException | IOException ex) {
            ex.printStackTrace();
        }
    }

    public void hideErr() {
        txt_err.setVisible(false);
        pn_err.setVisible(false);
        pn_welcome.setVisible(false);
        txt_wait.setVisible(false);
        txt_welcome.setVisible(false);
    }

    @FXML
    void nextNotif(ActionEvent event) {
        if(i == rem_list.size()-1)
        {
            i=0;
            notif.setText(rem_list.get(i));
            return;
        }
        notif.setText(rem_list.get(++i));
    }

    @FXML
    void prevNotif(ActionEvent event) {
        if(i == 0)
        {
            i=rem_list.size()-1;
            notif.setText(rem_list.get(i));
            return;
        }
        notif.setText(rem_list.get(--i));
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        hideErr();
        
        PreparedStatement preSql;
        try {
            this.openConnection();
            preSql = this.conn.prepareStatement("SELECT *\n" +
                "FROM reminder;");
            ResultSet rem_set = this.getData(preSql);
            while (rem_set.next()) {
                rem_list.add(rem_set.getString("REM_DESC"));
            }
            for(int j=rem_list.size()-1; j>=0; j--)
            {
                official_rem_list.add(rem_list.get(j));
            }
            notif.setText(official_rem_list.get(0));
            this.closeConnection(preSql);
        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        FadeTransition fadeTransition1 = new FadeTransition(Duration.seconds(3), notif);
        fadeTransition1.setFromValue(1.0); // Fully visible
        fadeTransition1.setToValue(0.0); // Completely transparent
        fadeTransition1.setOnFinished(event -> {
            // Code to be executed after the fade out
            showNextNotif();
        });
        fadeTransition1.play();
    }
    
    public void showNextNotif() {
        if (i++ == rem_list.size()-1) {
            // All notifications shown, you can handle this case as per your requirement
            i=0;
        }
        
        notif.setText(rem_list.get(i));
        notif.setVisible(true);
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(2), notif);
        fadeTransition.setFromValue(0.0); // Start with completely transparent
        fadeTransition.setToValue(1.0); // Fade in to fully visible
        fadeTransition.setOnFinished(event -> {
            // Code to be executed after the fade out
            Timeline timeline = new Timeline();
            KeyValue fadeOut = new KeyValue(notif.opacityProperty(), 0.0);
            KeyFrame fadeOutFrame = new KeyFrame(Duration.seconds(3), fadeOut);
            timeline.getKeyFrames().add(fadeOutFrame);
            timeline.setOnFinished(event1 -> showNextNotif());

            timeline.play();
        });
        fadeTransition.play();
    }
}
