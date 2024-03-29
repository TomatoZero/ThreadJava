import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int n = 20;

        MyThread[] myThreads = new MyThread[n];
        Thread[] threads = new Thread[n];

        Breaker breaker = new Breaker(myThreads);

        int j = n + 1;

        Random rnd = new Random();
        for (int i = 0; i < n; i++){
            myThreads[i] = new MyThread(i, i+1, (rnd.nextInt(10, 100)) * 1000);
            threads[i] = new Thread(myThreads[i]);
            threads[i].start();
        }

        breaker.start();
    }
}