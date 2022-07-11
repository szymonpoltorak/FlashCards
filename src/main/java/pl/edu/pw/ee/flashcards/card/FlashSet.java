package pl.edu.pw.ee.flashcards.card;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.ArrayList;

@Getter
@EqualsAndHashCode
public class FlashSet {
    private final String setName;
    private final ArrayList<FlashCard> flashcards;

    public FlashSet(String setName){
        this.setName = setName;
        this.flashcards = new ArrayList<>();
    }

    public void addNewFlashCard(FlashCard flashCard){
        if (flashcards.contains(flashCard)){
            return;
        }
        flashcards.add(flashCard);
    }

    public FlashCard findGivenFlashCard(String flashCardName){


        return null;
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
