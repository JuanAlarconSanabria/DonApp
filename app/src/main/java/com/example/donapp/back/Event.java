package com.example.donapp.back;
import android.os.Build;

import java.time.LocalDateTime;
public class Event {
     private LocalDateTime date;
     private Organization organization;
     private Location location;
     private String name;
     private String description;

     public Event(LocalDateTime d, Organization o, Location l, String nm, String dc)
     {
         date = d;
         organization = o;
         location = l;
         name = nm;
         description = dc;
     }
     public Event()
     {
         date = null;
         organization = null;
         location = null;
     }

     public void setDate(int year, int month, int dayOfMonth, int hour, int minute)
     {
         if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
             date = LocalDateTime.of(year,month, dayOfMonth, hour, minute);
         }

     }

     public void setOrganization(Organization o)
     {
         organization = o;
     }

     public void setLocation(Location loc)
     {
         location = loc;
     }


    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Organization getOrganization() {
        return organization;
    }

    public Location getLocation() {
        return location;
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
