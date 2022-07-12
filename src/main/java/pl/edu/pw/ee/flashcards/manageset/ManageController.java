package pl.edu.pw.ee.flashcards.manageset;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.edu.pw.ee.flashcards.card.FlashSet;
import pl.edu.pw.ee.flashcards.database.Connector;
import pl.edu.pw.ee.flashcards.switcher.SceneSwitcher;
import pl.edu.pw.ee.flashcards.utils.Utility;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ResourceBundle;

import static pl.edu.pw.ee.flashcards.switcher.FxmlUrls.MAIN;

public class ManageController implements Initializable {
    @FXML
    private Button returnButton;
    @FXML
    private Button editButton;
    @FXML
    private Button createSet;
    @FXML
    private Button removeButton;
    @FXML
    private TextField setNameField;
    @FXML
    private TextField editNameField;
    @FXML
    private TreeView<String> flashCardsTree;
    @FXML
    private Button moveButton;
    @FXML
    private TextField moveField;
    private List<FlashSet> flashSets;
    private Connection connection;
    private static final Logger logger = LoggerFactory.getLogger(ManageController.class);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        connection = Connector.establishConnection();
        flashCardsTree.setRoot(new TreeItem<>("root"));
        flashSets = Utility.reloadView(connection, flashCardsTree);

        createSet.setOnAction(event -> {
            if (createNewSet()){
                flashSets = Utility.reloadView(connection, flashCardsTree);
            }
        });

        removeButton.setOnAction(event -> {
            if (removeSet()){
                flashSets = Utility.reloadView(connection, flashCardsTree);
            }
        });

        editButton.setOnAction(event -> {
            if (editName()){
                flashSets = Utility.reloadView(connection, flashCardsTree);
            }
        });

        moveButton.setOnAction(event -> {
            if (moveElement()){
                flashSets = Utility.reloadView(connection, flashCardsTree);
            }
        });

        returnButton.setOnAction(event -> SceneSwitcher.switchToNewScene(MAIN.getPath(), event));
    }

    public boolean createNewSet(){
        var name = setNameField.getText();

        if (Utility.isThereSuchElement(name, flashSets)){
            ManageAlerts.popSameCardSetAlert();
            logger.error("There is such cardSet already");
            return false;
        }

        try (var statement = connection.createStatement()){
            statement.execute("INSERT INTO CARDSET(`set_name`) VALUES ('" + name + "')");
            return true;
        } catch (SQLException exception) {
            logger.error("There is problem with inserting new cardSet.", exception);
        }
        return false;
    }

    public boolean removeSet(){
        var selectedItem = flashCardsTree.getSelectionModel().getSelectedItem();

        if (!Utility.isThereSuchElement(selectedItem.getValue(), flashSets)){
            ManageAlerts.popNoSuchCardSetAlert();
            logger.error("There is no such card set.");
            return false;
        }

        try (var statement = connection.createStatement()){
            statement.executeUpdate("DELETE FROM CARDSET WHERE (`set_name` LIKE '" + selectedItem.getValue() + "')");
            return true;
        } catch (SQLException exception) {
            logger.error("There is problem with deleting cardSet.", exception);
        }
        return false;
    }

    public boolean editName(){
        var newName = editNameField.getText();
        var selectedItem = flashCardsTree.getSelectionModel().getSelectedItem();
        var selectedName = selectedItem.getValue();

        if (newName == null){
            logger.info("New name is null.");
            return false;
        }

        try (var statement = connection.createStatement()){
            if (Utility.isThereSuchElement(selectedName, flashSets)){
                editFlashSetsName(selectedName, newName, statement);
            }
            else if (Utility.isThereSuchFlashCard(selectedName, flashSets)){
                editFlashCardsName(statement, newName, selectedName);
            }
            return true;
        } catch (SQLException exception) {
            logger.error("There is problem with editing names.", exception);
        }
        return false;
    }

    public boolean moveElement(){
        var newSetName = moveField.getText();
        var selectedItem = flashCardsTree.getSelectionModel().getSelectedItem();

        if (!Utility.isThereSuchElement(newSetName, flashSets) || Utility.isThereSuchFlashCard(newSetName, flashSets)){
            logger.error("There is no such flashSet or this is flashcard");
            return false;
        }

        try (var statement = connection.createStatement()){
            statement.executeUpdate("UPDATE FLASHCARD SET `set_name` = '" + newSetName + "' WHERE `native_name` LIKE '" + selectedItem.getValue() + "'");
        } catch (SQLException exception) {
            logger.error("There is problem with moving flashcard to another set.", exception);
        }
        return true;
    }

    public static @Nullable FlashSet getProperFlashSet(String name, @NotNull List<FlashSet> flashSets){
        for (FlashSet flashSet : flashSets){
            if (flashSet.getSetName().equals(name)){
                return flashSet;
            }
        }
        return null;
    }

    public void editFlashSetsName(String selectedName, String newName, Statement statement) throws SQLException{
        var flashSet = getProperFlashSet(selectedName, flashSets);

        if (flashSet == null){
            logger.error("flashSet is null");
            return;
        }

        if (flashSet.getFlashcards().isEmpty()){
            statement.executeUpdate("UPDATE CARDSET SET `set_name` = '" + newName + "' WHERE `set_name` LIKE '" + selectedName + "'");
        }
        else {
            statement.execute("INSERT INTO CARDSET(`set_name`) VALUES ('" + newName + "')");
            statement.executeUpdate("UPDATE FLASHCARD SET `set_name` = '" + newName + "' WHERE `set_name` LIKE '" + selectedName + "'");
            statement.executeUpdate("DELETE FROM CARDSET WHERE `set_name` LIKE '" + selectedName + "'");
        }
    }

    public void editFlashCardsName(Statement statement, String newName, String selectedName) throws SQLException{
        var dialog = new TextInputDialog();
        dialog.setTitle("Need more intel");
        dialog.setContentText("Please give me a foreign name you want to edit. Leave free space to not change");
        var result = dialog.showAndWait();

        if (result.isPresent() && !result.get().equals("")){
            statement.executeUpdate("UPDATE FLASHCARD SET `native_name` = '" + newName +
                    "', `foreign_name` = '" + result.get() + "' WHERE `native_name` LIKE '" + selectedName + "'");
        }
        else {
            statement.executeUpdate("UPDATE FLASHCARD SET `native_name` = '" + newName + "' WHERE `native_name` LIKE '" + selectedName + "'");
        }
    }
}
