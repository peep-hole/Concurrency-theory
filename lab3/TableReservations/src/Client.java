import java.util.concurrent.ThreadLocalRandom;

public class Client implements Runnable{

    public final int ID;
    private final Waiter waiter;

    public Client(int id, Waiter waiter) {
        ID = id;
        this.waiter = waiter;
    }

    @Override
    public void run() {

        System.out.println("Client " + ID + ": doing stuff...");

        while (true) {
            int rand = ThreadLocalRandom.current().nextInt(2000);
            try {
                Thread.sleep(rand + 2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Client " + ID + ": Want table");

            try {
                Table table = waiter.bookTable(ID);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Client " + ID + ": Got table");

            int rand2 = ThreadLocalRandom.current().nextInt(2000);
            try {
                Thread.sleep(rand2 + 2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            waiter.freeTable(ID);

            System.out.println("Client " + ID + ": Finish eating");
        }

    }
}
