package Controller;

import Model.Rem;
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

public class AdminRemController extends BaseController {

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
    private Text txt_name;
    
    @FXML
    private AnchorPane body;
    
    @FXML
    private TableColumn<Rem, Void> col_delete;

    @FXML
    private TableColumn<Rem, Void> col_edit;

    @FXML
    private TableColumn<?, ?> col_empcode;

    @FXML
    private TableColumn<?, ?> col_notif;

    @FXML
    private TableView<Rem> tb_rem;
    
    private List<Rem> rem_list = new ArrayList<>();

    @FXML
    void ToDeptProfInfo(MouseEvent event) throws IOException {
        App.setRoot("AdminDeptEmpInfo");
    }

    @FXML
    void ToSche(MouseEvent event) throws IOException {
        App.setRoot("AdminSche");
    }
    
    @FXML
    void ToMain(MouseEvent event) throws IOException {
        App.setRoot("AdminMainMenu");
    }

    protected static Rem rem_edit;
    
    @FXML
    void addRem(ActionEvent event) throws IOException {
        App.setRoot("AddRem");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txt_name.setText(this.usr.getEmp().getEMP_LNAME() + " " + this.usr.getEmp().getEMP_MNAME() + " " + this.usr.getEmp().getEMP_FNAME());
        
        try {
            this.openConnection();
            PreparedStatement preSql = this.conn.prepareStatement("SELECT * FROM reminder");
            ResultSet rs = this.getData(preSql);
            while (rs.next()) {
                Rem rem = new Rem(
                    rs.getInt("REM_NUM"),
                    rs.getString("REM_DESC"),
                    rs.getInt("EMP_NUM")
                );
                rem_list.add(rem);
            }
            this.closeConnection(preSql);
            
            ObservableList<Rem> rem_obs = FXCollections.observableArrayList(rem_list);
            col_empcode.setCellValueFactory(new PropertyValueFactory<>("EMP_NUM"));
            col_notif.setCellValueFactory(new PropertyValueFactory<>("REM_DESC"));

            tb_rem.getItems().setAll(rem_obs);
            
            Callback<TableColumn<Rem, Void>, TableCell<Rem, Void>> btnEdit = new Callback<>() {
                @Override
                public TableCell<Rem, Void> call(TableColumn<Rem, Void> param) {
                    final TableCell<Rem, Void> cell = new TableCell<>() {
                        private final JFXButton button = new JFXButton();

                        {
                            button.setButtonType(JFXButton.ButtonType.FLAT);
                            button.setOnAction(event -> {
                                // Get the item associated with the current row
                                Rem rem = getTableView().getItems().get(getIndex());
                                rem_edit = rem;
                                try {
                                    App.setRoot("UpdateRem");
                                } catch (IOException ex) {
                                    Logger.getLogger(AdminRemController.class.getName()).log(Level.SEVERE, null, ex);
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
            
            Callback<TableColumn<Rem, Void>, TableCell<Rem, Void>> btnDel = new Callback<>() {
                @Override
                public TableCell<Rem, Void> call(TableColumn<Rem, Void> param) {
                    final TableCell<Rem, Void> cell = new TableCell<>() {
                        private final JFXButton button = new JFXButton();

                        {
                            button.setButtonType(JFXButton.ButtonType.FLAT);
                            button.setOnAction(event -> {
                                // Get the item associated with the current row
                                Rem rem = getTableView().getItems().get(getIndex());
                                deleteRem(rem.getREM_NUM());
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
            Logger.getLogger(AdminRemController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void deleteRem(int REM_NUM) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

        alert.setTitle("Xác nhận xóa");

        alert.setHeaderText(null);

        alert.setContentText("Bạn có chắc chắn muốn xóa không?");

        alert.showAndWait();

        if (alert.getResult() == ButtonType.OK) {
            PreparedStatement preSql;
            try {
                this.openConnection();
                preSql = this.conn.prepareStatement("DELETE FROM reminder WHERE REM_NUM = ?");
                preSql.setInt(1, REM_NUM);

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
