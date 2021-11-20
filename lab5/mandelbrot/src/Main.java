import java.util.LinkedList;
import java.util.concurrent.ExecutionException;

public class Main {


    public static void main(String[] args) throws ExecutionException, InterruptedException {

        LinkedList<TestResults> results = new LinkedList<>();

        new Mandelbrot(1 , 1, false, results);
        new Mandelbrot(1 , 10, false, results);
        new Mandelbrot(1 , 10, true, results);
        new Mandelbrot(4 , 4, false, results);
        new Mandelbrot(4 , 40, false, results);
        new Mandelbrot(4 , 10, true, results);
        new Mandelbrot(8 , 8, false, results);
        new Mandelbrot(8 , 80, false, results);
        new Mandelbrot(8 , 10, true, results);

        for (TestResults result: results) {
            System.out.println(result);
        }
    }
}
