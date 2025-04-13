package fhtw.adp;

public class Retry {

    private int maxRetries;
    private long retryDelay;

    public Retry(int maxRetries, long retryDelay) {
        this.maxRetries = maxRetries;
        this.retryDelay = retryDelay;
    }

    public <T> T attempt(RetryableAcion<T> action){

        int attemptts = 0;
        while (attemptts < maxRetries){
            try {
                return action.execute();
            } catch (Exception e){
                attemptts++;
                if (attemptts >= maxRetries){
                    throw new RuntimeException("RReached max reties", e);
                }
                try {
                    Thread.sleep(retryDelay);
                    retryDelay *= 2; // The value of exponential backoff
                } catch (InterruptedException ie){
                    Thread.currentThread().interrupt();
                }
            }
        }
        return null;
    }
}
