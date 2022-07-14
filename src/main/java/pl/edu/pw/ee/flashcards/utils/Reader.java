package pl.edu.pw.ee.flashcards.utils;

import javafx.scene.control.TreeView;
import org.jetbrains.annotations.NotNull;
import pl.edu.pw.ee.flashcards.card.FlashSet;

import java.util.List;

public interface Reader {
    List<FlashSet> readFlashSets();

    List<FlashSet> reloadView(TreeView<String> flashCardsTree);

    void readFlashCardsList(@NotNull List<FlashSet> flashSets, @NotNull TreeView<String> flashCardList);
}
