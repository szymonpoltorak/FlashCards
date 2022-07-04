package pl.edu.pw.ee.flashcards;

public enum WindowSize {
    WIDTH(800), HEIGHT(600);

    private final double value;

    WindowSize(double value){
        this.value = value;
    }

    public double getValue(){
        return value;
    }
}
