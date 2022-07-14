package pl.edu.pw.ee.flashcards.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.edu.pw.ee.flashcards.card.FlashCard;
import pl.edu.pw.ee.flashcards.card.FlashSet;

import java.util.ArrayList;
import java.util.List;

class UtilityTest {
    private List<FlashSet> flashSets;

    @BeforeEach
    void createNeededInstances(){
        flashSets = new ArrayList<>();

        flashSets.add(new FlashSet("name"));
        flashSets.add(new FlashSet("name1"));
        flashSets.add(new FlashSet("name2"));
    }

    @Test
    void isThereSuchElement_there_is_no_such_element(){
        //given
        var expected = false;

        //when
        var result = Utility.isThereSuchElement("hello", flashSets);

        //then
        Assertions.assertEquals(expected, result);
    }

    @Test
    void isThereSuchElement_there_is_such_element(){
        //given
        var expected = true;

        //when
        var result = Utility.isThereSuchElement("name1", flashSets);

        //then
        Assertions.assertEquals(expected, result);
    }

    @Test
    void isThereSuchFlashCard_there_is_no_such_flashcard(){
        //given
        var expected = false;

        //when
        var result = Utility.isThereSuchFlashCard("name4", flashSets);

        //then
        Assertions.assertEquals(expected, result);
    }

    @Test
    void isThereSuchFlashCard_there_is_such_flashcard(){
        //given
        var expected = true;
        flashSets.get(1).addNewFlashCard(new FlashCard("name5", "name7", 2));

        //when
        var result = Utility.isThereSuchFlashCard("name5", flashSets);

        //then
        Assertions.assertEquals(expected, result);
    }
}
