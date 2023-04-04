public class Consumer implements Runnable{
    private final Storage storage;
    private final int itemNum;

    public Consumer(Storage storage, int itemNum) {
        this.storage = storage;
        this.itemNum = itemNum;
    }

    @Override
    public void run() {
        try {
            String item;
            for(int i = 0; i < itemNum; i++){
                Thread.sleep(0);

                System.out.println("EmptyAcquire Consumer: " + i);
                storage.EmptyAcquire();
                System.out.println("AccessAcquire Consumer: " + i);
                storage.AccessAcquire();

                item = storage.TakeItem();
//                Thread.sleep(100);

                System.out.println("AccessRelease Consumer: " + i);
                storage.AccessRelease();
                System.out.println("FullRelease Consumer: " + i);
                storage.FullRelease();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
