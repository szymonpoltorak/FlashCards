package pl.edu.pw.ee.flashcards.learn;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import pl.edu.pw.ee.flashcards.database.Connector;
import pl.edu.pw.ee.flashcards.switcher.SceneSwitcher;
import pl.edu.pw.ee.flashcards.utils.DbUtils;

import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

import static pl.edu.pw.ee.flashcards.switcher.FxmlUrls.CHOOSE;

public class ClickAnswerController implements Initializable {
    @FXML
    private Label wordLabel;
    @FXML
    private Button exitButton;
    @FXML
    private Button submitButton;
    @FXML
    private RadioButton answerA;
    @FXML
    private RadioButton answerB;
    @FXML
    private RadioButton answerC;
    @FXML
    private RadioButton answerD;
    private Connection connection;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        connection = Connector.establishConnection();

        exitButton.setOnAction(event -> {
            DbUtils.deleteLearnSetData(connection);
            SceneSwitcher.switchToNewScene(CHOOSE.getPath(), event);
        });
    }
}
