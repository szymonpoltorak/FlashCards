module pl.edu.pw.ee.flashcards {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.jetbrains.annotations;
    requires static lombok;

    opens pl.edu.pw.ee.flashcards to javafx.fxml;
    exports pl.edu.pw.ee.flashcards;

    exports pl.edu.pw.ee.flashcards.switcher;
    opens pl.edu.pw.ee.flashcards.switcher to javafx.fxml;

    exports pl.edu.pw.ee.flashcards.manageset;
    opens pl.edu.pw.ee.flashcards.manageset to javafx.fxml;

    exports pl.edu.pw.ee.flashcards.saving;
    opens pl.edu.pw.ee.flashcards.saving to javafx.fxml;
}