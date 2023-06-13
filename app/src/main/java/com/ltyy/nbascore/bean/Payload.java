package com.ltyy.nbascore.bean;

import java.util.List;

public class Payload {
    private Season season;
    private List<Date> dates;

    public Season getSeason() {
        return season;
    }

    public void setSeason(Season season) {
        this.season = season;
    }

    public List<Date> getDates() {
        return dates;
    }

    public void setDates(List<Date> dates) {
        this.dates = dates;
    }
}
