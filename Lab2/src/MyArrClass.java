public class MyArrClass {
    private final int[] array;
    private int minValue = Integer.MAX_VALUE;
    private int minValueIndex = 0;
    private int threadCount = 0;

    public MyArrClass(int dimension) {
        array = new int[dimension];

        for(int i = 0; i < dimension; i++){
            array[i] = i;
        }

        array[4000000 - 1] = -13;
        System.out.println("Min element: " + array[4000000 - 1]);
    }

    public int[] partMin(int startIndex, int endIndex){
        int min = Integer.MAX_VALUE;
        int index = 0;

        for(int i = startIndex; i <= endIndex; i++){
            if(array[i] < min) {
                min = array[i];
                index = i;
            }
        }

        return new int[]{min, index};
    }

    public synchronized void SetMin(int value, int index){
        if(minValue > value) {
            minValue = value;
            minValueIndex = index;
        }
        threadCount++;
        notify();
    }

    public int[] ParallelMin(int threadNum){

        int minDimension = array.length / threadNum;
        Thread[] thread = new Thread[threadNum];

        int i = 0, j = 0;
        ThreadMin minThread;
        for(; j < threadNum; i+= minDimension, j++){
            if(array.length % threadNum != 0 & j == threadNum - 1) {
                minThread = new ThreadMin(i, array.length - 1, this);
//                System.out.println("Count " + ((array.length - 1) - i));
            }
            else {
                minThread = new ThreadMin(i, i + minDimension, this);
//                System.out.println("Count " + ((i + minDimension) - i));
            }

            thread[j] = new Thread(minThread);
        }

        for(i = 0; i < thread.length; i++){
            thread[i].start();
        }

        synchronized (this) {
            while (threadNum > threadCount) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return new int[]{ minValue, minValueIndex};
    }
}
