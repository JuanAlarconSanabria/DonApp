package com.example.donapp.back;

import java.util.ArrayList;

public class Usuario {
    private String name;
    private String pass;
    private Organization organization;
    private ArrayList<Event> events;

    public Usuario(String name, String pass, Organization organization)
    {
        this.name = name;
        this.pass = pass;
        this.organization = organization;
        events = new ArrayList<Event>();
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public ArrayList<Event> getEvents() {
        return events;
    }

    public void addEvent(Event e) {
        events.add(e);
    }
}
