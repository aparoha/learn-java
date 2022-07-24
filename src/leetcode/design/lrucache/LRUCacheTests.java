package leetcode.design.lrucache;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;

public class LRUCacheTests {

    @Test
    public void runMultiThreadTask_WhenPutDataInConcurrentToCache_ThenNoDataLost() throws Exception {
        final int size = 50;
        final ExecutorService executorService = Executors.newFixedThreadPool(5);
        Cache<Integer, String> cache = new LRUCache<>(size);
        CountDownLatch countDownLatch = new CountDownLatch(size);
        try {
            int bound = size;
            IntStream.range(0, bound).<Runnable>mapToObj(key -> () -> {
                cache.put(key, "value" + key);
                countDownLatch.countDown();
            }).forEach(executorService::submit);
            countDownLatch.await();
        } finally {
            executorService.shutdown();
        }
        assertEquals(cache.size(), size);
        IntStream.range(0, size).forEach(i -> assertEquals("value" + i,cache.get(i).get()));
    }
}
