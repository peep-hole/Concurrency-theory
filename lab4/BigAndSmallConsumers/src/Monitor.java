import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Monitor {

    private final int[] buffer;
    private int spaceLeft;

    private final Lock lock = new ReentrantLock();
    private final Condition accessCondition = lock.newCondition();
    private final Condition firstInQueueCondition = lock.newCondition();
    private boolean isQueueEmpty = true;

    private int consumersCount;
    private int producersCount;

    private final boolean isNaive;
    private final boolean printBufferStatus;

    private final File file;

    public Monitor(int bufferSize, int consumersCount, int producersCount, boolean isNaive, boolean printBufferStatus, File file) {
        buffer = new int[bufferSize];
        for (int i = 0; i < bufferSize; ++i) {
            buffer[i] = -1;
        }
        spaceLeft = bufferSize;
        this.consumersCount = consumersCount;
        this.producersCount= producersCount;
        this.isNaive = isNaive;
        this.printBufferStatus = printBufferStatus;
        this.file = file;
    }

    public void put(int size) throws InterruptedException {
        lock.lock();

        int initialSize = size;

        if (!isNaive) {
            while (!isQueueEmpty) {
                firstInQueueCondition.await();
            }
            isQueueEmpty = false;
        }

        while (spaceLeft < size) {
            accessCondition.await();
        }

        for (int i = 0; i < buffer.length; ++i) {
            if (size == 0) {
                break;
            }

            if (buffer[i] == -1) {

                buffer[i] = 1;
                size --;
                spaceLeft --;
            }
        }

        if (!isNaive) {
            isQueueEmpty = true;
            firstInQueueCondition.signal();
            accessCondition.signal();
            producersCount--;
        }

        if (printBufferStatus) {
            printBufferState("After Putting " + initialSize);
        }




        lock.unlock();


    }

    public void get(int size) throws InterruptedException {
        lock.lock();

        int initialSize = size;

        while (size > buffer.length - spaceLeft) {

            accessCondition.await();
        }

        for (int i = 0; i < buffer.length; ++i) {
            if (size == 0) {
                break;
            }

            if (buffer[i] == 1) {

                buffer[i] = -1;
                size --;
                spaceLeft ++;
            }
        }

        accessCondition.signal();
        consumersCount--;

        if (printBufferStatus) {
            printBufferState("After Taking " + initialSize);
        }


        lock.unlock();
    }


    private void printBufferState(String msg) {
        System.out.println("-------------------------------------");
        System.out.println("-----------" + msg + "----------");
        System.out.println("------------Buffer State-------------");
        for (int j : buffer) {
            System.out.print(" | " + j);
        }
        System.out.println(" |");
        System.out.println("-Consumers count: " + consumersCount + "   Producers Count: " + producersCount +
                "   Amount in buffer: " + (buffer.length - spaceLeft) + "-");
        System.out.println("-------------------------------------");
    }


    public void saveTime(long time, int buffer_size) throws IOException {
        FileWriter w = new FileWriter(file, true);
        w.write(time + "," + buffer_size + ";");
        w.close();
    }


}
