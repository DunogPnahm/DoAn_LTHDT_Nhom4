package Controller;

import Model.Cls;
import Model.Enroll;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.util.Callback;

public class StuScheController extends BaseController {

    private List<Cls> cls_list = new ArrayList<>();
    
    private String chooseAll = "All";
    
    @FXML
    private TextField in_search;
    
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
    private Text Cls;
    
    @FXML
    private JFXComboBox<String> ClsChooser;
    
    @FXML
    private Pane Sche_Ic;

    @FXML
    private Pane Task_Ic;

    @FXML
    private Pane UsrBckgr;

    @FXML
    private AnchorPane body;
    
    @FXML
    private TableColumn<Stu, Void> col_detail_stu;
    
    @FXML
    private TableColumn<Cls, Void> col_detail_cls;
    
    protected static Cls cls_detail;
    
    public class Stu_Alt extends Stu {
        private int no;
        
        private String clsCode;
        
        private String name;
        
        public Stu_Alt(int STU_NUM, String STU_LNAME, String STU_FNAME, String STU_INTIAL, String STU_EMAIL, Date STU_DOB, String STU_GENDER, String DEPT_CODE, String MGTCLS_CODE) {
            super(STU_NUM, STU_LNAME, STU_FNAME, STU_INTIAL, STU_EMAIL, STU_DOB, STU_GENDER, DEPT_CODE, MGTCLS_CODE);
        }

        public String getClsCode() {
            return clsCode;
        }

