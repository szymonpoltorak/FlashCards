package pl.edu.pw.ee.flashcards.learn;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.edu.pw.ee.flashcards.switcher.SceneSwitcher;

import java.io.IOException;
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
    private ScrollPane scrollPane;
    private static final Logger logger = LoggerFactory.getLogger(ChooseSetController.class);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        returnButton.setOnAction(event -> {
            try {
                SceneSwitcher.switchToNewScene(MAIN.getPath(), event);
            } catch (IOException e) {
                logger.error("IOException occured");
            }
        });
    }
}
