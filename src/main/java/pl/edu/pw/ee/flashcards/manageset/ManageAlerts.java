package pl.edu.pw.ee.flashcards.manageset;

import javafx.scene.control.Alert;

class ManageAlerts {
    private ManageAlerts(){}

    static void popNoSuchCardSetAlert(){
        var alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setContentText("It is not a cardSet!");
        alert.showAndWait();
    }

    static void popSameCardSetAlert(){
        var alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setContentText("You cannot insert second same cardSet!");
        alert.showAndWait();
    }
}
