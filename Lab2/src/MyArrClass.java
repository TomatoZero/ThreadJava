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

        array[4000000 - 2] = -13;
    }

    public int[] partMin(int startIndex, int endIndex){
        int min = Integer.MAX_VALUE;
        int index = 0;

        for(int i = startIndex; i < endIndex; i++){
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
        Thread[] thread;
        if(array.length % threadNum == 0)
            thread = new Thread[threadNum];
        else
            thread = new Thread[threadNum + 1];

        int i = 0, j = 0;
        for(; j < threadNum; i+= minDimension, j++){
            ThreadMin minThread = new ThreadMin(i, i + minDimension, this);
            thread[j] = new Thread(minThread);
        }

        if(array.length % threadNum != 0) {
            ThreadMin minThread = new ThreadMin(i, array.length - 1, this);
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
