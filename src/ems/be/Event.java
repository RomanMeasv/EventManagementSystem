package ems.be;

import java.time.LocalDateTime;
import java.util.List;

public class Event {
    private int id;
    private String name;
    private String description;
    private String notes;
    private LocalDateTime start;
    private LocalDateTime end;
    private String location;
    private String locationGuidance;
    private List<String> ticketTypes;

    public Event(String name, String description, String notes, LocalDateTime start, LocalDateTime end, String location, String locationGuidance, List<String> ticketType) {
        this.name = name;
        this.description = description;
        this.notes = notes;
        this.start = start;
        this.end = end;
        this.location = location;
        this.locationGuidance = locationGuidance;
        this.ticketTypes = ticketType;
    }

    public Event(int id, String name, String description, String notes, LocalDateTime start, LocalDateTime end, String location, String locationGuidance, List<String> ticketTypes) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.notes = notes;
        this.start = start;
        this.end = end;
        this.location = location;
        this.locationGuidance = locationGuidance;
        this.ticketTypes = ticketTypes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getNotes() {
        return notes;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public String getLocation() {
        return location;
    }

    public String getLocationGuidance() {
        return locationGuidance;
    }


    public List<String> getTicketTypes() {
        return ticketTypes;
    }

    @Override
    public String toString(){
        return this.name;
    }
}
