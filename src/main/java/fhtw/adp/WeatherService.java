package fhtw.adp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherService implements WeatherAPI {

    private static final String API_KEY = "b6d4ec19d9996402131369e8abc2f68d";
    private static final String API_URL = "https://api.openweathermap.org/data/2.5/weather?q=";

    public String getWeatherData(String city) throws Exception{

        String urlString = API_URL + city + "&appid=" + API_KEY;
        URL url = new URL(urlString);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        // Read the API RESPONSE
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null){
            response.append(inputLine);
        }
        in.close();

        // Finally, return the API RESPONSE
        return response.toString();
    }
}
