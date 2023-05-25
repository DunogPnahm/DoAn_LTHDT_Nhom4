package Controller;

import com.jfoenix.controls.JFXComboBox;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class StuMainMenuController extends BaseMainMenuController implements Initializable {
    
    private String[] testing = {"test 1","test 2","test 3","test 4","test 5","test 6"};
    
    @FXML
    private Text Cls;
    
    @FXML
    private Text Sem;
    
    @FXML
    private JFXComboBox<String> ClsChooser;
    
    @FXML
    private JFXComboBox<String> SemChooser;
    
    @FXML
    void ChooseSem(ActionEvent event) {
        Sem.setText(SemChooser.getValue());
    }
    
    @FXML
    void ChooseCls(ActionEvent event) {
        Cls.setText(ClsChooser.getValue());
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        SemChooser.getItems().addAll(testing);
        ClsChooser.getItems().addAll(testing);
        SemChooser.setOnAction(this::ChooseSem);
        ClsChooser.setOnAction(this::ChooseCls);
    }
}