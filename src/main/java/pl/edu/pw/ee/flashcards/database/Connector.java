package pl.edu.pw.ee.flashcards.database;

import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static pl.edu.pw.ee.flashcards.database.SQLSettings.*;

public class Connector {
    private static final Logger logger = LoggerFactory.getLogger(Connector.class);

    private Connector(){}

    public static @Nullable Connection establishConnection(){
        try (Connection connection = DriverManager.getConnection(URL.getString(), USER.getString(), PASSWORD.getString())){
            return connection;
        } catch (SQLException exception) {
            logger.error("SQLException occurred in Connector Class!", exception);
        }
        return null;
    }
}
