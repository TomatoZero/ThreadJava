import java.util.concurrent.Semaphore;

public class Table {
    private final Semaphore[] forks;
    private final Semaphore waiters = new Semaphore(2);

    public Table(int forksNum) {
        forks = new Semaphore[forksNum];

        for (var i = 0; i < forksNum; i++)
            forks[i] = new Semaphore(1);
    }

    private Semaphore[] getForks(int id) {
        Semaphore[] list = new Semaphore[2];
        list[0] = forks[id];

        if (id == 0) list[1] = forks[forks.length - 1];
        else list[1] = forks[id - 1];

        return list;
    }

    public void requestFork(Philosopher philosopher) {
        var philosopherForks = getForks(philosopher.getId());
        var leftFork = philosopherForks[0];
        var rightFork = philosopherForks[1];

        try {
             while (true) {
                waiters.acquire();
                System.out.println("The waiter came up to the philosopher " + philosopher.getId());

                if (leftFork.tryAcquire()) {
                    System.out.println("Philosopher " + philosopher.getId() + " took left fork");
                    if (rightFork.tryAcquire()) {
                        System.out.println("Philosopher " + philosopher.getId() + " took right fork");
                        break;
                    } else {
                        System.out.println("Philosopher " + philosopher.getId() + " put left fork and the waiter moved away because right fork was taken");
                        leftFork.release();
                        waiters.release();
                    }
                } else {
                    System.out.println("The waiter moved away from the Philosopher " + philosopher.getId() + " because right fork was taken");
                    waiters.release();
                    Thread.sleep(100);
                }
            }

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public void putFork(Philosopher philosopher) {
        var philosopherForks = getForks(philosopher.getId());
        var leftFork = philosopherForks[0];
        var rightFork = philosopherForks[1];

        System.out.println("Philosopher " + philosopher.getId() + " put down both forks, and the waiter moved away");
        leftFork.release();
        rightFork.release();
        waiters.release();
    }
}
