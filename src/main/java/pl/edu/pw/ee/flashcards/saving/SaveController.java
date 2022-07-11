package pl.edu.pw.ee.flashcards.saving;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import pl.edu.pw.ee.flashcards.card.FlashSet;
import pl.edu.pw.ee.flashcards.database.Connector;
import pl.edu.pw.ee.flashcards.switcher.SceneSwitcher;

import java.net.URL;
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
    private Connector connector;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        connector = new Connector();

        flashCardsTree.setRoot(new TreeItem<>("Root"));

        flashSets = CardsReader.readFlashSets(Objects.requireNonNull(connector.establishConnection()));
        CardsReader.readFlashCardsList(Objects.requireNonNull(flashSets), flashCardsTree);

        returnButton.setOnAction(event -> SceneSwitcher.switchToNewScene(MAIN.getPath(), event));

        addButton.setOnAction(event -> {

        });
    }
}
