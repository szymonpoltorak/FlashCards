package pl.edu.pw.ee.flashcards;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.edu.pw.ee.flashcards.database.Connector;
import pl.edu.pw.ee.flashcards.switcher.SceneSwitcher;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static pl.edu.pw.ee.flashcards.database.TablesSQL.*;
import static pl.edu.pw.ee.flashcards.switcher.FxmlUrls.*;

public class MainController implements Initializable {
    @FXML
    private Button addButton;
    @FXML
    private Button learnButton;
    @FXML
    private Button manageSetButton;
    private static final Logger logger = LoggerFactory.getLogger(MainController.class);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addButton.setOnAction(event -> SceneSwitcher.switchToNewScene(SAVE.getPath(), event));

        manageSetButton.setOnAction(event -> SceneSwitcher.switchToNewScene(MANAGE.getPath(), event));

        learnButton.setOnAction(event -> SceneSwitcher.switchToNewScene(CHOOSE.getPath(), event));

        createNecessary();
    }

    public void createNecessary(){
        try (var connector = Connector.establishConnection()) {
            if (connector == null){
                throw new SQLException();
            }

            try (var statement = connector.createStatement()){
                statement.execute(FLASHCARD.getSql());
                statement.execute(CARDSET.getSql());
                statement.execute(LEARNSET.getSql());
            }
        } catch (SQLException exception) {
            logger.error("There is a problem with creating necessary tables.", exception);
        }
    }
}
