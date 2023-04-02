package pl.edu.pw.ee.flashcards.utils;

import org.jetbrains.annotations.NotNull;
import pl.edu.pw.ee.flashcards.card.FlashSet;

import java.util.List;

public class Utility {
    private Utility(){}

    public static boolean isThereSuchElement(String name, @NotNull List<FlashSet> flashSets){
        return flashSets.stream().filter(flashSet -> flashSet.getSetName().equals(name)).count() == 1L;
    }

    public static boolean isThereSuchFlashCard(String name, @NotNull List<FlashSet> flashSets){
        return flashSets.stream().filter(flashSet -> flashSet.hasSuchFlashCard(name)).count() == 1L;
    }
}
