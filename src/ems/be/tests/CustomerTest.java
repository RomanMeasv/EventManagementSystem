package ems.be.tests;

import ems.be.Customer;
import ems.be.User;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CustomerTest {
    @Test
    public void testGetters(){
        User customer = new Customer();

        int uId = 3;
        String username = "customer";
        String password = "customer";

        customer.setId(uId);
        customer.setUsername(username);
        customer.setPassword(password);

        assertEquals(3, customer.getId());
        assertEquals("customer", customer.getUsername());
        assertEquals("customer", customer.getPassword());
    }
}
