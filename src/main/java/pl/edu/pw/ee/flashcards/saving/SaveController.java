package pl.edu.pw.ee.flashcards.saving;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.edu.pw.ee.flashcards.card.FlashSet;
import pl.edu.pw.ee.flashcards.database.Connector;
import pl.edu.pw.ee.flashcards.switcher.SceneSwitcher;
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
        flashCardsTree.setRoot(new TreeItem<>("Root"));
        flashSets = Utility.reloadView(connection, flashCardsTree);

        returnButton.setOnAction(event -> SceneSwitcher.switchToNewScene(MAIN.getPath(), event));

        addButton.setOnAction(event -> {
            if (insertItemsToDataBase()){
                flashSets = Utility.reloadView(connection, flashCardsTree);
            }
        });

        deleteButton.setOnAction(event -> {
            if (deleteSelectedFlashCard()){
                flashSets = Utility.reloadView(connection, flashCardsTree);
            }
        });
    }

    public boolean insertItemsToDataBase(){
        try (var statement = connection.createStatement()) {
            var foreign = foreignName.getText();
            var motherName = nativeName.getText();

            if (Utility.isThereSuchElement(motherName, flashSets)){
                SaveAlerts.popSameFlashCardAlert();
                return false;
            }

            statement.execute("INSERT INTO FLASHCARD(`native_name`, `foreign_name`, `set_name`) VALUES ('" + motherName
                    + "', '" + foreign + "', 'NotGrouped')");
            return true;
        } catch (SQLException exception) {
            logger.error("There is problem with inserting values.", exception);
        }
        return false;
    }

    public boolean deleteSelectedFlashCard(){
        var selectedItem = flashCardsTree.getSelectionModel().getSelectedItem();

        if (Utility.isThereSuchElement(selectedItem.getValue(), flashSets)) {
            SaveAlerts.popDeleteSetAlert();
            return false;
        }
        try (var statement = connection.createStatement()){
            statement.executeUpdate("DELETE FROM FLASHCARD WHERE `native_name` LIKE '" + selectedItem.getValue() + "'");
            return true;
        } catch (SQLException exception) {
            logger.error("There is problem with deleting values.", exception);
        }
        return false;
    }
}
