package org.boygear.DTO;

import org.boygear.entities.Station;

import java.util.List;

public class StationsList {
    private List<Long> ids;

    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }
}
