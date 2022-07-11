package pl.edu.pw.ee.flashcards.saving;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.edu.pw.ee.flashcards.card.FlashSet;
import pl.edu.pw.ee.flashcards.database.Connector;
import pl.edu.pw.ee.flashcards.switcher.SceneSwitcher;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

import static pl.edu.pw.ee.flashcards.switcher.FxmlUrls.MAIN;

public class SaveController implements Initializable {
    @FXML
    private Button returnButton;
    @FXML
    private Button addButton;
    @FXML
    private TextField foreignName;
    @FXML
    private TextField nativeName;
    @FXML
    private TreeView<String> flashCardsTree;
    private List<FlashSet> flashSets;
    private Connection connection;
    private static final Logger logger = LoggerFactory.getLogger(SaveController.class);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        connection = Connector.establishConnection();

        flashCardsTree.setRoot(new TreeItem<>("Root"));

        flashSets = CardsReader.readFlashSets(Objects.requireNonNull(connection));
        CardsReader.readFlashCardsList(Objects.requireNonNull(flashSets), flashCardsTree);

        returnButton.setOnAction(event -> SceneSwitcher.switchToNewScene(MAIN.getPath(), event));

        addButton.setOnAction(event -> {
            insertItemsToDataBase();
            flashSets = CardsReader.readFlashSets(connection);
            CardsReader.readFlashCardsList(Objects.requireNonNull(flashSets), flashCardsTree);
        });
    }

    public void insertItemsToDataBase(){
        try (var statement = connection.createStatement()) {
            var foreign = foreignName.getText();
            var motherName = nativeName.getText();

            statement.execute("INSERT INTO FLASHCARD(`native_name`, `foreign_name`, `set_name`) VALUES ('" + motherName
                    + "', '" + foreign + "', 'NotGrouped')");
        } catch (SQLException exception) {
            logger.error("There is problem with inserting values.", exception);
        }
    }
}
