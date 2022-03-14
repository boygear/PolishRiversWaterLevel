package org.boygear.controllers;

import org.boygear.entities.Measurement;
import org.boygear.services.download.DownloadService;
import org.boygear.services.MeasurementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MeasurementController {
    @Autowired
    private MeasurementService measurementService;


    @GetMapping("/measurement")
    public List<Measurement> getMeasurementList(@RequestParam(name = "id", required = false) Long stationId, @RequestParam(name = "sd",required = false) String startTime, @RequestParam(name = "ed",required = false) String endTime){
        return measurementService.getMeasurement(stationId, startTime, endTime);
    }
}
