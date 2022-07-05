package pl.edu.pw.ee.flashcards;

import java.net.URL;

public enum FxmlUrls {
    SAVE(FxmlUrls.class.getResource("fxml/SaveController.fxml")), MAIN(FxmlUrls.class.getResource("fxml/FlashCardController.fxml"));

    private final URL path;

    FxmlUrls(URL path){
        this.path = path;
    }

    public URL getPath(){
        return path;
    }
}
