package pl.edu.pw.ee.flashcards.learn;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.edu.pw.ee.flashcards.database.Connector;
import pl.edu.pw.ee.flashcards.switcher.SceneSwitcher;
import pl.edu.pw.ee.flashcards.utils.DbUtils;

import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.util.Random;
import java.util.ResourceBundle;

import static pl.edu.pw.ee.flashcards.learn.SwitchData.*;
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
    private Random random;
    private static final Logger logger = LoggerFactory.getLogger(ClickAnswerController.class);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        connection = Connector.establishConnection();
        try {
            random = SecureRandom.getInstanceStrong();
        } catch (NoSuchAlgorithmException exception) {
            logger.error("There is a problem in creating random instance", exception);
        }

        exitButton.setOnAction(event -> {
            DbUtils.deleteLearnSetData(connection);
            SceneSwitcher.switchToNewScene(CHOOSE.getPath(), event);
        });

        submitButton.setOnAction(event -> {
            var destination = random.nextInt(RAND_BOUND.getValue());

            if (destination == CLICK_DESTINATION.getValue()){
                initialize(location, resources);
            }
            else {
                SceneSwitcher.switchToRandomScene(INSERT_DESTINATION.getValue(), event);
            }
        });
    }
}
