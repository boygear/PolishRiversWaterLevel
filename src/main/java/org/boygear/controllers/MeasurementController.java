package org.boygear.controllers;

import org.boygear.entities.Measurement;
import org.boygear.services.download.DownloadService;
import org.boygear.services.MeasurementService;
import org.boygear.services.download.DownloadedMeasurement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MeasurementController {
    @Autowired
    private MeasurementService measurementService;
    @Autowired
    private DownloadService downloadService;


    @GetMapping("/measurement")
    public List<Measurement> getMeasurementList(){

        return measurementService.addNewMeasurementList(downloadService.getCurrentMeasurementList());
    }
}
