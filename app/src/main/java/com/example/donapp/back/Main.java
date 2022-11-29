package com.example.donapp.back;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public ArrayList<Event> events;
    public ArrayList<Usuario> usuarios;
    public ArrayList<Organization> organizations;
    public boolean user = false;
    private Usuario current = null;
    public ArrayList<Event> currentEvents;

    public ArrayList<Location> locations;
    public Event currentEvent;

    public Main()
    {
        events = new ArrayList<>();
        usuarios = new ArrayList<>();
        organizations = new ArrayList<>();
        locations = new ArrayList<>();
    }

    public String[] getLastEventInfo()
    {
        Event event = current.getEvents().get(current.getEvents().size()-1);
        Location loc = event.getLocation();
        String[] res = new String[]{event.getName(), event.getDescription(), loc.getDepartment(),loc.getCity(),
                loc.getOther(), loc.getAddress(),event.getDate().toString(),current.getOrganization().getName()};
        return res;
    }

    public ArrayList<Organization> getOrganizations()
    {
        return organizations;
    }

    public void setCurrentEventsAll()
    {
        currentEvents= events;
    }

    public ArrayList<String> getCurrentsStrings()
    {
        ArrayList<String> res = new ArrayList<>();
        for(int i = 0; i < currentEvents.size(); i++)
        {
            Event curr = currentEvents.get(i);
            res.add(curr.getName() + " - " + curr.getLocation().getCity() + ", " + curr.getLocation().getDepartment());
        }
        return res;
    }

    public ArrayList<String> getOrganizationsString()
    {
        ArrayList<String> res = new ArrayList<>();
        for(int i = 0; i < organizations.size(); i++)
        {
            res.add(organizations.get(i).getName());
        }
        return res;
    }
    public void setCurrentEventsDep(String dep)
    {
        currentEvents = new ArrayList<>();
        for(int i = 0; i < events.size(); i++)
        {
            if(events.get(i).getLocation().getDepartment().equals(dep))
            {
                currentEvents.add(events.get(i));
            }
        }
    }
    public void setCurrentEventsCity(String city)
    {
        currentEvents = new ArrayList<>();
        for(int i = 0; i < events.size(); i++)
        {
            if(events.get(i).getLocation().getCity().equals(city))
            {
                currentEvents.add(events.get(i));
            }
        }
    }
    public void setCurrentEventsOther(String other)
    {
        currentEvents = new ArrayList<>();
        for(int i = 0; i < events.size(); i++)
        {
            if(events.get(i).getLocation().getOther() != null && events.get(i).getLocation().getOther().equals(other))
            {
                currentEvents.add(events.get(i));
            }
        }
    }



    public void crearUsuario(String name, String pass, Organization o)
    {
        Usuario usuario = new Usuario(name, pass, o);
        usuarios.add(usuario);
        user = true;
        current = usuario;
    }

    public boolean login(String name, String pass)
    {
        boolean is = false;
        for(int i = 0;  i < usuarios.size(); i++)
        {
            if(usuarios.get(i).getName().equals(name) && usuarios.get(i).getPass().equals(pass))
            {
                is = true;
                current = usuarios.get(i);
                break;
            }
        }
        return is;
    }
    public void setCurrentEvent(String name)
    {
        for(int i = 0; i < currentEvents.size(); i++)
        {
            System.out.println(name);
            if(currentEvents.get(i).getName().equals(name))
            {

                currentEvent = currentEvents.get(i);
                System.out.println("entro");
                break;
            }
        }
    }
    public String[] getCurrentEventInfo()
    {
        Event event = currentEvent;
        Location loc = event.getLocation();
        String[] res = new String[]{event.getName(), event.getDescription(), loc.getDepartment(),loc.getCity(),
                loc.getOther(), loc.getAddress(),event.getDate().toString(),current.getOrganization().getName()};
        return res;
    }
    public boolean existeUsuario(String name)
    {
        for(int i = 0;  i < usuarios.size(); i++)
        {
            if(usuarios.get(i).getName().equals(name))
            {
                return true;
            }
        }
        return false;
    }

    public Organization encontrarOrganizacion(String name)
    {
        Organization org = null;
        for(int i = 0; i < organizations.size(); i++)
        {
            if(organizations.get(i).getName().equals(name))
            {
                org = organizations.get(i);
                break;
            }
        }
        return org;
    }


    public Organization crearOrganizacion(String name, String description)
    {
        Organization org = new Organization(name, description);
        organizations.add(org);
        return org;
    }

    public Event crearEvento(LocalDateTime date, String department, String city, String other, String address, String name, String description)
    {
        Location location = null;
        if (other != null)
        {
            location = new Location(department, city, other, address);
        }
        else
        {
            location = new Location(department, city, address);
        }
        locations.add(location);
        Event event = new Event(date,current.getOrganization(), location, name, description );
        location.addEvent(event);
        events.add(event);
        current.addEvent(event);
        current.getOrganization().addEvent(event);
        return event;
    }

    public ArrayList<Event> eventsDepartment(String s)
    {
        ArrayList<Event> evs = new ArrayList<>();
        for(int i = 0; i < locations.size(); i++)
        {
            if (locations.get(i).getDepartment().equals(s))
            {
                evs.addAll(locations.get(i).getEvents());
            }
        }
        return evs;
    }

    public ArrayList<String> getDepartmentsString() {
        ArrayList<String> res = new ArrayList<>();
        Map<String, String> map = new HashMap<String, String>();

        for(int i = 0; i < locations.size();i++)
        {
           map.put(locations.get(i).getDepartment(),"");
        }
        res.addAll(map.keySet());
        return res;
    }
    public ArrayList<String> getCitiesString(String dep)
    {
        ArrayList<String> res = new ArrayList<>();
        Map<String, String> map = new HashMap<String, String>();
        for(int i = 0; i < locations.size();i++)
        {
            if(locations.get(i).getDepartment().equals(dep))
            {
                map.put(locations.get(i).getCity(), "");
            }
        }
        res.addAll(map.keySet());
        return res;
    }
    public ArrayList<String> getOthersString(String city)
    {
        ArrayList<String> res = new ArrayList<>();
        Map<String, String> map = new HashMap<String, String>();
        for(int i = 0; i < locations.size();i++)
        {
            if(locations.get(i).getCity().equals(city))
            {
                if ((locations.get(i).getOther()!= null))
                {
                    map.put(locations.get(i).getOther(), "");
                }
            }
        }
        res.addAll(map.keySet());
        return res;
    }


    public ArrayList<Event> eventsCity(String s)
    {
        ArrayList<Event> evs = new ArrayList<>();
        for(int i = 0; i < locations.size(); i++)
        {
            if (locations.get(i).getCity().equals(s))
            {
                evs.addAll(locations.get(i).getEvents());
            }
        }
        return evs;
    }

    public ArrayList<Event> eventsOther(String s)
    {
        ArrayList<Event> evs = new ArrayList<>();
        for(int i = 0; i < locations.size(); i++)
        {
            if (locations.get(i).getOther().equals(s))
            {
                evs.addAll(locations.get(i).getEvents());
            }
        }
        return evs;
    }






}
