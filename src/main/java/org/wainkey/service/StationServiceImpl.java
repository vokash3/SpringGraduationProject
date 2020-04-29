/*
 *     Copyright © Kashin Vladimir Olegovich (Wain Key), 2020.
 *     This project is a part of a graduation qualification work of Bauman Moscow State Technical University (BMSTU) student.
 *     If You use this project or a part of it for your own purposes please make sure that You indicated authorship.
 */

package org.wainkey.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wainkey.DAO.StationDAO;
import org.wainkey.model.StationData;

import javax.transaction.Transactional;
import java.util.List;
@Service
public class StationServiceImpl implements StationService{

    private StationDAO stationDAO;
    @Transactional
    @Autowired
    public void setStationDAO(StationDAO stationDAO){
        this.stationDAO = stationDAO;
    }

    @Transactional
    @Override
    public List<StationData> records() {
        return stationDAO.records();
    }
    @Transactional
    @Override
    public void add(StationData stationData) {
        stationDAO.add(stationData);
    }
}
