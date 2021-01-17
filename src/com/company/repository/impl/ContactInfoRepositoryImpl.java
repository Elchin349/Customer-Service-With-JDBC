package com.company.repository.impl;

import com.company.config.DataBaseConfig;
import com.company.entity.ContactInfo;
import com.company.repository.ContactInfoRepository;

import java.sql.*;

public class ContactInfoRepositoryImpl implements ContactInfoRepository {


    @Override
    public ContactInfo saveContactInfo(ContactInfo contactInfo) throws ClassNotFoundException, SQLException {
        Connection con = DataBaseConfig.INSTANCE().connect();

        String sql = "insert into contact_info (phone_number1,phone_number2,home_number,customer_id) values (?,?,?,?)";

        PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, contactInfo.getPhoneNumber1());
        ps.setString(2, contactInfo.getPhoneNumber2());
        ps.setString(3, contactInfo.getHomeNumber());
        ps.setInt(4,contactInfo.getCustomerID());
        ps.execute();

        return findByCustomerId(contactInfo.getCustomerID());
    }

    @Override
    public ContactInfo findByCustomerId(Integer id) throws ClassNotFoundException, SQLException {
        Connection con = DataBaseConfig.INSTANCE().connect();
        Statement stmt = con.createStatement();


        String sql = String.format("select * from contact_info where customer_id = %s ",id);
        ResultSet rs = stmt.executeQuery(sql);
        ContactInfo contactInfo = new ContactInfo();
        while (rs.next()){
            contactInfo.setId(rs.getInt("id"));
            contactInfo.setPhoneNumber1(rs.getString("phone_number1"));
            contactInfo.setPhoneNumber2(rs.getString("phone_number2"));
            contactInfo.setHomeNumber(rs.getString("home_number"));
            contactInfo.setCustomerID(rs.getInt("customer_id"));
        }

        con.close();
        return contactInfo;
    }
}
