package Controller;

import Model.Cls;
import Model.Crs;
import Model.Dept;
import Model.Mgtcls;
import Model.Pre;
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
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Tab;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.util.Callback;

public class AdminScheController extends BaseMainMenuController {
    
    public class Mgtcls_Alt extends Mgtcls {
        private String PROF_NAME;

        public Mgtcls_Alt(String MGTCLS_CODE, int PROF_NUM, String SEM_CODE) {
            super(MGTCLS_CODE, PROF_NUM, SEM_CODE);
        }

        public String getPROF_NAME() {
            return PROF_NAME;
        }

        public void setPROF_NAME(String PROF_NAME) {
            this.PROF_NAME = PROF_NAME;
        }  
    }
    
    public class Stu_Alt8 extends Stu {
        
        private String name;
        
        public Stu_Alt8(int STU_NUM, String STU_LNAME, String STU_FNAME, String STU_INTIAL, String STU_EMAIL, Date STU_DOB, String STU_GENDER, String DEPT_CODE, String MGTCLS_CODE) {
            super(STU_NUM, STU_LNAME, STU_FNAME, STU_INTIAL, STU_EMAIL, STU_DOB, STU_GENDER, DEPT_CODE, MGTCLS_CODE);
        }
        
        public String getName() {
            return name;
        }

        public void setName() {
            name = STU_LNAME + " " + STU_MNAME + " " + STU_FNAME;
        }
    }
    
    public class Cls_Alt8 extends Cls {
        
        private String crsName;
        
        private String deptCode;
        
        private int stuSum;
        
        public Cls_Alt8(String CLS_CODE, String CLS_TIME, String CRS_CODE, String ROOM_CODE, String SEM_CODE, int PROF_NUM) {
            super(CLS_CODE, CLS_TIME, CRS_CODE, ROOM_CODE, SEM_CODE, PROF_NUM);
        }

        public String getCrsName() {
            return crsName;
        }

        public void setCrsName(String crsName) {
            this.crsName = crsName;
        }

        public String getDeptCode() {
            return deptCode;
        }

        public void setDeptCode(String deptCode) {
            this.deptCode = deptCode;
        }

        public int getStuSum() {
            return stuSum;
        }

        public void setStuSum(int stuSum) {
            this.stuSum = stuSum;
        }
        
    }
    
    public class Pre_Alt extends Pre {
        
        private String deptCode;
        
        public Pre_Alt(String PRE_TAKE, String CRS_REQ_PRE) {
            super(PRE_TAKE, CRS_REQ_PRE);
        }

        public String getDeptCode() {
            return deptCode;
        }

        public void setDeptCode(String deptCode) {
            this.deptCode = deptCode;
        }
        
    }
    
    @FXML
    private Pane Avatar;

    @FXML
    private JFXButton ClsButton;

    @FXML
    private Pane Cls_Ic;

    @FXML
    private Pane Dept_Ic;

    @FXML
    private Text Crs;

    @FXML
    private Text Dept;

    @FXML
    private Text Dept1;

    @FXML
    private Text Dept2;
    
    @FXML
    private Pane HuceLogo;

    @FXML
    private JFXButton Logout_btn;

    @FXML
    private Text Cls;
    
    @FXML
    private Pane Rem_Ic;

    @FXML
    private Pane Ret_Ic;

    @FXML
    private Pane UsrBckgr;

    @FXML
    private AnchorPane body;
    
    @FXML
    private TableColumn<?, ?> col_clscode;

    @FXML
    private TableColumn<?, ?> col_clscrs;
    
    @FXML
    private TableColumn<?, ?> col_cre;

    @FXML
    private TableColumn<?, ?> col_crscode;

    @FXML
    private TableColumn<?, ?> col_crscode1;

    @FXML
    private TableColumn<?, ?> col_crsname;

    @FXML
    private TableColumn<?, ?> col_crspre;
    
    @FXML
    private TableColumn<?, ?> col_dept1;

    @FXML
    private TableColumn<?, ?> col_dept2;

