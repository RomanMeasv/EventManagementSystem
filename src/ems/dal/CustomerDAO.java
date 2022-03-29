package ems.dal;

import ems.be.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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

    //read all customers
    public List<Customer> readAllCustomers() throws Exception {
        List<Customer> allCustomers = null;
        try (Connection con = ConnectionManager.getConnection()) {
            String sqlCommandReadAllCustomers = "SELECT * FROM Customers;";
            Statement stmtReadAllCustomers = con.createStatement();
            ResultSet rs = stmtReadAllCustomers.executeQuery(sqlCommandReadAllCustomers);

            allCustomers = new ArrayList<>();
            while (rs.next()) {
                allCustomers.add(new Customer(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("phoneNumber"),
                        rs.getString("notes")));
            }
        }
        return allCustomers;
    }



}
