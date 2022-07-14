package pl.edu.pw.ee.flashcards.card;

public interface CardSet {
    void addNewFlashCard(FlashCard flashCard);

    boolean hasSuchFlashCard(String name);

    boolean isEmpty();
}
