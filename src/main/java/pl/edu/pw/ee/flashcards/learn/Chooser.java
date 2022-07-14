package pl.edu.pw.ee.flashcards.learn;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import pl.edu.pw.ee.flashcards.card.FlashCard;

import java.sql.Connection;

public interface Chooser {
    boolean isEveryCardLearn(@NotNull Connection connection);

    @Nullable FlashCard getCardFromSet(@NotNull Connection connection);


}
