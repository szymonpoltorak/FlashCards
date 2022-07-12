CREATE TABLE IF NOT EXISTS CARDSET(
    `set_name`  VARCHAR(100)    NOT NULL,
    PRIMARY KEY (`set_name`)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;

CREATE TABLE IF NOT EXISTS FLASHCARD(
    `card_id`         INT            NOT NULL AUTO_INCREMENT,
    `native_name`     TEXT           NOT NULL,
    `foreign_name`    TEXT           NOT NULL,
    `set_name`        VARCHAR(100)   NOT NULL,
    PRIMARY KEY (`card_id`),
    FOREIGN KEY (`set_name`) REFERENCES CARDSET(`set_name`)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;

CREATE TABLE IF NOT EXISTS LEARNSET(
    `learned`   BOOLEAN     NOT NULL    DEFAULT (FALSE),
    `card_id`   INT         NOT NULL    UNIQUE,
    `set_id`    INT         NOT NULL    AUTO_INCREMENT,
    FOREIGN KEY (`card_id`) REFERENCES FLASHCARD(`card_id`),
    PRIMARY KEY (`set_id`)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;

SELECT * FROM LEARNSET;