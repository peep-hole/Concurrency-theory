public class Main {

    public static void main(String[] args) throws InterruptedException {

        BinarySemaphore sem = new BinarySemaphore();
        Counter c = new Counter();
        Subtractor s = new Subtractor(c, sem);
        Adder a = new Adder(c, sem);

        Thread t1 = new Thread(s);
        Thread t2 = new Thread(a);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(c.getCounter());;
    }
}
