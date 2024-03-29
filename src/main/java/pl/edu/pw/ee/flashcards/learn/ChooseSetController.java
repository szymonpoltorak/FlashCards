package pl.edu.pw.ee.flashcards.learn;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.edu.pw.ee.flashcards.card.FlashSet;
import pl.edu.pw.ee.flashcards.database.Connector;
import pl.edu.pw.ee.flashcards.manageset.ManageController;
import pl.edu.pw.ee.flashcards.switcher.SceneSwitcher;
import pl.edu.pw.ee.flashcards.utils.CardsReader;
import pl.edu.pw.ee.flashcards.utils.DbUtils;
import pl.edu.pw.ee.flashcards.utils.Reader;

import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

import static pl.edu.pw.ee.flashcards.learn.SwitchData.RAND_BOUND;
import static pl.edu.pw.ee.flashcards.switcher.FxmlUrls.MAIN;
import static pl.edu.pw.ee.flashcards.utils.Icons.CARDSET;

public class ChooseSetController implements Initializable {
    @FXML
    private Button letsLearnButton;
    @FXML
    private Button returnButton;
    @FXML
    private ListView<String> setList;
    @FXML
    private TextField numberCardField;
    @FXML
    private Button saveNumberButton;
    private Connection connection;
    private List<FlashSet> flashSets;
    private int numberOfCards;
    private static final Logger logger = LoggerFactory.getLogger(ChooseSetController.class);
    private Random random;
    private Reader reader;

    @Override
    public final void initialize(URL location, ResourceBundle resources) {
        connection = Connector.establishConnection();
        reader = new CardsReader(connection);
        flashSets = reader.readFlashSets();
        readSetList();

        try {
            random = SecureRandom.getInstanceStrong();
        } catch (NoSuchAlgorithmException exception) {
            logger.error("There is a problem with random number generator.", exception);
        }

        saveNumberButton.setOnAction(event -> {
            try {
                numberOfCards = Integer.parseInt(numberCardField.getText());
            } catch (NumberFormatException exception) {
                logger.error("It is not integer number!!", exception);
            }
        });

        letsLearnButton.setOnAction(event -> {
            DbUtils.deleteLearnSetData(connection);
            if (!prepareDataBaseTable()){
                LearnAlerts.popEmptySetError();
                return;
            }
            SceneSwitcher.switchToRandomScene(random.nextInt(RAND_BOUND.getValue()), event);
        });

        returnButton.setOnAction(event -> {
            SceneSwitcher.switchToNewScene(MAIN.getPath(), event);
            DbUtils.deleteLearnSetData(connection);
        });
    }

    private void readSetList(){
        setList.getItems().clear();

        for (FlashSet flashSet : flashSets){
            setList.getItems().add(flashSet.getSetName());
        }
        setList.setCellFactory(param -> new ListCell<>(){
            @Override
            public void updateItem(String name, boolean empty) {
                super.updateItem(name, empty);
                if (!empty) {
                    setText(name);
                    setGraphic(new ImageView(CARDSET.getIcon()));
                }
            }
        });
    }

    private boolean prepareDataBaseTable(){
        try (var statement = connection.createStatement()){
            var set = ManageController.getProperFlashSet(setList.getSelectionModel().getSelectedItem(), flashSets);

            if (set == null || set.isEmpty()){
                logger.error("Set is empty.");
                return false;
            }
            numberOfCards = numberOfCards == 0 ? set.getFlashcards().size() : numberOfCards;

            while (numberOfCards > 0){
                var card = random.nextInt(set.getFlashcards().size());
                var id = set.getFlashcards().get(card).getId();
                var result = statement.executeQuery("SELECT `card_id` FROM LEARNSET WHERE `card_id` = " + id + ";");

                if (!wasThereDuplicate(result, id)) {
                    statement.execute("INSERT INTO LEARNSET(`card_id`) VALUES (" + id + ") ON DUPLICATE KEY UPDATE `card_id`=`card_id`;");
                    numberOfCards--;
                }
            }
            return true;
        } catch (SQLException exception) {
            logger.error("There is a problem with creating", exception);
            LearnAlerts.popSqlError();
        }
        return false;
    }

    private boolean wasThereDuplicate(@NotNull ResultSet result, int id) throws SQLException{
        while (result.next()){
            if (result.getInt("card_id") == id){
                return true;
            }
        }
        return false;
    }
}
