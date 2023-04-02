package pl.edu.pw.ee.flashcards.card;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
public class FlashCard {
    private final String nativeName;
    private final String foreignName;
    private int id;

    @Override
    public final String toString(){
        return "NativeName : " + nativeName + ";  ForeignName : " + foreignName + ";  ID : " + id + ";\n";
    }
}
