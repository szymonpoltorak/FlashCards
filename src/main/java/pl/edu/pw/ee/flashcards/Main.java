package pl.edu.pw.ee.flashcards;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import static pl.edu.pw.ee.flashcards.switcher.FxmlUrls.MAIN;
import static pl.edu.pw.ee.flashcards.switcher.SceneSettings.ICON;
import static pl.edu.pw.ee.flashcards.switcher.SceneSettings.STYLE;

public class Main extends Application {
    @Override
    public void start(@NotNull Stage stage) throws IOException {
        var scene = new Scene(FXMLLoader.load(MAIN.getPath()));

        stage.setTitle("FlashCards");
        scene.getStylesheets().add(STYLE.getPath());
        stage.getIcons().add(ICON.getImage());
        stage.setScene(scene);

        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}