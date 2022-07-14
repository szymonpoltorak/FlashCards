package pl.edu.pw.ee.flashcards.utils;

import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.ImageView;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.edu.pw.ee.flashcards.card.FlashCard;
import pl.edu.pw.ee.flashcards.card.FlashSet;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static pl.edu.pw.ee.flashcards.utils.Icons.CARDSET;
import static pl.edu.pw.ee.flashcards.utils.Icons.FLASHCARD;

public class CardsReader implements Reader{
    private static final Logger logger = LoggerFactory.getLogger(CardsReader.class);
    private final Connection connection;

    public CardsReader(Connection connection){
        this.connection = connection;
    }

    @Override
    public List<FlashSet> reloadView(TreeView<String> flashCardsTree){
        var flashSets = readFlashSets();
        readFlashCardsList(Objects.requireNonNull(flashSets), flashCardsTree);

        return flashSets;
    }

    @Override
    public List<FlashSet> readFlashSets() {
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
        return Collections.emptyList();
    }

    @Override
    public void readFlashCardsList(@NotNull List<FlashSet> flashSets, @NotNull TreeView<String> flashCardList){
        flashCardList.getRoot().getChildren().clear();
        flashCardList.setShowRoot(false);

        for (FlashSet flashSet : flashSets){
            var set = new TreeItem<>(flashSet.getSetName(), new ImageView(CARDSET.getIcon()));

            for (FlashCard flashCard : flashSet.getFlashcards()){
                set.getChildren().add(new TreeItem<>(flashCard.getNativeName(), new ImageView(FLASHCARD.getIcon())));
            }
            flashCardList.getRoot().getChildren().add(set);
        }
    }
}
