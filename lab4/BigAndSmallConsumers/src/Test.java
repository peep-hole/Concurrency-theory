import java.io.File;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

public class Test implements Runnable{

    private final int bufferSize;
    private final int producersCount;
    private final int consumersCount;
    private final boolean isNaive;
    private final boolean printBufferStatus;
    private File file;

    public Test(int bufferSize,
                int producersCount,
                int consumersCount,
                boolean isNaive,
                boolean printBufferStatus,
                String filename) {
        this.bufferSize = bufferSize;
        this.producersCount = producersCount;
        this.consumersCount = consumersCount;
        this.isNaive = isNaive;
        this.printBufferStatus = printBufferStatus;

        try {
            file = new File(filename);
            file.createNewFile();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        Monitor monitor = new Monitor(bufferSize, consumersCount, producersCount, isNaive, printBufferStatus, file);

        Consumer[] consumers = new Consumer[consumersCount];
        Thread[] consumerThread = new Thread[consumersCount];

        Producer[] producers = new Producer[producersCount];
        Thread[] producersThreads = new Thread[producersCount];

        for (int i = 0; i < consumersCount; ++i) {
            int randomChunkSize = ThreadLocalRandom.current().nextInt((bufferSize / 2) - 1);
            consumers[i] = new Consumer(monitor, randomChunkSize + 1);
            consumerThread[i] = new Thread(consumers[i]);
            consumerThread[i].start();
            randomChunkSize = ThreadLocalRandom.current().nextInt((bufferSize / 2) - 1);
            producers[i] = new Producer(monitor, randomChunkSize + 1);
            producersThreads[i] = new Thread(producers[i]);
            producersThreads[i].start();
        }


        for (int i = 0; i < producersCount; ++i) {
            try {
                producersThreads[i].join();
                consumerThread[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
