package pl.edu.pw.ee.flashcards.learn;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;

import java.net.URL;
import java.util.ResourceBundle;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
