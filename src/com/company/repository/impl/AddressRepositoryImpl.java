package com.company.repository.impl;

import com.company.config.DataBaseConfig;
import com.company.entity.Address;
import com.company.repository.AddressRepository;

import java.sql.*;

public class AddressRepositoryImpl implements AddressRepository {

    @Override
    public Address findByCustomerId(Integer id) throws SQLException, ClassNotFoundException {

        Connection con = DataBaseConfig.INSTANCE().connect();
        Statement stmt = con.createStatement();

        String sql =  String.format( "select * from address where customer_id = %s ",id);

        ResultSet rs = stmt.executeQuery(sql);
        Address address = new Address();
        while (rs.next()) {
            address.setId(rs.getInt("id"));
            address.setCountry(rs.getString("country"));
            address.setCountryCode(rs.getString("country_code"));
            address.setCity(rs.getString("city"));
            address.setDistrict(rs.getString("district"));
            address.setStreet(rs.getString("street"));
            address.setCustomerId(rs.getInt("customer_id"));
        }

        con.close();

        return address;
    }

    @Override
    public Address saveAddress(Address address) throws ClassNotFoundException, SQLException {
        Connection con = DataBaseConfig.INSTANCE().connect();

        String sql = "insert into address(country,country_code,city,district,street,customer_id) values (?,?,?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
        ps.setString(1,address.getCountry());
        ps.setString(2,address.getCountryCode());
        ps.setString(3, address.getCity());
        ps.setString(4, address.getDistrict());
        ps.setString(5, address.getStreet());
        ps.setInt(6,address.getCustomerId());
        ps.execute();

        return findByCustomerId(address.getCustomerId());
    }
}
