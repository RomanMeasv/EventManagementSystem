package ems.be;

public class TicketType {
    //Customizable for each Event

    private String description;

    public TicketType(String description){
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
