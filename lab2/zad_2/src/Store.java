public class Store {
    private int peopleIn = 0;
    private final int covidLimit;

    public Store(int limit) {
        covidLimit = limit;
    }

    public void getIn() {
        peopleIn += 1;
        System.out.println("Getting in...");
        printStoreState();

    }

    public void getOut() {
        peopleIn -=1;
        System.out.println("Getting out...");
        printStoreState();
    }


    void printStoreState() {
        System.out.println("People in store " +
                peopleIn + ", basket left: " + (covidLimit - peopleIn));
    }
}
