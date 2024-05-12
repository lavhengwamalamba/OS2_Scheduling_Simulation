package barScheduling;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * This class implements a background timer that periodically records counts and their respective time intervals
 * to a specified file. It operates as a separate thread, periodically updating the count and time interval 
 * information and writing it to the designated file.
 */


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
        this.timeInterval = delayMillis;
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
                    timeInterval += delayMillis; // Increase the timeinterval for data recording
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