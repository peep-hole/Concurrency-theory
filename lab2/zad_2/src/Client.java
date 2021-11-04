import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Client implements Runnable {

    private EnumerableSemaphore sem;
    private Store store;

    public Client(EnumerableSemaphore semaphore, Store shop) {
        sem = semaphore;
        store = shop;
    }

    @Override
    public void run() {

        try {
            sem.block();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        store.getIn();
        int rand = ThreadLocalRandom.current().nextInt(2000);
        rand += 2000;
        try {
            Thread.sleep(rand);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        store.getOut();
        sem.free();



    }
}
