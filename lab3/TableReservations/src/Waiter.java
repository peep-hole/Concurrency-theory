import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Waiter {

    private final Lock lock = new ReentrantLock();
    private final Condition tableTaken = lock.newCondition();
    private final int condNumber;
    private final Condition[] conditions;
    private final int clientsNumber;
    private final boolean[] isWaiting;
    private final Table table;
    private boolean isTableTaken = false;

    public Waiter(int clientsNumber, Table table) {

        this.table = table;
        condNumber = clientsNumber/2;
        conditions = new Condition[condNumber];
        this.clientsNumber = clientsNumber;
        isWaiting = new boolean[clientsNumber];

        for (int i = 0; i < clientsNumber; ++i) {
            isWaiting[i] = false;
            if (i%2 == 0) {
                conditions[i/2] = lock.newCondition();
            }
        }
    }

    public Table bookTable(int id) throws InterruptedException {

        int condID = id/2;
        int pairID;
        if (id%2 == 0) {
            pairID = id + 1;
        }
        else {
            pairID = id - 1;
        }

        lock.lock();

        if (!isWaiting[pairID]) {

            isWaiting[id] = true;
            while (!isWaiting[pairID]) {
                conditions[condID].await();
            }
        }

        else {

            while (isTableTaken) {
                tableTaken.await();
            }
            isWaiting[id] = true;
            conditions[condID].signal();
        }

        isTableTaken = true;

        lock.unlock();

        return table;
    }

    public void freeTable(int id) {
        isWaiting[id] = false;

        int pairID;

        if (id % 2 == 0) {
            pairID = id + 1;
        }
        else {
            pairID = id - 1;
        }

        if (!isWaiting[pairID]) {
            isTableTaken = false;
        }

        lock.lock();

        tableTaken.signal();

        lock.unlock();
    }
}
