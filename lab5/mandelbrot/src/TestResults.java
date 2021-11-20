import java.util.LinkedList;

public class TestResults {

    private final int nThreads;
    private final int nTasks;
    private final LinkedList<Long> times;
    private double averageTime = 0;
    private double standardDeviation = 0;

    public TestResults(int nThreads, int nTasks) {
        this.nTasks = nTasks;
        this.nThreads = nThreads;
        times = new LinkedList<>();
    }

    public void addTime(long time) {
        times.add(time);
    }


    public void recalculate() {
        averageTime = 0;
        for (long time: times) {
            averageTime += time;
        }

        averageTime /= times.size();
        averageTime /= 1_000_000_000;

        standardDeviation = 0;

        for (long time: times) {
            standardDeviation += Math.pow(averageTime - ((double)time)/1_000_000_000, 2);
        }

        standardDeviation = Math.sqrt(standardDeviation);


    }

    public double getAverageTime() {
        return averageTime *  1_000_000_000;
    }

    @Override
    public String toString() {
        int numberOfTasksSpace = (int)Math.floor(Math.log10(nTasks));

        return "Number of tasks: " + nTasks + " ".repeat(Math.max(0, 6 - numberOfTasksSpace)) +"   |   Number of threads: " + nThreads + "   |   Average Time to draw: " +
                String.format("%.7f", averageTime) + " s   |   Standard deviation: " + standardDeviation;
    }


}
