package ems.be;

import java.util.UUID;

public class Ticket {
    private UUID uuid;
    private boolean isValid;
    private Event event;
    private String ticketType;
    private Customer customer;

    public Ticket(Event event, String ticketType, Customer customer) {
        this.uuid = UUID.randomUUID();
        this.isValid = true;
        this.event = event;
        this.ticketType = ticketType;
        this.customer = customer;
    }

    public Ticket(UUID uuid, boolean isValid, Event event, String ticketType, Customer customer) {
        this.uuid = uuid;
        this.isValid = isValid;
        this.event = event;
        this.ticketType = ticketType;
        this.customer = customer;
    }

    public UUID getUuid() {
        return uuid;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public String getTicketType() {
        return ticketType;
    }

    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "..." + uuid.toString().substring(uuid.toString().length()-4) + " (" + event.getName() + ", " + customer.getName() + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Ticket other = (Ticket) obj;
        if (!this.uuid.equals(other.uuid)) {
            return false;
        }
        return true;
    }
}
