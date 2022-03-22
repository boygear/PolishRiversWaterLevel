package org.boygear.services.download;

import org.boygear.entities.DownloadedMeasurement;
import org.junit.Test;
import java.util.List;
import static org.junit.Assert.*;

public class DownloadServiceTest {

    @Test
    public void getCurrentMeasurementList() {
        //when
        List<DownloadedMeasurement> measurementList = new DownloadService().getCurrentMeasurementList();
        //then
        assertNotNull(measurementList);
        assertTrue(measurementList.size()>3);
    }
}