package Controller;

import Model.Enroll;
import Model.Stu;
import com.jfoenix.controls.JFXComboBox;
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class StuMainMenuController extends BaseMainMenuController implements Initializable {
    
    public class Stu_Alt extends Stu {
        int no;
        double AVG_GRADE;

        public Stu_Alt() {
        }

        public Stu_Alt(int STU_NUM, String STU_LNAME, String STU_FNAME, String STU_INTIAL, String STU_EMAIL, Date STU_DOB, String STU_GENDER, String DEPT_CODE, String MGTCLS_CODE) {
            super(STU_NUM, STU_LNAME, STU_FNAME, STU_INTIAL, STU_EMAIL, STU_DOB, STU_GENDER, DEPT_CODE, MGTCLS_CODE);
        }

        public int getNo() {
            return no;
        }

        public void setNo(int no) {
            this.no = no;
        }

        public double getAVG_GRADE() {
            return AVG_GRADE;
        }

        public void setAVG_GRADE(double AVG_GRADE) {
            this.AVG_GRADE = AVG_GRADE;
        }
    }
    
    private String[] testing = {"test 1","test 2","test 3","test 4","test 5","test 6"};
    
    @FXML
    private Text Cls;
    
    @FXML
    private Text Sem;
    
    @FXML
    private JFXComboBox<String> ClsChooser;
    
    @FXML
    private JFXComboBox<String> SemChooser;
    
    @FXML
    private TableColumn<?, ?> col_dept_code;

    @FXML
    private TableColumn<?, ?> col_mgtcls_code;

    @FXML
    private TableColumn<?, ?> col_no;

    @FXML
    private TableColumn<?, ?> col_stu_dob;

    @FXML
    private TableColumn<?, ?> col_stu_email;

    @FXML
    private TableColumn<?, ?> col_stu_fname;

    @FXML
    private TableColumn<?, ?> col_stu_gender;

    @FXML
    private TableColumn<?, ?> col_stu_initial;

    @FXML
    private TableColumn<?, ?> col_stu_lname;

    @FXML
    private TableColumn<?, ?> col_stu_num;

    @FXML
    private TableView<Stu_Alt> tb_data;
    
    @FXML
    void ChooseSem(ActionEvent event) {
        Sem.setText(SemChooser.getValue());
    }
    
    @FXML
    void ChooseCls(ActionEvent event) {
        Cls.setText(ClsChooser.getValue());
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        SemChooser.getItems().addAll(testing);
        ClsChooser.getItems().addAll(testing);
        SemChooser.setOnAction(this::ChooseSem);
        ClsChooser.setOnAction(this::ChooseCls);
        
        //Get students data
        PreparedStatement preSql;
        try {
            this.openConnection();
            preSql = this.conn.prepareStatement("SELECT * FROM student");
            ResultSet stu_set = this.getData(preSql);
            List<Stu_Alt> stu_list = new ArrayList<>();
            int no = 0;
            while (stu_set.next()) {
                no++;
                Stu_Alt stu_alt = new Stu_Alt(
                        stu_set.getInt("STU_NUM"),
                        stu_set.getString("STU_LNAME"),
                        stu_set.getString("STU_FNAME"),
                        stu_set.getString("STU_INITIAL"),
                        stu_set.getString("STU_EMAIL"),
                        stu_set.getDate("STU_DOB"),   
                        stu_set.getString("STU_GENDER"),
                        stu_set.getString("DEPT_CODE"),
                        stu_set.getString("MGTCLS_CODE")
                );
                stu_alt.setNo(no);
                stu_list.add(stu_alt);
            }
            this.closeConnection(preSql);
            
            this.openConnection();
            preSql = this.conn.prepareStatement("SELECT * FROM enroll");
            ResultSet enroll_set = this.getData(preSql);
            List<Enroll> enroll_list = new ArrayList<>();
            while (enroll_set.next()) {
                Enroll enroll = new Enroll(
                        enroll_set.getInt("STU_NUM"),
                        enroll_set.getString("CLS_CODE"),
                        enroll_set.getDate("ENROLL_DATE"),
                        enroll_set.getFloat("ENROLL_GRADE")
                );
                enroll_list.add(enroll);
            }
            this.closeConnection(preSql);
            for (Stu_Alt stu_alt : stu_list) {
                int count_grade = 0;
                double total_grade = 0;
                for (Enroll enroll : enroll_list) {
                    if (stu_alt.getSTU_NUM() == enroll.getSTU_NUM()) {
                        count_grade++;
                        total_grade += enroll.getENROLL_GRADE();
                    }
                }
                if (count_grade == 0) stu_alt.setAVG_GRADE(0);
                else stu_alt.setAVG_GRADE(total_grade / count_grade);
            }
            
            ObservableList<Stu_Alt> stu_obs = FXCollections.observableArrayList(stu_list);
            col_no.setCellValueFactory(new PropertyValueFactory<>("no"));
            col_stu_num.setCellValueFactory(new PropertyValueFactory<>("STU_NUM"));
            col_stu_lname.setCellValueFactory(new PropertyValueFactory<>("STU_LNAME"));
            col_stu_fname.setCellValueFactory(new PropertyValueFactory<>("STU_FNAME"));
            col_stu_initial.setCellValueFactory(new PropertyValueFactory<>("STU_INITIAL"));
            col_stu_email.setCellValueFactory(new PropertyValueFactory<>("STU_EMAIL"));
            col_stu_dob.setCellValueFactory(new PropertyValueFactory<>("STU_DOB"));
            col_stu_gender.setCellValueFactory(new PropertyValueFactory<>("STU_GENDER"));
            col_dept_code.setCellValueFactory(new PropertyValueFactory<>("DEPT_CODE"));
            col_mgtcls_code.setCellValueFactory(new PropertyValueFactory<>("MGTCLS_CODE"));  
            
            tb_data.getItems().setAll(stu_obs);
            System.out.println(stu_list);
        } catch (SQLException ex) {
            Logger.getLogger(StuMainMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}