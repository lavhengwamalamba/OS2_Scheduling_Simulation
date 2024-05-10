package barScheduling;

import java.util.concurrent.atomic.AtomicInteger;

public class BackGroundTimer implements Runnable {
    private final AtomicInteger count;
    private final long delayMillis;
    private boolean running;

    public BackGroundTimer(AtomicInteger count, long delayMillis) {
        this.count = count;
        this.delayMillis = delayMillis;
        this.running = true;
    }

    @Override
    public void run() {
        while (running) {
            try {
                Thread.sleep(delayMillis);
                int currentCount = count.get(); // Retrieve and reset count to 0
                System.out.println("Current count: " + currentCount);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void stop() {
        running = false;
    }
}