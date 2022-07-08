package pl.edu.pw.ee.flashcards.card;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class FlashCard {
    private final String nativeName;
    private final String foreignName;
    private int id;
}
