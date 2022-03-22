package org.boygear.services;

import org.boygear.entities.DownloadedMeasurement;
import org.boygear.entities.Measurement;
import org.boygear.entities.Station;
import org.boygear.repositories.MeasurementRepository;
import org.boygear.repositories.StationRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class MeasurementServiceTest {

    @InjectMocks
    private MeasurementService measurementService;
    @Mock
    private MeasurementRepository measurementRepository;
    @Mock
    private StationRepository stationRepository;

    private DownloadedMeasurement downloadedMeasurement = new DownloadedMeasurement();
    private Measurement measurement = new Measurement();
    private Station station = new Station();
    @Before
    public void setData(){

        downloadedMeasurement.setStationID("151140030");
        downloadedMeasurement.setName("Przewoźniki");
        downloadedMeasurement.setRiver("Skroda");
        downloadedMeasurement.setProvince("lubuskie");
        downloadedMeasurement.setWaterLevel("234");
        downloadedMeasurement.setWaterLevelMeasurementDate("2022-03-22 12:10:00");
        downloadedMeasurement.setWaterTemperature(null);
        downloadedMeasurement.setIceDanger("0");
        downloadedMeasurement.setIceDangerMeasurementDate("2021-03-03 11:10:00");
        downloadedMeasurement.setEncroachLevel("302");
        downloadedMeasurement.setEncroachLevelMeasureDate("2021-08-24 06:00:00");


        station.setStationID(151140030L);
        station.setName("Przewoźniki");
        station.setRiver("Skroda");
        station.setProvince("lubuskie");


        measurement.setWaterLevel(234);
        LocalDateTime localDateTime = LocalDateTime.of(2022,3,22,12,10,0);
        measurement.setWaterLevelMeasurementDate(localDateTime);
        measurement.setWaterTemperature(null);
        measurement.setIceDanger(0);
        localDateTime = LocalDateTime.of(2022,1,14,11,10,0);
        measurement.setIceDangerMeasurementDate(localDateTime);
        measurement.setEncroachLevel(302);
        localDateTime = LocalDateTime.of(2021,8,24,6,0,0);
        measurement.setEncroachLevelMeasureDate(localDateTime);
        measurement.setStation(station);

    }

    @Test
    public void addNewMeasurementList() {
        //given
        List<DownloadedMeasurement> downloadedMeasurementList = List.of(downloadedMeasurement);
        List<Measurement> measurementList = List.of(measurement);
        List<Station> stationList = List.of(station);
        Mockito.when(measurementRepository.saveAll(Mockito.anyList())).thenReturn(measurementList);
        //when
        List<Measurement> measurementListResult = measurementService.addNewMeasurementList(downloadedMeasurementList);
        //then
        assertEquals(measurementList,measurementListResult);
        ArgumentCaptor<List<Measurement>> argumentCaptorMeasurement = ArgumentCaptor.forClass(List.class);
        Mockito.verify(measurementRepository).saveAll(argumentCaptorMeasurement.capture());
        assertNotNull(argumentCaptorMeasurement.getValue());
        assertTrue(argumentCaptorMeasurement.getValue().size()==1);
        assertEquals(measurementListResult, measurementList);
        assertEquals(argumentCaptorMeasurement.getValue().get(0).getId(), measurement.getId());
        ArgumentCaptor<List<Station>> argumentCaptorStation = ArgumentCaptor.forClass(List.class);
        Mockito.verify(stationRepository).saveAll(argumentCaptorStation.capture());
        assertNotNull(argumentCaptorStation.getValue());
        assertEquals(argumentCaptorStation.getValue().get(0).getStationID(), station.getStationID());

    }

    @Test
    public void getMeasurement() {
    }
}