package ems.dal;

import ems.be.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class CustomerDAO {
    public Customer createCustomer(Customer customer) throws Exception {
        Customer customerCreated = null;
        try (Connection con = ConnectionManager.getConnection()) {
            String sqlCommandInsertEvent = "INSERT INTO Customers([name], email, phoneNumber, notes) VALUES (?, ?, ?, ?);";
            PreparedStatement pstmtInsertEvent = con.prepareStatement(sqlCommandInsertEvent, Statement.RETURN_GENERATED_KEYS);

            pstmtInsertEvent.setString(1, customer.getName());
            pstmtInsertEvent.setString(2, customer.getEmail());
            pstmtInsertEvent.setString(3, customer.getPhoneNumber());
            pstmtInsertEvent.setString(4, customer.getNotes());

            pstmtInsertEvent.executeUpdate();
            ResultSet rs = pstmtInsertEvent.getGeneratedKeys();

            while (rs.next()) {
                customerCreated = new Customer(
                        rs.getInt(1),
                        customer.getName(),
                        customer.getEmail(),
                        customer.getPhoneNumber(),
                        customer.getNotes());
            }
        }
        return customerCreated;
    }
}
