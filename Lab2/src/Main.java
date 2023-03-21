public class Main {
    public static void main(String[] args) {
        long startTyme, endTyme;

        MyArrClass arr = new MyArrClass(4000000);

        startTyme = System.nanoTime();
        int[] a = arr.ParallelMin(50);
        endTyme = System.nanoTime();
        System.out.println("Parallel: min = " + a[0] + " min index: " + a[1] + " Time: " + (endTyme - startTyme));

        startTyme = System.nanoTime();
        a = arr.partMin(0, 4000000);
        endTyme = System.nanoTime();
        System.out.println("One thread: min = " + a[0] + " min index: " + a[1] + " Time: " + (endTyme - startTyme));


    }
}