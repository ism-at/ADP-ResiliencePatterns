package fhtw.adp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RetryTest {

    private Retry retry;

    @BeforeEach
    void setUp() {
        retry = new Retry(3, 1000); // 1 second
    }

    @Test
    void testRetryOnFailure() {
        final int[] attemptCount = {0};
        RetryableAcion<String> action = () -> {
            attemptCount[0]++;
            if (attemptCount[0] < 3) {
                throw new RuntimeException("Simulation failed");
            }
            return "Success";
        };

        String result = retry.attempt(action);
        assertEquals("Success", result, "Expected retry succeed.");
        assertEquals(3, attemptCount[0], "Expected 3 attempts.");
    }
}
