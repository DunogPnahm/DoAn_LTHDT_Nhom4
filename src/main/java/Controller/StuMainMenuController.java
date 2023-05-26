package Controller;

import Model.Cls;
import Model.Enroll;
import Model.Sem;
import Model.Stu;
import com.jfoenix.controls.JFXComboBox;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.AbstractList;
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
    
    public class Enroll_Alt extends Enroll {
        int no;

        public Enroll_Alt(int STU_NUM, String CLS_CODE, Date ENROLL_DATE, float ENROLL_GRADE) {
            super(STU_NUM, CLS_CODE, ENROLL_DATE, ENROLL_GRADE);
        }

        public int getNo() {
            return no;
        }

        public void setNo(int no) {
            this.no = no;
        }
    }
    
    private static Stu stu;
    
    private String[] testing = {"test 1","test 2","test 3","test 4","test 5","test 6"};
    
    private List<Sem> sem_list = new ArrayList<>();
    
    @FXML
    private Text Cls;
    
    @FXML
    private Text Sem;
    
    @FXML
    private JFXComboBox<String> ClsChooser;
    
    @FXML
    private JFXComboBox<String> SemChooser;
    
    @FXML
    private TableColumn<?, ?> col_no;

    @FXML
    private TableColumn<?, ?> col_cls_code;

    @FXML
    private TableColumn<?, ?> col_enroll_date;

    @FXML
    private TableColumn<?, ?> col_enroll_grade;

    @FXML
    private TableView<Enroll_Alt> tb_data;
    
    @FXML
    void ChooseSem(ActionEvent event) {
        Sem.setText(SemChooser.getValue());
        displayStuTable(this.sem_list.get(SemChooser.getSelectionModel().getSelectedIndex()), null);
    }
    
    @FXML
    void ChooseCls(ActionEvent event) {
        Cls.setText(ClsChooser.getValue());
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            this.openConnection();
            PreparedStatement preSql = this.conn.prepareStatement("SELECT * FROM SEMESTER");
            ResultSet sem_set = this.getData(preSql);
            List<String> sem_options = new ArrayList<String>();
            while (sem_set.next()) {
                Sem sem = new Sem(
                        sem_set.getString("SEM_CODE"),
                        sem_set.getInt("SEM_YEAR"),
                        sem_set.getDate("SEM_START_DATE"),
                        sem_set.getDate("SEM_END_DATE")
                );
                sem_options.add(sem.getSEM_CODE() + " - " + sem.getSEM_YEAR());
                this.sem_list.add(sem);
            }
            this.closeConnection(preSql);
            SemChooser.getItems().addAll(sem_options);
            ClsChooser.getItems().addAll(testing);
            SemChooser.setOnAction(this::ChooseSem);
            ClsChooser.setOnAction(this::ChooseCls);
        } catch (SQLException ex) {
            Logger.getLogger(StuMainMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void displayStuTable(Sem sem, Cls cls) {
        //Get students data
        PreparedStatement preSql;
        try {
            this.openConnection();
            preSql = this.conn.prepareStatement("SELECT * FROM student WHERE STU_NUM = ?");
            preSql.setInt(1, this.usr.getUSER_ID());
            ResultSet stu_set = this.getData(preSql);
            if (stu_set.next()) {
                Stu stu = new Stu(
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
                this.stu = stu;
            }
            this.closeConnection(preSql);
            
            this.openConnection();
            preSql = this.conn.prepareStatement("SELECT * FROM enroll WHERE ENROLL_DATE >= ? AND ENROLL_DATE <= ? AND STU_NUM = ?");
            preSql.setDate(1, sem.getSEM_START_DATE());
            preSql.setDate(2, sem.getSEM_END_DATE());            
            preSql.setInt(3, this.stu.getSTU_NUM());

            ResultSet enroll_set = this.getData(preSql);
            List<Enroll_Alt> enroll_list = new ArrayList<>();
            int no = 0;
            while (enroll_set.next()) {
                Enroll_Alt enroll = new Enroll_Alt(
                        enroll_set.getInt("STU_NUM"),
                        enroll_set.getString("CLS_CODE"),
                        enroll_set.getDate("ENROLL_DATE"),
                        enroll_set.getFloat("ENROLL_GRADE")
                );
                enroll.setNo(++no);
                enroll_list.add(enroll);
            }
            this.closeConnection(preSql);
            
            ObservableList<Enroll_Alt> enroll_obs = FXCollections.observableArrayList(enroll_list);
            col_no.setCellValueFactory(new PropertyValueFactory<>("no"));
            col_cls_code.setCellValueFactory(new PropertyValueFactory<>("CLS_CODE"));
            col_enroll_date.setCellValueFactory(new PropertyValueFactory<>("ENROLL_DATE"));
            col_enroll_grade.setCellValueFactory(new PropertyValueFactory<>("ENROLL_GRADE")); 
            
            tb_data.getItems().setAll(enroll_obs);
        } catch (SQLException ex) {
            Logger.getLogger(StuMainMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}