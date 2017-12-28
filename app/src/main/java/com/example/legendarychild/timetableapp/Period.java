package com.example.legendarychild.timetableapp;

import java.util.ArrayList;

/**
 * Created by LegendaryChild on 09/12/17.
 */

public class Period {
    public String names;
    public String startHours;
    public String startMins;
    public Period(String names,String startHours,String startMins)
    {
        this.names= names;
         this.startHours= startHours;
         this.startMins= startMins;

    }
    public Period()
    {

    }

    public String getNames() {
        return names;
    }

    public String getStartHours() {
        return startHours;
    }

    public String getStartMins() {
        return startMins;
    }
}
