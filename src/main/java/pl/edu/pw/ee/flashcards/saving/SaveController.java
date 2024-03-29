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
import pl.edu.pw.ee.flashcards.utils.CardsReader;
import pl.edu.pw.ee.flashcards.utils.Reader;
import pl.edu.pw.ee.flashcards.utils.Utility;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
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
    @FXML
    private Button deleteButton;
    private List<FlashSet> flashSets;
    private Connection connection;
    private static final Logger logger = LoggerFactory.getLogger(SaveController.class);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        connection = Connector.establishConnection();
        Reader cardsReader = new CardsReader(connection);
        flashCardsTree.setRoot(new TreeItem<>("Root"));
        flashSets = cardsReader.reloadView(flashCardsTree);

        returnButton.setOnAction(event -> SceneSwitcher.switchToNewScene(MAIN.getPath(), event));

        addButton.setOnAction(event -> {
            if (insertItemsToDataBase()){
                flashSets = cardsReader.reloadView(flashCardsTree);
            }
        });

        deleteButton.setOnAction(event -> {
            if (deleteSelectedFlashCard()){
                flashSets = cardsReader.reloadView(flashCardsTree);
            }
        });
    }

    private boolean insertItemsToDataBase(){
        try (var statement = connection.createStatement()) {
            var foreign = foreignName.getText().trim();
            var motherName = nativeName.getText().trim();

            if (Utility.isThereSuchElement(motherName, flashSets)){
                SaveAlerts.popSameFlashCardAlert();
                logger.error("There is such flashcard already.");
                return false;
            }

            statement.execute("INSERT INTO FLASHCARD(`native_name`, `foreign_name`, `set_name`) VALUES ('" + motherName
                    + "', '" + foreign + "', 'NotGrouped');");
            return true;
        } catch (SQLException exception) {
            logger.error("There is problem with inserting values.", exception);
        }
        return false;
    }

    private boolean deleteSelectedFlashCard(){
        var selectedItem = flashCardsTree.getSelectionModel().getSelectedItem();

        if (Utility.isThereSuchElement(selectedItem.getValue(), flashSets)) {
            SaveAlerts.popDeleteSetAlert();
            logger.error("Sets should not be deleted here.");
            return false;
        }
        try (var statement = connection.createStatement()){
            statement.executeUpdate("DELETE FROM FLASHCARD WHERE `native_name` LIKE '" + selectedItem.getValue() + "';");
            return true;
        } catch (SQLException exception) {
            logger.error("There is problem with deleting values.", exception);
        }
        return false;
    }
}
