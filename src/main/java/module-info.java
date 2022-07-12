module pl.edu.pw.ee.flashcards {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires org.jetbrains.annotations;
    requires logback.core;
    requires org.slf4j;
    requires mysql.connector.java;
    requires static lombok;
    requires java.sql;

    opens pl.edu.pw.ee.flashcards to javafx.fxml;
    exports pl.edu.pw.ee.flashcards;

    opens pl.edu.pw.ee.flashcards.learn to javafx.fxml;
    exports pl.edu.pw.ee.flashcards.learn;

    exports pl.edu.pw.ee.flashcards.switcher;
    opens pl.edu.pw.ee.flashcards.switcher to javafx.fxml;

    exports pl.edu.pw.ee.flashcards.manageset;
    opens pl.edu.pw.ee.flashcards.manageset to javafx.fxml;

    exports pl.edu.pw.ee.flashcards.saving;
    opens pl.edu.pw.ee.flashcards.saving to javafx.fxml;

    exports pl.edu.pw.ee.flashcards.database;
    exports pl.edu.pw.ee.flashcards.card;
    exports pl.edu.pw.ee.flashcards.utils;
    opens pl.edu.pw.ee.flashcards.utils to javafx.fxml;
}