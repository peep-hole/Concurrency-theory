import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PrinterMonitor {
    private final Printer[] printers;
    private final boolean[] printersTaken;  // true if taken
    private int printersLeft;
    private final int numberOfPrinters;

    private final Lock lock = new ReentrantLock();
    private final Condition allTaken = lock.newCondition();

    public PrinterMonitor(int numberOfPrinters) {
        printers = new Printer[numberOfPrinters];
        printersTaken = new boolean[numberOfPrinters];

        for (int i = 0; i < numberOfPrinters; ++i) {
            printersTaken[i] = false;
            printers[i] = new Printer(i);
        }

        printersLeft = numberOfPrinters;
        this.numberOfPrinters = numberOfPrinters;
    }

    public Printer getPrinter() throws InterruptedException {

        lock.lock();
        while(printersLeft <= 0) {
            allTaken.await();
        }

        int printerId;

        for (printerId = 0; printerId < numberOfPrinters; ++printerId) {
            if (!printersTaken[printerId]) {
                printersLeft--;
                printersTaken[printerId] = true;
                break;
            }
        }

        lock.unlock();

        return printers[printerId];

    }

    public void returnPrinter(int id) {
        lock.lock();
        for(int i = 0; i < numberOfPrinters; ++i) {
            if (printers[i].ID == id) {
                printersTaken[i] = false;
                printersLeft++;
                break;
            }
        }
        allTaken.signal();
        lock.unlock();
    }

}
