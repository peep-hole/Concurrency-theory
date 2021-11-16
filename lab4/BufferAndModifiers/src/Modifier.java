import java.util.concurrent.ThreadLocalRandom;

public class Modifier implements Runnable {

    private Monitor monitor;
    private int bufferSize;
    private int id;

    public Modifier(Monitor monitor, int bufferSize, int id) {
        this.monitor = monitor;
        this.bufferSize = bufferSize;
        this.id = id;

    }

    @Override
    public void run() {

        while (true) {

            for (int i = 0; i < bufferSize; ++i) {
                int rand = ThreadLocalRandom.current().nextInt(2000);
//                System.out.println("Modifier " + id + ": Modifying product");
                try {
                    Thread.sleep(rand + 2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                try {
                    monitor.modify(i, id);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

//                System.out.println("Modifier " + id +
//                        ": Placing modified product in buffer in " + i + " place.");
            }
        }
    }
}
