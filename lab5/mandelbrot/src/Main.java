import java.util.Comparator;
import java.util.LinkedList;
import java.util.concurrent.ExecutionException;

public class Main {


    public static void main(String[] args) throws ExecutionException, InterruptedException {

        LinkedList<TestResults> results = new LinkedList<>();
        results.add(new TestResults(1, 1));
        results.add(new TestResults(1, 10));
        results.add(new TestResults(1, 800*600));
        results.add(new TestResults(4, 4));
        results.add(new TestResults(4, 40));
        results.add(new TestResults(4, 800*600));
        results.add(new TestResults(8, 8));
        results.add(new TestResults(8, 80));
        results.add(new TestResults(8, 800*600));


        for (int i = 0; i < 10; ++i) {
            new Mandelbrot(1 , 1, false, results.get(0));
            new Mandelbrot(1 , 10, false, results.get(1));
            new Mandelbrot(1 , 10, true, results.get(2));
            new Mandelbrot(4 , 4, false, results.get(3));
            new Mandelbrot(4 , 40, false, results.get(4));
            new Mandelbrot(4 , 10, true, results.get(5));
            new Mandelbrot(8 , 8, false, results.get(6));
            new Mandelbrot(8 , 80, false, results.get(7));
            new Mandelbrot(8 , 10, true, results.get(8));
        }


        for (TestResults result: results) {
            result.recalculate();
        }

        results.sort(new Comparator<TestResults>() {
            @Override
            public int compare(TestResults o1, TestResults o2) {
                return (int)(o1.getAverageTime() - o2.getAverageTime());
            }
        });

        for (TestResults result: results) {
            System.out.println(result);
        }
    }
}
