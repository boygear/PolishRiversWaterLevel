package org.boygear.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.annotations.SerializedName;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "station")
public class Station {
    @Id
    private Long stationID;
    private String name;
    private String river;
    private String province;

    @OneToMany(mappedBy = "station")
    //@JsonIgnore
    private List<Measurement> measurementList;

    public Long getStationID() {
        return stationID;
    }

    public void setStationID(Long stationID) {
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
}
