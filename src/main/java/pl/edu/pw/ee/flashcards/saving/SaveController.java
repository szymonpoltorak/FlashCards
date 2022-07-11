package pl.edu.pw.ee.flashcards.saving;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
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
    @FXML
    private Button deleteButton;
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
            if (insertItemsToDataBase()){
                reloadView();
            }
        });

        deleteButton.setOnAction(event -> {
            if (deleteSelectedFlashCard()){
                reloadView();
            }
        });
    }

    public boolean insertItemsToDataBase(){
        try (var statement = connection.createStatement()) {
            var foreign = foreignName.getText();
            var motherName = nativeName.getText();

            statement.execute("INSERT INTO FLASHCARD(`native_name`, `foreign_name`, `set_name`) VALUES ('" + motherName
                    + "', '" + foreign + "', 'NotGrouped')");
            return true;
        } catch (SQLException exception) {
            logger.error("There is problem with inserting values.", exception);
        }
        return false;
    }

    public void reloadView(){
        flashSets = CardsReader.readFlashSets(connection);
        CardsReader.readFlashCardsList(Objects.requireNonNull(flashSets), flashCardsTree);
    }

    public boolean deleteSelectedFlashCard(){
        var selectedItem = flashCardsTree.getSelectionModel().getSelectedItem();

        if (!isThisFlashCard(selectedItem)){
            try (var statement = connection.createStatement()){
                var name = selectedItem.getValue();
                statement.executeUpdate("DELETE FROM FLASHCARD WHERE `native_name` LIKE '" + name + "'");
                return true;
            } catch (SQLException exception) {
                logger.error("There is problem with deleting values.", exception);
            }
        }
        else {
            var alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setContentText("You cannot delete set here! Go to Manage Set.");
            alert.showAndWait();
        }
        return false;
    }

    public boolean isThisFlashCard(TreeItem<String> susItem){
        for (TreeItem<String> sets : flashCardsTree.getRoot().getChildren()){
            if (sets.equals(susItem)){
                return true;
            }
        }
        return false;
    }
}
