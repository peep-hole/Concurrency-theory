public class Printer {
    public final int ID;

    public Printer(int id) {
        ID = id;
    }

    public void printThis(String msg) {
        System.out.println("Printer " + ID + ": " + msg);
    }
}
