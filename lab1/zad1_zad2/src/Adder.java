public class Adder implements Runnable{

    private final Counter counter;

    public Adder(Counter c) {
        counter = c;
    }

    @Override
    public void run() {

        for(int i = 0; i < 100000000; i++) {
            counter.add();
        }


    }
}
