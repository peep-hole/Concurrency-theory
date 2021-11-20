public class TestResults {

    private final int nThreads;
    private final int nTasks;
    private final long time;

    public TestResults(int nThreads, int nTasks, long time) {
        this.nTasks = nTasks;
        this.nThreads = nThreads;
        this.time = time;
    }

    @Override
    public String toString() {
        return "Number of tasks: " + nTasks + " | Number of threads: " + nThreads + " | Time to draw: " +
                ((double)time)/1000000000 + " s";
    }


}
