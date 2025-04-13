package fhtw.adp;

import java.util.concurrent.*;

public class Timeout {

    private long timeout;

    public Timeout(long timeout) {
        this.timeout = timeout;
    }

    public <T> T executeWithTimeout(Callable<T> task) throws TimeoutException{

        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<T> future = executor.submit(task);

        try {
            return future.get(timeout, TimeUnit.MILLISECONDS);
        }
        catch (TimeoutException e){
            future.cancel(true);
            throw new TimeoutException("The time of the operation is out");
        }
        catch (InterruptedException | ExecutionException e){
            throw new RuntimeException("The execution of task is failed", e);
        }
        finally {
            executor.shutdown();
        }
    }
}
