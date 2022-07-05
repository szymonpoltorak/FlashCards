package pl.edu.pw.ee.flashcards.switcher;

import pl.edu.pw.ee.flashcards.FlashCard;

import java.net.URL;

public enum FxmlUrls {
    SAVE(FlashCard.class.getResource("fxml/SaveController.fxml")), MAIN(FlashCard.class.getResource("fxml/FlashCardController.fxml")),
    MANAGE(FlashCard.class.getResource("fxml/ManageController.fxml"));

    private final URL path;

    FxmlUrls(URL path){
        this.path = path;
    }

    public URL getPath(){
        return path;
    }
}
