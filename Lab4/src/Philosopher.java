public class Philosopher extends Thread {
    private final int id;
    private final Table table;
    private final int leftFork;
    private final int rightFork;


    public Philosopher(int id, Table table)
    {
        int rightFork1;
        this.id = id;
        this.table = table;
        this.leftFork = id;
        rightFork1 = id - 1;


        if(rightFork1 < 0)
            rightFork1 = 4;

        this.rightFork = rightFork1;

        start();
    }

    @Override
    public void run() {
        for(int i = 0; i < 10; i++) {
            System.out.println("Philosopher " + id + " thinking " + i + " time");

            System.out.println("Philosopher want eat");
            table.WaiterAcquire();

            table.ForkAcquire(leftFork);
            System.out.println("Philosopher " + id + " took left fork");

            table.ForkAcquire(rightFork);
            System.out.println("Philosopher " + id + " took right fork");

            System.out.println("Philosopher " + id + " eating " + i + " time");

            table.ForkRelease(rightFork);
            System.out.println("Philosopher " + id + " put right fork");

            table.ForkRelease(leftFork);
            System.out.println("Philosopher " + id + " put left fork");

            System.out.println("Philosopher dene eating");
            table.WaiterRelease();
        }

    }
}
