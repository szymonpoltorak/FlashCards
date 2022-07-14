INSERT INTO `CARDSET`(`set_name`) VALUES ('Work');
INSERT INTO `CARDSET`(`set_name`) VALUES ('Colors');
INSERT INTO `CARDSET`(`set_name`) VALUES ('NotGrouped');

INSERT INTO `FLASHCARD`(`native_name`, `foreign_name`, `set_name`) VALUES ('Praca', 'Job', 'Work');
INSERT INTO `FLASHCARD`(`native_name`, `foreign_name`, `set_name`) VALUES ('Ogrodnik', 'Gardener', 'Work');
INSERT INTO `FLASHCARD`(`native_name`, `foreign_name`, `set_name`) VALUES ('Gracz', 'Player', 'Work');
INSERT INTO `FLASHCARD`(`native_name`, `foreign_name`, `set_name`) VALUES ('Nauczyciel', 'Teacher', 'Work');

INSERT INTO `FLASHCARD`(`native_name`, `foreign_name`, `set_name`) VALUES ('Czerwony', 'Red', 'Colors');
INSERT INTO `FLASHCARD`(`native_name`, `foreign_name`, `set_name`) VALUES ('Niebieski', 'Blue', 'Colors');
INSERT INTO `FLASHCARD`(`native_name`, `foreign_name`, `set_name`) VALUES ('Zielony', 'Green', 'Colors');
INSERT INTO `FLASHCARD`(`native_name`, `foreign_name`, `set_name`) VALUES ('Czarny', 'Black', 'Colors');

INSERT INTO `FLASHCARD`(`native_name`, `foreign_name`, `set_name`) VALUES ('Policjant', 'Policeman', 'NotGrouped');
INSERT INTO `FLASHCARD`(`native_name`, `foreign_name`, `set_name`) VALUES ('Drzewo', 'Tree', 'NotGrouped');
INSERT INTO `FLASHCARD`(`native_name`, `foreign_name`, `set_name`) VALUES ('Szkoła', 'School', 'NotGrouped');
