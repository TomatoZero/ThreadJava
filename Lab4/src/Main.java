public class Main {
    public static void main(String[] args) {
        int numPhilosophers = 5;

        Table table = new Table(numPhilosophers);
        Philosopher[] philosophers = new Philosopher[numPhilosophers];
        Thread[] threads = new Thread[numPhilosophers];

        for (int i = 0; i < numPhilosophers; i++) {
            philosophers[i] = new Philosopher(i, table);
            threads[i] = new Thread(philosophers[i]);
            threads[i].start();
        }
    }
}