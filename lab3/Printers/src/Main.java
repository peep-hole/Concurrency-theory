public class Main {
    public static final int printersNumber = 3;
    public static final int clientsNumber = 10;


    public static void main(String[] args) throws InterruptedException {
        PrinterMonitor monitor = new PrinterMonitor(printersNumber);
        Client[] clients = new Client[clientsNumber];
        Thread[] threads = new Thread[clientsNumber];

        for (int i = 0; i < clientsNumber; ++i) {
            clients[i] = new Client("Client " + i + " message", monitor);
            threads[i] = new Thread(clients[i]);
            threads[i].start();
        }

        for (int i = 0; i < clientsNumber; ++i) {
            threads[i].join();
        }

    }
}
