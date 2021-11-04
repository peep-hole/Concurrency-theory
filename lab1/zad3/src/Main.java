public class Main {

    static final int producers_n = 4;
    static final int consumers_n = producers_n;
    static final int producers_instruction_n = 5;
    static final int consumers_instructions_n = producers_instruction_n;

    private static final Producer []producers = new Producer[producers_n];
    private static final Consumer []consumers = new Consumer[consumers_n];

    private static final Thread [] threads_c = new Thread[producers_n];
    private static final Thread []threads_p = new Thread[consumers_n];

    public static void main(String []args) throws InterruptedException {

        Buffer buf = new Buffer("None");

        for(int i = 0; i < producers_n; i++) {
            producers[i] = new Producer(buf, producers_instruction_n);
            threads_p[i] = new Thread(producers[i]);
            consumers[i] = new Consumer(buf, consumers_instructions_n);
            threads_c[i] = new Thread(consumers[i]);
        }

        for(int i = 0; i < producers_n; i++) {
            threads_p[i].start();
            threads_c[i].start();
        }

        for(int i = 0; i < producers_n; i++) {
            threads_p[i].join();
            threads_c[i].join();
        }
    }
}
