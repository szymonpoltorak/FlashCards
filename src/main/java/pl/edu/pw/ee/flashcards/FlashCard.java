package pl.edu.pw.ee.flashcards;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import static pl.edu.pw.ee.flashcards.FxmlUrls.MAIN;

public class FlashCard extends Application {
    @Override
    public void start(@NotNull Stage stage) throws IOException {
        var scene = new Scene(FXMLLoader.load(MAIN.getPath()));

        stage.setTitle("FlashCards");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}