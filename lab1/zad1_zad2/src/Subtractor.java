public class Subtractor implements Runnable{

    private final Counter counter;

    public Subtractor(Counter c) {
        counter = c;
    }

    @Override
    public void run() {
        for(int i = 0; i < 100000000; i++) {
            counter.sub();
        }

    }
}
