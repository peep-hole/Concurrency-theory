public class BinarySemaphore {
    private boolean isBlocked = false;

    synchronized public void block() throws InterruptedException {
        while(isBlocked()) {
            wait();
        }
        isBlocked = true;
    }

    synchronized public void free() {
        isBlocked = false;
        notifyAll();
    }

    public boolean isBlocked() {
        return isBlocked;
    }
}
