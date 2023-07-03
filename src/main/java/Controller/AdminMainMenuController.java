package Controller;

import Model.Bldg;
import Model.Cls;
import Model.Enroll;
import Model.Prof;
import Model.Room;
import Model.Sem;
import Model.Stu;
import Model.User;
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

public class AdminMainMenuController extends BaseMainMenuController {
    
    private String empstartdate;
    
    private String chooseAll = "All";
    
    @FXML
    private Text AdminDOB;

    @FXML
    private Text AdminEmail;

    @FXML
    private Text AdminID;

    @FXML
    private Text AdminName;

    @FXML
    private Text AdminStartDate;
    
    @FXML
    private Text Role;

    @FXML
    private JFXComboBox<String> RoleChooser;

    @FXML
    private TableColumn<User, Void> col_delete;

    @FXML
    private TableColumn<User, Void> col_edit;

    @FXML
    private TableColumn<?, ?> col_id;

    @FXML
    private TableColumn<?, ?> col_no;

    @FXML
    private TableColumn<?, ?> col_usrname;

    @FXML
    private TableColumn<?, ?> col_bldgcode;

    @FXML
    private TableColumn<?, ?> col_bldglocation;

    @FXML
    private TableColumn<?, ?> col_end;
    
    @FXML
    private TableColumn<?, ?> col_no1;

    @FXML
    private TableColumn<?, ?> col_no2;

    @FXML
    private TableColumn<?, ?> col_roomcode;

    @FXML
    private TableColumn<?, ?> col_roomlocate;

    @FXML
    private TableColumn<?, ?> col_roomtype;

    @FXML
    private TableColumn<?, ?> col_start;

    @FXML
    private TableColumn<?, ?> col_term;

    @FXML
    private TableColumn<?, ?> col_year;
    
    @FXML
    private TableView<Bldg_Alt> tb_bldg;

    @FXML
    private TableView<Room_Alt> tb_room;

    @FXML
    private TableView<Sem> tb_sem;
    
    @FXML
    private Tab tab_user;

    @FXML
    private TableView<User_Alt> tb_user;

    protected static User user_edit;
    
    private String[] role = {"Sinh viên", "Giảng viên", "Nhân viên giáo vụ"};
    
    public class User_Alt extends User {
        private int no;
        
        private User_Alt uniqueInstance;
        
        public User_Alt(String USER_ACC, String USER_PSWD, String USER_ROLE, int USER_ID) {
            super(USER_ACC, USER_PSWD, USER_ROLE, USER_ID);
        }
        
        public int getNo() {
            return no;
        }

        public void setNo(int no) {
            this.no = no;
        }
    }
    
    public class Bldg_Alt extends Bldg {
        private int no;
        
        public Bldg_Alt(String BLDG_CODE, String BLDG_LOCATION) {
            super(BLDG_CODE, BLDG_LOCATION);
        }
        
        public int getNo() {
            return no;
        }

        public void setNo(int no) {
            this.no = no;
        }
    }
    
    public class Room_Alt extends Room {
        private int no;
        
        public Room_Alt(String ROOM_CODE, String ROOM_TYPE, String BLDG_CODE) {
            super(ROOM_CODE, ROOM_TYPE, BLDG_CODE);
        }
        
        public int getNo() {
            return no;
        }

        public void setNo(int no) {
            this.no = no;
        }
    }
    
