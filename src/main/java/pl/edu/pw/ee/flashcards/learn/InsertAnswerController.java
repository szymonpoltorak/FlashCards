package pl.edu.pw.ee.flashcards.learn;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import pl.edu.pw.ee.flashcards.switcher.SceneSwitcher;

import java.net.URL;
import java.util.ResourceBundle;

import static pl.edu.pw.ee.flashcards.switcher.FxmlUrls.CHOOSE;

public class InsertAnswerController implements Initializable {
    @FXML
    private TextField userAnswerField;
    @FXML
    private Label wordLabel;
    @FXML
    private Button exitButton;
    @FXML
    private Button submitAnswerButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        exitButton.setOnAction(event -> SceneSwitcher.switchToNewScene(CHOOSE.getPath(), event));
    }
}
