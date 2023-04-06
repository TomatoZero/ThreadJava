import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Storage {
    private final Semaphore access;
    private final Semaphore empty;
    private final Semaphore full;
    private final ArrayList<String> storage = new ArrayList<>();

    public Storage(int storageSize) {
        full = new Semaphore(storageSize);
        empty = new Semaphore(0);
        access = new Semaphore(1);
    }

    public String TakeItem(){
        String item = storage.get(0);
        storage.remove(0);
        return item;
    }

    public void PutItem(String item){
        storage.add(item);
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
