public class Adder implements Runnable{

    private final Counter counter;
    private BinarySemaphore sem;

    public Adder(Counter c, BinarySemaphore sem) {
        this.sem = sem;
        counter = c;
    }

    @Override
    public void run() {

        for(int i = 0; i < 100000000; i++) {

            try {
                sem.block();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            counter.add();

            sem.free();


        }


    }
}
