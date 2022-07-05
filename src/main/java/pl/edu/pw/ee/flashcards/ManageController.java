package pl.edu.pw.ee.flashcards;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static pl.edu.pw.ee.flashcards.FxmlUrls.MAIN;

public class ManageController implements Initializable {
    @FXML
    private Button returnButton;
    @FXML
    private Button editName;
    @FXML
    private Button createSet;
    @FXML
    private Button removeButton;
    @FXML
    private TextField setNameField;
    @FXML
    private TextField editNameField;
    @FXML
    private TreeView<String> flashCardsTree;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        returnButton.setOnAction(event -> {
            try {
                SceneSwitcher.switchToNewScene(MAIN.getPath(), event);
            } catch (IOException e) {
                System.err.println("IOException");
                e.printStackTrace();
            }
        });
    }
}
