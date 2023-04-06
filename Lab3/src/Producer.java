public class Producer extends Thread{
    private final Storage storage;
    private final int productNumber;
    private final int id;

    public Producer(Storage storage, int productNumber, int id) {
        this.storage = storage;
        this.productNumber = productNumber;
        this.id = id;
        start();
    }

    @Override
    public void run() {
        try {
            for(int i = 0; i < productNumber; i++){
                sleep(0);

                System.out.println("Producer #" + id + " See if the storage is full " + i);
                storage.FullAcquire();
                System.out.println("Producer #" + id + " Near the storage Item # " + i);
                storage.AccessAcquire();
                System.out.println("Producer #" + id + " In the storage Item # " + i);

                storage.PutItem("Item " + i);
                System.out.println("Producer #" + id + " Put item : " + i);

                System.out.println("Producer #" + id + " Near the exit Item # " + i);
                storage.AccessRelease();
                System.out.println("Producer #" + id + " Left the storage Item # " + i);
                System.out.println("Producer #" + id + " EmptyRelease : " + i);
                storage.EmptyRelease();
        }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
