public class Main {
    public static void main(String[] args) {
        Storage storage = new Storage(12);
        int consumerNum = 6;
        int producerNum = 4;

        int consumerProductNum = 2;
        int producerProductNum = 3;

        Consumer[] consumers = new Consumer[consumerNum];
        Thread[] consumersThread = new Thread[consumerNum];

        for(int i = 0; i < consumers.length; i++){
            consumers[i] = new Consumer(storage, consumerProductNum, i);
            consumersThread[i] = new Thread(consumers[i]);

            consumersThread[i].start();
        }

        for(int i = 0; i < producerNum; i++){
            new Producer(storage, producerProductNum, i);
        }

    }
}