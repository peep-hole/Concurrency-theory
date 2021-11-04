public class Consumer implements Runnable {
    private final Buffer buffer;
    private final int n_instructions;

    public Consumer(Buffer buffer, int i) {
        this.buffer = buffer;
        n_instructions = i;
    }

    public void run() {

        for(int i = 0;  i < n_instructions;   i++) {
            String message = buffer.take();
//            System.out.println("Message is: " + message);

        }

    }
}