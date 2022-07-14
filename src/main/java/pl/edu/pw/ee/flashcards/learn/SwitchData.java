package pl.edu.pw.ee.flashcards.learn;

public enum SwitchData {
    RAND_BOUND(2), INSERT_DESTINATION(0), CLICK_DESTINATION(1);

    private final int value;

    SwitchData(int value){
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
