package pl.edu.pw.ee.flashcards.card;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.ArrayList;

@EqualsAndHashCode
@Getter
public class FlashSet implements CardSet {
    private final String setName;
    private final ArrayList<FlashCard> flashcards;

    public FlashSet(String setName){
        this.setName = setName;
        this.flashcards = new ArrayList<>();
    }

    @Override
    public void addNewFlashCard(FlashCard flashCard){
        if (flashcards.contains(flashCard) || flashCard == null){
            return;
        }
        flashcards.add(flashCard);
    }

    @Override
    public boolean hasSuchFlashCard(String name){
        for (FlashCard flashCard : flashcards){
            if (flashCard.getNativeName().equals(name)){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isEmpty(){
        return flashcards.isEmpty();
    }

    @Override
    public String toString(){
        var builder = new StringBuilder();

        builder.append("\nSetName : ").append(setName).append(";\n");

        for (FlashCard flashCard : flashcards){
            builder.append(flashCard.toString());
        }
        return builder.toString();
    }
}
