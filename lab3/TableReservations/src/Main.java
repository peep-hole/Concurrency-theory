public class Main {

    public static final int clientsNumber = 10;

    public static void main(String[] args) throws InterruptedException {

        Table table = new Table();
        Waiter waiter = new Waiter(clientsNumber, table);

        Client[] clients = new Client[clientsNumber];
        Thread[] threads = new Thread[clientsNumber];

        for (int i = 0; i < clientsNumber; ++i) {
            clients[i] = new Client(i, waiter);
            threads[i] = new Thread(clients[i]);
            threads[i].start();
        }

        for (int i = 0; i < clientsNumber; ++i) {
            threads[i].join();
        }
    }
}
