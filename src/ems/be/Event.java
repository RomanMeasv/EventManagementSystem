package ems.be;

public class Event {
    //Time
    //Location
    //Participants
    //Notes

    //OPTIONAL INFORMATION (must be supported but doesn't apply for all events)
    //End date and time (nullable)
    //Location guidance (nullable)
    private String name;

    public Event(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
