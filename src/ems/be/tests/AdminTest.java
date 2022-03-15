package ems.be.tests;

import ems.be.Admin;
import ems.be.User;
import org.junit.Test;

import static org.junit.Assert.*;

public class AdminTest {
    @Test
    public void testGetters(){
        User admin = new Admin();

        int uId = 1;
        String username = "admin";
        String password = "admin";

        admin.setId(uId);
        admin.setUsername(username);
        admin.setPassword(password);

        assertEquals(1, admin.getId());
        assertEquals("admin", admin.getUsername());
        assertEquals("admin", admin.getPassword());
    }
}
