public class MyThread implements Runnable, Comparable<MyThread> {
    final int id;
    final int sleepTime;
    final int steep;

    private volatile boolean isStop = false;

    public MyThread(int id, int steep, int sleepTime) {
        this.id = id;
        this.sleepTime = sleepTime;
        this.steep = steep;
    }

    @Override
    public void run() {
//        try{
//            Thread.sleep(sleepTime);
//        }catch (InterruptedException e){
//            throw new RuntimeException();
//        }
        Calculate();
    }
    
    @Override
    public int compareTo(MyThread other) {
        if(this.sleepTime > other.sleepTime) return 1;
        else if(this.sleepTime < other.sleepTime) return -1;
        else return 0;
    }

    public void StopThread(){
        isStop = true;
    }

    public void Calculate(){
        double sum = 0.0;
        long i = 1;

        while(!isStop){
            sum += i;
            i += steep;
        }
        System.out.println(id + " Sum: " + sum + " Steep: " + i / steep);
    }

}
