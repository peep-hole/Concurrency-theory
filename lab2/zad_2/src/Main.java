import java.lang.ref.Cleaner;
import java.util.List;

public class Main {

    private static final int c_number = 20;
    private final static int limit = 3;

    public static void main(String[] args) throws InterruptedException {

        Store s = new Store(limit);
        EnumerableSemaphore sem = new EnumerableSemaphore(limit);


        Client []c = new Client[c_number];

        for (int i=0; i<c_number; ++i) {
            c[i] = new Client(sem, s);
        }

        Thread[]t = new Thread[c_number];

        for (int i=0; i<c_number; ++i) {
            t[i] = new Thread(c[i]);
        }

        for (int i=0; i<c_number; ++i) {
            t[i].start();
        }

        for (int i=0; i<c_number; ++i) {
            t[i].join();
        }





    }
}
