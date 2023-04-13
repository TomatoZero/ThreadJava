import java.util.concurrent.Semaphore;

public class Table {
    private final Semaphore[] forks;
    private final Semaphore waiter;

    public Table(int forksCount, int waiterCount) {
//        if(waiterCount >= forksCount - 1) {
//
//           return;
//        }

        forks = new Semaphore[forksCount];
        waiter = new Semaphore(waiterCount);

        for(int i = 0; i < forksCount; i++)
            forks[i] = new Semaphore(1);

//        for (int i = 0; i < waiterCount; i++)
//            waiter[i] = new Semaphore()
    }

    public void ForkAcquire(int id){
        try {
            forks[id].acquire();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void ForkRelease(int id){
        forks[id].release();
    }

    public void WaiterAcquire(){
        try {
            waiter.acquire();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public void WaiterRelease(){
        waiter.release();
    }
}
