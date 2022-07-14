package pl.edu.pw.ee.flashcards.card;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FlashSetTest {
    private FlashSet flashSet;

    @BeforeEach
    void createFlashSetInstance(){
        flashSet = new FlashSet("name");
    }

    @Test
    void addNewFlashCard_test_add_valid_flashcard(){
        //given
        var expected = new FlashCard("name", "name2", 0);

        //when
        flashSet.addNewFlashCard(expected);
        var result = flashSet.getFlashcards().get(0);

        //then
        Assertions.assertEquals(expected, result);
    }

    @Test
    void addNewFlashCard_test_add_null_flashCard(){
        //given
        FlashCard flashCard = null;
        var expected = flashSet.getFlashcards().size();

        //when
        flashSet.addNewFlashCard(flashCard);
        var result = flashSet.getFlashcards().size();

        //then
        Assertions.assertEquals(expected, result);
    }

    @Test
    void addNewFlashCard_test_add_existing_flashCard(){
        //given
        var flashCard = new FlashCard("name", "name2", 0);
        var expected = 1;

        //when
        flashSet.addNewFlashCard(flashCard);
        flashSet.addNewFlashCard(flashCard);
        var result = flashSet.getFlashcards().size();

        //then
        Assertions.assertEquals(expected, result);
    }

    @Test
    void isEmpty_test_empty_list(){
        //given
        var expected = true;

        //when
        var result = flashSet.isEmpty();

        //then
        Assertions.assertEquals(expected, result);
    }

    @Test
    void isEmpty_test_not_empty_list(){
        //given
        var expected = false;
        flashSet.addNewFlashCard(new FlashCard("name", "name2", 1));

        //when
        var result = flashSet.isEmpty();

        //then
        Assertions.assertEquals(expected, result);
    }

    @Test
    void hasSuchFlashCard_test_such_card_is_in_set(){
        //given
        var expected = true;
        flashSet.addNewFlashCard(new FlashCard("name", "name2", 1));

        //when
        var result = flashSet.hasSuchFlashCard("name");

        //then
        Assertions.assertEquals(expected, result);
    }

    @Test
    void hasSuchFlashCard_test_no_such_card_in_set(){
        //given
        var expected = false;

        //when
        var result = flashSet.hasSuchFlashCard("name");

        //then
        Assertions.assertEquals(expected, result);
    }
}
