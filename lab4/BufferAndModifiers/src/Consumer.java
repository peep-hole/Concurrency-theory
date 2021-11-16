import java.util.concurrent.ThreadLocalRandom;

public class Consumer implements Runnable {

    private final int bufferSize;
    private final Monitor monitor;

    public Consumer(Monitor monitor, int bufferSize) {
        this.bufferSize = bufferSize;
        this.monitor = monitor;
    }

    @Override
    public void run() {
        while (true) {
            for (int i = 0; i < bufferSize; ++i) {
                int rand = ThreadLocalRandom.current().nextInt(2000);
                try {
                    Thread.sleep(rand + 2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Consumer: Trying to consume.");
                try {
                    monitor.consume(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("Consumer: Consuming.");

            }
        }
    }
}
