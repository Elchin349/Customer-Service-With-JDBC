package com.company.repository;

import com.company.entity.Address;

import java.sql.SQLException;

public interface AddressRepository {

    Address findByCustomerId(Integer id) throws SQLException, ClassNotFoundException;

    Address saveAddress(Address address) throws ClassNotFoundException, SQLException;

}
