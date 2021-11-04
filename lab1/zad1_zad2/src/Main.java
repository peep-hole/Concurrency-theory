public class Main {

    public static void main(String []arg) throws InterruptedException {

        Counter c = new Counter();

        Subtractor s = new Subtractor(c);

        Adder a = new Adder(c);

        Thread t1 = new Thread(a);
        Thread t2 = new Thread(s);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(c.getCounter());

        // without synchronization results differ and almost never are equal 0


    }
}
