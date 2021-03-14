package com.jordynbx.persistence;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.openweatherapi.Weather;
import com.utilities.PropertiesLoader;
import lombok.extern.log4j.Log4j2;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.util.Properties;

@Log4j2
public class WeatherDao implements PropertiesLoader {


    Weather getWeather() {
        Properties properties = loadProperties("/api.properties");
        String apiKey = properties.getProperty("api.key");
        Client client = ClientBuilder.newClient();
        String apiCall = "http://api.openweathermap.org/data/2.5/weather?q=madison&appid=" + apiKey;
        WebTarget target = client.target(apiCall);
        log.info(apiCall);
        String response = target.request(MediaType.APPLICATION_JSON).get(String.class);
        ObjectMapper mapper = new ObjectMapper();
        Weather weather = null;
        try {
            weather = mapper.readValue(response, Weather.class);
        } catch (JsonProcessingException e) {
            //TODO set up logging and write this to the log
            e.printStackTrace();
        }
        return weather;
    }
}
