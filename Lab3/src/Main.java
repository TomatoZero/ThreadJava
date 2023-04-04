public class Main {
    public static void main(String[] args) {
        Storage storage = new Storage(3);

        Consumer consumer = new Consumer(storage, 10);
        Thread consumerThread = new Thread(consumer);
        consumerThread.start();

        Producer producer = new Producer(storage, 10);
    }
}