package basics.collections.blockingqueue;

import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {
    private final BlockingQueue<Integer> sharedQueue;
    private int threadNo;
    public Consumer (BlockingQueue<Integer> sharedQueue,int threadNo) {
        this.sharedQueue = sharedQueue;
        this.threadNo = threadNo;
    }
    @Override
    public void run() {
        // Consumer consumes numbers generated from Producer threads continuously
        while(true){
            try {
                int num = sharedQueue.take(); // thread blocks if queue empty
                System.out.println("Consumed: "+ num + ":by thread:"+threadNo);
            } catch (InterruptedException err) {
                err.printStackTrace();
            }
        }
    }
}
