package fhtw.adp;

public class CircuitBreaker {

    private int failureThreshold = 3;
    private int failureCount = 0;
    private boolean open = false;
    private long lastFailureTime = 0;
    private long coolDownPeriod = 5000; // Which is equal 5 Seconds

    public boolean isCircutOpen() {
        if (failureCount >= failureThreshold){
            if (System.currentTimeMillis() - lastFailureTime > coolDownPeriod){
                reset();
                return false; // STATE: Half-Open --> try again
            }
            return true; // STATE: Full-Open --> Stop making API calls
        }
        return false; // STATE: Close
    }

    public void recordFailure(){
        failureCount++;
        lastFailureTime = System.currentTimeMillis();
    }


    public void reset(){
        failureCount = 0;
        open = false;
    }
}
