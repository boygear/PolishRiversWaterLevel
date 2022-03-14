package org.boygear.repositories;

import org.boygear.entities.Station;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StationRepository extends JpaRepository<Station, Long> {

public List<Station> findAllByOrderByNameAsc(Pageable pageable);


}
