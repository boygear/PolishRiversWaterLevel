package org.boygear;

import org.boygear.entities.Measurement;
import org.boygear.services.MeasurementService;
import org.boygear.services.download.DownloadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class ScheduledTasks {
    @Autowired
    private MeasurementService measurementService;
    @Autowired
    private DownloadService downloadService;

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 60000)
    public List<Measurement> getAndSaveNewMeasurement() {
        log.info("The time is now {}", dateFormat.format(new Date()));
        return measurementService.addNewMeasurementList(downloadService.getCurrentMeasurementList());
    }
}
