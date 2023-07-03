package Controller;

import Model.Crs;
import Model.Enroll;
import Model.Sem;
import Model.Stu;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.util.Callback;

public class StuMainMenuController extends BaseMainMenuController {
    
    private float grade;
    
    private String chooseAll = "All";
    
    private List<Sem> sem_list = new ArrayList<>();
    
    private List<String> crs_list = new ArrayList<>();

    @FXML
    private Text StuDOB;

    @FXML
    private Text StuGrade;
    
    @FXML
    private Text StuEmail;

    @FXML
    private Text StuGender;

    @FXML
    private Text StuID;

    @FXML
    private Text StuMajor;

    @FXML
    private Text StuStartDate;
    
    @FXML
    private Text FinalAssess;
    
    @FXML
    private Text AvgSem;
    
    @FXML
    private Text Cls;
    
    @FXML
    private Text Sem;
    
    @FXML
    private JFXComboBox<String> ClsChooser;
    
    @FXML
    private JFXComboBox<String> SemChooser;
    
    @FXML
    private TableColumn<Enroll, Void> col_info;
    
    @FXML
    private TableColumn<?, ?> col_no;

    @FXML
    private Text Credit;
    
    @FXML
    private TableColumn<?, ?> col_cls_code;

    @FXML
    private TableColumn<?, ?> col_enroll_date;

    @FXML
    private TableColumn<?, ?> col_enroll_grade;
    
    @FXML
    private TableColumn<Enroll, Void> col_detail;
    
    @FXML
    private TableView<Enroll> tb_data;
    
