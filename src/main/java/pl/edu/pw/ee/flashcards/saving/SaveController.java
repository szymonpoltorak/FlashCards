package pl.edu.pw.ee.flashcards.saving;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeView;
import pl.edu.pw.ee.flashcards.switcher.SceneSwitcher;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static pl.edu.pw.ee.flashcards.switcher.FxmlUrls.MAIN;

public class SaveController implements Initializable {
    @FXML
    private Button returnButton;
    @FXML
    private Button addButton;
    @FXML
    private TextField foreignName;
    @FXML
    private TextField nativeName;
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
