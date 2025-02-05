public class SynchronizedThreads {
    public static void main(String[] args)
    throws InterruptedException {
        Counter counter = new Counter();
        Thread t1 = new Thread(new MyRunnable(counter));
        Thread t2 = new Thread(new MyRunnable(counter));

        t1.start();
        t2.start();

        t1.join();
        t2.join();;

        System.out.println(counter.getCount());
    }
}
