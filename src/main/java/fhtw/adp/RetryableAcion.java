package fhtw.adp;

public interface RetryableAcion<T> {
    T execute() throws Exception;
}
