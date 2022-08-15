package companies.grab.indeed;

import java.util.LinkedList;
import java.util.Queue;

/* =============================================================================
Question Description
=============================================================================
 Given a stream of input, and a API int getNow() to get the current time stamp,
         Finish two methods:

         1. void record(int val) to save the record.
         2. double getAvg() to calculate the averaged value of all the records in 5 minutes.
/* =============================================================================
code
=============================================================================*/
public class MovingAverage {
    private Queue<Event> queue;
    private double sum;
    private long avgValueTime;

    public MovingAverage() {
        this.queue = new LinkedList<>();
        this.sum = 0;
        this.avgValueTime = 1000;
    }

    private long getNow(){
        return System.currentTimeMillis();
    }

    public void record(double val) {
        Event event = new Event(val, getNow());
        queue.offer(event);
        sum += val;
        removeExpireEvent();
    }

    public double getAvg(){
        removeExpireEvent();
        if (!queue.isEmpty()){
            return sum/queue.size();
        }
        return 0.0;
    }

    private void removeExpireEvent(){
        while (!queue.isEmpty() && (getNow() - queue.peek().time > avgValueTime)){
            Event curE = queue.poll();
            sum -= curE.val;
        }
    }

    class Event{
        private double val;
        private double time;
        public Event(double val, long time){
            this.val = val;
            this.time = time;
        }
    }

    public static void main(String[] args) {
        MovingAverage movingAverage = new MovingAverage();
        movingAverage.record(1);
        movingAverage.record(2);
        movingAverage.record(3);
        movingAverage.record(4);
        System.out.println(movingAverage.getAvg());
    }
}
