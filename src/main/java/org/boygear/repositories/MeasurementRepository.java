package org.boygear.repositories;

import org.boygear.entities.Measurement;
import org.boygear.entities.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MeasurementRepository extends JpaRepository<Measurement, Integer> {

    boolean existsByStationAndWaterLevelMeasurementDate(Station station, LocalDateTime waterLevelMeasurementDate);
    List<Measurement> findAllByStationAndWaterLevelMeasurementDateBetween(Station station, LocalDateTime startTime, LocalDateTime endTime);

}
