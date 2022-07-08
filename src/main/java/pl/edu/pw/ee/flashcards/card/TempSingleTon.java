package pl.edu.pw.ee.flashcards.card;

public class TempSingleTon {
    private final TestSet1 set1;
    private final TestSet2 set2;
    private static final TempSingleTon singleTon = new TempSingleTon();

    private TempSingleTon(){
        this.set1 = new TestSet1();
        this.set2 = new TestSet2();
    }

    public static TempSingleTon getInstance(){
        return singleTon;
    }

    public FlashSet getSet1(){
        return set1.getSet1();
    }

    public FlashSet getSet2(){
        return set2.getSet2();
    }
}
