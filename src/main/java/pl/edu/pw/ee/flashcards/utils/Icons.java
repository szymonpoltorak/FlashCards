package pl.edu.pw.ee.flashcards.utils;

import javafx.scene.image.Image;
import pl.edu.pw.ee.flashcards.Main;

import java.util.Objects;

public enum Icons {
    FLASHCARD(new Image(Objects.requireNonNull(Main.class.getResource("images/flashcard.png")).toExternalForm())),
    CARDSET(new Image(Objects.requireNonNull(Main.class.getResource("images/cardset.png")).toExternalForm()));

    private final Image icon;

    Icons(Image icon){
        this.icon = icon;
    }

    public Image getIcon(){
        return icon;
    }
}
