package pl.edu.pw.ee.flashcards;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.net.URL;

public class SceneSwitcher {
    private SceneSwitcher(){}

    public static void switchToNewScene(URL sceneUrl, @NotNull ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(sceneUrl);
        var stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        var scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
