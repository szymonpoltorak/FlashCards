package pl.edu.pw.ee.flashcards.utils;

import javafx.scene.control.Alert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.edu.pw.ee.flashcards.card.FlashCard;

import java.sql.Connection;
import java.sql.SQLException;

public class AnswerUtils {
    private static final Logger logger = LoggerFactory.getLogger(AnswerUtils.class);

    private AnswerUtils(){}

    public static void handleAnswer(String userAnswer, String correctAnswer, Connection connection, FlashCard answerCard){
        if (!correctAnswer.equals(userAnswer)){
            var alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Your answer");
            alert.setContentText("Your answer was incorrect! Correct answer was " + correctAnswer);
            alert.showAndWait();
        } else {
            try (var statement = connection.createStatement()) {
                statement.executeUpdate("UPDATE LEARNSET SET `learned` = TRUE WHERE `card_id` = " + answerCard.getId() + ";");
            } catch (SQLException exception) {
                logger.error("There is a problem with updating learned record in learnSet", exception);
            }
        }
    }
}
