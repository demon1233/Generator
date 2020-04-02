package com.example.generator;

import com.example.generator.dao.GeoInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(GeolocationController.class)
public class GeolocationControllerTest {

  @Autowired private MockMvc mockMvc;

  @MockBean private GeolocationController geolocationController;

  @MockBean private GeoLocationJsonGenerator geoLocationJsonGenerator;

  private static GeoInfo createGeoInfo(int i) {

    Map<String, Double> geoPosition = new HashMap<>();
    geoPosition.put("latitude", 111.123 + i);
    geoPosition.put("longitude", 2222.2222 + i);
    return GeoInfo.builder()
        .id("123" + i)
        .type("type1" + i)
        .coreCountry(true)
        .countryCode("US")
        .distance(40L)
        .fullName("SampleName")
        .geoPosition(geoPosition.toString())
        .iataAirportCode("432")
        .inEurope(true)
        .key("key")
        .locationId(13L)
        .name("samplename")
        .position("Vancover")
        .build();
  }

  @Test
  public void shouldReturnGeoInformationWithStatusOk() throws Exception {

    String json =
        "[\n"
            + "   {\n"
            + "      \"position\":\"Vancover\",\n"
            + "      \"key\":\"key\",\n"
            + "      \"name\":\"samplename\",\n"
            + "      \"fullName\":\"SampleName\",\n"
            + "      \"country\":null,\n"
            + "      \"inEurope\":true,\n"
            + "      \"countryCode\":\"US\",\n"
            + "      \"coreCountry\":true,\n"
            + "      \"distance\":40,\n"
            + "      \"iata_airport_code\":\"432\",\n"
            + "      \"_type\":\"type11\",\n"
            + "      \"_id\":\"1231\",\n"
            + "      \"geo_position\":\"{latitude=112.123, longitude=2223.2222}\",\n"
            + "      \"location_id\":13\n"
            + "   }\n"
            + "]";

    List<GeoInfo> geoInfos = Collections.singletonList(createGeoInfo(1));

    when(geoLocationJsonGenerator.generateGeoInfos(1)).thenReturn(geoInfos);
    when(geolocationController.getGeoLocationList(1)).thenReturn(geoInfos);
    mockMvc
        .perform(get("/generate/json/1").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().json(json));
  }

  @Test
  public void shouldReturnResponseWithEmptyListAndStatusOk() throws Exception {
    when(geolocationController.getGeoLocationList(0)).thenReturn(Collections.emptyList());
    mockMvc
        .perform(get("/generate/json/0").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().json("[]"));
  }
}
