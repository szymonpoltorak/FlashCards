package pl.edu.pw.ee.flashcards.database;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static pl.edu.pw.ee.flashcards.database.SQLSettings.*;

@NoArgsConstructor
public class Connector {
    private static final Logger logger = LoggerFactory.getLogger(Connector.class);

    public @Nullable Connection establishConnection(){
        try {
            return DriverManager.getConnection(URL.getString(), USER.getString(), PASSWORD.getString());
        } catch (SQLException exception) {
            logger.error("SQLException occurred in Connector Class!", exception);
        }
        return null;
    }
}
