public class EnumerableSemaphore {
    private int enumerator = 0;
    private final int maxVal;

    public EnumerableSemaphore() {
        maxVal = 10;
        enumerator = maxVal;
    }

    public EnumerableSemaphore(int maxVal) {
        this.maxVal = maxVal;
        enumerator = maxVal;
    }


    synchronized public void block() throws InterruptedException {

        while(!canBlock()) {
            wait();
        }

        enumerator -= 1;
    }

    synchronized public void free() {
        enumerator += 1;
        notifyAll();
    }

    private void checkIfOk() {
        if(enumerator > maxVal) {
            enumerator = maxVal;
        }

        else if(enumerator < 0) {
            enumerator = 0;
        }
    }

    private boolean canBlock() {
        return enumerator > 0;
    }
}
