package com.company.repository;

import com.company.entity.Customer;

import java.sql.SQLException;
import java.util.List;

public interface CustomerRepository {

    Customer save(Customer customer) throws SQLException, ClassNotFoundException;

    List<Customer> findAll() throws SQLException, ClassNotFoundException;

    Customer findById(int id) throws ClassNotFoundException, SQLException;

    Customer findByFinCode(String finCode) throws ClassNotFoundException, SQLException;

    Customer findCustomerDetailByFinCode(String finCode)throws ClassNotFoundException, SQLException;

    void deleteById(int id);
}
