package pl.edu.pw.ee.flashcards;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.edu.pw.ee.flashcards.switcher.SceneSwitcher;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static pl.edu.pw.ee.flashcards.switcher.FxmlUrls.MANAGE;
import static pl.edu.pw.ee.flashcards.switcher.FxmlUrls.SAVE;

public class FlashCardController implements Initializable {
    @FXML
    private Button addButton;
    @FXML
    private Button learnButton;
    @FXML
    private Button manageSetButton;
    private static final Logger logger = LoggerFactory.getLogger(FlashCardController.class);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addButton.setOnAction(event -> {
            try {
                SceneSwitcher.switchToNewScene(SAVE.getPath(), event);
            } catch (IOException e) {
                logger.warn("IOException occured");
            }
        });

        manageSetButton.setOnAction(event -> {
            try {
                SceneSwitcher.switchToNewScene(MANAGE.getPath(), event);
            } catch (IOException e) {
                logger.warn("IOException occured");
            }
        });
    }
}
