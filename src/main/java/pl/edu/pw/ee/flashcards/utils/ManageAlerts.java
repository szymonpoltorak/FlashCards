package pl.edu.pw.ee.flashcards.utils;

import javafx.scene.control.Alert;

public class ManageAlerts {
    private ManageAlerts(){}

    public static void popNoSuchCardSetAlert(){
        var alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setContentText("It is not a cardSet!");
        alert.showAndWait();
    }

    public static void popSameCardSetAlert(){
        var alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setContentText("You cannot insert second same cardSet!");
        alert.showAndWait();
    }
}
