package org.boygear.controllers;

import org.boygear.entities.Station;
import org.boygear.services.StationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class StationControllerTest {

    @Mock
    private StationService stationService;

    @InjectMocks
    private StationController stationController;

    @Test
    public void stationList() {
        //given
        Integer page = 0;
        Integer size = 100;
        List<Station> stationList = List.of(new Station());
        Pageable pageable = PageRequest.of(page, size);
        Mockito.when(stationService.getStationsPage(pageable)).thenReturn(stationList);

        //when
        List<Station> stationResultList = stationController.stationList(page,size);

        //then
        assertEquals(stationList, stationResultList);
        Mockito.verify(stationService, Mockito.times(1)).getStationsPage(pageable);
    }
}