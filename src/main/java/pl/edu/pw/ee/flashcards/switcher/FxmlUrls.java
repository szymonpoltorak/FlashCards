package pl.edu.pw.ee.flashcards.switcher;

import pl.edu.pw.ee.flashcards.Main;

import java.net.URL;

public enum FxmlUrls {
    SAVE(Main.class.getResource("fxml/SaveController.fxml")), MAIN(Main.class.getResource("fxml/MainController.fxml")),
    MANAGE(Main.class.getResource("fxml/ManageController.fxml")), CHOOSE(Main.class.getResource("fxml/ChooseSetController.fxml"));

    private final URL path;

    FxmlUrls(URL path){
        this.path = path;
    }

    public URL getPath(){
        return path;
    }
}
