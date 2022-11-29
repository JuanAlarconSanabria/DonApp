package com.example.donapp.back;

import java.util.ArrayList;

public class Organization {
    private String name;
    private String description;
    private ArrayList<Event> events;
    public Organization(String name, String description)
    {
        this.name = name;
        this.description = description;
        events = new ArrayList<Event>();
    }

    public void addEvent(Event event)
    {
        events.add(event);

    }
    public ArrayList<Event> getEvents()
    {
        return events;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
