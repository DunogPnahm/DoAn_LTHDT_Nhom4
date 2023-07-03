package Controller;

import static Controller.BaseController.page;
import static Controller.BaseController.stu_detail;
import Model.Dept;
import Model.Prof;
import Model.Stu;
import com.jfoenix.controls.JFXButton;
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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.util.Callback;

public class StuDeptProfInfoController extends BaseController {

    private String chooseAll = "All";
    
    private List<Dept> dept_list = new ArrayList<>();
    
    public class Stu_Alt1 extends Stu {
        private int no;
        
        private String name;
        
        public Stu_Alt1(int STU_NUM, String STU_LNAME, String STU_FNAME, String STU_INTIAL, String STU_EMAIL, Date STU_DOB, String STU_GENDER, String DEPT_CODE, String MGTCLS_CODE) {
            super(STU_NUM, STU_LNAME, STU_FNAME, STU_INTIAL, STU_EMAIL, STU_DOB, STU_GENDER, DEPT_CODE, MGTCLS_CODE);
        }
        
        public int getNo() {
            return no;
        }

        public void setNo(int no) {
            this.no = no;
        }
        
        public String getName() {
            return name;
        }

        public void setName() {
            name = STU_LNAME + " " + STU_MNAME + " " + STU_FNAME;
        }
    }
    
    public class Dept_Alt extends Dept {
        private int no;
        
        private Prof_Alt prof;
        
        private String profName;
        
        public Dept_Alt(String DEPT_CODE, String DEPT_NAME, int PROF_NUM) {
            super(DEPT_CODE, DEPT_NAME, PROF_NUM);
        }
        
        public int getNo() {
            return no;
        }

        public void setNo(int no) {
            this.no = no;
        }
        
        public Prof_Alt getProf() {
            return prof;
        }

        public void setProf(Prof_Alt prof) {
            this.prof = prof;
        }
        
        public String getProfName() {
            return profName;
        }

        public void setProfName(String profName) {
            this.profName = profName;
        }
    }
    
    public class Prof_Alt extends Prof {
        private int no;
        
        private String name;
        
        public Prof_Alt(int PROF_NUM, String PROF_SPECIALTY, String DEPT_CODE, String PROF_RANK, String EMP_LNAME, String EMP_FNAME, String EMP_INITIAL, String EMP_JOBCODE, Date EMP_HIREDATE, Date EMP_DOB, String EMP_EMAIL) {
            super(PROF_NUM, PROF_SPECIALTY, DEPT_CODE, PROF_RANK, EMP_LNAME, EMP_FNAME, EMP_INITIAL, EMP_JOBCODE, EMP_HIREDATE, EMP_DOB, EMP_EMAIL);
        }

        public int getNo() {
            return no;
        }

        public void setNo(int no) {
            this.no = no;
        }
        
        public String getName() {
            return name;
        }

        public void setName() {
            name = EMP_LNAME + " " + EMP_MNAME + " " + EMP_FNAME;
        }
    }
    
    @FXML
    private Pane Avatar;

    @FXML
    private JFXButton ClsButton;

    @FXML
    private Pane Dept_Ic;

    @FXML
    private Pane HuceLogo;

    @FXML
    private Pane Ret_Ic;

    @FXML
    private Pane Sche_Ic;

    @FXML
    private Pane Task_Ic;

    @FXML
    private Pane UsrBckgr;

    @FXML
    private AnchorPane body;

    @FXML
    private Text Dept;

    @FXML
    private Text Dept1;
    
    @FXML
    private TableColumn<Stu, Void> col_detail_stu;

    @FXML
    private JFXComboBox<String> DeptChooser;

    @FXML
    private JFXComboBox<String> DeptChooser1;
    
    @FXML
    private TableColumn<?, ?> col_dept;

    @FXML
    private TableColumn<?, ?> col_dept1;

    @FXML
    private TableColumn<?, ?> col_dept2;

    @FXML
    private TableColumn<?, ?> col_deptname;

    @FXML
    private TableColumn<?, ?> col_no;

    @FXML
    private TableColumn<?, ?> col_no1;

    @FXML
    private TableColumn<?, ?> col_no2;

