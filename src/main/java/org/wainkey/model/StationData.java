/*
 *     Copyright © Kashin Vladimir Olegovich (Wain Key), 2020.
 *     This project is a part of a graduation qualification work of Bauman Moscow State Technical University (BMSTU) student.
 *     If You use this project or a part of it for your own purposes please make sure that You indicated authorship.
 */

package org.wainkey.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "stations")
public class StationData {
    @Id
    @Column(name = "number")
    @GeneratedValue(strategy = GenerationType.IDENTITY) // свойство будет генерироваться автоматически
    private int number;

    @Column(name = "station")
    private String station;

    @Column(name = "date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy HH:mm:ss")
    private Date date;

    @Column(name = "power")
    private int power;

    public int getNumber() {
        return number;
    }

    public String getStation() {
        return station;
    }

    public Date getDate() {
        return date;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public float getPower() {
        return power;
    }
}
