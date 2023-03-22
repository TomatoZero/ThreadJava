public class ThreadMin implements Runnable{
    private final int startIndex, endIndex;
    private final MyArrClass arr;

    public ThreadMin(int startIndex, int endIndex, MyArrClass arr) {
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.arr = arr;
    }

    @Override
    public void run() {
        int[] min = arr.partMin(startIndex, endIndex);
        arr.SetMin(min[0], min[1]);
    }
}
