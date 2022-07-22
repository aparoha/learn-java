package threading;

public class MyRunnable implements Runnable {

    private int count = 0;
    private MyOject myOject = null;
    public MyRunnable() {}
    public MyRunnable(MyOject myOject) {
        this.myOject = myOject;
    }
    @Override
    public void run() {
        System.out.println(this.myOject);
        for (int i = 0; i < 1_000_000; i++) {
            this.count++;
        }
        System.out.println(
                Thread.currentThread().getName()
                + " : " + this.count
        );
    }
}
