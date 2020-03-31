package com.example.generator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GeoLocationJsonGeneratorTest {

    @Autowired
    private GeoLocationJsonGenerator geoLocationJsonGenerator;

    @Test
    public void shouldReturnEmptyList() {
        Assertions.assertTrue(geoLocationJsonGenerator.generateGeoInfos(0).isEmpty());

    }

    @Test
    public void shouldReturnList() {
        Assertions.assertEquals(100, geoLocationJsonGenerator.generateGeoInfos(100).size());
    }

}