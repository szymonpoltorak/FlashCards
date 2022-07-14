package pl.edu.pw.ee.flashcards.learn;

public enum Language {
    NATIVE(0), FOREIGN(1);

    private final int langId;

    Language(int langId){
        this.langId = langId;
    }

    public int getLangId() {
        return langId;
    }
}
