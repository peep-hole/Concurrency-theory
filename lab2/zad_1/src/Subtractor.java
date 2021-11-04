public class Subtractor implements Runnable{

    private final Counter counter;
    private BinarySemaphore sem;

    public Subtractor(Counter c, BinarySemaphore sem) {
        counter = c;
        this.sem = sem;
    }

    @Override
    public void run() {
        for(int i = 0; i < 100000000; i++) {

            try {
                sem.block();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            counter.sub();

            sem.free();

        }

    }
}
