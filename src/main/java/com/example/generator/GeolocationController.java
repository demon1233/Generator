package com.example.generator;

import com.example.generator.dao.GeoInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/generate/json")
public class GeolocationController {

  @Autowired private GeoLocationJsonGenerator geoLocationJsonListGenerator;

  @GetMapping(value = "/{size}", produces = "application/json")
  @ResponseStatus(code = HttpStatus.OK)
  public List<GeoInfo> getGeoLocationList(@PathVariable Integer size) {
    return geoLocationJsonListGenerator.generateGeoInfos(size);
  }
}
