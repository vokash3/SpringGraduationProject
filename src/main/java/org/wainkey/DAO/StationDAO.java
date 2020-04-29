/*
 *     Copyright © Kashin Vladimir Olegovich (Wain Key), 2020.
 *     This project is a part of a graduation qualification work of Bauman Moscow State Technical University (BMSTU) student.
 *     If You use this project or a part of it for your own purposes please make sure that You indicated authorship.
 */

package org.wainkey.DAO;

import org.wainkey.model.StationData;

import java.util.List;

public interface StationDAO {
    List<StationData> records();
    void add(StationData stationData);
}
