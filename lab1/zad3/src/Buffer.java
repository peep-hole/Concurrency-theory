public class Buffer {

    String message;
    public boolean isWritten = false;

    public Buffer(String message) {
        message = new String(message);
    }

    synchronized public void put(String message) {
        while(isWritten) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.message = message;
        System.out.println("Putting....");
        isWritten = true;
        notifyAll();
    }

    synchronized public String take() {

        while(!isWritten) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        String result = new String(message);

        System.out.println("Taking....");

        isWritten = false;
        notifyAll();

        return result;
    }
}
