package org.boygear.services.download;

import com.google.gson.annotations.SerializedName;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Component
public class DownloadedMeasurement {

    @SerializedName("id_stacji")
    private String stationID;
    @SerializedName("stacja")
    private String name;
    @SerializedName("rzeka")
    private String river;
    @SerializedName("województwo")
    private String province;
    @SerializedName("stan_wody")
    private String waterLevel;
    @SerializedName("stan_wody_data_pomiaru")
    private String waterLevelMeasurementDate;
    @SerializedName("temperatura_wody")
    private String waterTemperature;
    @SerializedName("zjawisko_lodowe")
    private String iceDanger;
    @SerializedName("zjawisko_lodowe_data_pomiaru")
    private String iceDangerMeasurementDate;
    @SerializedName("zjawisko_zarastania")
    private String encroachLevel;
    @SerializedName("zjawisko_zarastania_data_pomiaru")
    private String encroachLevelMeasureDate;


    public String getStationID() {
        return stationID;
    }

    public void setStationID(String stationID) {
        this.stationID = stationID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRiver() {
        return river;
    }

    public void setRiver(String river) {
        this.river = river;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getWaterLevel() {
        return waterLevel;
    }

    public void setWaterLevel(String waterLevel) {
        this.waterLevel = waterLevel;
    }

    public String getWaterLevelMeasurementDate() {
        return waterLevelMeasurementDate;
    }

    public void setWaterLevelMeasurementDate(String waterLevelMeasurementDate) {
        this.waterLevelMeasurementDate = waterLevelMeasurementDate;
    }

    public String getWaterTemperature() {
        return waterTemperature;
    }

    public void setWaterTemperature(String waterTemperature) {
        this.waterTemperature = waterTemperature;
    }

    public String getIceDanger() {
        return iceDanger;
    }

    public void setIceDanger(String iceDanger) {
        this.iceDanger = iceDanger;
    }

    public String getIceDangerMeasurementDate() {
        return iceDangerMeasurementDate;
    }

    public void setIceDangerMeasurementDate(String iceDangerMeasurementDate) {
        this.iceDangerMeasurementDate = iceDangerMeasurementDate;
    }

    public String getEncroachLevel() {
        return encroachLevel;
    }

    public void setEncroachLevel(String encroachLevel) {
        this.encroachLevel = encroachLevel;
    }

    public String getEncroachLevelMeasureDate() {
        return encroachLevelMeasureDate;
    }

    public void setEncroachLevelMeasureDate(String encroachLevelMeasureDate) {
        this.encroachLevelMeasureDate = encroachLevelMeasureDate;
    }
}


