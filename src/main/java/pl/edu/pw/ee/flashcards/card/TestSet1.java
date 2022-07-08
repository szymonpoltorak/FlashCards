package pl.edu.pw.ee.flashcards.card;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class TestSet1 {
    private final FlashSet set1;

    public TestSet1(){
        set1 = new FlashSet();

        set1.addCardToSet(new FlashCard("imie", "name", 1));
        set1.addCardToSet(new FlashCard("nazwisko", "surname", 2));
        set1.addCardToSet(new FlashCard("dom", "house", 3));
        set1.addCardToSet(new FlashCard("noga", "leg", 4));
    }
}
