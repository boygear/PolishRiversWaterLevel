package org.boygear.controllers;

import org.boygear.entities.Station;
import org.boygear.services.StationService;
import org.boygear.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StationController {

    @Autowired
    private UserService userService;
    @Autowired
    private StationService stationService;

    @GetMapping(value = "/stations/{page}/{size}")
    public List<Station> stationList(@PathVariable Integer page,@PathVariable Integer size){

        Pageable pageable = PageRequest.of(page, size);
        List<Station> allStations = stationService.getStationsPage(pageable);


        return allStations;
    }

}
