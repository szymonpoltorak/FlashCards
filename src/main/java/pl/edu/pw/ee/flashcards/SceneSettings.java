package pl.edu.pw.ee.flashcards;

import javafx.scene.image.Image;

import java.util.Objects;

public enum SceneSettings {
    STYLE(Objects.requireNonNull(SceneSettings.class.getResource("css/style.css")).toExternalForm()),
    ICON(new Image(Objects.requireNonNull(SceneSettings.class.getResource("images/icon.png")).toExternalForm()));

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
