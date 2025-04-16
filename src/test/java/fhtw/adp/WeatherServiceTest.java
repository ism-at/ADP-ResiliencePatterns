package fhtw.adp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class WeatherServiceTest {

    private WeatherService weatherService;

    @BeforeEach
    void setUp() {
        weatherService = new WeatherService();
    }

    @Test
    void testGetWeatherData() {
        String city = "Vienna";
        try {
            String response = weatherService.getWeatherData(city);
            assertNotNull(response, "Weather data should not be null");
        } catch (Exception e) {
            fail("Failed to fetch weather data: " + e.getMessage());
        }
    }
}
