import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Storage {
    private Semaphore access;
    private Semaphore empty;
    private Semaphore full;
    private ArrayList<String> storage = new ArrayList<>();

    public Storage(int storageSize) {
        full = new Semaphore(storageSize);
        empty = new Semaphore(0);
        access = new Semaphore(1);
    }

    public String TakeItem(){
        String item = storage.get(0);
        storage.remove(0);
        System.out.println("Take: " + item);
        return item;
    }

    public void PutItem(String item){
        storage.add(item);
        System.out.println("Put: " + item);
    }

    public void AccessAcquire() {   //--
        try {
            access.acquire();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public void AccessRelease() {
        access.release();
    }   //++

    public void FullAcquire(){
        try {
            full.acquire();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public void FullRelease(){
        full.release();
    }

    public void EmptyAcquire(){
        try{
            empty.acquire();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public void EmptyRelease(){ empty.release();}
}
