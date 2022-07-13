package pl.edu.pw.ee.flashcards.utils;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;

public class DbUtils {
    private static final Logger logger = LoggerFactory.getLogger(DbUtils.class);

    private DbUtils(){}

    public static void deleteLearnSetData(@NotNull Connection connection){
        try(var statement = connection.createStatement()){
            statement.execute("DELETE FROM LEARNSET;");
            statement.execute("ALTER TABLE LEARNSET AUTO_INCREMENT = 1;");
        } catch (SQLException exception) {
            logger.error("There is problem in deleting learnSet table.", exception);
        }
    }
}
