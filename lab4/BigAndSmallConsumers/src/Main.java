
public class Main {




    public static void main(String[] args) {

        Test[] tests = new Test[6];
        tests[0] = new Test(1000, 10, 10,
                true, false, "naive1.txt");
        tests[1] = new Test(1000, 10, 10,
                false, false, "smart1.txt");
        tests[2] = new Test(10000, 100, 100,
                true, false, "naive2.txt");
        tests[3] = new Test(10000, 100, 100,
                true, false, "smart2.txt");
        tests[4] = new Test(100000, 1000, 1000,
                true, false, "naive3.txt");
        tests[5] = new Test(100000, 1000, 1000,
                true, false, "smart3.txt");

        Thread[] threads = new Thread[6];

        for (int i = 0; i < 6; ++i) {
            threads[i] = new Thread(tests[i]);
            threads[i].start();
        }
    }
}
