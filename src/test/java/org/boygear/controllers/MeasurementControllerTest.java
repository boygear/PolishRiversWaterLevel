package org.boygear.controllers;

import org.boygear.entities.Measurement;
import org.boygear.services.MeasurementService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class MeasurementControllerTest {

    @Mock
    private MeasurementService measurementService;

    @InjectMocks
    private MeasurementController measurementController;

    @Test
    public void getMeasurementList() {
        //given
        Long stationId = 100L;
        String startTime = "2021-11-01 22:00:00";
        String endTime = "2021-12-01 23:00:00";
        List<Measurement> measurementList = List.of(new Measurement());
        Mockito.when(measurementService.getMeasurement(stationId, startTime, endTime)).thenReturn(measurementList);
        //when

        List<Measurement> measurementsResultsList = measurementController.getMeasurementList(stationId, startTime, endTime);

        //then

        assertEquals(measurementList, measurementsResultsList);
        Mockito.verify(measurementService, Mockito.times(1)).getMeasurement(stationId, startTime, endTime);
    }
}