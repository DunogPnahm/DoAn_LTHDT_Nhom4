/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Enroll;
import Model.Stu;
import com.jfoenix.controls.JFXButton;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

/**
 *
 * @author nguye
 */
public class Config {
    public static final String HOST = "jdbc:mysql://localhost:3306/";
    public static final String DATABASE = "quanlydiemthi";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "";
    
    public static final String STUDENT_ROLE = "STU";    
    public static final String PROFESSOR_ROLE = "PROF";
    public static final String ADMIN_ROLE = "ADMIN";

    
}

class BaseFunction extends BaseController {
    
    /**
     * Fields
     */
    @FXML
    private TableColumn<?, ?> col_NAME1;
    
    @FXML
    private TableColumn<?, ?> col_NAME2;
    
    @FXML
    private TableColumn<?, ?> col_func;
    
    @FXML
    private TableView<Object> tb_data;
    
    Object obj; //Lấy từ BaseController (usr/emp/prof/stu)
    
    public void Xem() {
        PreparedStatement preSql;
        try {
            this.openConnection();
            preSql = this.conn.prepareStatement("CÂU TRUY VẤN SQL");
            preSql.setString(1, "DỮ LIỆU ĐƯƠC ĐIỀN VÀO");
            ResultSet rs = this.getData(preSql);
            List<Object> obj_list = new ArrayList<>();
//            while (rs.next()) {
//                Object obj = new Object(
//                        rs.getInt("PROPERTY 1"),                        
//                        rs.getInt("PROPERTY 2")
//                );
//                obj_list.add(obj);
//            }
            this.closeConnection(preSql);
            
            ObservableList<Object> obj_obs = FXCollections.observableArrayList(obj_list);
            col_NAME1.setCellValueFactory(new PropertyValueFactory<>("FIELD 1"));            
            col_NAME2.setCellValueFactory(new PropertyValueFactory<>("FIELD 2"));
            
            tb_data.getItems().setAll(obj_obs);
        } catch (SQLException ex) {
            Logger.getLogger(StuMainMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void Them() {
        PreparedStatement preSql;
        try {
            this.openConnection();
            preSql = this.conn.prepareStatement("INSERT INTO TABLE_NAME VALUES"
                    + "(?, ?, ?)");
            preSql.setString(1, "DỮ LIỆU 1");
            preSql.setString(2, "DỮ LIỆU 2");
            preSql.setString(3, "DỮ LIỆU 3");

            int result = this.postData(preSql);
            if (result > 0) System.out.println("Thêm thành công");
        } catch (SQLException ex) {
            Logger.getLogger(StuMainMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void Sua() {
        PreparedStatement preSql;
        try {
            this.openConnection();
            preSql = this.conn.prepareStatement("UPDATE TABLE_NAME SET "
                    + "FIELD1 = ?, "
                    + "FIELD2 = ?, "
                    + "FIELD3 = ?");
            preSql.setString(1, "DỮ LIỆU 1");
            preSql.setString(2, "DỮ LIỆU 2");
            preSql.setString(3, "DỮ LIỆU 3");

            int result = this.postData(preSql);
            if (result > 0) System.out.println("Sửa thành công");
        } catch (SQLException ex) {
            Logger.getLogger(StuMainMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void Xoa() {
        PreparedStatement preSql;
        try {
            this.openConnection();
            preSql = this.conn.prepareStatement("DELETE FROM TABLE_NAME WHERE FIELD_ID = ?");
            preSql.setString(1, "ID");

            int result = this.deleteData(preSql);
            if (result > 0) System.out.println("Xóa thành công");
        } catch (SQLException ex) {
            Logger.getLogger(StuMainMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void addButton() {
        // Create button cell factory
        Callback<TableColumn<Enroll, Void>, TableCell<Enroll, Void>> buttonCellFactory = new Callback<>() {
            @Override
            public TableCell<Enroll, Void> call(TableColumn<Enroll, Void> param) {
                final TableCell<Enroll, Void> cell = new TableCell<>() {
                    private final JFXButton button = new JFXButton();

                    {
                        button.setButtonType(JFXButton.ButtonType.FLAT);
                        button.setOnAction(event -> {
                            // Get the item associated with the current row
                            Enroll enroll_alt = getTableView().getItems().get(getIndex());
                            System.out.println("Button clicked for item: " + enroll_alt.getSTU_NUM() + " - " + enroll_alt.getCLS_CODE());
                        });
                    }

                    @Override
                    protected void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            // Get the item associated with the current row
                            Enroll enroll_alt = getTableView().getItems().get(getIndex());
                            button.setText("Sửa");
                            setGraphic(button);
                        }
                    }
                };
                return cell;
            }
        };
//        col_func.setCellFactory(buttonCellFactory);
    }
}
