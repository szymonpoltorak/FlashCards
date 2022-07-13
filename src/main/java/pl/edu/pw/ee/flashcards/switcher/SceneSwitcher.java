package pl.edu.pw.ee.flashcards.switcher;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.edu.pw.ee.flashcards.database.Connector;
import pl.edu.pw.ee.flashcards.utils.DbUtils;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

import static pl.edu.pw.ee.flashcards.learn.SwitchData.INSERT_DESTINATION;
import static pl.edu.pw.ee.flashcards.switcher.FxmlUrls.CLICK;
import static pl.edu.pw.ee.flashcards.switcher.FxmlUrls.INSERT;
import static pl.edu.pw.ee.flashcards.switcher.SceneSettings.ICON;
import static pl.edu.pw.ee.flashcards.switcher.SceneSettings.STYLE;

public class SceneSwitcher {
    private static final Logger logger = LoggerFactory.getLogger(SceneSwitcher.class);

    private SceneSwitcher(){}

    public static void switchToNewScene(URL sceneUrl, @NotNull ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(sceneUrl);
            var stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            var scene = new Scene(root);

            scene.getStylesheets().add(STYLE.getPath());
            stage.getIcons().add(ICON.getImage());
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setOnCloseRequest(closeEvent -> DbUtils.deleteLearnSetData(Objects.requireNonNull(Connector.establishConnection())));

            stage.show();
        } catch (IOException exception){
            logger.error("There is problem with URL to the scene.", exception);
        }
    }

    public static void switchToRandomScene(int id, ActionEvent event){
        SceneSwitcher.switchToNewScene(id == INSERT_DESTINATION.getValue() ? INSERT.getPath() : CLICK.getPath(), event);
    }
}
