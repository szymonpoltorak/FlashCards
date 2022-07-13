package pl.edu.pw.ee.flashcards.utils;

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

            var resultSet = statement.executeQuery("SELECT * FROM CARDSET;");

            while (resultSet.next()) {
                flashSets.add(new FlashSet(resultSet.getString("set_name")));
            }

            for (FlashSet flashSet : flashSets){
                resultSet = statement.executeQuery("SELECT * FROM FLASHCARD WHERE `set_name` LIKE '" + flashSet.getSetName() + "';");

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

    public static void readFlashCardsList(@NotNull List<FlashSet> flashSets, @NotNull TreeView<String> flashCardList){
        flashCardList.getRoot().getChildren().clear();
        flashCardList.setShowRoot(false);

        for (FlashSet flashSet : flashSets){
            var set = new TreeItem<>(flashSet.getSetName());

            for (FlashCard flashCard : flashSet.getFlashcards()){
                set.getChildren().add(new TreeItem<>(flashCard.getNativeName()));
            }
            flashCardList.getRoot().getChildren().add(set);
        }
    }
}
