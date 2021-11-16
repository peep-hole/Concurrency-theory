import java.io.IOException;

public class Producer implements Runnable{
    private final Monitor monitor;
    private final int chunkSize;

    public Producer(Monitor monitor, int chunkSize) {
        this.monitor = monitor;
        this.chunkSize = chunkSize;
    }


    @Override
    public void run() {
        long start, end;
        try {
            start = System.nanoTime();
            monitor.put(chunkSize);
            end = System.nanoTime();
            monitor.saveTime(end-start);
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }
}
