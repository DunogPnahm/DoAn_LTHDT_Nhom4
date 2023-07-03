package Controller;

import Model.Cls;
import Model.Enroll;
import Model.Prof;
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
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.util.Callback;

public class ProfMainMenuController extends BaseMainMenuController {
    
    private String profstartdate;
    
    private String deptname;
    
    private String chooseAll = "All";
    
    private List<Sem> sem_list = new ArrayList<>();
    
    private List<String> crs_list = new ArrayList<>();
    
    @FXML
    private Text ProfDOB;

    @FXML
    private Text ProfEmail;

    @FXML
    private Text ProfHireDate;

    @FXML
    private Text ProfID;

    @FXML
    private Text ProfRank;

    @FXML
    private Text ProfSpecialty;
    
    @FXML
    private Text Cls;
    
    @FXML
    private JFXButton add_button;
    
    @FXML
    private Text Sem;
    
    @FXML
    private JFXComboBox<String> ClsChooser;
    
    @FXML
    private JFXComboBox<String> SemChooser;
    
    @FXML
    private TableColumn<?, ?> col_stu_num;

    @FXML
    private TableColumn<?, ?> col_cls_code;

    @FXML
    private TableColumn<?, ?> col_enroll_date;

    @FXML
    private TableColumn<?, ?> col_enroll_grade;
    
    @FXML
    private TableColumn<Enroll, Void> col_del;

    @FXML
    private TableColumn<Enroll, Void> col_edit;
    
    @FXML
    private TableView<Enroll> tb_data;
    
    @FXML
    void ChooseSem(ActionEvent event) {
        Sem.setText(SemChooser.getValue());
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
    void AddEnroll(MouseEvent event) throws IOException {
//        App.setRoot("");
    }
    
    @FXML
    void ToSche(MouseEvent event) throws IOException {
        App.setRoot("ProfSche");
    }
    
    protected static Enroll enroll_edit;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        int day;
        int month;
        int year;
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
            this.closeConnection(preSql);
            
            this.openConnection();
            preSql = this.conn.prepareStatement("SELECT EMP_HIREDATE, EMP_NUM, PROF_NUM\n" +
                "FROM professor INNER JOIN employee\n" +
                "ON professor.PROF_NUM = employee.EMP_NUM WHERE EMP_NUM = ?;"
            );
            preSql.setInt(1, this.usr.getProf().getPROF_NUM());
            ResultSet prof_start = this.getData(preSql);
            if(prof_start.next())
            {
                Calendar cal = Calendar.getInstance();
                cal.setTime(prof_start.getDate("EMP_HIREDATE"));
                day = cal.get(Calendar.DAY_OF_MONTH);
                month = cal.get(Calendar.MONTH) + 1;
                year = cal.get(Calendar.YEAR);
                profstartdate = day + "/" + month + "/" + year;   
            }
            preSql = this.conn.prepareStatement("SELECT DISTINCT professor.PROF_NUM, DEPT_NAME\n" +
                "FROM department INNER JOIN professor\n" +
                "ON department.DEPT_CODE = professor.DEPT_CODE WHERE professor.PROF_NUM = ?;"
            );
            preSql.setInt(1, this.usr.getProf().getPROF_NUM());
            ResultSet DeptName = this.getData(preSql);
            if(DeptName.next())
            {
                deptname = DeptName.getString("DEPT_NAME");
            }
            preSql = this.conn.prepareStatement("SELECT *, ROUND((ENROLL_FINAL*0.7 + ENROLL_ASSESS*0.15 + ENROLL_MIDTERM*0.15),2) AS TB\n" +
                "FROM enroll INNER JOIN class\n" +
                "ON enroll.CLS_CODE = class.CLS_CODE\n" +
                "WHERE PROF_NUM = ?;");
            preSql.setInt(1, this.usr.getProf().getPROF_NUM());
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
                enroll.setAVG(enroll_set.getFloat("TB"));
                enroll_list.add(enroll);
            }
            this.closeConnection(preSql);
            
            ObservableList<Enroll> enroll_obs = FXCollections.observableArrayList(enroll_list);
            col_stu_num.setCellValueFactory(new PropertyValueFactory<>("STU_NUM"));
            col_cls_code.setCellValueFactory(new PropertyValueFactory<>("CLS_CODE"));
            col_enroll_date.setCellValueFactory(new PropertyValueFactory<>("ENROLL_DATE"));
            col_enroll_grade.setCellValueFactory(new PropertyValueFactory<>("AVG"));
            
