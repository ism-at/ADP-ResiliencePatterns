package fhtw.adp;

import java.util.concurrent.TimeoutException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
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
            System.out.println("Weather Data For the city : " + city + ": " + "\n" + weatherData);
        }
        catch (TimeoutException e){
            System.err.println("Request timed out " + e.getMessage());
        }
        catch (Exception e){
            System.err.println("ERROR: " + e.getMessage());
        }
    }
}