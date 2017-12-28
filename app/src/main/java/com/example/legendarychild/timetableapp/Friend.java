package com.example.legendarychild.timetableapp;

/**
 * Created by LegendaryChild on 09/12/17.
 */

public class Friend {

    String name;
    String regNo;
    String id;
    public Friend(String name,String  regNo,String id)
    {
        this.name=name;
        this.regNo=regNo;
        this.id=id;
    }
    public Friend()
    {
        
    }

    public String getName() {
        return name;
    }

    public String getRegNo() {
        return regNo;
    }

    public String getId()
    {
        return id;
    }
}
