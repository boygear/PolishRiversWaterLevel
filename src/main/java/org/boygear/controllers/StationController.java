package org.boygear.controllers;

import org.boygear.entities.Station;
import org.boygear.services.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StationController {

    @Autowired
    private StationService stationService;

    @GetMapping(value = "/stations")
    public List<Station> stationList(@RequestParam(required = false, defaultValue = "0") Integer page, @RequestParam(required = false, defaultValue = "50") Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        List<Station> allStations = stationService.getStationsPage(pageable);


        return allStations;
    }

}
