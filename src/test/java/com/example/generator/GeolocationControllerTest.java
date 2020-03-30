package com.example.generator;


import com.example.generator.dao.GeoInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(GeolocationController.class)
public class GeolocationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GeolocationController geolocationController;


    @Test
    public void shouldReturnGeoInformationWithStatusOk() throws Exception {

        String json = "[\n" +
                "   {\n" +
                "      \"position\":\"Vancover\",\n" +
                "      \"key\":\"key\",\n" +
                "      \"name\":\"samplename\",\n" +
                "      \"fullName\":\"SampleName\",\n" +
                "      \"country\":null,\n" +
                "      \"inEurope\":true,\n" +
                "      \"countryCode\":\"US\",\n" +
                "      \"coreCountry\":true,\n" +
                "      \"distance\":40,\n" +
                "      \"iata_airport_code\":\"432\",\n" +
                "      \"_type\":\"type10\",\n" +
                "      \"_id\":\"1230\",\n" +
                "      \"geo_position\":\"{latitude=111.123, longitude=2222.2222}\",\n" +
                "      \"location_id\":13\n" +
                "   }\n" +
                "]";


        GeoLocationJsonGenerator geoLocationJsonGenerator = new GeoLocationJsonGenerator();
        List<GeoInfo> geoInfoList = geoLocationJsonGenerator.generateListSizeOfJson(1);

        when(geolocationController.getGeoLocationList(1)).thenReturn(geoInfoList);
        mockMvc.perform(get("/generate/json/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(json));


    }

    @Test
    public void shouldReturnResponseWithEmptyListAndStatusOk() throws Exception {
        when(geolocationController.getGeoLocationList(1)).thenReturn(Collections.emptyList());
        mockMvc.perform(get("/generate/json/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(content().json("[]"));

    }
}