    @FXML
    void ChooseRole(ActionEvent event) {
        Role.setText(RoleChooser.getValue());
        PreparedStatement preSql;
        try {
            this.openConnection();
            if (RoleChooser.getValue() == "Sinh viên") {
                preSql = this.conn.prepareStatement("SELECT *\n" +
                    "FROM user\n" +
                    "WHERE USER_ROLE = \"STU\";");      
            } else if (RoleChooser.getValue() == "Giảng viên") {
                preSql = this.conn.prepareStatement("SELECT *\n" +
                    "FROM user\n" +
                    "WHERE USER_ROLE = \"PROF\";");
            } else {
                preSql = this.conn.prepareStatement("SELECT *\n" +
                    "FROM user\n" +
                    "WHERE USER_ROLE = \"ADMIN\";"); 
            }
            ResultSet user_set = this.getData(preSql);
            List<User_Alt> user_list = new ArrayList<>();
            int no = 0;
            while (user_set.next()) {
                User_Alt user = new User_Alt(
                        user_set.getString("USER_ACC"),
                        "",
                        user_set.getString("USER_ROLE"),
                        user_set.getInt("USER_ID")
                );
                user.setNo(++no);
                user_list.add(user);
            }
            this.closeConnection(preSql);
            
            ObservableList<User_Alt> user_obs = FXCollections.observableArrayList(user_list);
            col_no.setCellValueFactory(new PropertyValueFactory<>("no"));
            col_usrname.setCellValueFactory(new PropertyValueFactory<>("USER_ACC"));
            col_id.setCellValueFactory(new PropertyValueFactory<>("USER_ID"));
            
            tb_user.getItems().setAll(user_obs);
        } catch (SQLException ex) {
            Logger.getLogger(AdminMainMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    void ToSche(MouseEvent event) throws IOException {
        App.setRoot("AdminSche");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        int day;
        int month;
        int year;
        try {
            this.openConnection();
            PreparedStatement preSql = this.conn.prepareStatement("SELECT EMP_HIREDATE, EMP_NUM\n" +
                "FROM employee\n" +
                "WHERE EMP_NUM = ?;"
            );
            preSql.setInt(1, this.usr.getEmp().getEMP_NUM());
            ResultSet emp_start = this.getData(preSql);
            if(emp_start.next())
            {
                Calendar cal = Calendar.getInstance();
                cal.setTime(emp_start.getDate("EMP_HIREDATE"));
                day = cal.get(Calendar.DAY_OF_MONTH);
                month = cal.get(Calendar.MONTH) + 1;
                year = cal.get(Calendar.YEAR);
                empstartdate = day + "/" + month + "/" + year;   
            }
            this.closeConnection(preSql);
            
            this.openConnection();
            preSql = this.conn.prepareStatement("SELECT *\n" +
                "FROM building;"
            );
            ResultSet bldg_set = this.getData(preSql);
            List<Bldg_Alt> bldg_list = new ArrayList<>();
            int no = 0;
            while (bldg_set.next()) {
                Bldg_Alt bldg = new Bldg_Alt(
                        bldg_set.getString("BLDG_CODE"),
                        bldg_set.getString("BLDG_LOCATION")
                );
                bldg.setNo(++no);
                bldg_list.add(bldg);
            }
            ObservableList<Bldg_Alt> bldg_obs = FXCollections.observableArrayList(bldg_list);
            col_no1.setCellValueFactory(new PropertyValueFactory<>("no"));
            col_bldgcode.setCellValueFactory(new PropertyValueFactory<>("BLDG_CODE"));
            col_bldglocation.setCellValueFactory(new PropertyValueFactory<>("BLDG_LOCATION"));
            
            tb_bldg.getItems().setAll(bldg_obs);
            this.closeConnection(preSql);
            
            this.openConnection();
            preSql = this.conn.prepareStatement("SELECT *\n" +
                "FROM room;"
            );
            ResultSet room_set = this.getData(preSql);
            List<Room_Alt> room_list = new ArrayList<>();
            int no1 = 0;
            while (room_set.next()) {
                Room_Alt room = new Room_Alt(
                        room_set.getString("ROOM_CODE"),
                        room_set.getString("ROOM_TYPE"),
                        room_set.getString("BLDG_CODE")
                );
                room.setNo(++no1);
                room_list.add(room);
            }
            ObservableList<Room_Alt> room_obs = FXCollections.observableArrayList(room_list);
            col_no2.setCellValueFactory(new PropertyValueFactory<>("no"));
            col_roomcode.setCellValueFactory(new PropertyValueFactory<>("ROOM_CODE"));
            col_roomtype.setCellValueFactory(new PropertyValueFactory<>("ROOM_TYPE"));
            col_roomlocate.setCellValueFactory(new PropertyValueFactory<>("BLDG_CODE"));
            
            tb_room.getItems().setAll(room_obs);
            this.closeConnection(preSql);
            
            this.openConnection();
            preSql = this.conn.prepareStatement("SELECT *\n" +
                "FROM semester;"
            );
            ResultSet sem_set = this.getData(preSql);
            List<Sem> sem_list = new ArrayList<>();
            while (sem_set.next()) {
                Sem sem = new Sem(
                        sem_set.getString("SEM_CODE"),
                        sem_set.getInt("SEM_YEAR"),
                        sem_set.getDate("SEM_START_DATE"),
                        sem_set.getDate("SEM_END_DATE")
                );
                sem_list.add(sem);
            }
            ObservableList<Sem> sem_obs = FXCollections.observableArrayList(sem_list);
            col_year.setCellValueFactory(new PropertyValueFactory<>("SEM_YEAR"));
            col_term.setCellValueFactory(new PropertyValueFactory<>("SEM_CODE"));
            col_start.setCellValueFactory(new PropertyValueFactory<>("SEM_START_DATE"));
            col_end.setCellValueFactory(new PropertyValueFactory<>("SEM_END_DATE"));
            
            tb_sem.getItems().setAll(sem_obs);
            this.closeConnection(preSql);
            
            Callback<TableColumn<User, Void>, TableCell<User, Void>> btnEdit = new Callback<>() {
                @Override
                public TableCell<User, Void> call(TableColumn<User, Void> param) {
                    final TableCell<User, Void> cell = new TableCell<>() {
                        private final JFXButton button = new JFXButton();

                        {
                            button.setButtonType(JFXButton.ButtonType.FLAT);
                            button.setOnAction(event -> {
                                // Get the item associated with the current row
                                User user = getTableView().getItems().get(getIndex());
                                user_edit = user;
                                try {
                                    App.setRoot("UpdateUser");
                                } catch (IOException ex) {
                                    Logger.getLogger(AdminMainMenuController.class.getName()).log(Level.SEVERE, null, ex);
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
            
            Callback<TableColumn<User, Void>, TableCell<User, Void>> btnDel = new Callback<>() {
                @Override
                public TableCell<User, Void> call(TableColumn<User, Void> param) {
                    final TableCell<User, Void> cell = new TableCell<>() {
                        private final JFXButton button = new JFXButton();

                        {
                            button.setButtonType(JFXButton.ButtonType.FLAT);
                            button.setOnAction(event -> {
                                // Get the item associated with the current row
                                User user = getTableView().getItems().get(getIndex());
                                deleteUser(user.getUSER_ACC());
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
            Logger.getLogger(StuMainMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
        RoleChooser.getItems().addAll(role);
        AdminName.setText(usr.getEmp().getEMP_LNAME() + " " + usr.getEmp().getEMP_MNAME() + " " + usr.getEmp().getEMP_FNAME());
        AdminStartDate.setText("Bắt đầu vào làm: " + empstartdate);
        AdminID.setText(Integer.toString(usr.getEmp().getEMP_NUM()));
        AdminEmail.setText(usr.getEmp().getEMP_EMAIL());
        Calendar cal = Calendar.getInstance();
        cal.setTime(usr.getEmp().getEMP_DOB());
        day = cal.get(Calendar.DAY_OF_MONTH);
        month = cal.get(Calendar.MONTH) + 1;
        year = cal.get(Calendar.YEAR);
        AdminDOB.setText(day+"/"+month+"/"+year);
    }
    
    public void deleteUser(String USER_ACC) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

        alert.setTitle("Xác nhận xóa");

        alert.setHeaderText(null);

        alert.setContentText("Bạn có chắc chắn muốn xóa không?");

        alert.showAndWait();

        if (alert.getResult() == ButtonType.OK) {
            PreparedStatement preSql;
            try {
                this.openConnection();
                preSql = this.conn.prepareStatement("DELETE FROM user WHERE USER_ACC = ?");
                preSql.setString(1, USER_ACC);

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
    
    @FXML
    void addUser(ActionEvent event) {
        try {
            App.setRoot("AddUser");
        } catch (IOException ex) {
            Logger.getLogger(AdminScheController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}