public class Producer extends Thread{
    private final Storage storage;
    private final int itemNum;

    public Producer(Storage storage, int itemNum) {
        this.storage = storage;
        this.itemNum = itemNum;
        start();
    }

    @Override
    public void run() {
        try {
            for(int i = 0; i < itemNum; i++){
                sleep(0);

                System.out.println("FullAcquire Producer: " + i);
                storage.FullAcquire();
                System.out.println("AccessAcquire Producer: " + i);
                storage.AccessAcquire();

                storage.PutItem("Item " + i);
//                sleep(1000);

                System.out.println("AccessRelease Producer: " + i);
                storage.AccessRelease();
                System.out.println("EmptyRelease Producer: " + i);
                storage.EmptyRelease();
        }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
