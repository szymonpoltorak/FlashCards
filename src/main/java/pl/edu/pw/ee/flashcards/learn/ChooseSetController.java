package pl.edu.pw.ee.flashcards.learn;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import pl.edu.pw.ee.flashcards.switcher.SceneSwitcher;

import java.net.URL;
import java.util.ResourceBundle;

import static pl.edu.pw.ee.flashcards.switcher.FxmlUrls.MAIN;

public class ChooseSetController implements Initializable {
    @FXML
    private Button letsLearnButton;
    @FXML
    private Button returnButton;
    @FXML
    private ListView<String> setList;
    @FXML
    private TextField numberCardField;
    @FXML
    private Button saveNumberButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        returnButton.setOnAction(event -> SceneSwitcher.switchToNewScene(MAIN.getPath(), event));
    }
}
