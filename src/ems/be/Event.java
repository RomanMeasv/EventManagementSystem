package ems.be;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
    //private List<EventCoordinator> eventCoordinators;



    public Event(String name, String description, String notes, LocalDateTime start, LocalDateTime end, String location, String locationGuidance, List<String> ticketType) {
        this.name = name;
        this.description = description;
        this.notes = notes;
        this.start = start;
        this.end = end;
        this.location = location;
        this.locationGuidance = locationGuidance;
        this.ticketTypes = ticketType;

        //this.eventCoordinators = eventCoordinators;
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

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocationGuidance() {
        return locationGuidance;
    }

    public void setLocationGuidance(String locationGuidance) {
        this.locationGuidance = locationGuidance;
    }

    public List<String> getTicketTypes() {
        return ticketTypes;
    }

    public void setTicketTypes(List<String> ticketTypes) {
        this.ticketTypes = ticketTypes;
    }

//    public List<EventCoordinator> getEventCoordinators() {return eventCoordinators;}
//
//    public void setEventCoordinators(List<EventCoordinator> eventCoordinators) {
//        this.eventCoordinators = eventCoordinators;
//    }

    @Override
    public String toString(){
        return this.name;
    }
}
