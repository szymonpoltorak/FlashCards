package pl.edu.pw.ee.flashcards.card;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.HashMap;

@ToString
@EqualsAndHashCode
public class FlashSet {
    private final HashMap<Integer, FlashCard> set;

    public FlashSet() {
        this.set = new HashMap<>();
    }

    public void addCardToSet(FlashCard card){
        this.set.put(card.getId(), card);
    }

    public FlashCard getCardFromSet(int id){
        return set.get(id);
    }

    public void removeCardFromSet(int id){
        set.remove(id);
    }
}
