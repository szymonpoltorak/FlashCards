package pl.edu.pw.ee.flashcards;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class FlashCardApp extends Application {
    @Override
    public void start(@NotNull Stage stage) throws IOException {
        var fxmlLoader = new FXMLLoader(FlashCardApp.class.getResource("FlashCardController.fxml"));
        var scene = new Scene(fxmlLoader.load(), WindowSize.WIDTH.getValue(), WindowSize.HEIGHT.getValue());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}