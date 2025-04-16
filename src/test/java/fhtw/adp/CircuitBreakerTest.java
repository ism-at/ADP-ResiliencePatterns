package fhtw.adp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CircuitBreakerTest {

    private CircuitBreaker circuitBreaker;

    @BeforeEach
    void setUp() {
        circuitBreaker = new CircuitBreaker();
    }

    @Test
    void testCircuitBreakerStates() {
        circuitBreaker.recordFailure(); // Simulation of Falling
        circuitBreaker.recordFailure();
        circuitBreaker.recordFailure();

        assertTrue(circuitBreaker.isCircutOpen(), "Circuit should be open after 3 failures.");

        // Simulation of cool-down period and reset
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        assertFalse(circuitBreaker.isCircutOpen(), "Circuit should be closed after the cool-down period.");
    }
}
