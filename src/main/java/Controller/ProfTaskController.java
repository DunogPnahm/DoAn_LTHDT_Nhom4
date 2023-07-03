package Controller;

import Model.Cls;
import Model.Task;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.util.Callback;

public class ProfTaskController extends BaseController {

    private String deptname;
    
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
    private JFXComboBox<String> ClsChooser;
    
    @FXML
    private AnchorPane body;
    
    @FXML
    private Text Cls;
    
    @FXML
    private TableColumn<?, ?> col_cls;

    @FXML
    private TableColumn<?, ?> col_task;

    @FXML
    private TableView<Task> tb_task;
    
    @FXML
    private TableColumn<Task, Void> col_delete;

    @FXML
    private TableColumn<Task, Void> col_edit;
    
    protected static Task task_edit;

    @FXML
    void ToDeptProfInfo(MouseEvent event) throws IOException {
        App.setRoot("ProfDeptProfInfo");
    }

    @FXML
    void ToSche(MouseEvent event) throws IOException {
        App.setRoot("ProfSche");
    }
    
    @FXML
    void ToMain(MouseEvent event) throws IOException {
        App.setRoot("ProfMainMenu");
    }

    @FXML
    void ChooseCls(ActionEvent event) {
        Cls.setText(ClsChooser.getValue());
        PreparedStatement preSql;
        try {
            this.openConnection();
            preSql = this.conn.prepareStatement("SELECT *\n" +
                "FROM task\n" +
                "WHERE CLS_CODE = ?;");
            preSql.setString(1, ClsChooser.getValue());
            ResultSet rs = this.getData(preSql);
            List<Task> task_list = new ArrayList<>();
            while (rs.next()) {
                Task task = new Task(
                        rs.getString("TASK_CODE"),
                        rs.getString("TASK_DESC"),
                        rs.getString("CLS_CODE")
                );
                task_list.add(task);
            }
            this.closeConnection(preSql);
            
            ObservableList<Task> task_obs = FXCollections.observableArrayList(task_list);
            col_cls.setCellValueFactory(new PropertyValueFactory<>("CLS_CODE"));
            col_task.setCellValueFactory(new PropertyValueFactory<>("TASK_DESC"));

            tb_task.getItems().setAll(task_obs);
            
            Callback<TableColumn<Task, Void>, TableCell<Task, Void>> btnEdit = new Callback<>() {
                @Override
                public TableCell<Task, Void> call(TableColumn<Task, Void> param) {
                    final TableCell<Task, Void> cell = new TableCell<>() {
                        private final JFXButton button = new JFXButton();

                        {
                            button.setButtonType(JFXButton.ButtonType.FLAT);
                            button.setOnAction(event -> {
                                // Get the item associated with the current row
                                Task task = getTableView().getItems().get(getIndex());
                                task_edit = task;
                                try {
                                    App.setRoot("UpdateTask");
                                } catch (IOException ex) {
                                    Logger.getLogger(ProfTaskController.class.getName()).log(Level.SEVERE, null, ex);
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
            
            Callback<TableColumn<Task, Void>, TableCell<Task, Void>> btnDel = new Callback<>() {
                @Override
                public TableCell<Task, Void> call(TableColumn<Task, Void> param) {
                    final TableCell<Task, Void> cell = new TableCell<>() {
                        private final JFXButton button = new JFXButton();

                        {
                            button.setButtonType(JFXButton.ButtonType.FLAT);
                            button.setOnAction(event -> {
                                // Get the item associated with the current row
                                Task task = getTableView().getItems().get(getIndex());
                                deleteTask(task.getTASK_CODE());
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
            
        } catch (SQLException ex) {
            Logger.getLogger(StuTaskController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    void addTask(ActionEvent event) throws IOException {
        App.setRoot("AddTask");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            this.openConnection();
            this.openConnection();
            PreparedStatement preSql = this.conn.prepareStatement("SELECT DISTINCT professor.PROF_NUM, DEPT_NAME\n" +
                "  FROM department INNER JOIN professor\n" +
                "  ON department.DEPT_CODE = professor.DEPT_CODE WHERE professor.PROF_NUM = ?;"
            );
            preSql.setInt(1, this.usr.getProf().getPROF_NUM());
            ResultSet DeptName = this.getData(preSql);
            if(DeptName.next())
            {
                deptname = DeptName.getString("DEPT_NAME");
            }
            this.closeConnection(preSql);
        } catch (SQLException ex) {
            Logger.getLogger(StuScheController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        List<String> clsCode_list = new ArrayList<>();
        try {
            this.openConnection();
            PreparedStatement preSql = this.conn.prepareStatement("SELECT * FROM class WHERE PROF_NUM = ?");
            preSql.setInt(1, this.usr.getProf().getPROF_NUM());
            ResultSet rs = this.getData(preSql);
            while (rs.next()) {
                Cls cls = new Cls(
                    rs.getString("CLS_CODE"),
                    rs.getString("CLS_TIME"),
                    rs.getString("CRS_CODE"),
                    rs.getString("ROOM_CODE"),
                    rs.getString("SEM_CODE"),
                    rs.getInt("PROF_NUM")
                );
                clsCode_list.add(cls.getCLS_CODE());
            }
            this.closeConnection(preSql);
        } catch (SQLException ex) {
            Logger.getLogger(ProfTaskController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ClsChooser.getItems().setAll(clsCode_list);
        
        ProfName.setText(usr.getProf().getEMP_LNAME() + " " + usr.getProf().getEMP_MNAME() + " " + usr.getProf().getEMP_FNAME());
        ProfDept.setText(deptname);
    }
    
    public void deleteTask(String TASK_CODE) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

        alert.setTitle("Xác nhận xóa");

        alert.setHeaderText(null);

        alert.setContentText("Bạn có chắc chắn muốn xóa không?");

        alert.showAndWait();

        if (alert.getResult() == ButtonType.OK) {
            PreparedStatement preSql;
            try {
                this.openConnection();
                preSql = this.conn.prepareStatement("DELETE FROM task WHERE TASK_CODE = ?");
                preSql.setString(1, TASK_CODE);

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
