import java.util.concurrent.ThreadLocalRandom;

public class Client implements Runnable{

    private final String msg;
    PrinterMonitor monitor;

    public Client(String msg, PrinterMonitor mon) {
        this.msg = msg;
        monitor = mon;
    }


    @Override
    public void run() {

        while (true) {
            int rand = ThreadLocalRandom.current().nextInt(2000);
            try {
                Thread.sleep(rand + 2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                Printer pr = monitor.getPrinter();
                int rand2 = ThreadLocalRandom.current().nextInt(2000);
                Thread.sleep(rand2 + 2000);
                pr.printThis(msg);
                monitor.returnPrinter(pr.ID);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }



    }
}
