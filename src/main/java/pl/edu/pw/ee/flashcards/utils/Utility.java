package pl.edu.pw.ee.flashcards.utils;

import org.jetbrains.annotations.NotNull;
import pl.edu.pw.ee.flashcards.card.FlashSet;

import java.util.List;

public class Utility {
    private Utility(){}

    public static boolean isThereSuchElement(String name, @NotNull List<FlashSet> flashSets){
        for (FlashSet flashSet : flashSets){
            if (flashSet.getSetName().equals(name)){
                return true;
            }
        }
        return false;
    }

    public static boolean isThereSuchFlashCard(String name, @NotNull List<FlashSet> flashSets){
        for (FlashSet flashSet : flashSets){
            if (flashSet.hasSuchFlashCard(name)){
                return true;
            }
        }
        return false;
    }
}
