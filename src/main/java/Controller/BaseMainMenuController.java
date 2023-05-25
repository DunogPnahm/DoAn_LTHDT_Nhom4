package Controller;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class BaseMainMenuController extends BaseController{
    
    @FXML
    private Pane UsrBckgr;

    @FXML
    private AnchorPane body;

    @FXML
    private Pane HuceLogo;

    @FXML
    private JFXButton Logout_btn;
    
    @FXML
    void Logout(MouseEvent event) throws IOException {
        App.setRoot("Login");
    }
}