package pl.edu.pw.ee.flashcards;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static pl.edu.pw.ee.flashcards.FxmlUrls.MAIN;

public class SaveController implements Initializable {
    @FXML
    private Button returnButton;

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
