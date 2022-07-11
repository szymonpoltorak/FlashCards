package pl.edu.pw.ee.flashcards.utils;

import javafx.scene.control.TreeView;
import org.jetbrains.annotations.NotNull;
import pl.edu.pw.ee.flashcards.card.FlashSet;

import java.sql.Connection;
import java.util.List;
import java.util.Objects;

public class Utility {
    private Utility(){}

    public static List<FlashSet> reloadView(Connection connection, TreeView<String> flashCardsTree){
        var flashSets = CardsReader.readFlashSets(connection);
        CardsReader.readFlashCardsList(Objects.requireNonNull(flashSets), flashCardsTree);

        return flashSets;
    }

    public static boolean isThereSuchElement(String name, @NotNull List<FlashSet> flashSets){
        for (FlashSet flashSet : flashSets){
            if (flashSet.getSetName().equals(name)){
                return true;
            }
        }
        return false;
    }
}
