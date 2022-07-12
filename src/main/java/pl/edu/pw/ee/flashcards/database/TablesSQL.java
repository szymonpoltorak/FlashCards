package pl.edu.pw.ee.flashcards.database;

public enum TablesSQL {
    FLASHCARD("CREATE TABLE IF NOT EXISTS FLASHCARD(\n" +
            "    `card_id`         INT            NOT NULL AUTO_INCREMENT,\n" +
            "    `native_name`     TEXT           NOT NULL,\n" +
            "    `foreign_name`    TEXT           NOT NULL,\n" +
            "    `set_name`        VARCHAR(100)   NOT NULL,\n" +
            "    PRIMARY KEY (`card_id`),\n" +
            "    FOREIGN KEY (`set_name`) REFERENCES CARDSET(`set_name`)\n" +
            ") ENGINE=INNODB DEFAULT CHARSET=UTF8;"),

    CARDSET("CREATE TABLE IF NOT EXISTS CARDSET(\n" +
            "    `set_name`  VARCHAR(100)    NOT NULL,\n" +
            "    PRIMARY KEY (`set_name`)\n" +
            ") ENGINE=INNODB DEFAULT CHARSET=UTF8;"),
    LEARNSET("CREATE TABLE IF NOT EXISTS LEARNSET(\n" +
            "    `learned`   BOOLEAN     NOT NULL    DEFAULT (FALSE),\n" +
            "    `card_id`   INT         NOT NULL    UNIQUE,\n" +
            "    `set_id`    INT         NOT NULL    AUTO_INCREMENT,\n" +
            "    FOREIGN KEY (`card_id`) REFERENCES FLASHCARD(`card_id`),\n" +
            "    PRIMARY KEY (`set_id`)\n" +
            ") ENGINE=INNODB DEFAULT CHARSET=UTF8;");
    private final String sql;

    TablesSQL(String sql){
        this.sql = sql;
    }

    public String getSql(){
        return sql;
    }
}
