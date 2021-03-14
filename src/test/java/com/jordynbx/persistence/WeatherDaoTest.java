package com.jordynbx.persistence;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Log4j2
class WeatherDaoTest {

    @Test
    void getWeatherSuccess() {
        WeatherDao dao = new WeatherDao();
        assertEquals("Madison", dao.getWeather().getName());
    }
}