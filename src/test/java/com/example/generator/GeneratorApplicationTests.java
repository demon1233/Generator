package com.example.generator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GeneratorApplicationTests {


    @Autowired
    private GeoLocationJsonGenerator geoLocationJsonGenerator;

    @Test
    void contextLoads() {
    }

    @Test
    public void shouldReturnEmptyList() {
        Assertions.assertTrue(geoLocationJsonGenerator.generateListSizeOfJson(0).isEmpty());

    }

    @Test
    public void shouldReturnList() {
        Assertions.assertEquals(100, geoLocationJsonGenerator.generateListSizeOfJson(100).size());
    }

}