            tb_data.getItems().setAll(enroll_obs);
            this.closeConnection(preSql);
            SemChooser.getItems().addAll(sem_options);
            SemChooser.getItems().add(chooseAll);
            ClsChooser.getItems().addAll(crs_options);
            ClsChooser.getItems().add(chooseAll);
//            SemChooser.setOnAction(this::ChooseSem);
//            ClsChooser.setOnAction(this::ChooseCls);
        } catch (SQLException ex) {
            Logger.getLogger(StuMainMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ProfName.setText(usr.getProf().getEMP_LNAME() + " " + usr.getProf().getEMP_MNAME() + " " + usr.getProf().getEMP_FNAME());
        ProfDept.setText(deptname);
        ProfHireDate.setText("Bắt đầu giảng dạy: " + profstartdate);
        ProfID.setText(Integer.toString(usr.getProf().getPROF_NUM()));
        ProfEmail.setText(usr.getProf().getEMP_EMAIL());
        ProfSpecialty.setText(usr.getProf().getPROF_SPECIALTY());
        Calendar cal = Calendar.getInstance();
        cal.setTime(usr.getProf().getEMP_DOB());
        day = cal.get(Calendar.DAY_OF_MONTH);
        month = cal.get(Calendar.MONTH) + 1;
        year = cal.get(Calendar.YEAR);
        ProfDOB.setText(day+"/"+month+"/"+year);
        ProfRank.setText(usr.getProf().getPROF_RANK());
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
                            enroll_edit = enroll;
                            try {
                                App.setRoot("UpdateEnroll");
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
                            button.setText("Sửa");
                            setGraphic(button);
                        }
                    }
                };
                return cell;
            }
        };
        col_edit.setCellFactory(buttonCellFactory);
        Callback<TableColumn<Enroll, Void>, TableCell<Enroll, Void>> buttonCellFactory1 = new Callback<>() {
            @Override
            public TableCell<Enroll, Void> call(TableColumn<Enroll, Void> param) {
                final TableCell<Enroll, Void> cell = new TableCell<>() {
                    private final JFXButton button = new JFXButton();

                    {
                        button.setButtonType(JFXButton.ButtonType.FLAT);
                        button.setOnAction(event -> {
                            // Get the item associated with the current row
                            Enroll enroll = getTableView().getItems().get(getIndex());
                            deleteEnroll(enroll.getSTU_NUM(), enroll.getCLS_CODE());
                        });
                    }

                    @Override
                    protected void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            // Get the item associated with the current row
                            button.setText("Xóa");
                            setGraphic(button);
                        }
                    }
                };
                return cell;
            }
        };
        col_del.setCellFactory(buttonCellFactory1);
    }
    
    public void displayStuTable(Sem sem, String crs) {
        //Get students data
        PreparedStatement preSql;
        try {
            this.openConnection();
            if (sem != null && crs == null) {
                preSql = this.conn.prepareStatement("SELECT *,ROUND((ENROLL_FINAL*0.7 + ENROLL_ASSESS*0.15 + ENROLL_MIDTERM*0.15),2) AS TB \n" +
                    "FROM enroll INNER JOIN class\n" +
                    "ON enroll.CLS_CODE = class.CLS_CODE\n" +
                    "WHERE PROF_NUM = ? AND class.SEM_CODE = ?;");
                preSql.setInt(1, this.usr.getProf().getPROF_NUM());
                preSql.setString(2, sem.getSEM_CODE());          
            } else if (sem == null && crs != null) {
                preSql = this.conn.prepareStatement("SELECT STU_NUM, CRS_TITLE, A.CLS_CODE AS CLS_CODE, ENROLL_DATE, ENROLL_FINAL, ENROLL_ASSESS, ENROLL_MIDTERM, ROUND((ENROLL_FINAL*0.7 + ENROLL_ASSESS*0.15 + ENROLL_MIDTERM*0.15),2) AS TB\n" +
                    "FROM (SELECT STU_NUM, CRS_CODE, class.CLS_CODE, ENROLL_DATE, ENROLL_FINAL, ENROLL_ASSESS, ENROLL_MIDTERM\n" +
                    "FROM enroll INNER JOIN class\n" +
                    "ON enroll.CLS_CODE = class.CLS_CODE WHERE PROF_NUM = ?) AS A INNER JOIN course\n" +
                    "ON A.CRS_CODE = course.CRS_CODE\n" +
                    "WHERE CRS_TITLE = ?;");
                preSql.setInt(1, this.usr.getProf().getPROF_NUM());
                preSql.setString(2, crs);          
            } else if(sem != null && crs != null){
                preSql = this.conn.prepareStatement("SELECT STU_NUM, CRS_TITLE, A.CLS_CODE AS CLS_CODE, SEM_CODE, ENROLL_DATE, ENROLL_FINAL, ENROLL_ASSESS, ENROLL_MIDTERM, ROUND((ENROLL_FINAL*0.7 + ENROLL_ASSESS*0.15 + ENROLL_MIDTERM*0.15),2) AS TB\n" +
                    "FROM (SELECT STU_NUM, CRS_CODE, SEM_CODE, class.CLS_CODE, ENROLL_DATE, ENROLL_FINAL, ENROLL_ASSESS, ENROLL_MIDTERM\n" +
                    "FROM enroll INNER JOIN class\n" +
                    "ON enroll.CLS_CODE = class.CLS_CODE WHERE PROF_NUM = ?) AS A INNER JOIN course\n" +
                    "ON A.CRS_CODE = course.CRS_CODE\n" +
                    "WHERE CRS_TITLE = ? AND SEM_CODE = ?;");
                preSql.setInt(1, this.usr.getProf().getPROF_NUM());
                preSql.setString(2, crs);
                preSql.setString(3, sem.getSEM_CODE());
            } else {
                preSql = this.conn.prepareStatement("SELECT *, ROUND((ENROLL_FINAL*0.7 + ENROLL_ASSESS*0.15 + ENROLL_MIDTERM*0.15),2) AS TB\n" +
                    "FROM enroll INNER JOIN class\n" +
                    "ON enroll.CLS_CODE = class.CLS_CODE\n" +
                    "WHERE PROF_NUM = ?;");
                preSql.setInt(1, this.usr.getProf().getPROF_NUM());
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
                enroll.setAVG(enroll_set.getFloat("TB"));
                enroll_list.add(enroll);
            }
            this.closeConnection(preSql);
            
            ObservableList<Enroll> enroll_obs = FXCollections.observableArrayList(enroll_list);
            col_stu_num.setCellValueFactory(new PropertyValueFactory<>("STU_NUM"));
            col_cls_code.setCellValueFactory(new PropertyValueFactory<>("CLS_CODE"));
            col_enroll_date.setCellValueFactory(new PropertyValueFactory<>("ENROLL_DATE"));
            col_enroll_grade.setCellValueFactory(new PropertyValueFactory<>("AVG"));
            
            tb_data.getItems().setAll(enroll_obs);
        } catch (SQLException ex) {
            Logger.getLogger(ProfMainMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    void addEnroll(ActionEvent event) {
        try {
            App.setRoot("AddEnroll");
        } catch (IOException ex) {
            Logger.getLogger(ProfMainMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void deleteEnroll(int STU_NUM, String CLS_CODE) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

        alert.setTitle("Xác nhận xóa");

        alert.setHeaderText(null);

        alert.setContentText("Bạn có chắc chắn muốn xóa không?");

        alert.showAndWait();

        if (alert.getResult() == ButtonType.OK) {
            PreparedStatement preSql;
            try {
                this.openConnection();
                preSql = this.conn.prepareStatement("DELETE FROM enroll WHERE STU_NUM = ? AND CLS_CODE = ?");
                preSql.setInt(1, STU_NUM);
                preSql.setString(2, CLS_CODE);

                int result = this.deleteData(preSql);
                if (result > 0) System.out.println("Xóa thành công");
            } catch (SQLException ex) {
                Logger.getLogger(StuMainMenuController.class.getName()).log(Level.SEVERE, null, ex);
            }
            return;
        } else {
            return;
        }
    }
    
    @FXML
    void changePswd(ActionEvent event) throws IOException {
        App.setRoot("UpdatePassword");
    }

}