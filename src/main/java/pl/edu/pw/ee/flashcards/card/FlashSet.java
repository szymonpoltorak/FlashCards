package pl.edu.pw.ee.flashcards.card;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode
@Getter
public class FlashSet implements CardSet {
    private final String setName;
    private final List<FlashCard> flashcards;

    public FlashSet(String setName){
        this.setName = setName;
        this.flashcards = new ArrayList<>();
    }

    @Override
    public final void addNewFlashCard(FlashCard flashCard){
        if (flashcards.contains(flashCard) || flashCard == null){
            return;
        }
        flashcards.add(flashCard);
    }

    @Override
    public final boolean hasSuchFlashCard(String name){
        return flashcards.stream().filter(flashCard -> flashCard.getNativeName().equals(name)).count() == 1L;
    }

    @Override
    public final boolean isEmpty(){
        return flashcards.isEmpty();
    }

    @Override
    public final String toString(){
        var builder = new StringBuilder();

        builder.append("\nSetName : ").append(setName).append(";\n");

        for (FlashCard flashCard : flashcards){
            builder.append(flashCard.toString());
        }
        return builder.toString();
    }
}
