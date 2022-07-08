package pl.edu.pw.ee.flashcards.card;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class TestSet2 {
    private final FlashSet set2;

    public TestSet2(){
        set2 = new FlashSet();

        set2.addCardToSet(new FlashCard("myszka", "mouse", 5));
        set2.addCardToSet(new FlashCard("trawa", "grass", 6));
        set2.addCardToSet(new FlashCard("drzewo", "tree", 7));
        set2.addCardToSet(new FlashCard("twarz", "face", 8));
    }
}
