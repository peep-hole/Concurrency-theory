import java.util.concurrent.ThreadLocalRandom;

public class Producer implements Runnable {

    private Monitor monitor;
    private int bufferSize;

    public Producer(Monitor monitor, int bufferSize) {
        this.monitor = monitor;
        this.bufferSize = bufferSize;
    }

    @Override
    public void run() {

        while (true) {

            for (int i = 0; i < bufferSize; ++i) {
                int rand = ThreadLocalRandom.current().nextInt(2000);
//                System.out.println("Producer: Preparing product");
                try {
                    Thread.sleep(rand + 2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                try {
                    monitor.modify(i, 0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

//                System.out.println("Producer: Placing product in buffer in " + i + " place.");
            }
        }





    }
}
