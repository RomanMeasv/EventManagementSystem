package ems.be;

public class EventCoordinator extends User {
    public EventCoordinator(String username, String password) {
        setUsername(username);
        setPassword(password);
    }

    public EventCoordinator(int id, String username, String password) {
        setId(id);
        setUsername(username);
        setPassword(password);
    }

    @Override
    public String toString() {
        return this.getUsername();
    }
}
