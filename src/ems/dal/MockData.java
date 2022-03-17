package ems.dal;

import ems.be.Admin;
import ems.be.EventCoordinator;
import ems.be.User;

import java.util.*;

public class MockData {

    private final List<User> userList = new ArrayList<>();

    public MockData() {
        User admin = new Admin();
        User eventCoordinator = new EventCoordinator();

        userList.add(admin);
        userList.add(eventCoordinator);
    }

    public boolean tryLogin(String username, String password) {
        return username.equals("admin") || username.equals("event");
    }
}
