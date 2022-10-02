package ru.liga;

import java.time.LocalDate;
import java.util.Objects;

public class Rate {
    private final Integer nominal;
    private final LocalDate dayDate;
    private final double curs;
    private final String nameRate;

    public Rate(Integer nominal, LocalDate dayDate, double curs, String nameRate) {
        this.nominal = nominal;
        this.dayDate = dayDate;
        this.curs = curs;
        this.nameRate = nameRate;
    }

    public LocalDate getDayDate(){
        return dayDate;
    }
    public Integer getNominal(){
        return nominal;
    }
    public double getCurs(){
        return curs;
    }
    public String getNameRate(){
        return nameRate;
    }

}

