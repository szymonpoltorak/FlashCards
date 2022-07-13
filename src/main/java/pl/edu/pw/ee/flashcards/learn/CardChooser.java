package pl.edu.pw.ee.flashcards.learn;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.edu.pw.ee.flashcards.card.FlashCard;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

public class CardChooser {
    private static final Logger logger = LoggerFactory.getLogger(CardChooser.class);
    private Random random;

    public CardChooser(Random random){
        this.random = random;
    }

    public int getMaxValue(@NotNull Statement statement) throws SQLException {
        try {
            int max = 0;
            int min = 0;
            var maxValueSet = statement.executeQuery("SELECT MAX(`card_id`) AS max, MIN(`card_id`) AS min FROM LEARNSET;");

            while (maxValueSet.next()) {
                min = maxValueSet.getInt("min");
                max = maxValueSet.getInt("max");
            }
            random = SecureRandom.getInstanceStrong();

            return random.nextInt(max - min) + min;
        } catch (NoSuchAlgorithmException exception) {
            logger.error("There is an error in creating Random class instance", exception);
        }
        return -1;
    }

    public boolean isEveryCardLearn(@NotNull Connection connection){
        try (var statement = connection.createStatement()) {
            var resultSet = statement.executeQuery("SELECT COUNT(*) AS LEARNED FROM LEARNSET WHERE learned = false");
            int number = -1;

            while (resultSet.next()){
                number = resultSet.getInt("LEARNED");
            }
            System.out.println("Number : " + number);
            if (number == 0){
                return true;
            }
        } catch (SQLException exception) {
            logger.error("There is a problem with selecting learned records from learnSet.", exception);
        }
        return false;
    }

    public @Nullable FlashCard getCardFromSet(@NotNull Connection connection){
        try (var statement = connection.createStatement()){
            String nativeName = "";
            String foreignName = "";
            int id = 0;
            var wasLearned = false;

            while (nativeName.equals("") || foreignName.equals("") || wasLearned) {
                id = getMaxValue(statement);
                var resultSet = statement.executeQuery("SELECT f.native_name, f.foreign_name, f.card_id, l.learned " +
                        " FROM LEARNSET l INNER JOIN FLASHCARD f ON (f.card_id = l.card_id) WHERE l.card_id = " + id + ";");

                while (resultSet.next()) {
                    nativeName = resultSet.getString("native_name");
                    foreignName = resultSet.getString("foreign_name");
                    wasLearned = resultSet.getBoolean("learned");
                }
            }
            return new FlashCard(nativeName, foreignName, id);
        } catch (SQLException exception) {
            logger.error("There is an error in getting card from LearnSet.", exception);
        }
        return null;
    }
}
