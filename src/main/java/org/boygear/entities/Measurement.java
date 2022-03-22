package org.boygear.entities;

import org.springframework.stereotype.Indexed;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(indexes = @Index(columnList = "waterLevelMeasurementDate"))
public class Measurement {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer waterLevel;
    private LocalDateTime waterLevelMeasurementDate;
    private Double waterTemperature;
    private Integer iceDanger;
    private LocalDateTime iceDangerMeasurementDate;
    private Integer encroachLevel;
    private LocalDateTime encroachLevelMeasureDate;

    @ManyToOne
    @JoinColumn(name = "stationID")
    private Station station;

    public Integer getId() {
        return id;
    }

    public Integer getWaterLevel() {
        return waterLevel;
    }

    public void setWaterLevel(Integer waterLevel) {
        this.waterLevel = waterLevel;
    }

    public LocalDateTime getWaterLevelMeasurementDate() {
        return waterLevelMeasurementDate;
    }

    public void setWaterLevelMeasurementDate(LocalDateTime waterLevelMeasurementDate) {
        this.waterLevelMeasurementDate = waterLevelMeasurementDate;
    }

    public Double getWaterTemperature() {
        return waterTemperature;
    }

    public void setWaterTemperature(Double waterTemperature) {
        this.waterTemperature = waterTemperature;
    }

    public Integer getIceDanger() {
        return iceDanger;
    }

    public void setIceDanger(Integer iceDanger) {
        this.iceDanger = iceDanger;
    }

    public LocalDateTime getIceDangerMeasurementDate() {
        return iceDangerMeasurementDate;
    }

    public void setIceDangerMeasurementDate(LocalDateTime iceDangerMeasurementDate) {
        this.iceDangerMeasurementDate = iceDangerMeasurementDate;
    }

    public Integer getEncroachLevel() {
        return encroachLevel;
    }

    public void setEncroachLevel(Integer encroachLevel) {
        this.encroachLevel = encroachLevel;
    }

    public LocalDateTime getEncroachLevelMeasureDate() {
        return encroachLevelMeasureDate;
    }

    public void setEncroachLevelMeasureDate(LocalDateTime encroachLevelMeasureDate) {
        this.encroachLevelMeasureDate = encroachLevelMeasureDate;
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Measurement that = (Measurement) o;
        return Objects.equals(id, that.id) && Objects.equals(waterLevel, that.waterLevel) && Objects.equals(waterLevelMeasurementDate, that.waterLevelMeasurementDate) && Objects.equals(waterTemperature, that.waterTemperature) && Objects.equals(iceDanger, that.iceDanger) && Objects.equals(iceDangerMeasurementDate, that.iceDangerMeasurementDate) && Objects.equals(encroachLevel, that.encroachLevel) && Objects.equals(encroachLevelMeasureDate, that.encroachLevelMeasureDate) && Objects.equals(station, that.station);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, waterLevel, waterLevelMeasurementDate, waterTemperature, iceDanger, iceDangerMeasurementDate, encroachLevel, encroachLevelMeasureDate, station);
    }
}


