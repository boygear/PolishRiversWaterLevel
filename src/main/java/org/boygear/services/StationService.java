package org.boygear.services;

import org.boygear.entities.Station;
import org.boygear.repositories.StationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StationService {

    @Autowired
    private StationRepository stationRepository;

    public List<Station> getStationsPage(Pageable pageable){

        return stationRepository.findAllByOrderByNameAsc(pageable);
    }
}
