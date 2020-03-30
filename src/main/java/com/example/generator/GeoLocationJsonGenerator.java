package com.example.generator;


import com.example.generator.dao.GeoInfo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GeoLocationJsonGenerator {

    public List<GeoInfo> generateListSizeOfJson(int size) {
        List<GeoInfo> geoInfos = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            geoInfos.add(createGeoInfo(i));
        }
        return geoInfos;

    }

    private GeoInfo createGeoInfo(int i) {

        Map<String, Double> geoPosition = new HashMap<>();
        geoPosition.put("latitude", 111.123 + i);
        geoPosition.put("longitude", 2222.2222 + i);
        return GeoInfo.builder().id("123" + i).
                type("type1" + i).
                coreCountry(true).
                countryCode("US").
                distance(40L).
                fullName("SampleName").
                geoPosition(geoPosition.toString())
                .iataAirportCode("432")
                .inEurope(true)
                .key("key")
                .locationId(13L)
                .name("samplename")
                .position("Vancover")
                .build();
    }

}