    @FXML
    private TableColumn<?, ?> col_dept3;

    @FXML
    private TableColumn<?, ?> col_stusum;

    @FXML
    private TableColumn<Stu, Void> col_delete;
    
    @FXML
    private TableColumn<Crs, Void> col_delete1;

    @FXML
    private TableColumn<Cls, Void> col_delete2;

    @FXML
    private TableColumn<Pre, Void> col_delete3;

    @FXML
    private TableColumn<Stu, Void> col_edit;
    
    @FXML
    private TableColumn<Crs, Void> col_edit1;

    @FXML
    private TableColumn<Cls, Void> col_edit2;

    @FXML
    private TableColumn<Pre, Void> col_edit3;

    @FXML
    private TableColumn<?, ?> col_mgtcls;
    
    @FXML
    private TableColumn<?, ?> col_dept;
    
    @FXML
    private TableColumn<?, ?> col_stuname;

    @FXML
    private TableColumn<?, ?> col_msv;
    
    @FXML
    private JFXComboBox<String> cb_cls;
    
    @FXML
    private TableView<Stu_Alt8> tb_stu;
    
    @FXML
    private TableView<Cls_Alt8> tb_cls;

    @FXML
    private TableView<Crs> tb_crs;
    
    @FXML
    private TableView<Pre_Alt> tb_pre;
    
    @FXML
    private JFXComboBox<Dept> cb_dept;
    
    @FXML
    private JFXComboBox<Crs> cb_crs;

    @FXML
    private JFXComboBox<Dept> cb_dept1;

    @FXML
    private JFXComboBox<Dept> cb_dept2;
    
    @FXML
    private TableColumn<?, ?> col_code;
    
    @FXML
    private TableColumn<?, ?> col_prof_code;

    @FXML
    private TableColumn<?, ?> col_prof_name;

    @FXML
    private TableColumn<?, ?> col_sem;
    
    @FXML
    private TableColumn<Mgtcls, Void> col_delete_mgtcls;

    @FXML
    private TableColumn<Mgtcls, Void> col_edit_mgtcls;

    @FXML
    private TableView<Mgtcls_Alt> tb_mgtcls;
    
    @FXML
    private Text txt_name;
    
    @FXML
    private Tab tab_mgtcls;

    @FXML
    private Tab tab_stu;
    
    private List<Mgtcls> mgtcls_list = new ArrayList<>();
    private List<Dept> dept_list = new ArrayList<>();
    private List<Dept> dept_list1 = new ArrayList<>();
    private List<Dept> dept_list2 = new ArrayList<>();
    private List<Crs> crs_list = new ArrayList<>();

    protected static Mgtcls mgtcls_edit;
    protected static Stu stu_edit;
    protected static Crs crs_edit;
    protected static Cls cls_edit;
    protected static Pre pre_edit;
    
    @FXML
    void ToDeptProfInfo(MouseEvent event) throws IOException {
        App.setRoot("AdminDeptEmpInfo");
    }

