package pl.edu.pw.ee.flashcards.learn;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

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

    }
}