    @FXML
    private TableColumn<?, ?> col_profname;
    
    @FXML
    private TableColumn<?, ?> col_profname1;

    @FXML
    private TableColumn<?, ?> col_profnum;

    @FXML
    private TableColumn<?, ?> col_stu;

    @FXML
    private TableColumn<?, ?> col_stunum;

    @FXML
    private TableView<Stu_Alt1> tb_data;

    @FXML
    private TableView<Dept_Alt> tb_data1;

    @FXML
    private TableView<Prof_Alt> tb_data2;
    
    @FXML
    private TableColumn<Prof, Void> col_detail_prof;
    
    protected static Prof prof_detail;
    
    @FXML
    void ToSche(MouseEvent event) throws IOException {
        App.setRoot("StuSche");
    }

    @FXML
    void ToTask(MouseEvent event) throws IOException {
        App.setRoot("StuTask");
    }
    
    @FXML
    void ToMain(MouseEvent event) throws IOException {
        App.setRoot("StuMainMenu");
    }

    @FXML
    void ChooseDept(ActionEvent event) {
        Dept.setText(DeptChooser.getValue());
        if(DeptChooser.getValue() == chooseAll)
        {
            displayTable(null, "STU");
        }
        else
        {
            displayTable(this.dept_list.get(DeptChooser.getSelectionModel().getSelectedIndex()), "STU");
        }
    }
    
