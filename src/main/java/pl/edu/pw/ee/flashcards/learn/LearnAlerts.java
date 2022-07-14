package pl.edu.pw.ee.flashcards.learn;

import javafx.scene.control.Alert;

public class LearnAlerts {
    private LearnAlerts(){}

    public static void popLearnedAlert(){
        var alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("GOOD JOB!");
        alert.setContentText("Congratulations you have passed every flashcard correctly!");
        alert.showAndWait();
    }

    public static void popSqlError(){
        var alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERROR");
        alert.setContentText("SQL Exception occurred during");
        alert.showAndWait();
    }

    public static void popEmptySetError(){
        var alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Working error");
        alert.setContentText("This is empty, you cannot learn from it!");
        alert.showAndWait();
    }
}
