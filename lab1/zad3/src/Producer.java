public class Producer implements Runnable {
    private final Buffer buffer;
    private final int n_instructions;

    public Producer(Buffer buffer, int i) {
        this.buffer = buffer;
        n_instructions = i;
    }

    public void run() {

        for(int i = 0;  i < n_instructions;   i++) {
            buffer.put("message "+i);
        }

    }
}

