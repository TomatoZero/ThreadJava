import java.util.Arrays;

public class Breaker extends Thread {
    private MyThread[] myThreads;

    public Breaker(MyThread[] myThreads) {
        this.myThreads = myThreads;
    }

    @Override
    public void run(){
        Arrays.sort(myThreads);
        int allTimeSleep = 0;

        int thisThreadSleep = myThreads[0].sleepTime;

        StopAfterSleep(thisThreadSleep, 0);

        allTimeSleep += thisThreadSleep;

        for(int i = 1; i < myThreads.length; i++){
            thisThreadSleep = myThreads[i].sleepTime;
            int leftToSleep = thisThreadSleep - allTimeSleep;

            if(leftToSleep < 0){
                System.out.println("leftToSleep < 0");
                StopAfterSleep(0, i);
            }
            else StopAfterSleep(leftToSleep, i);

        }
    }

    private void StopAfterSleep(int thisThreadSleep, int i) {
        try {
            sleep(thisThreadSleep);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        finally {
            myThreads[i].StopThread();
        }
    }

}
