package pl.edu.pw.ee.flashcards.manageset;

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
import pl.edu.pw.ee.flashcards.saving.CardsReader;
import pl.edu.pw.ee.flashcards.switcher.SceneSwitcher;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

import static pl.edu.pw.ee.flashcards.switcher.FxmlUrls.MAIN;

public class ManageController implements Initializable {
    @FXML
    private Button returnButton;
    @FXML
    private Button editName;
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
        reloadView();

        createSet.setOnAction(event -> {
            if (createNewSet()){
                reloadView();
            }
        });

        removeButton.setOnAction(event -> {
            if (removeSet()){
                reloadView();
            }
        });

        returnButton.setOnAction(event -> SceneSwitcher.switchToNewScene(MAIN.getPath(), event));
    }

    public boolean createNewSet(){
        var name = setNameField.getText();

        if (isThereSuchSet(name)){
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

    public boolean isThereSuchSet(String name){
        for (FlashSet flashSet : flashSets){
            if (flashSet.getSetName().equals(name)){
                return true;
            }
        }
        return false;
    }

    public boolean removeSet(){
        var selectedItem = flashCardsTree.getSelectionModel().getSelectedItem();

        if (!isThisFlashSet(selectedItem)){
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

    public void reloadView(){
        flashSets = CardsReader.readFlashSets(connection);
        CardsReader.readFlashCardsList(Objects.requireNonNull(flashSets), flashCardsTree);
    }

    public boolean isThisFlashSet(TreeItem<String> susItem){
        for (TreeItem<String> sets : flashCardsTree.getRoot().getChildren()){
            if (sets.equals(susItem)){
                return true;
            }
        }
        return false;
    }
}
