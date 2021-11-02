package org.boygear.services;

import org.boygear.entities.Measurement;
import org.boygear.entities.Station;
import org.boygear.repositories.MeasurementRepository;
import org.boygear.repositories.StationRepository;
import org.boygear.services.download.DownloadedMeasurement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MeasurementService {

    @Autowired
    private MeasurementRepository measurementRepository;
    @Autowired
    private StationRepository stationRepository;

    public List<Measurement> addNewMeasurementList(List<DownloadedMeasurement> DownloadedMeasurementList){

        List<Measurement> measurementList = parseDownloadedMeasurementToMeasurementAndStation(DownloadedMeasurementList);
        List<Station> stations = measurementList.stream()
                .map(measurement -> measurement.getStation())
                .collect(Collectors.toList());

        stationRepository.saveAll(stations);
        return measurementRepository.saveAll(measurementList);
    }

    public List<Measurement> parseDownloadedMeasurementToMeasurementAndStation(List<DownloadedMeasurement> downloadedMeasurementList){
        List<Measurement> measurementList = new ArrayList<>();


        for(DownloadedMeasurement downloadedMeasurement : downloadedMeasurementList){
            Station station = new Station();
            station.setStationID(Long.parseLong(downloadedMeasurement.getStationID()));
            station.setName(downloadedMeasurement.getName());
            station.setRiver(downloadedMeasurement.getRiver());
            station.setProvince(downloadedMeasurement.getProvince());

            Measurement measurement = new Measurement();
            //2021-10-28 14:00:00
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            if(downloadedMeasurement.getWaterLevel() != null){
                measurement.setWaterLevel(Integer.parseInt(downloadedMeasurement.getWaterLevel()));
            }

            if(downloadedMeasurement.getWaterLevelMeasurementDate() != null){
                measurement.setWaterLevelMeasurementDate(LocalDateTime.parse(downloadedMeasurement.getWaterLevelMeasurementDate(),formatter));
            }

            if(downloadedMeasurement.getWaterTemperature() != null){
                measurement.setWaterTemperature(Double.parseDouble(downloadedMeasurement.getWaterTemperature()));
            }

            if(downloadedMeasurement.getIceDanger() != null){
                measurement.setIceDanger(Integer.parseInt(downloadedMeasurement.getIceDanger()));
            }

            if(downloadedMeasurement.getIceDangerMeasurementDate() != null){
                measurement.setIceDangerMeasurementDate(LocalDateTime.parse(downloadedMeasurement.getIceDangerMeasurementDate(),formatter));
            }

            if(downloadedMeasurement.getEncroachLevel() != null){
                measurement.setEncroachLevel(Integer.parseInt(downloadedMeasurement.getEncroachLevel()));
            }
            if(downloadedMeasurement.getEncroachLevelMeasureDate() != null){
                measurement.setEncroachLevelMeasureDate(LocalDateTime.parse(downloadedMeasurement.getEncroachLevelMeasureDate(),formatter));
            }

            if(!measurementRepository.existsByStationAndWaterLevelMeasurementDate(station, measurement.getWaterLevelMeasurementDate())){
                measurement.setStation(station);
                measurementList.add(measurement);
            }
        }
        return measurementList;
    }
}
