import ems.be.Event;
import ems.dal.EventDAO;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;

public class EventDAOTest {
    @Test
    public void testCreateEventReturnsId() throws Exception {
        EventDAO eDAO = new EventDAO();
        Event e = new Event("Name", "Description", "Notes", LocalDateTime.now(), LocalDateTime.now(), "Location", "Location guidance");

        Event eCreated = eDAO.createEvent(e);
        int createdId = eCreated.getId();

        Assert.assertNotEquals(0, createdId);
    }
}
