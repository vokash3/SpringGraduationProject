/*
 *     Copyright © Kashin Vladimir Olegovich (Wain Key), 2020.
 *     This project is a part of a graduation qualification work of Bauman Moscow State Technical University (BMSTU) student.
 *     If You use this project or a part of it for your own purposes please make sure that You indicated authorship.
 */

package org.wainkey.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.ArrayList;

public class Data {
    public String stationId;
    @JsonDeserialize(as = ArrayList.class)
    public ArrayList<StationData> stationData;

    public void setStationId(String stationId) {
        this.stationId = stationId;
    }

    public void setStationData(ArrayList<StationData> stationData) {
        this.stationData = stationData;
    }

    public String getStationId() {
        return stationId;
    }

    public ArrayList<StationData> getStationData() {
        return stationData;
    }
}
