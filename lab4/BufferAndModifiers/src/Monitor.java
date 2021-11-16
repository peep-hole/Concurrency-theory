import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Monitor {

    private final int modifiersNumber;
    private final int[] buffer;
    private final Lock lock = new ReentrantLock();
    private final Condition[] conditions;
    private final Condition customerCondition = lock.newCondition();

    public Monitor(int bufferSize, int modifiersNumber) {
        this.buffer = new int[bufferSize];
        this.modifiersNumber = modifiersNumber;
        conditions = new Condition[modifiersNumber];
        for (int i = 0; i < modifiersNumber; ++i) {
            conditions[i] = lock.newCondition();
        }
        for (int i = 0; i < bufferSize; ++i) {
            buffer[i] = -1;
        }
    }


    public void modify(int index, int id) throws InterruptedException {
        lock.lock();

        while(buffer[index] != id - 1) {
            conditions[id].await();
        }

        buffer[index] = id;

        if(id != modifiersNumber - 1) {
            conditions[id + 1].signal();
        }
        else {
            customerCondition.signal();
        }

        System.out.println("------------------------------------------------");
        System.out.println("---------------Buffer State---------------------");
        for (int i = 0; i < buffer.length; ++i) {
            System.out.print(" | " + buffer[i]);
        }
        System.out.println(" |");
        System.out.println("------------------------------------------------");

        lock.unlock();

    }

    public int consume(int index) throws InterruptedException {
        lock.lock();

        while(buffer[index] != modifiersNumber - 1) {
            customerCondition.await();
        }
        int result = buffer[index];
        buffer[index] = -1;
        conditions[0].signal();
        lock.unlock();

        return result;
    }




}
