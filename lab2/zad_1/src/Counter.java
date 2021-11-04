public class Counter {

    private int count = 0;

    synchronized public void add() {
        count += 1;

    }

    synchronized public void sub() {
        count -= 1;

    }

    public int getCounter() {
        return count;
    }
}
