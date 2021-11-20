import java.awt.image.BufferedImage;
import java.util.concurrent.Callable;

public class CallableMandelbrot implements Callable<Boolean> {

    private final int xStart, xEnd, yStart, yEnd;

    private double zx, zy, cX, cY, tmp;

    private int MAX_ITER;

    private final double ZOOM = 150;

    private BufferedImage I;


    public CallableMandelbrot(int xStart, int xEnd, int yStart, int yEnd, int maxIter, BufferedImage image) {
        this.xStart = xStart;
        this.xEnd = xEnd;
        this.yStart = yStart;
        this.yEnd = yEnd;
        MAX_ITER = maxIter;
        I = image;

    }

    @Override
    public Boolean call() {

        for (int y = yStart; y < yEnd; y++) {
            for (int x = xStart; x < xEnd; x++) {
                zx = zy = 0;
                cX = (x - 400) / ZOOM;
                cY = (y - 300) / ZOOM;
                int iter = MAX_ITER;
                while (zx * zx + zy * zy < 4 && iter > 0) {
                    tmp = zx * zx - zy * zy + cX;
                    zy = 2.0 * zx * zy + cY;
                    zx = tmp;
                    iter--;
                }
                I.setRGB(x, y, iter | (iter << 8));
            }
        }

        return Boolean.TRUE;
    }
}
