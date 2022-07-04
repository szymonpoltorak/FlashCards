package pl.edu.pw.ee.flashcards;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class FlashCardController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}