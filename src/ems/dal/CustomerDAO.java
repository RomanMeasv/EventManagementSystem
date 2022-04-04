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

    public List<Customer> readAllCustomers() throws Exception {
        List<Customer> allCustomers;
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

    //update customer by id
    public void updateCustomer(Customer customer) throws Exception {
        try (Connection con = ConnectionManager.getConnection()) {
            String sqlCommandUpdateCustomer = "UPDATE Customers SET [name] = ?, email = ?, phoneNumber = ?, notes = ? WHERE id = ?;";
            PreparedStatement pstmtUpdateCustomer = con.prepareStatement(sqlCommandUpdateCustomer);

            pstmtUpdateCustomer.setString(1, customer.getName());
            pstmtUpdateCustomer.setString(2, customer.getEmail());
            pstmtUpdateCustomer.setString(3, customer.getPhoneNumber());
            pstmtUpdateCustomer.setString(4, customer.getNotes());
            pstmtUpdateCustomer.setInt(5, customer.getId());

            pstmtUpdateCustomer.executeUpdate();
        }
    }

    //delete customer
    public void deleteCustomer(Customer c) throws Exception {
        try (Connection con = ConnectionManager.getConnection()) {
            String sqlCommandDeleteCustomer = "DELETE FROM Customers WHERE id = ?;";
            PreparedStatement pstmtDeleteCustomer = con.prepareStatement(sqlCommandDeleteCustomer);

            pstmtDeleteCustomer.setInt(1, c.getId());

            pstmtDeleteCustomer.executeUpdate();
        }
    }

    public List<Customer> filterCustomers(String query) throws Exception {
        List<Customer> filtered = new ArrayList<>();

        try (Connection con = ConnectionManager.getConnection()) {
            String sqlCommandFilter = "SElECT * FROM Customers WHERE [name] LIKE ?;";
            PreparedStatement pstmtFilter = con.prepareStatement(sqlCommandFilter);
            pstmtFilter.setString(1, "%" + query + "%");
            ResultSet rs = pstmtFilter.executeQuery();

            while (rs.next()) {
                filtered.add(new Customer(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("phoneNumber"),
                        rs.getString("notes")
                ));
            }

        }
        return filtered;
    }

    //get customer by id
    public Customer readCustomer(int id) throws Exception {
        Customer customer = null;
        try (Connection con = ConnectionManager.getConnection()) {
            String sqlCommandGetCustomerById = "SELECT * FROM Customers WHERE id = ?;";
            PreparedStatement pstmtGetCustomerById = con.prepareStatement(sqlCommandGetCustomerById);
            pstmtGetCustomerById.setInt(1, id);
            ResultSet rs = pstmtGetCustomerById.executeQuery();

            while (rs.next()) {
                customer = new Customer(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("phoneNumber"),
                        rs.getString("notes")
                );
            }
        }
        return customer;
    }
}