    public void semAVG()
    {
        try {
            this.openConnection();
            PreparedStatement preSql = this.conn.prepareStatement("SELECT ROUND(AVG(ENROLL_FINAL*0.7 + ENROLL_ASSESS*0.15 + ENROLL_MIDTERM*0.15),2) AS TB\n" +
                "FROM enroll INNER JOIN class\n" +
                "ON enroll.CLS_CODE = class.CLS_CODE\n" +
                "WHERE SEM_CODE = ? AND STU_NUM = ? AND ENROLL_FINAL IS NOT NULL;");
            preSql.setString(1, this.sem_list.get(SemChooser.getSelectionModel().getSelectedIndex()).getSEM_CODE());
            preSql.setInt(2, this.usr.getStu().getSTU_NUM());
            ResultSet avg_set = this.getData(preSql);
            while (avg_set.next()) {
                AvgSem.setText("Điểm trung bình theo kỳ: " + avg_set.getFloat("TB"));
            }
            this.closeConnection(preSql);
        } catch (SQLException ex) {
            Logger.getLogger(StuMainMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    void ChooseSem(ActionEvent event) {
        Sem.setText(SemChooser.getValue());
        if(SemChooser.getValue() == chooseAll && ClsChooser.getValue() == chooseAll)
        {
            AvgSem.setText("Hãy chọn một kỳ để xem điểm trung bình theo kỳ học đó");
            displayStuTable(null, null);
        }
        else if(SemChooser.getValue() == chooseAll && ClsChooser.getValue() != chooseAll && ClsChooser.getSelectionModel().getSelectedIndex() != -1)
        {
            AvgSem.setText("Hãy chọn một kỳ để xem điểm trung bình theo kỳ học đó");
            displayStuTable(null, this.crs_list.get(ClsChooser.getSelectionModel().getSelectedIndex()));
        }
        else if(SemChooser.getValue() != chooseAll && ClsChooser.getValue() == chooseAll && SemChooser.getSelectionModel().getSelectedIndex() != -1)
        {
            semAVG();
            displayStuTable(this.sem_list.get(SemChooser.getSelectionModel().getSelectedIndex()), null);
        }
        else if(ClsChooser.getSelectionModel().getSelectedIndex() == -1 && SemChooser.getValue() == chooseAll)
        {
            AvgSem.setText("Hãy chọn một kỳ để xem điểm trung bình theo kỳ học đó");
            displayStuTable(null, null);
        }
        else if(SemChooser.getSelectionModel().getSelectedIndex() == -1 && ClsChooser.getValue() == chooseAll)
        {
            AvgSem.setText("Hãy chọn một kỳ để xem điểm trung bình theo kỳ học đó");
            displayStuTable(null, null);
        }
        else if(ClsChooser.getSelectionModel().getSelectedIndex() == -1)
        {
            semAVG();
            displayStuTable(this.sem_list.get(SemChooser.getSelectionModel().getSelectedIndex()), null);
        }
        else if(SemChooser.getSelectionModel().getSelectedIndex() == -1)
        {
            AvgSem.setText("Hãy chọn một kỳ để xem điểm trung bình theo kỳ học đó");
            displayStuTable(null, this.crs_list.get(ClsChooser.getSelectionModel().getSelectedIndex()));
        }
        else
        {
            semAVG();
            displayStuTable(this.sem_list.get(SemChooser.getSelectionModel().getSelectedIndex()), this.crs_list.get(ClsChooser.getSelectionModel().getSelectedIndex()));
        }
    }
    
    @FXML
    void ChooseCls(ActionEvent event) {
        Cls.setText(ClsChooser.getValue());
        if(SemChooser.getValue() == chooseAll && ClsChooser.getValue() == chooseAll)
        {
            displayStuTable(null, null);
        }
        else if(SemChooser.getValue() == chooseAll && ClsChooser.getValue() != chooseAll && ClsChooser.getSelectionModel().getSelectedIndex() != -1)
        {
            displayStuTable(null, this.crs_list.get(ClsChooser.getSelectionModel().getSelectedIndex()));
        }
        else if(SemChooser.getValue() != chooseAll && ClsChooser.getValue() == chooseAll && SemChooser.getSelectionModel().getSelectedIndex() != -1)
        {
            displayStuTable(this.sem_list.get(SemChooser.getSelectionModel().getSelectedIndex()), null);
        }
        else if(ClsChooser.getSelectionModel().getSelectedIndex() == -1 && SemChooser.getValue() == chooseAll)
        {
            displayStuTable(null, null);
        }
        else if(SemChooser.getSelectionModel().getSelectedIndex() == -1 && ClsChooser.getValue() == chooseAll)
        {
            displayStuTable(null, null);
        }
        else if(ClsChooser.getSelectionModel().getSelectedIndex() == -1)
        {
            displayStuTable(this.sem_list.get(SemChooser.getSelectionModel().getSelectedIndex()), null);
        }
        else if(SemChooser.getSelectionModel().getSelectedIndex() == -1)
        {
            displayStuTable(null, this.crs_list.get(ClsChooser.getSelectionModel().getSelectedIndex()));
        }
        else
        {
            displayStuTable(this.sem_list.get(SemChooser.getSelectionModel().getSelectedIndex()), this.crs_list.get(ClsChooser.getSelectionModel().getSelectedIndex()));
        }
    }
    
    @FXML
    void ToSche(MouseEvent event) throws IOException {
        App.setRoot("StuSche");
    }
    
    private String stustartdate;
    
    private String deptname;
            
    protected static Enroll enroll_detail;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        int day;
        int month;
        int year;
        int b;
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
            preSql = this.conn.prepareStatement("SELECT DISTINCT CRS_TITLE FROM COURSE");
            ResultSet crs_set = this.getData(preSql);
            List<String> crs_options = new ArrayList<String>();
            while (crs_set.next()) {
                crs_options.add(crs_set.getString("CRS_TITLE"));
                this.crs_list.add(crs_set.getString("CRS_TITLE"));
            }
            preSql = this.conn.prepareStatement("SELECT STU_NUM, semester.SEM_START_DATE\n" +
                "FROM (SELECT STU_NUM, SEM_CODE\n" +
                "FROM management_class INNER JOIN student\n" +
                "ON management_class.MGTCLS_CODE = student.MGTCLS_CODE) AS A INNER JOIN semester\n" +
                "ON A.SEM_CODE = semester.SEM_CODE\n" + 
                "WHERE STU_NUM = ?;"
            );
            preSql.setInt(1, this.usr.getStu().getSTU_NUM());
            ResultSet stu_start = this.getData(preSql);
            if(stu_start.next())
            {
                Calendar cal = Calendar.getInstance();
                cal.setTime(stu_start.getDate("SEM_START_DATE"));
                day = cal.get(Calendar.DAY_OF_MONTH);
                month = cal.get(Calendar.MONTH) + 1;
                year = cal.get(Calendar.YEAR);
                stustartdate = day + "/" + month + "/" + year;   
            }
            preSql = this.conn.prepareStatement("SELECT STU_NUM, DEPT_NAME\n" +
                "FROM department INNER JOIN student\n" +
                "ON department.DEPT_CODE = student.DEPT_CODE WHERE STU_NUM = ?;"
            );
            preSql.setInt(1, this.usr.getStu().getSTU_NUM());
            ResultSet DeptName = this.getData(preSql);
            if(DeptName.next())
            {
                deptname = DeptName.getString("DEPT_NAME");
            }
            preSql = this.conn.prepareStatement("SELECT *, ROUND((ENROLL_FINAL*0.7 + ENROLL_ASSESS*0.15 + ENROLL_MIDTERM*0.15),2) AS TB\n" +
                    "FROM enroll WHERE ENROLL_FINAL IS NOT NULL AND STU_NUM = ?;");
            preSql.setInt(1, this.usr.getStu().getSTU_NUM());
            ResultSet enroll_set = this.getData(preSql);
            List<Enroll> enroll_list = new ArrayList<>();
            int no = 0;
            while (enroll_set.next()) {
                Enroll enroll = new Enroll(
                        enroll_set.getInt("STU_NUM"),
                        enroll_set.getString("CLS_CODE"),
                        enroll_set.getDate("ENROLL_DATE"),
                        enroll_set.getFloat("ENROLL_FINAL"),
                        enroll_set.getFloat("ENROLL_ASSESS"),
                        enroll_set.getFloat("ENROLL_MIDTERM")
                );
                enroll.setNo(++no);
                enroll.setAVG(enroll_set.getFloat("TB"));
                enroll_list.add(enroll);
            }
            this.closeConnection(preSql);
            
            this.openConnection();
            preSql = this.conn.prepareStatement("SELECT ROUND(SUM(B.TB)/SUM(CRS_CREDIT),2) AS FINALAVG\n" +
                "FROM (SELECT A.CLS_CODE, STU_NUM, ENROLL_DATE, (ENROLL_FINAL*0.7 + ENROLL_ASSESS*0.15 + ENROLL_MIDTERM*0.15)*CRS_CREDIT AS TB, A.CRS_CODE, CRS_CREDIT\n" +
                "FROM (SELECT enroll.CLS_CODE, STU_NUM, ENROLL_DATE, ENROLL_FINAL, ENROLL_ASSESS, ENROLL_MIDTERM, CRS_CODE\n" +
                "FROM enroll INNER JOIN class\n" +
                "ON enroll.CLS_CODE = class.CLS_CODE\n" +
                "WHERE ENROLL_FINAL IS NOT NULL AND STU_NUM = ?) AS A INNER JOIN course\n" +
                "ON A.CRS_CODE = course.CRS_CODE\n" +
                "WHERE course.CRS_COMPULSORY = 'YES') AS B;");
            preSql.setInt(1, this.usr.getStu().getSTU_NUM());
            ResultSet stuGrade = this.getData(preSql);
            if(stuGrade.next())
            {
                grade = stuGrade.getFloat("FINALAVG");
            }
            this.closeConnection(preSql);
            
            this.openConnection();
            preSql = this.conn.prepareStatement("SELECT DISTINCT STU_NUM, SUM(CRS_CREDIT) AS TinChi\n" +
                "FROM (SELECT enroll.*, CRS_CODE\n" +
                "FROM enroll INNER JOIN class\n" +
                "ON enroll.CLS_CODE = class.CLS_CODE\n" +
                "WHERE STU_NUM = ? AND ENROLL_MIDTERM >= 4 AND ENROLL_ASSESS >= 4 AND ENROLL_FINAL >= 4 AND ENROLL_FINAL IS NOT NULL) AS A INNER JOIN course\n" +
                "ON A.CRS_CODE = course.CRS_CODE;");
            preSql.setInt(1, this.usr.getStu().getSTU_NUM());
            ResultSet stuCre = this.getData(preSql);
            if(stuCre.next())
            {
                b = stuCre.getInt("TinChi");
                Credit.setText("Số tín chỉ tích lũy:" + Integer.toString(b));
            }
            this.closeConnection(preSql);
            
            ObservableList<Enroll> enroll_obs = FXCollections.observableArrayList(enroll_list);
            col_no.setCellValueFactory(new PropertyValueFactory<>("no"));
            col_cls_code.setCellValueFactory(new PropertyValueFactory<>("CLS_CODE"));
            col_enroll_date.setCellValueFactory(new PropertyValueFactory<>("ENROLL_DATE"));
            col_enroll_grade.setCellValueFactory(new PropertyValueFactory<>("AVG"));
            
            tb_data.getItems().setAll(enroll_obs);
            
            Callback<TableColumn<Enroll, Void>, TableCell<Enroll, Void>> buttonCellFactory = new Callback<>() {
                @Override
                public TableCell<Enroll, Void> call(TableColumn<Enroll, Void> param) {
                    final TableCell<Enroll, Void> cell = new TableCell<>() {
                        private final JFXButton button = new JFXButton();

                        {
                            button.setButtonType(JFXButton.ButtonType.FLAT);
                            button.setOnAction(event -> {
                                // Get the item associated with the current row
                                Enroll enroll = getTableView().getItems().get(getIndex());
                                enroll_detail = enroll;
                                try {
                                    App.setRoot("DetailEnroll");
                                } catch (IOException ex) {
                                    Logger.getLogger(ProfMainMenuController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            });
                        }

                        @Override
                        protected void updateItem(Void item, boolean empty) {
                            super.updateItem(item, empty);
                            if (empty) {
                                setGraphic(null);
                            } else {
                                // Get the item associated with the current row
                                button.setText("(i)");
                                setGraphic(button);
                            }
                        }
                    };
                    return cell;
                }
            };
            col_detail.setCellFactory(buttonCellFactory);
            this.closeConnection(preSql);
            SemChooser.getItems().addAll(sem_options);
            SemChooser.getItems().add(chooseAll);
            ClsChooser.getItems().addAll(crs_options);
            ClsChooser.getItems().add(chooseAll);
            SemChooser.setOnAction(this::ChooseSem);
            ClsChooser.setOnAction(this::ChooseCls);
        } catch (SQLException ex) {
            Logger.getLogger(StuMainMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
        StuName.setText(usr.getStu().getSTU_LNAME() + " " + usr.getStu().getSTU_MNAME() + " " + usr.getStu().getSTU_FNAME());
        StuCls.setText(usr.getStu().getMGTCLS_CODE());
        StuStartDate.setText("Bắt đầu vào trường: " + stustartdate);
        StuID.setText(Integer.toString(usr.getStu().getSTU_NUM()));
        StuEmail.setText(usr.getStu().getSTU_EMAIL());
        StuGender.setText(usr.getStu().getSTU_GENDER());
        Calendar cal = Calendar.getInstance();
        cal.setTime(usr.getStu().getSTU_DOB());
        day = cal.get(Calendar.DAY_OF_MONTH);
        month = cal.get(Calendar.MONTH) + 1;
        year = cal.get(Calendar.YEAR);
        StuDOB.setText(day+"/"+month+"/"+year);
        StuMajor.setText(deptname);
        StuGrade.setText("Điểm trung bình tích lũy: " + Float.toString(grade));
        String a;
        if(grade < 4)
        {
            a = "Kém";
        }
        else if(grade < 7)
        {
            a = "Trung bình";
        }
        else if(grade < 8.5)
        {
            a = "Khá";
        }
        else
        {
            a = "Giỏi";
        }
        FinalAssess.setText("Học lực: " + a);
        
    }
    
    public void displayStuTable(Sem sem, String crs) {
        //Get students data
        PreparedStatement preSql;
        try {
            this.openConnection();
            if (sem != null && crs == null) {
                preSql = this.conn.prepareStatement("SELECT *, ROUND((ENROLL_FINAL*0.7 + ENROLL_ASSESS*0.15 + ENROLL_MIDTERM*0.15),2) AS TB \n" +
                    "FROM enroll INNER JOIN class\n" +
                    "ON enroll.CLS_CODE = class.CLS_CODE\n" +
                    "WHERE ENROLL_FINAL IS NOT NULL AND class.SEM_CODE = ? AND STU_NUM = ?;");
                preSql.setString(1, sem.getSEM_CODE());          
                preSql.setInt(2, this.usr.getStu().getSTU_NUM());
            } else if (sem == null && crs != null) {
                preSql = this.conn.prepareStatement("SELECT STU_NUM, CRS_TITLE, A.CLS_CODE AS CLS_CODE, ENROLL_DATE, ENROLL_FINAL, ENROLL_ASSESS, ENROLL_MIDTERM, ROUND((ENROLL_FINAL*0.7 + ENROLL_ASSESS*0.15 + ENROLL_MIDTERM*0.15),2) AS TB\n" +
                    "FROM (SELECT STU_NUM, CRS_CODE, class.CLS_CODE, ENROLL_DATE, ENROLL_FINAL, ENROLL_ASSESS, ENROLL_MIDTERM\n" +
                    "FROM enroll INNER JOIN class\n" +
                    "ON enroll.CLS_CODE = class.CLS_CODE WHERE ENROLL_FINAL IS NOT NULL) AS A INNER JOIN course\n" +
                    "ON A.CRS_CODE = course.CRS_CODE\n" +
                    "WHERE CRS_TITLE = ? AND STU_NUM = ?;");
                preSql.setString(1, crs);          
                preSql.setInt(2, this.usr.getStu().getSTU_NUM());
            } else if(sem != null && crs != null){
                preSql = this.conn.prepareStatement("SELECT STU_NUM, CRS_TITLE, A.CLS_CODE AS CLS_CODE, SEM_CODE, ENROLL_DATE, ENROLL_FINAL, ENROLL_ASSESS, ENROLL_MIDTERM, ROUND((ENROLL_FINAL*0.7 + ENROLL_ASSESS*0.15 + ENROLL_MIDTERM*0.15),2) AS TB\n" +
                    "FROM (SELECT STU_NUM, CRS_CODE, SEM_CODE, class.CLS_CODE, ENROLL_DATE, ENROLL_FINAL, ENROLL_ASSESS, ENROLL_MIDTERM\n" +
                    "FROM enroll INNER JOIN class\n" +
                    "ON enroll.CLS_CODE = class.CLS_CODE WHERE ENROLL_FINAL IS NOT NULL) AS A INNER JOIN course\n" +
                    "ON A.CRS_CODE = course.CRS_CODE\n" +
                    "WHERE CRS_TITLE = ? AND STU_NUM = ? AND SEM_CODE = ?;");
                preSql.setString(1, crs);
                preSql.setInt(2, this.usr.getStu().getSTU_NUM());
                preSql.setString(3, sem.getSEM_CODE());
            } else {
                preSql = this.conn.prepareStatement("SELECT *, ROUND((ENROLL_FINAL*0.7 + ENROLL_ASSESS*0.15 + ENROLL_MIDTERM*0.15),2) AS TB FROM enroll WHERE ENROLL_FINAL IS NOT NULL AND STU_NUM = ?;"); 
                preSql.setInt(1, this.usr.getStu().getSTU_NUM());
            }
            ResultSet enroll_set = this.getData(preSql);
            List<Enroll> enroll_list = new ArrayList<>();
            int no = 0;
            while (enroll_set.next()) {
                Enroll enroll = new Enroll(
                        enroll_set.getInt("STU_NUM"),
                        enroll_set.getString("CLS_CODE"),
                        enroll_set.getDate("ENROLL_DATE"),
                        enroll_set.getFloat("ENROLL_FINAL"),
                        enroll_set.getFloat("ENROLL_ASSESS"),
                        enroll_set.getFloat("ENROLL_MIDTERM")
                );
                enroll.setNo(++no);
                enroll.setAVG(enroll_set.getFloat("TB"));
                enroll_list.add(enroll);
            }
            this.closeConnection(preSql);
            
            ObservableList<Enroll> enroll_obs = FXCollections.observableArrayList(enroll_list);
            col_no.setCellValueFactory(new PropertyValueFactory<>("no"));
            col_cls_code.setCellValueFactory(new PropertyValueFactory<>("CLS_CODE"));
            col_enroll_date.setCellValueFactory(new PropertyValueFactory<>("ENROLL_DATE"));
            col_enroll_grade.setCellValueFactory(new PropertyValueFactory<>("AVG"));
            
            tb_data.getItems().setAll(enroll_obs);
            
        } catch (SQLException ex) {
            Logger.getLogger(StuMainMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    void changePswd(ActionEvent event) throws IOException {
        App.setRoot("UpdatePassword");
    }
}