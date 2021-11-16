public class Main {

    private static final int bufferSize = 10;
    private static final int modifiersCount = 5;

    public static void main(String[] args) throws InterruptedException {
        Monitor monitor = new Monitor(bufferSize, modifiersCount);
        Producer producer = new Producer(monitor, bufferSize);
        Thread producerThread = new Thread(producer);
        producerThread.start();
        Modifier[] modifiers = new Modifier[modifiersCount - 1];
        Thread[] modifierThreads = new Thread[modifiersCount - 1];

        for (int i = 1; i < modifiersCount; ++i) {
            modifiers[i - 1] = new Modifier(monitor, bufferSize, i);
            modifierThreads[i - 1] = new Thread(modifiers[i - 1]);
            modifierThreads[i - 1].start();
        }

        Consumer consumer = new Consumer(monitor, bufferSize);
        Thread consumerThread = new Thread(consumer);
        consumerThread.start();

        producerThread.join();
        for (int i = 1; i < modifiersCount; ++i) {
            modifierThreads[i - 1].join();
        }
        consumerThread.join();

    }
}
