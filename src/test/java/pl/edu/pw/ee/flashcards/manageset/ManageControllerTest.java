package pl.edu.pw.ee.flashcards.manageset;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.edu.pw.ee.flashcards.card.FlashSet;

import java.util.ArrayList;
import java.util.List;

class ManageControllerTest {
    private List<FlashSet> flashSets;

    @BeforeEach
    void createNeededInstances(){
        flashSets = new ArrayList<>();

        flashSets.add(new FlashSet("name"));
        flashSets.add(new FlashSet("name1"));
        flashSets.add(new FlashSet("name2"));
    }

    @Test
    void getProperFlashSet_there_is_such_set_in_list(){
        //given
        var expected = new FlashSet("name");

        //when
        var result = ManageController.getProperFlashSet("name", flashSets);

        //then
        Assertions.assertEquals(expected, result);
    }

    @Test
    void getProperFlashSet_there_is_no_such_set_in_list(){
        //given
        FlashSet expected = null;

        //when
        var result = ManageController.getProperFlashSet("name5", flashSets);

        //then
        Assertions.assertEquals(expected, result);
    }
}