    @FXML
    void ToMain(MouseEvent event) {
        try {
            App.setRoot("AdminMainMenu");
        } catch (IOException ex) {
            Logger.getLogger(AdminScheController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void ToTask(MouseEvent event) throws IOException {
        App.setRoot("AdminRem");
    }
    
    @FXML
    void switchCrs() {
        try {
            this.openConnection();
            PreparedStatement preSql = this.conn.prepareStatement("SELECT * FROM department");
            ResultSet dept_set = this.getData(preSql);
            while (dept_set.next()) {
                Dept dept = new Dept(
                        dept_set.getString("DEPT_CODE"),
                        dept_set.getString("DEPT_NAME"),
                        dept_set.getInt("PROF_NUM")
                );
                dept_list1.add(dept);
            }
            this.closeConnection(preSql);
            cb_dept1.getItems().addAll(dept_list1);
        } catch (SQLException ex) {
            Logger.getLogger(AdminScheController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    void switchCls() {
        try {
            this.openConnection();
            PreparedStatement preSql = this.conn.prepareStatement("SELECT * FROM course");
            ResultSet course_set = this.getData(preSql);
            while (course_set.next()) {
                Crs course = new Crs(                      
                        course_set.getString("CRS_CODE"),
                        course_set.getString("DEPT_CODE"),
                        course_set.getString("CRS_TITLE"),
                        course_set.getInt("CRS_CREDIT"),
                        course_set.getString("CRS_DESC"),
                        course_set.getString("CRS_COMPULSORY")
                );
                crs_list.add(course);
            }
            this.closeConnection(preSql);
            cb_crs.getItems().addAll(crs_list);
        } catch (SQLException ex) {
            Logger.getLogger(AdminScheController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    void switchPre() {
        try {
            this.openConnection();
            PreparedStatement preSql = this.conn.prepareStatement("SELECT * FROM department");
            ResultSet dept_set = this.getData(preSql);
            while (dept_set.next()) {
                Dept dept = new Dept(
                        dept_set.getString("DEPT_CODE"),
                        dept_set.getString("DEPT_NAME"),
                        dept_set.getInt("PROF_NUM")
                );
                dept_list2.add(dept);
            }
            this.closeConnection(preSql);
            cb_dept2.getItems().addAll(dept_list2);
        } catch (SQLException ex) {
            Logger.getLogger(AdminScheController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    void switchMgtcls() {
        try {
            this.openConnection();
            PreparedStatement preSql = this.conn.prepareStatement("SELECT * FROM department");
            ResultSet dept_set = this.getData(preSql);
            while (dept_set.next()) {
                Dept dept = new Dept(
                        dept_set.getString("DEPT_CODE"),
                        dept_set.getString("DEPT_NAME"),
                        dept_set.getInt("PROF_NUM")
                );
                dept_list.add(dept);
            }
            this.closeConnection(preSql);
            cb_dept.getItems().addAll(dept_list);
        } catch (SQLException ex) {
            Logger.getLogger(AdminScheController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    void chooseCrs(ActionEvent event) {
        Crs.setText(cb_crs.getValue().toString());
        PreparedStatement preSql;
        try {
            this.openConnection();
            preSql = this.conn.prepareStatement("SELECT A.*, CRS_TITLE, DEPT_CODE\n" +
                "FROM (SELECT class.*, COUNT(STU_NUM) AS stuSum\n" +
                "FROM enroll RIGHT JOIN class\n" +
                "ON enroll.CLS_CODE = class.CLS_CODE\n" +
                "GROUP BY CLS_CODE) AS A INNER JOIN course\n" +
                "ON A.CRS_CODE = course.CRS_CODE WHERE course.CRS_CODE = ?;");
            preSql.setString(1, this.crs_list.get(cb_crs.getSelectionModel().getSelectedIndex()).getCRS_CODE());
            ResultSet class_set = this.getData(preSql);
            List<Cls_Alt8> class_list = new ArrayList<>();
            while (class_set.next()) {
                Cls_Alt8 cls = new Cls_Alt8(
                        class_set.getString("CLS_CODE"),
                        class_set.getString("CLS_TIME"),
                        class_set.getString("CRS_CODE"),
                        class_set.getString("ROOM_CODE"),
                        class_set.getString("SEM_CODE"),
                        class_set.getInt("PROF_NUM")
                );
                cls.setCrsName(class_set.getString("CRS_TITLE"));
                cls.setStuSum(class_set.getInt("stuSum"));
                cls.setDeptCode(class_set.getString("DEPT_CODE"));
                class_list.add(cls);
            }
            this.closeConnection(preSql);
            
            ObservableList<Cls_Alt8> class_obs = FXCollections.observableArrayList(class_list);
            col_clscode.setCellValueFactory(new PropertyValueFactory<>("CLS_CODE"));
            col_clscrs.setCellValueFactory(new PropertyValueFactory<>("crsName"));
            col_stusum.setCellValueFactory(new PropertyValueFactory<>("stuSum"));
            col_dept2.setCellValueFactory(new PropertyValueFactory<>("deptCode"));
            
            tb_cls.getItems().setAll(class_obs);
            
            Callback<TableColumn<Cls, Void>, TableCell<Cls, Void>> btnEdit = new Callback<>() {
                @Override
                public TableCell<Cls, Void> call(TableColumn<Cls, Void> param) {
                    final TableCell<Cls, Void> cell = new TableCell<>() {
                        private final JFXButton button = new JFXButton();

                        {
                            button.setButtonType(JFXButton.ButtonType.FLAT);
                            button.setOnAction(event -> {
                                // Get the item associated with the current row
                                Cls cls = getTableView().getItems().get(getIndex());
                                cls_edit = cls;
                                try {
                                    App.setRoot("UpdateClass");
                                } catch (IOException ex) {
                                    Logger.getLogger(AdminScheController.class.getName()).log(Level.SEVERE, null, ex);
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
            col_edit2.setCellFactory(btnEdit);
            
            Callback<TableColumn<Cls, Void>, TableCell<Cls, Void>> btnDel = new Callback<>() {
                @Override
                public TableCell<Cls, Void> call(TableColumn<Cls, Void> param) {
                    final TableCell<Cls, Void> cell = new TableCell<>() {
                        private final JFXButton button = new JFXButton();

                        {
                            button.setButtonType(JFXButton.ButtonType.FLAT);
                            button.setOnAction(event -> {
                                // Get the item associated with the current row
                                Cls cls = getTableView().getItems().get(getIndex());
                                deleteCls(cls.getCLS_CODE());
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
            col_delete2.setCellFactory(btnDel);
            
        } catch (SQLException ex) {
            Logger.getLogger(AdminScheController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void chooseDept1(ActionEvent event) {
        Dept1.setText(cb_dept1.getValue().toString());
        PreparedStatement preSql;
        try {
            this.openConnection();
            preSql = this.conn.prepareStatement("SELECT * FROM course\n" +
                                                "WHERE DEPT_CODE = ?;");
            preSql.setString(1, this.dept_list.get(cb_dept1.getSelectionModel().getSelectedIndex()).getDEPT_CODE());
            ResultSet course_set = this.getData(preSql);
            List<Crs> course_list = new ArrayList<>();
            while (course_set.next()) {
                Crs course = new Crs(                      
                        course_set.getString("CRS_CODE"),
                        course_set.getString("DEPT_CODE"),
                        course_set.getString("CRS_TITLE"),
                        course_set.getInt("CRS_CREDIT"),
                        course_set.getString("CRS_DESC"),
                        course_set.getString("CRS_COMPULSORY")
                );
                course_list.add(course);
            }
            this.closeConnection(preSql);
            
            ObservableList<Crs> course_obs = FXCollections.observableArrayList(course_list);
            col_crscode.setCellValueFactory(new PropertyValueFactory<>("CRS_CODE"));
            col_crsname.setCellValueFactory(new PropertyValueFactory<>("CRS_TITLE"));
            col_cre.setCellValueFactory(new PropertyValueFactory<>("CRS_CREDIT"));
            col_dept1.setCellValueFactory(new PropertyValueFactory<>("DEPT_CODE"));
            
            tb_crs.getItems().setAll(course_obs);
            
            Callback<TableColumn<Crs, Void>, TableCell<Crs, Void>> btnEdit = new Callback<>() {
                @Override
                public TableCell<Crs, Void> call(TableColumn<Crs, Void> param) {
                    final TableCell<Crs, Void> cell = new TableCell<>() {
                        private final JFXButton button = new JFXButton();

                        {
                            button.setButtonType(JFXButton.ButtonType.FLAT);
                            button.setOnAction(event -> {
                                // Get the item associated with the current row
                                Crs crs = getTableView().getItems().get(getIndex());
                                crs_edit = crs;
                                try {
                                    App.setRoot("UpdateCourse");
                                } catch (IOException ex) {
                                    Logger.getLogger(AdminScheController.class.getName()).log(Level.SEVERE, null, ex);
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
            col_edit1.setCellFactory(btnEdit);
            
            Callback<TableColumn<Crs, Void>, TableCell<Crs, Void>> btnDel = new Callback<>() {
                @Override
                public TableCell<Crs, Void> call(TableColumn<Crs, Void> param) {
                    final TableCell<Crs, Void> cell = new TableCell<>() {
                        private final JFXButton button = new JFXButton();

                        {
                            button.setButtonType(JFXButton.ButtonType.FLAT);
                            button.setOnAction(event -> {
                                // Get the item associated with the current row
                                Crs crs = getTableView().getItems().get(getIndex());
                                deleteCrs(crs.getCRS_CODE());
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
            col_delete1.setCellFactory(btnDel);
            
        } catch (SQLException ex) {
            Logger.getLogger(AdminScheController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void chooseDept2(ActionEvent event) {
        Dept2.setText(cb_dept2.getValue().toString());
        PreparedStatement preSql;
        try {
            this.openConnection();
            preSql = this.conn.prepareStatement("SELECT *\n" +
                "FROM prerequisite INNER JOIN course\n" +
                "ON prerequisite.CRS_REQ_PRE = course.CRS_CODE\n" +
                "WHERE DEPT_CODE = ?;");
            preSql.setString(1, this.dept_list2.get(cb_dept2.getSelectionModel().getSelectedIndex()).getDEPT_CODE());
            ResultSet pre_set = this.getData(preSql);
            List<Pre_Alt> pre_list = new ArrayList<>();
            while (pre_set.next()) {
                Pre_Alt pre = new Pre_Alt(                      
                        pre_set.getString("PRE_TAKE"),
                        pre_set.getString("CRS_REQ_PRE")
                );
                pre.setDeptCode(pre_set.getString("DEPT_CODE"));
                pre_list.add(pre);
            }
            this.closeConnection(preSql);
            
            ObservableList<Pre_Alt> pre_obs = FXCollections.observableArrayList(pre_list);
            col_crscode1.setCellValueFactory(new PropertyValueFactory<>("CRS_REQ_PRE"));
            col_crspre.setCellValueFactory(new PropertyValueFactory<>("PRE_TAKE"));
            col_dept3.setCellValueFactory(new PropertyValueFactory<>("deptCode"));
            
            tb_pre.getItems().setAll(pre_obs);
            
            Callback<TableColumn<Pre, Void>, TableCell<Pre, Void>> btnEdit = new Callback<>() {
                @Override
                public TableCell<Pre, Void> call(TableColumn<Pre, Void> param) {
                    final TableCell<Pre, Void> cell = new TableCell<>() {
                        private final JFXButton button = new JFXButton();

                        {
                            button.setButtonType(JFXButton.ButtonType.FLAT);
                            button.setOnAction(event -> {
                                // Get the item associated with the current row
                                Pre pre = getTableView().getItems().get(getIndex());
                                pre_edit = pre;
                                try {
                                    App.setRoot("UpdatePre");
                                } catch (IOException ex) {
                                    Logger.getLogger(AdminScheController.class.getName()).log(Level.SEVERE, null, ex);
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
            col_edit3.setCellFactory(btnEdit);
            
            Callback<TableColumn<Pre, Void>, TableCell<Pre, Void>> btnDel = new Callback<>() {
                @Override
                public TableCell<Pre, Void> call(TableColumn<Pre, Void> param) {
                    final TableCell<Pre, Void> cell = new TableCell<>() {
                        private final JFXButton button = new JFXButton();

                        {
                            button.setButtonType(JFXButton.ButtonType.FLAT);
                            button.setOnAction(event -> {
                                // Get the item associated with the current row
                                Pre pre = getTableView().getItems().get(getIndex());
                                deletePre(pre.getPRE_TAKE(), pre.getCRS_CODE());
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
            col_delete3.setCellFactory(btnDel);
            
        } catch (SQLException ex) {
            Logger.getLogger(AdminScheController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    void chooseDept(ActionEvent event) {
        Dept.setText(cb_dept.getValue().toString());
        PreparedStatement preSql;
        try {
            this.openConnection();
            preSql = this.conn.prepareStatement("SELECT * FROM `management_class`\n" +
                                                "WHERE PROF_NUM IN (SELECT PROF_NUM FROM professor\n" +
                                                "                   WHERE DEPT_CODE = ?)");
            preSql.setString(1, this.dept_list.get(cb_dept.getSelectionModel().getSelectedIndex()).getDEPT_CODE());
            ResultSet mgtcls_set = this.getData(preSql);
            List<Mgtcls_Alt> mgtcls_list = new ArrayList<>();
            while (mgtcls_set.next()) {
                Mgtcls_Alt mgtcls = new Mgtcls_Alt(                      
                        mgtcls_set.getString("MGTCLS_CODE"),
                        mgtcls_set.getInt("PROF_NUM"),
                        mgtcls_set.getString("SEM_CODE")
                );
                preSql = this.conn.prepareStatement("SELECT * FROM employee WHERE EMP_NUM = ?");
                preSql.setInt(1, mgtcls.getPROF_NUM());
                ResultSet rs = this.getData(preSql);
                if (rs.next()) mgtcls.setPROF_NAME(rs.getString("EMP_LNAME") + " " + rs.getString("EMP_MNAME") + " " + rs.getString("EMP_FNAME"));
                else mgtcls.setPROF_NAME("");
                mgtcls_list.add(mgtcls);
            }
            this.closeConnection(preSql);
            
            ObservableList<Mgtcls_Alt> mgtcls_obs = FXCollections.observableArrayList(mgtcls_list);
            col_code.setCellValueFactory(new PropertyValueFactory<>("MGTCLS_CODE"));
            col_prof_code.setCellValueFactory(new PropertyValueFactory<>("PROF_NUM"));
            col_prof_name.setCellValueFactory(new PropertyValueFactory<>("PROF_NAME"));
            col_sem.setCellValueFactory(new PropertyValueFactory<>("SEM_CODE"));
            Callback<TableColumn<Mgtcls, Void>, TableCell<Mgtcls, Void>> btnEdit = new Callback<>() {
                @Override
                public TableCell<Mgtcls, Void> call(TableColumn<Mgtcls, Void> param) {
                    final TableCell<Mgtcls, Void> cell = new TableCell<>() {
                        private final JFXButton button = new JFXButton();

                        {
                            button.setButtonType(JFXButton.ButtonType.FLAT);
                            button.setOnAction(event -> {
                                // Get the item associated with the current row
                                Mgtcls mgtcls = getTableView().getItems().get(getIndex());
                                mgtcls_edit = mgtcls;
                                try {
                                    App.setRoot("UpdateMgtcls");
                                } catch (IOException ex) {
                                    Logger.getLogger(AdminScheController.class.getName()).log(Level.SEVERE, null, ex);
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
            col_edit_mgtcls.setCellFactory(btnEdit);
            
            Callback<TableColumn<Mgtcls, Void>, TableCell<Mgtcls, Void>> btnDel = new Callback<>() {
                @Override
                public TableCell<Mgtcls, Void> call(TableColumn<Mgtcls, Void> param) {
                    final TableCell<Mgtcls, Void> cell = new TableCell<>() {
                        private final JFXButton button = new JFXButton();

                        {
                            button.setButtonType(JFXButton.ButtonType.FLAT);
                            button.setOnAction(event -> {
                                // Get the item associated with the current row
                                Mgtcls mgtcls = getTableView().getItems().get(getIndex());
                                deleteMgtcls(mgtcls.getMGTCLS_CODE());
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
            col_delete_mgtcls.setCellFactory(btnDel);
            
            tb_mgtcls.getItems().setAll(mgtcls_obs);
        } catch (SQLException ex) {
            Logger.getLogger(AdminScheController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void switchStu() {
        try {
            this.openConnection();
            PreparedStatement preSql = this.conn.prepareStatement("SELECT * FROM management_class");
            ResultSet mgtcls_set = this.getData(preSql);
            List<String> mgtcls_options = new ArrayList<String>();
            while (mgtcls_set.next()) {
                Mgtcls mgtcls = new Mgtcls(
                        mgtcls_set.getString("MGTCLS_CODE"),
                        mgtcls_set.getInt("PROF_NUM"),
                        mgtcls_set.getString("SEM_CODE")
                );
                mgtcls_options.add(mgtcls.getMGTCLS_CODE());
                mgtcls_list.add(mgtcls);
            }
            this.closeConnection(preSql);
            cb_cls.getItems().addAll(mgtcls_options);
        } catch (SQLException ex) {
            Logger.getLogger(AdminScheController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    void chooseCls(ActionEvent event) {
        Cls.setText(cb_cls.getValue());
        PreparedStatement preSql;
        try {
            this.openConnection();
            preSql = this.conn.prepareStatement("SELECT * FROM student WHERE MGTCLS_CODE = ?");
            preSql.setString(1, this.mgtcls_list.get(cb_cls.getSelectionModel().getSelectedIndex()).getMGTCLS_CODE());
            ResultSet rs = this.getData(preSql);
            List<Stu_Alt8> stu_list = new ArrayList<>();
            while (rs.next()) {
                Stu_Alt8 stu = new Stu_Alt8(
                        rs.getInt("STU_NUM"),                        
                        rs.getString("STU_LNAME"),
                        rs.getString("STU_FNAME"),
                        rs.getString("STU_MNAME"),
                        rs.getString("STU_EMAIL"),
                        rs.getDate("STU_DOB"),
                        rs.getString("STU_GENDER"),
                        rs.getString("DEPT_CODE"),
                        rs.getString("MGTCLS_CODE")
                );
                stu.setName();
                stu_list.add(stu);
            }
            this.closeConnection(preSql);
            
            ObservableList<Stu_Alt8> stu_obs = FXCollections.observableArrayList(stu_list);
            col_msv.setCellValueFactory(new PropertyValueFactory<>("STU_NUM"));
            col_stuname.setCellValueFactory(new PropertyValueFactory<>("name"));
            col_dept.setCellValueFactory(new PropertyValueFactory<>("DEPT_CODE"));
            col_mgtcls.setCellValueFactory(new PropertyValueFactory<>("MGTCLS_CODE"));
            Callback<TableColumn<Stu, Void>, TableCell<Stu, Void>> btnEdit = new Callback<>() {
                @Override
                public TableCell<Stu, Void> call(TableColumn<Stu, Void> param) {
                    final TableCell<Stu, Void> cell = new TableCell<>() {
                        private final JFXButton button = new JFXButton();

                        {
                            button.setButtonType(JFXButton.ButtonType.FLAT);
                            button.setOnAction(event -> {
                                // Get the item associated with the current row
                                Stu stu = getTableView().getItems().get(getIndex());
                                stu_edit = stu;
                                try {
                                    App.setRoot("UpdateStudentInfo");
                                } catch (IOException ex) {
                                    Logger.getLogger(AdminScheController.class.getName()).log(Level.SEVERE, null, ex);
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
            col_edit.setCellFactory(btnEdit);
            
            Callback<TableColumn<Stu, Void>, TableCell<Stu, Void>> btnDel = new Callback<>() {
                @Override
                public TableCell<Stu, Void> call(TableColumn<Stu, Void> param) {
                    final TableCell<Stu, Void> cell = new TableCell<>() {
                        private final JFXButton button = new JFXButton();

                        {
                            button.setButtonType(JFXButton.ButtonType.FLAT);
                            button.setOnAction(event -> {
                                // Get the item associated with the current row
                                Stu stu = getTableView().getItems().get(getIndex());
                                deleteStu(stu.getSTU_NUM());
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
            col_delete.setCellFactory(btnDel);
            
            tb_stu.getItems().setAll(stu_obs);
        } catch (SQLException ex) {
            Logger.getLogger(AdminScheController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txt_name.setText(this.usr.getEmp().getEMP_LNAME() + " " + this.usr.getEmp().getEMP_MNAME() + " " + this.usr.getEmp().getEMP_FNAME());
        switchStu();
        switchMgtcls();
        switchCrs();
        switchPre();
        switchCls();
    }
    
    @FXML
    void addStu(ActionEvent event) {
        try {
            App.setRoot("AddStudent");
        } catch (IOException ex) {
            Logger.getLogger(AdminScheController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    void addMgtcls(ActionEvent event) {
        try {
            App.setRoot("AddMgtcls");
        } catch (IOException ex) {
            Logger.getLogger(AdminScheController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void addCourse(ActionEvent event) {
        try {
            App.setRoot("AddCourse");
        } catch (IOException ex) {
            Logger.getLogger(AdminScheController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    void addCls(ActionEvent event) {
        try {
            App.setRoot("AddClass");
        } catch (IOException ex) {
            Logger.getLogger(AdminScheController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    void addPre(ActionEvent event) {
        try {
            App.setRoot("AddPre");
        } catch (IOException ex) {
            Logger.getLogger(AdminScheController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void deleteStu(int STU_NUM) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

        alert.setTitle("Xác nhận xóa");

        alert.setHeaderText(null);

        alert.setContentText("Bạn có chắc chắn muốn xóa không?");

        alert.showAndWait();

        if (alert.getResult() == ButtonType.OK) {
            PreparedStatement preSql;
            try {
                this.openConnection();
                preSql = this.conn.prepareStatement("DELETE FROM student WHERE STU_NUM = ?");
                preSql.setInt(1, STU_NUM);

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
    
    public void deleteMgtcls(String MGTCLS_CODE) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

        alert.setTitle("Xác nhận xóa");

        alert.setHeaderText(null);

        alert.setContentText("Bạn có chắc chắn muốn xóa không?");

        alert.showAndWait();

        if (alert.getResult() == ButtonType.OK) {
            PreparedStatement preSql;
            try {
                this.openConnection();
                preSql = this.conn.prepareStatement("DELETE FROM management_class WHERE MGTCLS_CODE = ?");
                preSql.setString(1, MGTCLS_CODE);

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
    
    public void deleteCrs(String CRS_CODE) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

        alert.setTitle("Xác nhận xóa");

        alert.setHeaderText(null);

        alert.setContentText("Bạn có chắc chắn muốn xóa không?");

        alert.showAndWait();

        if (alert.getResult() == ButtonType.OK) {
            PreparedStatement preSql;
            try {
                this.openConnection();
                preSql = this.conn.prepareStatement("DELETE FROM course WHERE CRS_CODE = ?");
                preSql.setString(1, CRS_CODE);

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
    
    public void deleteCls(String CLS_CODE) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

        alert.setTitle("Xác nhận xóa");

        alert.setHeaderText(null);

        alert.setContentText("Bạn có chắc chắn muốn xóa không?");

        alert.showAndWait();

        if (alert.getResult() == ButtonType.OK) {
            PreparedStatement preSql;
            try {
                this.openConnection();
                preSql = this.conn.prepareStatement("DELETE FROM class WHERE CLS_CODE = ?");
                preSql.setString(1, CLS_CODE);

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
    
    public void deletePre(String PRE_TAKE, String CRS_CODE) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

        alert.setTitle("Xác nhận xóa");

        alert.setHeaderText(null);

        alert.setContentText("Bạn có chắc chắn muốn xóa không?");

        alert.showAndWait();

        if (alert.getResult() == ButtonType.OK) {
            PreparedStatement preSql;
            try {
                this.openConnection();
                preSql = this.conn.prepareStatement("DELETE FROM prerequisite WHERE PRE_TAKE = ? AND CRS_CODE = ?");
                preSql.setString(1, PRE_TAKE);                
                preSql.setString(2, CRS_CODE);

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
    
}
