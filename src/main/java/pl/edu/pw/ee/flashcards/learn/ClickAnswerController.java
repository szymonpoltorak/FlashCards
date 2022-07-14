package pl.edu.pw.ee.flashcards.learn;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
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
import java.sql.SQLException;
import java.util.*;

import static pl.edu.pw.ee.flashcards.learn.Language.FOREIGN;
import static pl.edu.pw.ee.flashcards.learn.Language.NATIVE;
import static pl.edu.pw.ee.flashcards.learn.SwitchData.*;
import static pl.edu.pw.ee.flashcards.switcher.FxmlUrls.CHOOSE;

public class ClickAnswerController implements Initializable, AnswerChecker {
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
    private CardChooser cardChooser;
    private static final Logger logger = LoggerFactory.getLogger(ClickAnswerController.class);
    private FlashCard answerCard;
    private int chosenLanguage;
    private String userAnswer;
    private List<RadioButton> buttonList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        connection = Connector.establishConnection();
        try {
            random = SecureRandom.getInstanceStrong();
            cardChooser = new CardChooser();
        } catch (NoSuchAlgorithmException exception) {
            logger.error("There is a problem in creating random instance", exception);
        }
        chosenLanguage = random.nextInt(RAND_BOUND.getValue());
        answerCard = cardChooser.getCardFromSet(connection);

        if (answerCard == null){
            logger.error("Answer Card is null I cannot continue.");
            return;
        }
        var wordQuestion = chosenLanguage == NATIVE.getLangId() ? answerCard.getNativeName() : answerCard.getForeignName();
        wordLabel.setText(wordQuestion);

        assignRadioButtonData();

        exitButton.setOnAction(event -> {
            DbUtils.deleteLearnSetData(connection);
            SceneSwitcher.switchToNewScene(CHOOSE.getPath(), event);
        });

        submitButton.setOnAction(event -> {
            checkUserAnswer();
            if (cardChooser.isEveryCardLearn(connection)) {
                DbUtils.deleteLearnSetData(connection);
                var alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("GOOD JOB!");
                alert.setContentText("Congratulations you have passed every flashcard correctly!");
                alert.showAndWait();
                SceneSwitcher.switchToNewScene(CHOOSE.getPath(), event);
            } else {
                decideWhereToSwitch(location, resources, event);
            }
        });
    }

    @Override
    public void checkUserAnswer() {
        var correctAnswer = chosenLanguage == NATIVE.getLangId() ? answerCard.getForeignName() : answerCard.getNativeName();

        AnswerUtils.handleAnswer(userAnswer, correctAnswer, connection, answerCard);

        for (RadioButton button : buttonList) {
            button.setSelected(false);
        }
    }

    public List<String> assignWordsToButtons(){
        try (var statement = connection.createStatement()) {
            var list = new ArrayList<String>();
            var answerLocalised = chosenLanguage == FOREIGN.getLangId() ? "f.native_name" : "f.foreign_name";
            var resultSet = statement.executeQuery("SELECT " + answerLocalised +
                            " FROM LEARNSET l INNER JOIN FLASHCARD f ON (l.card_id = f.card_id) WHERE NOT (f.card_id = "
                            + answerCard.getId() + ")  LIMIT 3");

            while (resultSet.next()){
                list.add(resultSet.getString(answerLocalised));
            }
            list.add(chosenLanguage == FOREIGN.getLangId() ? answerCard.getNativeName() : answerCard.getForeignName());

            return list;
        } catch (SQLException exception) {
            logger.error("There is a problem with assigning words to buttons.", exception);
        }
        return Collections.emptyList();
    }

    public void decideWhereToSwitch(URL location, ResourceBundle resources, ActionEvent event){
        var destination = random.nextInt(RAND_BOUND.getValue());

        if (destination == CLICK_DESTINATION.getValue()){
            initialize(location, resources);
        }
        else {
            SceneSwitcher.switchToRandomScene(INSERT_DESTINATION.getValue(), event);
        }
    }

    public void assignRadioButtonData(){
        var group = new ToggleGroup();
        buttonList = new ArrayList<>();

        answerA.setToggleGroup(group);
        buttonList.add(answerA);
        answerB.setToggleGroup(group);
        buttonList.add(answerB);
        answerC.setToggleGroup(group);
        buttonList.add(answerC);
        answerD.setToggleGroup(group);
        buttonList.add(answerD);

        var answerList = assignWordsToButtons();
        Collections.shuffle(answerList);

        for (int i = 0; i < answerList.size(); i++){
            var answer = answerList.get(i);
            buttonList.get(i).setText(answer);
            buttonList.get(i).setOnAction(event -> userAnswer = answer);
        }
        for (int i = answerList.size(); buttonList.size() > answerList.size() && i < buttonList.size(); i++){
            buttonList.get(i).setVisible(false);
            buttonList.get(i).toBack();
        }
    }
}
