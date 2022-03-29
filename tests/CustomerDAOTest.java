import ems.be.Customer;
import ems.be.User;
import ems.dal.CustomerDAO;
import ems.dal.UserDAO;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CustomerDAOTest {
    //test create customer
    @Test
    public void testCreateCustomer() throws Exception {
        CustomerDAO customerDAO = new CustomerDAO();
        Customer customer = new Customer("Name", "myemail@gmail.com", "1234567", "notes");

        int cId = customerDAO.createCustomer(customer).getId();
        Assertions.assertEquals(1, cId);
    }
}
