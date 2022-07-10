package pl.edu.pw.ee.flashcards;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import pl.edu.pw.ee.flashcards.switcher.SceneSwitcher;

import java.net.URL;
import java.util.ResourceBundle;

import static pl.edu.pw.ee.flashcards.switcher.FxmlUrls.*;

public class MainController implements Initializable {
    @FXML
    private Button addButton;
    @FXML
    private Button learnButton;
    @FXML
    private Button manageSetButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addButton.setOnAction(event -> SceneSwitcher.switchToNewScene(SAVE.getPath(), event));

        manageSetButton.setOnAction(event -> SceneSwitcher.switchToNewScene(MANAGE.getPath(), event));

        learnButton.setOnAction(event -> SceneSwitcher.switchToNewScene(CHOOSE.getPath(), event));
    }
}
