package pl.edu.pw.ee.flashcards.learn;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.edu.pw.ee.flashcards.card.FlashCard;
import pl.edu.pw.ee.flashcards.database.Connector;
import pl.edu.pw.ee.flashcards.switcher.SceneSwitcher;
import pl.edu.pw.ee.flashcards.utils.AnswerUtils;
import pl.edu.pw.ee.flashcards.utils.DbUtils;

import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.util.Random;
import java.util.ResourceBundle;

import static pl.edu.pw.ee.flashcards.learn.Language.NATIVE;
import static pl.edu.pw.ee.flashcards.learn.SwitchData.*;
import static pl.edu.pw.ee.flashcards.switcher.FxmlUrls.CHOOSE;

public class InsertAnswerController implements Initializable, AnswerChecker {
    @FXML
    private TextField userAnswerField;
    @FXML
    private Label wordLabel;
    @FXML
    private Button exitButton;
    @FXML
    private Button submitAnswerButton;
    private Connection connection;
    private static final Logger logger = LoggerFactory.getLogger(InsertAnswerController.class);
    private Random random;
    private CardChooser cardChooser;
    private FlashCard answerCard;
    private int chosenLanguage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        connection = Connector.establishConnection();
        try {
            random = SecureRandom.getInstanceStrong();
        } catch (NoSuchAlgorithmException exception) {
            logger.error("There is a problem in creating random instance.", exception);
        }
        cardChooser = new CardChooser();

        exitButton.setOnAction(event -> {
            DbUtils.deleteLearnSetData(connection);
            SceneSwitcher.switchToNewScene(CHOOSE.getPath(), event);
        });
        chosenLanguage = random.nextInt(RAND_BOUND.getValue());
        answerCard = cardChooser.getCardFromSet(connection);

        if (answerCard == null){
            logger.error("Answer Card is null");
            return;
        }
        var word = chosenLanguage == NATIVE.getLangId() ? answerCard.getNativeName() : answerCard.getForeignName();
        wordLabel.setText(word);

        submitAnswerButton.setOnAction(event -> {
            checkUserAnswer();
            if (cardChooser.isEveryCardLearn(connection)) {
                DbUtils.deleteLearnSetData(connection);
                LearnAlerts.popLearnedAlert();
                SceneSwitcher.switchToNewScene(CHOOSE.getPath(), event);
            } else {
                userAnswerField.clear();
                decideWhereToSwitch(location, resources, event);
            }
        });
    }

    @Override
    public void checkUserAnswer(){
        var userAnswer = userAnswerField.getText();
        var correctAnswer = chosenLanguage == NATIVE.getLangId() ? answerCard.getForeignName() : answerCard.getNativeName();

        AnswerUtils.handleAnswer(userAnswer, correctAnswer, connection, answerCard);
    }

    public void decideWhereToSwitch(URL location, ResourceBundle resources, ActionEvent event){
        var destination = random.nextInt(RAND_BOUND.getValue());

        if (destination == INSERT_DESTINATION.getValue()){
            initialize(location, resources);
        }
        else {
            SceneSwitcher.switchToRandomScene(CLICK_DESTINATION.getValue(), event);
        }
    }
}
