package Controller;

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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class StuTaskController extends BaseController {

    @FXML
    private Pane Avatar;

    @FXML
    private JFXComboBox<String> ClsChooser;
    
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
    private Text Cls;
    
    @FXML
    private TableColumn<?, ?> col_cls;

    @FXML
    private TableColumn<?, ?> col_task;

    @FXML
    private TableView<Task> tb_task;

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
        } catch (SQLException ex) {
            Logger.getLogger(StuTaskController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void ToDeptProfInfo(MouseEvent event) throws IOException {
        App.setRoot("StuDeptProfInfo");
    }

    @FXML
    void ToSche(MouseEvent event) throws IOException {
        App.setRoot("StuSche");
    }
    
    @FXML
    void ToMain(MouseEvent event) throws IOException {
        App.setRoot("StuMainMenu");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        StuName.setText(usr.getStu().getSTU_LNAME() + " " + usr.getStu().getSTU_MNAME() + " " + usr.getStu().getSTU_FNAME());
        StuCls.setText(usr.getStu().getMGTCLS_CODE());
        
        PreparedStatement preSql;
        try {
            this.openConnection();
            preSql = this.conn.prepareStatement("SELECT DISTINCT CLS_CODE\n" +
                "FROM enroll\n" +
                "WHERE STU_NUM = ?;");
            preSql.setInt(1, this.usr.getStu().getSTU_NUM());
            ResultSet rs = this.getData(preSql);
            while (rs.next()) {
                ClsChooser.getItems().addAll(rs.getString("CLS_CODE"));
            }
            this.closeConnection(preSql);
        } catch (SQLException ex) {
            Logger.getLogger(StuTaskController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