        public void setClsCode(String clsCode) {
            this.clsCode = clsCode;
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

    public class Enroll_Alt1 extends Enroll {
        
        private String crsName;
        
        public Enroll_Alt1(int STU_NUM, String CLS_CODE, Date ENROLL_DATE, float ENROLL_FINAL, float ENROLL_ASSESS, float ENROLL_MIDTERM) {
            super(STU_NUM, CLS_CODE, ENROLL_DATE, ENROLL_FINAL, ENROLL_ASSESS, ENROLL_MIDTERM);
        }

        public String getCrsName() {
            return crsName;
        }

        public void setCrsName(String crsName) {
            this.crsName = crsName;
        }
        
    }
    
    public class Cls_Alt1 extends Cls {
        private int no;
        
        private String crsName;
        
        private Date semStart;
        
        public Cls_Alt1(String CLS_CODE, String CLS_TIME, String CRS_CODE, String ROOM_CODE, String SEM_CODE, int PROF_NUM) {
            super(CLS_CODE, CLS_TIME, CRS_CODE, ROOM_CODE, SEM_CODE, PROF_NUM);
        }

        public int getNo() {
            return no;
        }

        public void setNo(int no) {
            this.no = no;
        }
        
        public String getCrsName() {
            return crsName;
        }

        public void setCrsName(String crsName) {
            this.crsName = crsName;
        }

        public Date getSemStart() {
            return semStart;
        }

        public void setSemStart(Date semStart) {
            this.semStart = semStart;
        }
        
    }
    
    @FXML
    private TableColumn<?, ?> col_cls_code;

    @FXML
    private TableColumn<?, ?> col_cls_code1;

    @FXML
    private TableColumn<?, ?> col_cls_code2;

    @FXML
    private TableColumn<?, ?> col_crs;

    @FXML
    private TableColumn<?, ?> col_crs1;

    @FXML
    private TableColumn<?, ?> col_enroll_date;

    @FXML
    private TableColumn<?, ?> col_enroll_date1;

    @FXML
    private TableColumn<?, ?> col_no;

    @FXML
    private TableColumn<?, ?> col_no1;

    @FXML
    private TableColumn<?, ?> col_no2;

    @FXML
    private TableColumn<?, ?> col_stu;

    @FXML
    private TableColumn<?, ?> col_stunum;
    
    @FXML
    private TableView<Enroll> tb_data;

    @FXML
    private TableView<Cls_Alt1> tb_data1;

    @FXML
    private TableView<Stu_Alt> tb_data2;
    
    @FXML
    void ToDeptProfInfo(MouseEvent event) throws IOException {
        App.setRoot("StuDeptProfInfo");
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
    void ChooseCls(ActionEvent event) {
        Cls.setText(ClsChooser.getValue());
        if(ClsChooser.getValue() == chooseAll)
        {
            displayStuTable(null);
        }
        else
        {
            displayStuTable(this.cls_list.get(ClsChooser.getSelectionModel().getSelectedIndex()));
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        StuName.setText(usr.getStu().getSTU_LNAME() + " " + usr.getStu().getSTU_MNAME() + " " + usr.getStu().getSTU_FNAME());
        StuCls.setText(usr.getStu().getMGTCLS_CODE());
        try {
            this.openConnection();
            PreparedStatement preSql = this.conn.prepareStatement("SELECT STU_NUM, A.CLS_CODE, ENROLL_DATE, ENROLL_FINAL, ENROLL_ASSESS, ENROLL_MIDTERM, course.CRS_CODE, CRS_TITLE\n" +
                "FROM (SELECT STU_NUM, enroll.CLS_CODE, ENROLL_DATE, ENROLL_FINAL, ENROLL_ASSESS, ENROLL_MIDTERM, CRS_CODE\n" +
                "FROM enroll INNER JOIN class\n" +
                "ON enroll.CLS_CODE = class.CLS_CODE) AS A INNER JOIN course\n" +
                "ON a.CRS_CODE = course.CRS_CODE\n" +
                "WHERE ENROLL_FINAL IS NULL AND STU_NUM = ?;");
            preSql.setInt(1, this.usr.getStu().getSTU_NUM());
            ResultSet enroll_set = this.getData(preSql);
            List<Enroll_Alt1> enroll_list = new ArrayList<>();
            int no = 0;
            while (enroll_set.next()) {
                Enroll_Alt1 enroll = new Enroll_Alt1(
                        enroll_set.getInt("STU_NUM"),
                        enroll_set.getString("CLS_CODE"),
                        enroll_set.getDate("ENROLL_DATE"),
                        enroll_set.getFloat("ENROLL_FINAL"),
                        enroll_set.getFloat("ENROLL_ASSESS"),
                        enroll_set.getFloat("ENROLL_MIDTERM")
                );
                enroll.setNo(++no);
                enroll.setCrsName(enroll_set.getString("CRS_TITLE"));
                enroll_list.add(enroll);
            }
            ObservableList<Enroll_Alt1> enroll_obs = FXCollections.observableArrayList(enroll_list);
            col_no.setCellValueFactory(new PropertyValueFactory<>("no"));
            col_crs.setCellValueFactory(new PropertyValueFactory<>("crsName"));
            col_cls_code.setCellValueFactory(new PropertyValueFactory<>("CLS_CODE"));
            col_enroll_date.setCellValueFactory(new PropertyValueFactory<>("ENROLL_DATE"));
            
            tb_data.getItems().setAll(enroll_obs);
            this.closeConnection(preSql);
            
            this.openConnection();
            preSql = this.conn.prepareStatement("SELECT DISTINCT CLS_CODE, CLS_TIME, ROOM_CODE, A.SEM_CODE, CRS_CODE, CRS_TITLE, SEM_START_DATE, PROF_NUM\n" +
                "FROM (SELECT CLS_CODE, CLS_TIME, ROOM_CODE, SEM_CODE, course.CRS_CODE, CRS_TITLE, PROF_NUM\n" +
                "FROM class INNER JOIN course\n" +
                "ON class.CRS_CODE = course.CRS_CODE) AS A INNER JOIN semester\n" +
                "ON A.SEM_CODE = semester.SEM_CODE;");
            ResultSet class_set = this.getData(preSql);
            List<Cls_Alt1> class_list = new ArrayList<>();
            int no1 = 0;
            while (class_set.next()) {
                Cls_Alt1 cls = new Cls_Alt1(
                        class_set.getString("CLS_CODE"),
                        class_set.getString("CLS_TIME"),
                        class_set.getString("CRS_CODE"),
                        class_set.getString("ROOM_CODE"),
                        class_set.getString("SEM_CODE"),
                        class_set.getInt("PROF_NUM")
                );
                cls.setNo(++no1);
                cls.setCrsName(class_set.getString("CRS_TITLE"));
                cls.setSemStart(class_set.getDate("SEM_START_DATE"));
                class_list.add(cls);
            }
            ObservableList<Cls_Alt1> class_obs = FXCollections.observableArrayList(class_list);
            col_no1.setCellValueFactory(new PropertyValueFactory<>("no"));
            col_crs1.setCellValueFactory(new PropertyValueFactory<>("crsName"));
            col_cls_code1.setCellValueFactory(new PropertyValueFactory<>("CLS_CODE"));
            col_enroll_date1.setCellValueFactory(new PropertyValueFactory<>("semStart"));
            
            tb_data1.getItems().setAll(class_obs);
            this.closeConnection(preSql);
            
            this.openConnection();
            preSql = this.conn.prepareStatement("SELECT enroll.STU_NUM, STU_LNAME, STU_FNAME, STU_MNAME, STU_EMAIL, STU_DOB, STU_GENDER, DEPT_CODE, MGTCLS_CODE, CLS_CODE\n" +
                "FROM student INNER JOIN enroll\n" +
                "ON student.STU_NUM = enroll.STU_NUM;");
            ResultSet stu_set = this.getData(preSql);
            List<Stu_Alt> stu_list = new ArrayList<>();
            int no2 = 0;
            while (stu_set.next()) {
                Stu_Alt stu = new Stu_Alt(
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
                stu.setNo(++no2);
                stu.setName();
                stu.setClsCode(stu_set.getString("CLS_CODE"));
                stu_list.add(stu);
            }
            ObservableList<Stu_Alt> stu_obs = FXCollections.observableArrayList(stu_list);
            col_no2.setCellValueFactory(new PropertyValueFactory<>("no"));
            col_stu.setCellValueFactory(new PropertyValueFactory<>("name"));
            col_stunum.setCellValueFactory(new PropertyValueFactory<>("STU_NUM"));
            col_cls_code2.setCellValueFactory(new PropertyValueFactory<>("clsCode"));
            
            tb_data2.getItems().setAll(stu_obs);
            this.closeConnection(preSql);
            
            this.openConnection();
            preSql = this.conn.prepareStatement("SELECT * FROM CLASS");
            ResultSet cls_set = this.getData(preSql);
            List<String> cls_options = new ArrayList<String>();
            while (cls_set.next()) {
                Cls cls = new Cls(
                        cls_set.getString("CLS_CODE"),
                        cls_set.getString("CLS_TIME"),
                        cls_set.getString("CRS_CODE"),
                        cls_set.getString("ROOM_CODE"),
                        cls_set.getString("SEM_CODE"),
                        cls_set.getInt("PROF_NUM")
                );
                cls_options.add(cls.getCLS_CODE());
                this.cls_list.add(cls);
            }
            this.closeConnection(preSql);
            ClsChooser.getItems().addAll(cls_options);
            ClsChooser.getItems().add(chooseAll);
            
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
                                page = "StuSche";
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
            
            Callback<TableColumn<Cls, Void>, TableCell<Cls, Void>> buttonCellFactory1 = new Callback<>() {
                @Override
                public TableCell<Cls, Void> call(TableColumn<Cls, Void> param) {
                    final TableCell<Cls, Void> cell = new TableCell<>() {
                        private final JFXButton button = new JFXButton();

                        {
                            button.setButtonType(JFXButton.ButtonType.FLAT);
                            button.setOnAction(event -> {
                                // Get the item associated with the current row
                                Cls cls = getTableView().getItems().get(getIndex());
                                cls_detail = cls;
                                try {
                                    App.setRoot("DetailCls");
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
            col_detail_cls.setCellFactory(buttonCellFactory1);
            
        } catch (SQLException ex) {
            Logger.getLogger(StuScheController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    void SearchResult(ActionEvent event) {
        try {
            this.openConnection();
            PreparedStatement preSql = this.conn.prepareStatement("SELECT DISTINCT CLS_CODE, CLS_TIME, ROOM_CODE, A.SEM_CODE, CRS_CODE, CRS_TITLE, SEM_START_DATE, PROF_NUM\n" +
                "FROM (SELECT CLS_CODE, CLS_TIME, ROOM_CODE, SEM_CODE, course.CRS_CODE, CRS_TITLE, PROF_NUM\n" +
                "FROM class INNER JOIN course\n" +
                "ON class.CRS_CODE = course.CRS_CODE) AS A INNER JOIN semester\n" +
                "ON A.SEM_CODE = semester.SEM_CODE WHERE CLS_CODE LIKE ?\n" +
                "ORDER BY CLS_CODE;");
            preSql.setString(1, "%" + in_search.getText() + "%");
            ResultSet class_set = this.getData(preSql);
            List<Cls_Alt1> class_list = new ArrayList<>();
            int no1 = 0;
            while (class_set.next()) {
                Cls_Alt1 cls = new Cls_Alt1(
                        class_set.getString("CLS_CODE"),
                        class_set.getString("CLS_TIME"),
                        class_set.getString("CRS_CODE"),
                        class_set.getString("ROOM_CODE"),
                        class_set.getString("SEM_CODE"),
                        class_set.getInt("PROF_NUM")
                );
                cls.setNo(++no1);
                cls.setCrsName(class_set.getString("CRS_TITLE"));
                cls.setSemStart(class_set.getDate("SEM_START_DATE"));
                class_list.add(cls);
            }
            ObservableList<Cls_Alt1> class_obs = FXCollections.observableArrayList(class_list);
            col_no1.setCellValueFactory(new PropertyValueFactory<>("no"));
            col_crs1.setCellValueFactory(new PropertyValueFactory<>("crsName"));
            col_cls_code1.setCellValueFactory(new PropertyValueFactory<>("CLS_CODE"));
            col_enroll_date1.setCellValueFactory(new PropertyValueFactory<>("semStart"));
            
            tb_data1.getItems().setAll(class_obs);
            this.closeConnection(preSql);
        } catch (SQLException ex) {
            Logger.getLogger(StuMainMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void displayStuTable(Cls cls) {
        //Get students data
        PreparedStatement preSql;
        try {
            this.openConnection();
            if(cls != null)
            {
                preSql = this.conn.prepareStatement("SELECT enroll.STU_NUM, STU_LNAME, STU_FNAME, STU_MNAME, STU_EMAIL, STU_DOB, STU_GENDER, DEPT_CODE, MGTCLS_CODE, CLS_CODE\n" +
                    "FROM student INNER JOIN enroll\n" +
                    "ON student.STU_NUM = enroll.STU_NUM\n" +
                    "WHERE CLS_CODE = ?;");
                preSql.setString(1, cls.getCLS_CODE());
            }
            else
            {
                preSql = this.conn.prepareStatement("SELECT enroll.STU_NUM, STU_LNAME, STU_FNAME, STU_MNAME, STU_EMAIL, STU_DOB, STU_GENDER, DEPT_CODE, MGTCLS_CODE, CLS_CODE\n" +
                    "FROM student INNER JOIN enroll\n" +
                    "ON student.STU_NUM = enroll.STU_NUM;");
            }
            ResultSet stu_set = this.getData(preSql);
            List<Stu_Alt> stu_list = new ArrayList<>();
            int no2 = 0;
            while (stu_set.next()) {
                Stu_Alt stu = new Stu_Alt(
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
                stu.setNo(++no2);
                stu.setName();
                stu.setClsCode(stu_set.getString("CLS_CODE"));
                stu_list.add(stu);
            }
            ObservableList<Stu_Alt> stu_obs = FXCollections.observableArrayList(stu_list);
            col_no2.setCellValueFactory(new PropertyValueFactory<>("no"));
            col_stu.setCellValueFactory(new PropertyValueFactory<>("name"));
            col_stunum.setCellValueFactory(new PropertyValueFactory<>("STU_NUM"));
            col_cls_code2.setCellValueFactory(new PropertyValueFactory<>("clsCode"));
            
            tb_data2.getItems().setAll(stu_obs);
            this.closeConnection(preSql);
        } catch (SQLException ex) {
            Logger.getLogger(StuMainMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
