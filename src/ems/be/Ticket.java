package ems.be;

public class Ticket {
    //QR Code
    //1D Barcode
    //Event information (excluding participants)
    //Type of ticket


    //Print tickets and/or show them at event
    private String name;

    public Ticket(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
