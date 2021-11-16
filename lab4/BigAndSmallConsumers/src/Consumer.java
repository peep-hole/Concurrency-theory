import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

public class Consumer implements Runnable {

    private final Monitor monitor;
    private final int consumingChunkSize;

    public Consumer(Monitor monitor, int consumingChunkSize) {
        this.monitor = monitor;
        this.consumingChunkSize = consumingChunkSize;
    }

    @Override
    public void run() {

        long start, end;

//        int rand = ThreadLocalRandom.current().nextInt(15000);
        try {
//            Thread.sleep(rand);
            start = System.nanoTime();
            monitor.get(consumingChunkSize);
            end = System.nanoTime();
//            monitor.saveTime(end-start);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
