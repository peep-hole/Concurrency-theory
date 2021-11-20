import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.concurrent.*;
import javax.swing.JFrame;

public class Mandelbrot extends JFrame {

    private final int MAX_ITER = 570;
    private final double ZOOM = 150;
    private BufferedImage I;

    public Mandelbrot(int nThreads, int nParts,
                      boolean oneTaskOnePixel,
                      TestResults results) throws ExecutionException, InterruptedException {


        super("Mandelbrot Set");
        setBounds(100, 100, 800, 600);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        I = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);

        ExecutorService pool = Executors.newFixedThreadPool(nThreads);
        LinkedList<Future<Boolean>> futureList = new LinkedList<>();

        long start = System.nanoTime();

        if (oneTaskOnePixel) {
            for (int y = 0; y < getHeight(); ++y) {
                for (int x = 0; x < getWidth(); ++x) {
                    Callable<Boolean>  callable = new CallableMandelbrot(x, x+1, y, y+1, MAX_ITER, I);
                    Future<Boolean> future = pool.submit(callable);
                    futureList.add(future);
                }
            }
        }

        else {
            int partSize = getHeight() / nParts;

            for (int i = 0; i < nParts; ++i) {
                Callable<Boolean> callable = new CallableMandelbrot(0, getWidth(), i * partSize,
                        (i + 1)*partSize, MAX_ITER, I);

                Future<Boolean> future = pool.submit(callable);
                futureList.add(future);
            }
        }

        for (Future<Boolean> future: futureList) {
            if (future.get());
        }

        long end = System.nanoTime();

        results.addTime(end - start);

    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(I, 0, 0, this);
    }

}