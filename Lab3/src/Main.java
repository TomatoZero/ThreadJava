public class Main {
    public static void main(String[] args) {
        Storage storage = new Storage(3);
        int consumerNum = 15;
        int producerNum = 6;

        int consumerProductNum = 2;
        int producerProductNum = 5;

        Consumer[] consumers = new Consumer[consumerNum];
        Thread[] consumersThread = new Thread[consumerNum];
//        Producer[] producers = new Producer[producerNum];

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