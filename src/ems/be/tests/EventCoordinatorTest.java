package ems.be.tests;

import ems.be.EventCoordinator;
import ems.be.User;
import org.junit.Test;

import static org.junit.Assert.*;

public class EventCoordinatorTest {
    @Test
    public void testGetters(){
        User eventCoordinator = new EventCoordinator();

        int uId = 2;
        String username = "eventCoordinator";
        String password = "eventCoordinator";

        eventCoordinator.setId(uId);
        eventCoordinator.setUsername(username);
        eventCoordinator.setPassword(password);

        assertEquals(2, eventCoordinator.getId());
        assertEquals("eventCoordinator", eventCoordinator.getUsername());
        assertEquals("eventCoordinator", eventCoordinator.getPassword());
    }
}
