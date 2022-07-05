package pl.edu.pw.ee.flashcards.switcher;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.net.URL;

import static pl.edu.pw.ee.flashcards.switcher.SceneSettings.ICON;
import static pl.edu.pw.ee.flashcards.switcher.SceneSettings.STYLE;

public class SceneSwitcher {
    private SceneSwitcher(){}

    public static void switchToNewScene(URL sceneUrl, @NotNull ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(sceneUrl);
        var stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        var scene = new Scene(root);

        scene.getStylesheets().add(STYLE.getPath());
        stage.getIcons().add(ICON.getImage());
        stage.setScene(scene);

        stage.show();
    }
}
