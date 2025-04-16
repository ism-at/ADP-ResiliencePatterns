package fhtw.adp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeoutException;
import static org.junit.jupiter.api.Assertions.*;

class TimeoutTest {

    private Timeout timeout;

    @BeforeEach
    void setUp() {
        timeout = new Timeout(1000); // Set timeout to 1 second
    }

    @Test
    void testTimeout() {
        Callable<String> task = () -> {
            try {
                Thread.sleep(2000); // Simulation (long-running-task)
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            return "Completed";
        };

        assertThrows(TimeoutException.class, () -> timeout.executeWithTimeout(task),
                "Expected TimeoutException due to long execution time.");
    }
}
