package fhtw.adp;

import java.util.concurrent.TimeoutException;

public class Main {
    public static void main(String[] args) {
        // Instances
        CircuitBreaker circuitBreaker = new CircuitBreaker();
        Retry retry = new Retry(3, 1000); // Changeable: 3 retry with 1 second
        Timeout timeout = new Timeout(5000); // Changeable also: 5 second
        WeatherService weatherService = new WeatherService();

        try {
            // Now fetch data for any city
            String city = "Vienna";
            String weatherData = timeout.executeWithTimeout(() -> {
                if (circuitBreaker.isCircutOpen()){
                    throw  new RuntimeException("The Circut is open, try again later");
                }
                return retry.attempt(() -> weatherService.getWeatherData(city));
            });
            System.out.println("WEATHER DATA FOR THE CITY : " + city + ": " + "\n" + weatherData);
        }
        catch (TimeoutException e){
            System.err.println("Request timed out " + e.getMessage());
        }
        catch (Exception e){
            System.err.println("ERROR: " + e.getMessage());
        }
    }
}