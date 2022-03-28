import ems.be.Event;
import ems.dal.EventDAO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Disabled;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class EventDAOTest {

    @Test
    @Disabled
    public void testCreateEventReturnsId() throws Exception {
        EventDAO eDAO = new EventDAO();
        Event e = new Event("Name", "Description", "Notes", LocalDateTime.now(), LocalDateTime.now(), "Location", "Location guidance", new ArrayList<String>());

        Event eCreated = eDAO.createEvent(e);
        int createdId = eCreated.getId();

        Assert.assertNotEquals(0, createdId);
    }

}
