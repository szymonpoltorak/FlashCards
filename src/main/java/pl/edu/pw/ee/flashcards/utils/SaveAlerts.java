package pl.edu.pw.ee.flashcards.utils;

import javafx.scene.control.Alert;

public class SaveAlerts {
    private SaveAlerts(){}

    public static void popSameFlashCardAlert(){
        var alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setContentText("You cannot insert second same flashcard!");
        alert.showAndWait();
    }

    public static void popDeleteSetAlert(){
        var alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setContentText("You cannot delete set here! Go to Manage Set.");
        alert.showAndWait();
    }
}
