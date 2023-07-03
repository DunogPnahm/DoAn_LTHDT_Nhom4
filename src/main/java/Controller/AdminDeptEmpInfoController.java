package Controller;

import Model.Dept;
import Model.Emp;
import Model.Prof;
import com.jfoenix.controls.JFXButton;
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

public class AdminDeptEmpInfoController extends BaseMainMenuController {

    public class Dept_Alt extends Dept {
        private String PROF_NAME;

        public Dept_Alt(String DEPT_CODE, String DEPT_NAME, int PROF_NUM) {
            super(DEPT_CODE, DEPT_NAME, PROF_NUM);
        }

        public String getPROF_NAME() {
            return PROF_NAME;
        }

        public void setPROF_NAME(String PROF_NAME) {
            this.PROF_NAME = PROF_NAME;
        }
        
        
    }
    
    public class Emp_Alt extends Emp {
        private String empName;

        public Emp_Alt(int EMP_NUM, String EMP_LNAME, String EMP_FNAME, String EMP_MNAME, String EMP_EMAIL, String EMP_JOBCODE, Date EMP_HIREDATE, Date EMP_DOB) {
            super(EMP_NUM, EMP_LNAME, EMP_FNAME, EMP_MNAME, EMP_EMAIL, EMP_JOBCODE, EMP_HIREDATE, EMP_DOB);
        }

        public String getEmpName() {
            return empName;
        }

        public void setEmpName() {
            this.empName = EMP_LNAME + " " + EMP_MNAME + " " + EMP_FNAME;
        }
        
    }
    
    public class Prof_Alt extends Prof {
        private String profName;
        
        private String deptCode;

        public Prof_Alt(int PROF_NUM, String PROF_SPECIALTY, String DEPT_CODE, String PROF_RANK, String EMP_LNAME, String EMP_FNAME, String EMP_MNAME, String EMP_JOBCODE, Date EMP_HIREDATE, Date EMP_DOB, String EMP_EMAIL) {
            super(PROF_NUM, PROF_SPECIALTY, DEPT_CODE, PROF_RANK, EMP_LNAME, EMP_FNAME, EMP_MNAME, EMP_JOBCODE, EMP_HIREDATE, EMP_DOB, EMP_EMAIL);
        }

        public String getProfName() {
            return profName;
        }

        public void setProfName() {
            this.profName = EMP_LNAME + " " + EMP_MNAME + " " + EMP_FNAME;
        }

        public String getDeptCode() {
            return deptCode;
        }

        public void setDeptCode(String deptCode) {
            this.deptCode = deptCode;
        }
        
    }
    
    @FXML
    private TableColumn<Prof_Alt, Void> col_delete_prof;

    @FXML
    private TableColumn<?, ?> col_dept;
    
    @FXML
    private TableColumn<Prof_Alt, Void> col_edit_prof;

    @FXML
    private TableColumn<?, ?> col_profcode;

    @FXML
    private TableColumn<?, ?> col_profname;

    @FXML
    private TableColumn<?, ?> col_rank;
    
    @FXML
    private TableView<Prof_Alt> tb_prof;
    
    @FXML
    private Pane Avatar;

    @FXML
    private JFXButton ClsButton;

    @FXML
    private Pane Cls_Ic;

    @FXML
    private Pane Dept_Ic;

    @FXML
    private Pane HuceLogo;

    @FXML
    private JFXButton Logout_btn;

    @FXML
    private Pane Rem_Ic;

    @FXML
    private Pane Ret_Ic;

    @FXML
    private Pane UsrBckgr;

    @FXML
    private AnchorPane body;

    @FXML
    private TableColumn<?, ?> col_code;

    @FXML
    private TableColumn<Dept_Alt, Void> col_delete_dept;

    @FXML
    private TableColumn<Emp, Void> col_delete_emp;

    @FXML
    private TableColumn<Dept_Alt, Void> col_edit_dept;

    @FXML
    private TableColumn<Emp, Void> col_edit_emp;

    @FXML
    private TableColumn<?, ?> col_name;

    @FXML
    private TableColumn<?, ?> col_prof_code;

    @FXML
    private TableColumn<?, ?> col_prof_name;

    @FXML
    private TableView<Dept_Alt> tb_dept;
    
    @FXML
    private TableColumn<?, ?> col_emp_code;
    
    @FXML
    private TableColumn<?, ?> col_empname;

    @FXML
    private TableColumn<?, ?> col_hib;
    
    @FXML
    private TableColumn<?, ?> col_pos;
    
    @FXML
    private TableColumn<?, ?> col_dob;
    
    @FXML
    private TableView<Emp_Alt> tb_emp;
    
    @FXML
    private Text txt_name;
    
