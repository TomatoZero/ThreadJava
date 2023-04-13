public class Main {
    public static void main(String[] args) {
        int forkCount = 5;
        int waiterCount = 2;

        Table table = new Table(forkCount, waiterCount);

        Philosopher[] philosophers = new Philosopher[forkCount];
//        Thread[] threadsPhilosopher = new Thread[forkCount];

        for (int i = 0; i < forkCount; i++){
            philosophers[i] = new Philosopher(i, table);
        }

    }
}