    @FXML
    void ChooseDept1(ActionEvent event) {
        Dept1.setText(DeptChooser1.getValue());
        if(DeptChooser1.getValue() == chooseAll)
        {
            displayTable(null, "PROF");
        }
        else
        {
            displayTable(this.dept_list.get(DeptChooser1.getSelectionModel().getSelectedIndex()), "PROF");
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        StuName.setText(usr.getStu().getSTU_LNAME() + " " + usr.getStu().getSTU_MNAME() + " " + usr.getStu().getSTU_FNAME());
        StuCls.setText(usr.getStu().getMGTCLS_CODE());
        try {
            this.openConnection();
            PreparedStatement preSql = this.conn.prepareStatement("SELECT * \n" +
                "FROM student");
            ResultSet stu_set = this.getData(preSql);
            List<Stu_Alt1> stu_list = new ArrayList<>();
            int no = 0;
            while (stu_set.next()) {
                Stu_Alt1 stu = new Stu_Alt1(
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
                stu.setNo(++no);
                stu.setName();
                stu_list.add(stu);
            }
            ObservableList<Stu_Alt1> enroll_obs = FXCollections.observableArrayList(stu_list);
            col_no.setCellValueFactory(new PropertyValueFactory<>("no"));
            col_stu.setCellValueFactory(new PropertyValueFactory<>("name"));
            col_stunum.setCellValueFactory(new PropertyValueFactory<>("STU_NUM"));
            col_dept.setCellValueFactory(new PropertyValueFactory<>("DEPT_CODE"));
            
            tb_data.getItems().setAll(enroll_obs);
            this.closeConnection(preSql);
            
            this.openConnection();
            preSql = this.conn.prepareStatement("SELECT *\n" +
                "FROM department;");
            ResultSet dept_set = this.getData(preSql);
            List<String> dept_options = new ArrayList<>();
            while (dept_set.next()) {
                Dept dept = new Dept(
                        dept_set.getString("DEPT_CODE"),
                        dept_set.getString("DEPT_NAME"),
                        dept_set.getInt("PROF_NUM")
                );
                dept_options.add(dept.getDEPT_NAME());
                dept_list.add(dept);
                
            }
            this.closeConnection(preSql);
            
            this.openConnection();
            preSql = this.conn.prepareStatement("SELECT * \n" +
                "FROM professor INNER JOIN employee\n" +
                "ON professor.PROF_NUM = employee.EMP_NUM;");
            ResultSet prof_set = this.getData(preSql);
            List<Prof_Alt> prof_list = new ArrayList<>();
            int no1 = 0;
            while (prof_set.next()) {
                Prof_Alt prof = new Prof_Alt(
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
                prof.setNo(++no1);
                prof.setName();
                prof_list.add(prof);
            }
            ObservableList<Prof_Alt> prof_obs = FXCollections.observableArrayList(prof_list);
            col_no2.setCellValueFactory(new PropertyValueFactory<>("no"));
            col_profname1.setCellValueFactory(new PropertyValueFactory<>("name"));
            col_profnum.setCellValueFactory(new PropertyValueFactory<>("PROF_NUM"));
            col_dept2.setCellValueFactory(new PropertyValueFactory<>("DEPT_CODE"));

            tb_data2.getItems().setAll(prof_obs);
            this.closeConnection(preSql);
            
            this.openConnection();
            preSql = this.conn.prepareStatement("SELECT DISTINCT DEPT_CODE, PROF_SPECIALTY, PROF_RANK, DEPT_NAME, A.PROF_NUM, PROF_NUM, EMP_LNAME, EMP_FNAME, EMP_MNAME, EMP_JOBCODE, EMP_HIREDATE, EMP_DOB, EMP_EMAIL\n" +
                "FROM (SELECT department.DEPT_CODE, DEPT_NAME, department.PROF_NUM, PROF_SPECIALTY, PROF_RANK\n" +
                "FROM department INNER JOIN professor\n" +
                "ON department.DEPT_CODE = professor.DEPT_CODE\n" +
                "WHERE PROF_SPECIALTY = 'Trưởng khoa') AS A INNER JOIN employee\n" +
                "ON A.PROF_NUM = employee.EMP_NUM;");
            ResultSet deptprof_set = this.getData(preSql);
            List<Dept_Alt> dept_list = new ArrayList<>();
            int no2 = 0;
            while (deptprof_set.next()) {
                Prof_Alt prof = new Prof_Alt(
                        deptprof_set.getInt("PROF_NUM"),
                        deptprof_set.getString("PROF_SPECIALTY"),
                        deptprof_set.getString("DEPT_CODE"),
                        deptprof_set.getString("PROF_RANK"),
                        deptprof_set.getString("EMP_LNAME"),
                        deptprof_set.getString("EMP_FNAME"),
                        deptprof_set.getString("EMP_MNAME"),
                        deptprof_set.getString("EMP_JOBCODE"),
                        deptprof_set.getDate("EMP_HIREDATE"),
                        deptprof_set.getDate("EMP_DOB"),
                        deptprof_set.getString("EMP_EMAIL")
                );
                prof.setNo(++no2);
                prof.setName();
                Dept_Alt dept = new Dept_Alt(
                        deptprof_set.getString("DEPT_CODE"),
                        deptprof_set.getString("DEPT_NAME"),
                        deptprof_set.getInt("PROF_NUM")
                );
                dept.setNo(no2);
                dept.setProf(prof);
                dept.setProfName(dept.getProf().getName());
                dept_list.add(dept);    
            }
            ObservableList<Dept_Alt> dept_obs = FXCollections.observableArrayList(dept_list);
            col_no1.setCellValueFactory(new PropertyValueFactory<>("no"));
            col_deptname.setCellValueFactory(new PropertyValueFactory<>("DEPT_NAME"));
            col_dept1.setCellValueFactory(new PropertyValueFactory<>("DEPT_CODE"));
            col_profname.setCellValueFactory(new PropertyValueFactory<>("profName"));

            tb_data1.getItems().setAll(dept_obs);
            this.closeConnection(preSql);
            
            DeptChooser.getItems().addAll(dept_options);
            DeptChooser.getItems().add(chooseAll);
            DeptChooser1.getItems().addAll(dept_options);
            DeptChooser1.getItems().add(chooseAll);
            
            Callback<TableColumn<Stu, Void>, TableCell<Stu, Void>> buttonCellFactory = new Callback<>() {
                @Override
                public TableCell<Stu, Void> call(TableColumn<Stu, Void> param) {
                    final TableCell<Stu, Void> cell = new TableCell<>() {
                        private final JFXButton button = new JFXButton();

                        {
                            button.setButtonType(JFXButton.ButtonType.FLAT);
                            button.setOnAction(event -> {
                                // Get the item associated with the current row
                                Stu stu = getTableView().getItems().get(getIndex());
                                stu_detail = stu;
                                page = "StuDeptProfInfo";
                                try {
                                    App.setRoot("DetailStu");
                                } catch (IOException ex) {
                                    Logger.getLogger(StuScheController.class.getName()).log(Level.SEVERE, null, ex);
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
            col_detail_stu.setCellFactory(buttonCellFactory);
            
            Callback<TableColumn<Prof, Void>, TableCell<Prof, Void>> buttonCellFactory1 = new Callback<>() {
                @Override
                public TableCell<Prof, Void> call(TableColumn<Prof, Void> param) {
                    final TableCell<Prof, Void> cell = new TableCell<>() {
                        private final JFXButton button = new JFXButton();

                        {
                            button.setButtonType(JFXButton.ButtonType.FLAT);
                            button.setOnAction(event -> {
                                // Get the item associated with the current row
                                Prof prof = getTableView().getItems().get(getIndex());
                                prof_detail = prof;
                                try {
                                    App.setRoot("DetailProf");
                                } catch (IOException ex) {
                                    Logger.getLogger(StuScheController.class.getName()).log(Level.SEVERE, null, ex);
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
            col_detail_prof.setCellFactory(buttonCellFactory1);
            
        } catch (SQLException ex) {
            Logger.getLogger(StuScheController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void displayTable(Dept dept, String role) {
        //Get students data
        PreparedStatement preSql;
        try {
            if(role == "STU")
            {
                this.openConnection();
                if(dept != null)
                {
                    preSql = this.conn.prepareStatement("SELECT *\n" +
                        "FROM student WHERE DEPT_CODE = ?");
                    preSql.setString(1, dept.getDEPT_CODE());
                }
                else
                {
                    preSql = this.conn.prepareStatement("SELECT *\n" +
                        "FROM student");
                }
                ResultSet stu_set = this.getData(preSql);
                List<Stu_Alt1> stu_list = new ArrayList<>();
                int no = 0;
                while (stu_set.next()) {
                    Stu_Alt1 stu = new Stu_Alt1(
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
                    stu.setNo(++no);
                    stu.setName();
                    stu_list.add(stu);
                }
                ObservableList<Stu_Alt1> enroll_obs = FXCollections.observableArrayList(stu_list);
                col_no.setCellValueFactory(new PropertyValueFactory<>("no"));
                col_stu.setCellValueFactory(new PropertyValueFactory<>("name"));
                col_stunum.setCellValueFactory(new PropertyValueFactory<>("STU_NUM"));
                col_dept.setCellValueFactory(new PropertyValueFactory<>("DEPT_CODE"));

                tb_data.getItems().setAll(enroll_obs);
                this.closeConnection(preSql);
            }
            else
            {
                this.openConnection();
                if(dept != null)
                {
                    preSql = this.conn.prepareStatement("SELECT * \n" +
                        "FROM professor INNER JOIN employee\n" +
                        "ON professor.PROF_NUM = employee.EMP_NUM WHERE DEPT_CODE = ?;");
                    preSql.setString(1, dept.getDEPT_CODE());
                }
                else
                {
                    preSql = this.conn.prepareStatement("SELECT * \n" +
                        "FROM professor INNER JOIN employee\n" +
                        "ON professor.PROF_NUM = employee.EMP_NUM;");
                }
                ResultSet prof_set = this.getData(preSql);
                List<Prof_Alt> prof_list = new ArrayList<>();
                int no1 = 0;
                while (prof_set.next()) {
                    Prof_Alt prof = new Prof_Alt(
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
                    prof.setNo(++no1);
                    prof.setName();
                    prof_list.add(prof);
                }
                ObservableList<Prof_Alt> prof_obs = FXCollections.observableArrayList(prof_list);
                col_no2.setCellValueFactory(new PropertyValueFactory<>("no"));
                col_profname.setCellValueFactory(new PropertyValueFactory<>("name"));
                col_profnum.setCellValueFactory(new PropertyValueFactory<>("PROF_NUM"));
                col_dept2.setCellValueFactory(new PropertyValueFactory<>("DEPT_CODE"));

                tb_data2.getItems().setAll(prof_obs);
                this.closeConnection(preSql);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StuMainMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