    protected static Dept_Alt dept_edit;
    protected static Emp emp_edit;
    protected static Prof prof_edit;
    
    @FXML
    void ToMain(MouseEvent event) {
        try {
            App.setRoot("AdminMainMenu");
        } catch (IOException ex) {
            Logger.getLogger(AdminScheController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    void ToSche(MouseEvent event) {
        try {
            App.setRoot("AdminSche");
        } catch (IOException ex) {
            Logger.getLogger(AdminScheController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    void ToTask(MouseEvent event) throws IOException {
        App.setRoot("AdminRem");
    }
    
    @FXML
    void addDept() {
        try {
            App.setRoot("AddDept");
        } catch (IOException ex) {
            Logger.getLogger(AdminDeptEmpInfoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    void addEmp() {
        try {
            App.setRoot("AddEmp");
        } catch (IOException ex) {
            Logger.getLogger(AdminDeptEmpInfoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    void addProf() {
        try {
            App.setRoot("AddProf");
        } catch (IOException ex) {
            Logger.getLogger(AdminDeptEmpInfoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    void switchDept() {
        PreparedStatement preSql;
        try {
            this.openConnection();
            preSql = this.conn.prepareStatement("SELECT department.*, employee.EMP_LNAME, employee.EMP_MNAME, employee.EMP_FNAME FROM department LEFT JOIN employee ON department.PROF_NUM = employee.EMP_NUM");
            ResultSet dept_set = this.getData(preSql);
            List<Dept_Alt> dept_list = new ArrayList<>();
            while (dept_set.next()) {
                Dept_Alt dept = new Dept_Alt(
                        dept_set.getString("DEPT_CODE"),                        
                        dept_set.getString("DEPT_NAME"),
                        dept_set.getInt("PROF_NUM")
                );
                dept.setPROF_NAME(dept_set.getString("EMP_LNAME") + " " + dept_set.getString("EMP_MNAME") + " " + dept_set.getString("EMP_FNAME"));
                dept_list.add(dept);
            }
            this.closeConnection(preSql);
            
            ObservableList<Dept_Alt> dept_obs = FXCollections.observableArrayList(dept_list);
            col_code.setCellValueFactory(new PropertyValueFactory<>("DEPT_CODE"));            
            col_name.setCellValueFactory(new PropertyValueFactory<>("DEPT_NAME"));
            col_prof_code.setCellValueFactory(new PropertyValueFactory<>("PROF_NUM"));
            col_prof_name.setCellValueFactory(new PropertyValueFactory<>("PROF_NAME"));
            
            Callback<TableColumn<Dept_Alt, Void>, TableCell<Dept_Alt, Void>> buttonEdit = new Callback<>() {
                @Override
                public TableCell<Dept_Alt, Void> call(TableColumn<Dept_Alt, Void> param) {
                    final TableCell<Dept_Alt, Void> cell = new TableCell<>() {
                        private final JFXButton button = new JFXButton();

                        {
                            button.setButtonType(JFXButton.ButtonType.FLAT);
                            button.setOnAction(event -> {
                                // Get the item associated with the current row
                                Dept_Alt dept = getTableView().getItems().get(getIndex());
                                dept_edit = dept;
                                try {
                                    App.setRoot("UpdateDept");
                                } catch (IOException ex) {
                                    Logger.getLogger(AdminDeptEmpInfoController.class.getName()).log(Level.SEVERE, null, ex);
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
                                Dept_Alt dept = getTableView().getItems().get(getIndex());
                                button.setText("Sửa");
                                setGraphic(button);
                            }
                        }
                    };
                    return cell;
                }
            };
            col_edit_dept.setCellFactory(buttonEdit);
            
            Callback<TableColumn<Dept_Alt, Void>, TableCell<Dept_Alt, Void>> buttonDel = new Callback<>() {
                @Override
                public TableCell<Dept_Alt, Void> call(TableColumn<Dept_Alt, Void> param) {
                    final TableCell<Dept_Alt, Void> cell = new TableCell<>() {
                        private final JFXButton button = new JFXButton();

                        {
                            button.setButtonType(JFXButton.ButtonType.FLAT);
                            button.setOnAction(event -> {
                                // Get the item associated with the current row
                                Dept dept = getTableView().getItems().get(getIndex());
                                deleteDept(dept.getDEPT_CODE());
                            });
                        }

                        @Override
                        protected void updateItem(Void item, boolean empty) {
                            super.updateItem(item, empty);
                            if (empty) {
                                setGraphic(null);
                            } else {
                                // Get the item associated with the current row
                                Dept_Alt dept = getTableView().getItems().get(getIndex());
                                button.setText("Xóa");
                                setGraphic(button);
                            }
                        }
                    };
                    return cell;
                }
            };
            col_delete_dept.setCellFactory(buttonDel);
            
            tb_dept.getItems().setAll(dept_obs);
        } catch (SQLException ex) {
            Logger.getLogger(AdminDeptEmpInfoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    public void switchProf() {
        PreparedStatement preSql;
        try {
            this.openConnection();
            preSql = this.conn.prepareStatement("SELECT *\n" +
                "FROM professor INNER JOIN employee\n" +
                "ON professor.PROF_NUM = employee.EMP_NUM;");
            ResultSet prof_set = this.getData(preSql);
            List<Prof_Alt> prof_list = new ArrayList<>();
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
                prof.setProfName();
                prof_list.add(prof);
            }
            this.closeConnection(preSql);
            
            ObservableList<Prof_Alt> prof_obs = FXCollections.observableArrayList(prof_list);
            col_profcode.setCellValueFactory(new PropertyValueFactory<>("PROF_NUM"));            
            col_profname.setCellValueFactory(new PropertyValueFactory<>("profName"));
            col_dept.setCellValueFactory(new PropertyValueFactory<>("DEPT_CODE"));
            col_rank.setCellValueFactory(new PropertyValueFactory<>("PROF_RANK"));
            
            Callback<TableColumn<Prof_Alt, Void>, TableCell<Prof_Alt, Void>> buttonEdit = new Callback<>() {
                @Override
                public TableCell<Prof_Alt, Void> call(TableColumn<Prof_Alt, Void> param) {
                    final TableCell<Prof_Alt, Void> cell = new TableCell<>() {
                        private final JFXButton button = new JFXButton();

                        {
                            button.setButtonType(JFXButton.ButtonType.FLAT);
                            button.setOnAction(event -> {
                                // Get the item associated with the current row
                                Prof prof = getTableView().getItems().get(getIndex());
                                prof_edit = prof;
                                try {
                                    App.setRoot("UpdateProf");
                                } catch (IOException ex) {
                                    Logger.getLogger(AdminDeptEmpInfoController.class.getName()).log(Level.SEVERE, null, ex);
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
            col_edit_prof.setCellFactory(buttonEdit);
            
            Callback<TableColumn<Prof_Alt, Void>, TableCell<Prof_Alt, Void>> buttonDel = new Callback<>() {
                @Override
                public TableCell<Prof_Alt, Void> call(TableColumn<Prof_Alt, Void> param) {
                    final TableCell<Prof_Alt, Void> cell = new TableCell<>() {
                        private final JFXButton button = new JFXButton();

                        {
                            button.setButtonType(JFXButton.ButtonType.FLAT);
                            button.setOnAction(event -> {
                                // Get the item associated with the current row
                                Prof prof = getTableView().getItems().get(getIndex());
                                deleteProf(prof.getPROF_NUM());
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
            col_delete_prof.setCellFactory(buttonDel);
            
            tb_prof.getItems().setAll(prof_obs);
        } catch (SQLException ex) {
            Logger.getLogger(AdminDeptEmpInfoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    public void switchEmp() {
        PreparedStatement preSql;
        try {
            this.openConnection();
            preSql = this.conn.prepareStatement("SELECT * FROM employee WHERE EMP_JOBCODE <> 'PROF';");
            ResultSet emp_set = this.getData(preSql);
            List<Emp_Alt> emp_list = new ArrayList<>();
            while (emp_set.next()) {
                Emp_Alt emp = new Emp_Alt (
                        emp_set.getInt("EMP_NUM"),
                        emp_set.getString("EMP_LNAME"),
                        emp_set.getString("EMP_FNAME"),
                        emp_set.getString("EMP_MNAME"),
                        emp_set.getString("EMP_EMAIL"),
                        emp_set.getString("EMP_JOBCODE"),
                        emp_set.getDate("EMP_HIREDATE"),
                        emp_set.getDate("EMP_DOB")
                        
                );
                emp.setEmpName();
                emp_list.add(emp);
            }
            this.closeConnection(preSql);
            
            ObservableList<Emp_Alt> emp_obs = FXCollections.observableArrayList(emp_list);
            col_emp_code.setCellValueFactory(new PropertyValueFactory<>("EMP_NUM"));            
            col_empname.setCellValueFactory(new PropertyValueFactory<>("empName"));
            col_pos.setCellValueFactory(new PropertyValueFactory<>("EMP_JOBCODE"));
            col_hib.setCellValueFactory(new PropertyValueFactory<>("EMP_HIREDATE"));
            col_dob.setCellValueFactory(new PropertyValueFactory<>("EMP_DOB"));
            
            
            Callback<TableColumn<Emp, Void>, TableCell<Emp, Void>> buttonEdit = new Callback<>() {
                @Override
                public TableCell<Emp, Void> call(TableColumn<Emp, Void> param) {
                    final TableCell<Emp, Void> cell = new TableCell<>() {
                        private final JFXButton button = new JFXButton();

                        {
                            button.setButtonType(JFXButton.ButtonType.FLAT);
                            button.setOnAction(event -> {
                                // Get the item associated with the current row
                                Emp emp = getTableView().getItems().get(getIndex());
                                emp_edit = emp;
                                try {
                                    App.setRoot("UpdateEmp");
                                } catch (IOException ex) {
                                    Logger.getLogger(AdminDeptEmpInfoController.class.getName()).log(Level.SEVERE, null, ex);
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
                                Emp emp = getTableView().getItems().get(getIndex());
                                button.setText("Sửa");
                                setGraphic(button);
                            }
                        }
                    };
                    return cell;
                }
            };
            col_edit_emp.setCellFactory(buttonEdit);
            
            Callback<TableColumn<Emp, Void>, TableCell<Emp, Void>> buttonDel = new Callback<>() {
                @Override
                public TableCell<Emp, Void> call(TableColumn<Emp, Void> param) {
                    final TableCell<Emp, Void> cell = new TableCell<>() {
                        private final JFXButton button = new JFXButton();

                        {
                            button.setButtonType(JFXButton.ButtonType.FLAT);
                            button.setOnAction(event -> {
                                // Get the item associated with the current row
                                Emp emp = getTableView().getItems().get(getIndex());
                                deleteEmp(emp.getEMP_NUM());
                            });
                        }

                        @Override
                        protected void updateItem(Void item, boolean empty) {
                            super.updateItem(item, empty);
                            if (empty) {
                                setGraphic(null);
                            } else {
                                // Get the item associated with the current row
                                Emp emp = getTableView().getItems().get(getIndex());
                                button.setText("Xóa");
                                setGraphic(button);
                            }
                        }
                    };
                    return cell;
                }
            };
            col_delete_emp.setCellFactory(buttonDel);
            
            tb_emp.getItems().setAll(emp_obs);
        } catch (SQLException ex) {
            Logger.getLogger(AdminDeptEmpInfoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txt_name.setText(this.usr.getEmp().getEMP_LNAME() + " " + this.usr.getEmp().getEMP_MNAME() + " " + this.usr.getEmp().getEMP_FNAME());
        switchDept();
        switchProf();
        switchEmp();
    }

    public void deleteDept(String DEPT_CODE) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

        alert.setTitle("Xác nhận xóa");

        alert.setHeaderText(null);

        alert.setContentText("Bạn có chắc chắn muốn xóa không?");

        alert.showAndWait();

        if (alert.getResult() == ButtonType.OK) {
            PreparedStatement preSql;
            try {
                this.openConnection();
                preSql = this.conn.prepareStatement("DELETE FROM department WHERE DEPT_CODE = ?");
                preSql.setString(1, DEPT_CODE);

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
    
    public void deleteProf(int PROF_NUM) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

        alert.setTitle("Xác nhận xóa");

        alert.setHeaderText(null);

        alert.setContentText("Bạn có chắc chắn muốn xóa không?");

        alert.showAndWait();

        if (alert.getResult() == ButtonType.OK) {
            PreparedStatement preSql;
            try {
                this.openConnection();
                preSql = this.conn.prepareStatement("DELETE FROM professor WHERE PROF_NUM = ?");
                preSql.setInt(1, PROF_NUM);

                int result = this.deleteData(preSql);
                
                preSql = this.conn.prepareStatement("DELETE FROM employee WHERE EMP_NUM = ?");
                preSql.setInt(1, PROF_NUM);

                int rs = this.deleteData(preSql);
            } catch (SQLException ex) {
                Logger.getLogger(StuMainMenuController.class.getName()).log(Level.SEVERE, null, ex);
            }
            return;
        } else {
            return;
        }
    }
    
    public void deleteEmp(int EMP_NUM) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

        alert.setTitle("Xác nhận xóa");

        alert.setHeaderText(null);

        alert.setContentText("Bạn có chắc chắn muốn xóa không?");

        alert.showAndWait();

        if (alert.getResult() == ButtonType.OK) {
            PreparedStatement preSql;
            try {
                this.openConnection();
                preSql = this.conn.prepareStatement("DELETE FROM employee WHERE EMP_NUM = ?");
                preSql.setInt(1, EMP_NUM);

                int result = this.deleteData(preSql);
            } catch (SQLException ex) {
                Logger.getLogger(StuMainMenuController.class.getName()).log(Level.SEVERE, null, ex);
            }
            return;
        } else {
            return;
        }
    }
    
}
