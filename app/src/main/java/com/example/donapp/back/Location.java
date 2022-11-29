package com.example.donapp.back;

import java.util.ArrayList;

public class Location {
    private String department;
    private String city;
    private String other;
    private ArrayList<Event> events;
    private String address;

    public Location(String dep, String cid, String add)
    {
        department = dep;
        city = cid;
        events = new ArrayList<Event>();
        address = add;
        other = null;

    }
    public Location(String dep, String cid, String ot, String add)
    {
        department = dep;
        city = cid;
        other = ot;
        events = new ArrayList<Event>();
        address = add;
    }


    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public void addEvent(Event e)
    {
        events.add(e);
    }
    public ArrayList<Event> getEvents() {
        return events;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
