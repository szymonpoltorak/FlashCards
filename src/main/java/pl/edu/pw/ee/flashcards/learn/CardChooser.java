package pl.edu.pw.ee.flashcards.learn;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.edu.pw.ee.flashcards.card.FlashCard;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@NoArgsConstructor
public class CardChooser {
    private static final Logger logger = LoggerFactory.getLogger(CardChooser.class);

    public int getRandomId(@NotNull Statement statement) throws SQLException {
        var resultSet = statement.executeQuery("SELECT `card_id` FROM LEARNSET WHERE learned = FALSE ORDER BY RAND() LIMIT 1");
        int id = -1;

        while (resultSet.next()) {
            id = resultSet.getInt("card_id");
        }
        return id;
    }

    public boolean isEveryCardLearn(@NotNull Connection connection){
        try (var statement = connection.createStatement()) {
            var resultSet = statement.executeQuery("SELECT COUNT(*) AS LEARNED FROM LEARNSET WHERE learned = false");
            int number = -1;

            while (resultSet.next()){
                number = resultSet.getInt("LEARNED");
            }
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
                id = getRandomId(statement);
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
