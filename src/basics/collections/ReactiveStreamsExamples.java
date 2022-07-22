package basics.collections;

import java.util.*;

/*
        Reactive programming concepts

        1. Elastic
        2. Message driven
        3. Responsive - e.g. infinite scrolling
        4. Resilience

        Reactive applications - MS Excel, google maps, google docs

        Its dataflow computing

        Similarities

        java stream                     reactive stream
        pipelines                       pipelines
        push data                       push data
        lazy - waits until terminal     lazy
                operation
        zero, one or more data          zero, one or more data
------------------------------------------------------------------------------------------------------
        Differences

        java stream                         reactive stream

        data only                           3 channels - data, error, complete
        no support for exception handling   deal with it downstream (circuit breaker)
                                            error is just another form of data
        sequential vs parallel              Synch vs. Async
        single pipeline - no forks          Multiple subscribers are possible
--------------------------------------------------------------------------------------------------

        Reactive streams                                    CompletableFuture / Promise

        zero, one or more data (3 channels)                  zero or one data (2 channels)



        How many threads should you create?
            If your task is compute intensive <= # of cores
            If your task is IO intensive
                # thread <= # of cores / 1 - blocking factor
                    0 <= blocking factor < 1

 */
public class ReactiveStreamsExamples {

    static class Booking {
        int user;
        int res_id;
        int checkin;
        int checkout;
        public Booking(int user, int resId, int checkin, int checkout){
            this.user = user;
            this.res_id = resId;
            this.checkin = checkin;
            this.checkout = checkout;
        }
    }
    public static void main(String[] args) {

        List<Booking> bookings = new ArrayList<>() {{
            add(new Booking(1, 1001, 100, 101));
            add(new Booking(2, 1002, 104, 105));
            add(new Booking(1, 1003, 101, 103));
            add(new Booking(3, 1004, 104, 105));
            add(new Booking(3, 1005, 105, 107));
            add(new Booking(4, 1006, 106, 108));
            add(new Booking(4, 1007, 108, 110));
            add(new Booking(4, 1008, 108, 109));
            add(new Booking(4, 1009, 110, 112));
            add(new Booking(4, 1010, 109, 113));
        }};

        //chain - sequence of >=2 bookings
        //For user 1 we have the following chain: 100-101-103 which means the user
        //had to make two reservations, 1001 and 1003. The task is to retrieve all the bookings per user.
        //{ 1: {{1001, 1003}}, 3: {{1004, 1005}}, 4 : {{1006, 1007, 1009}, {1006, 1008, 1009}} }

//        int[][] bookings = [
//        {user = 1, res_id = 1001, checkin = 100, checkout 101 },
//        {user = 2, res_id = 1002, checkin = 104, checkout 105 },
//        {user = 1, res_id = 1003, checkin = 101, checkout 103 },
//        {user = 3, res_id = 1004, checkin = 104, checkout 105},
//        {user = 3, res_id = 1005, checkin = 105, checkout 107},
//        {user = 4, res_id = 1006, checkin = 106, checkout 108},
//        {user = 4, res_id = 1007, checkin = 108, checkout 110},
//        {user = 4, res_id = 1008, checkin = 108, checkout 109},
//        {user = 4, res_id = 1009, checkin = 110, checkout 112},
//        {user = 4, res_id = 1010, checkin = 109, checkout 113},
//];
    }

    public static Map<Integer, List<List<Integer>>> findBookings(List<Booking> bookings) {
        Map<Integer, List<List<Integer>>> result = new HashMap<>();
        return Collections.emptyMap();
    }

}
