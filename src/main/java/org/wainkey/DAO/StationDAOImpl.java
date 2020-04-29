/*
 *     Copyright © Kashin Vladimir Olegovich (Wain Key), 2020.
 *     This project is a part of a graduation qualification work of Bauman Moscow State Technical University (BMSTU) student.
 *     If You use this project or a part of it for your own purposes please make sure that You indicated authorship.
 */

package org.wainkey.DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.wainkey.model.StationData;

import java.util.List;

@Repository
public class StationDAOImpl implements StationDAO{
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<StationData> records() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from StationData").list();
    }

    @Override
    public void add(StationData stationData) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(stationData);
    }
}
