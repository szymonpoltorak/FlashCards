package pl.edu.pw.ee.flashcards.saving;

import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.edu.pw.ee.flashcards.card.FlashCard;
import pl.edu.pw.ee.flashcards.card.FlashSet;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CardsReader {
    private static final Logger logger = LoggerFactory.getLogger(CardsReader.class);

    private CardsReader(){}

    @Contract(pure = true)
    public static @Nullable List<FlashSet> readFlashSets(@NotNull Connection connection) {
        try (var statement = connection.createStatement()) {
            var flashSets = new ArrayList<FlashSet>();

            var resultSet = statement.executeQuery("SELECT * FROM CARDSET");

            while (resultSet.next()) {
                flashSets.add(new FlashSet(resultSet.getString("set_name")));
            }

            for (FlashSet flashSet : flashSets){
                resultSet = statement.executeQuery("SELECT * FROM FLASHCARD WHERE `set_name` LIKE '" + flashSet.getSetName() + "'");

                while (resultSet.next()){
                    var nativeName = resultSet.getString("native_name");
                    var foreignName = resultSet.getString("foreign_name");
                    var id = resultSet.getInt("card_id");

                    flashSet.addNewFlashCard(new FlashCard(nativeName, foreignName, id));
                }
            }
            return flashSets;
        } catch (SQLException exception) {
            logger.error("Statement created sql exception", exception);
        }
        return null;
    }

    public static @NotNull TreeView<String> readFlashCardsList(List<FlashSet> flashSets){
        var flashCardList = new TreeView<String>();

        flashCardList.setRoot(new TreeItem<>("Root"));
        flashCardList.setShowRoot(false);

        return flashCardList;
    }
}
