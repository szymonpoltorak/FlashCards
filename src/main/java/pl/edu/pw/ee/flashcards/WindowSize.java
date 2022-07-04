package pl.edu.pw.ee.flashcards;

public enum WindowSize {
    WIDTH(1270), HEIGHT(720);

    private final double value;

    WindowSize(double value){
        this.value = value;
    }

    public double getValue(){
        return value;
    }
}
