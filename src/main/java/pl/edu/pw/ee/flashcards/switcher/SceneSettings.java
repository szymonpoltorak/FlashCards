package pl.edu.pw.ee.flashcards.switcher;

import javafx.scene.image.Image;
import pl.edu.pw.ee.flashcards.Main;

import java.util.Objects;

public enum SceneSettings {
    STYLE(Objects.requireNonNull(Main.class.getResource("css/style.css")).toExternalForm()),
    ICON(new Image(Objects.requireNonNull(Main.class.getResource("images/icon.png")).toExternalForm()));

    private String path;
    private Image image;

    SceneSettings(String path){
        this.path = path;
    }

    SceneSettings(Image image){
        this.image = image;
    }

    public String getPath(){
        if (path == null){
            throw new IllegalStateException();
        }
        return path;
    }

    public Image getImage(){
        if (image == null){
            throw new IllegalStateException();
        }
        return image;
    }
}
