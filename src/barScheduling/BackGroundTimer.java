package barScheduling;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

public class BackGroundTimer implements Runnable {
    private final AtomicInteger count;
    private final long delayMillis;
    private boolean running;
    private final String fileName;
    private long timeInterval;

    public BackGroundTimer(AtomicInteger count, long delayMillis, String fileName) {
        this.count = count;
        this.delayMillis = delayMillis;
        this.running = true;
        this.fileName = fileName;
        this.timeInterval = 100;
    }

    @Override
    public void run() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
            writer.write("Time Interval(milliseconds),Count\n"); // Write header
            while (running) {
                try {
                    Thread.sleep(delayMillis);
                    int currentCount = count.getAndSet(0); // Retrieve and reset count to 0
                    String line = timeInterval + "," + currentCount + "\n";
                    writer.write(line);
                    timeInterval += 100;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        running = false;
    }
}