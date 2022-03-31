import ems.be.Ticket;
import ems.dal.TicketDAO;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TicketDAOTest {
    //test readAllTickets in TicketDAO
    @Test
    public void testReadAllTickets() throws Exception {
        TicketDAO dao = new TicketDAO();
        List<Ticket> tickets = dao.readAllTickets();
        Assert.assertEquals(1, tickets.size());
    }
}
