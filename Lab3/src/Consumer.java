public class Consumer implements Runnable{
    private final Storage storage;
    private final int productNumber;
    private final int id;

    public Consumer(Storage storage, int productNumber, int id) {
        this.storage = storage;
        this.productNumber = productNumber;
        this.id = id;
    }

    @Override
    public void run() {
        try {
            String item;
            for(int i = 0; i < productNumber; i++){
                Thread.sleep(0);

                System.out.println("Consumer #" + id + " See if the storage is empty : " + i);
                storage.EmptyAcquire();
                System.out.println("Consumer #" + id + " Near the storage Item #" + i);
                storage.AccessAcquire();
                System.out.println("Consumer #" + id + " In the storage Item #" + i);

                item = storage.TakeItem();
                System.out.println("Consumer #" + id + " Take item : " + item);

                System.out.println("Consumer #" + id + " Near the exit Item # " + i);
                storage.AccessRelease();
                System.out.println("Consumer #" + id + " Left the storage Item # " + i);
                System.out.println("Consumer #" + id + " FullRelease : " + i);
                storage.FullRelease();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
