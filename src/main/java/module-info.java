module pl.edu.pw.ee.flashcards {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.jetbrains.annotations;
    requires static lombok;

    opens pl.edu.pw.ee.flashcards to javafx.fxml;
    exports pl.edu.pw.ee.flashcards;
}