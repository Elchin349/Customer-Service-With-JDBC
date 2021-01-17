package com.company.repository.impl;

import com.company.config.DataBaseConfig;
import com.company.constants.CommonMessage;
import com.company.entity.Address;
import com.company.entity.ContactInfo;
import com.company.entity.Customer;
import com.company.exceptions.NotFoundException;
import com.company.repository.CustomerRepository;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CustomerRepositoryImpl implements CustomerRepository {


    @Override
    public Customer save(Customer customer) throws SQLException, ClassNotFoundException {
        Connection con = DataBaseConfig.INSTANCE().connect();

        String sql = " insert into customer(first_name,last_name,father_name,fin_code,doc_serial,email_address,birth_date,create_date) values (?,?,?,?,?,?,?,?)";

        PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, customer.getFirstName());
        ps.setString(2, customer.getLastName());
        ps.setString(3, customer.getFatherName());
        ps.setString(4, customer.getFinCode());
        ps.setString(5, customer.getDocSerial());
        ps.setString(6, customer.getEmailAddress());
        ps.setDate(7, java.sql.Date.valueOf(customer.getBirthDate()));
        ps.setTimestamp(8,Timestamp.valueOf(customer.getCreatedDate()));
        ps.execute();

        return findByFinCode(customer.getFinCode());
    }

    @Override
    public List<Customer> findAll() throws SQLException, ClassNotFoundException {
        Connection con = DataBaseConfig.INSTANCE().connect();
        Statement stmt = con.createStatement();

        String sql = "select * from customer ";

        ResultSet rs = stmt.executeQuery(sql);
        List<Customer> customerList = new ArrayList<>();
        while (rs.next()) {
            Customer customer = new Customer();
            customer.setId(rs.getInt("id"));
            customer.setFirstName(rs.getString("first_name"));
            customer.setLastName(rs.getString("last_name"));
            customer.setFatherName(rs.getString("father_name"));
            customer.setFinCode(rs.getString("fin_code"));
            customer.setEmailAddress(rs.getString("email_address"));
            customer.setDocSerial(rs.getString("doc_serial"));
           // customer.setCreatedDate(rs.getTimestamp("create_date").toLocalDateTime());
            customer.setBirthDate(rs.getDate("birth_date").toLocalDate());
            customerList.add(customer);
        }
        con.close();
        return customerList;
    }

    @Override
    public Customer findById(int id) throws ClassNotFoundException, SQLException {
        Connection con = DataBaseConfig.INSTANCE().connect();
        Statement stmt = con.createStatement();


        String sql =  String.format("select * from customer where id = '%s'",id);

        ResultSet rs = stmt.executeQuery(sql);
        Customer customer = new Customer();
        while (rs.next()) {
            customer.setId(rs.getInt("id"));
            customer.setFirstName(rs.getString("first_name"));
            customer.setLastName(rs.getString("last_name"));
            customer.setFatherName(rs.getString("father_name"));
            customer.setFinCode(rs.getString("fin_code"));
            customer.setEmailAddress(rs.getString("email_address"));
            customer.setDocSerial(rs.getString("doc_serial"));
            customer.setCreatedDate(rs.getTimestamp("created_date").toLocalDateTime());
            customer.setBirthDate(rs.getDate("birth_date").toLocalDate());
        }
        con.close();
        return customer;
    }

    @Override
    public Customer findByFinCode(String finCode) throws ClassNotFoundException, SQLException {
        Connection con = DataBaseConfig.INSTANCE().connect();
        Statement stmt = con.createStatement();


        String sql =  String.format("select * from customer where fin_code = '%s' ",finCode);
        //String sql = "select * from customer where fin_code = '5wm1712'";

            ResultSet rs = stmt.executeQuery(sql);
            Customer customer = new Customer();
            while (rs.next()) {
                customer.setId(rs.getInt("id"));
                customer.setFirstName(rs.getString("first_name"));
                customer.setLastName(rs.getString("last_name"));
                customer.setFatherName(rs.getString("father_name"));
                customer.setFinCode(rs.getString("fin_code"));
                customer.setEmailAddress(rs.getString("email_address"));
                customer.setDocSerial(rs.getString("doc_serial"));
               // customer.setCreatedDate(rs.getTimestamp("create_date").toLocalDateTime());
                customer.setBirthDate(rs.getDate("birth_date").toLocalDate());
            }
            con.close();
            if(StringUtils.isBlank(customer.getFinCode())){
                throw new NotFoundException(String.format(CommonMessage.Messages.NOT_FOUND_BY_FIN_CODE,finCode));
            }
            return customer;

    }

    @Override
    public Customer findCustomerDetailByFinCode(String finCode) throws ClassNotFoundException, SQLException {
        Connection con = DataBaseConfig.INSTANCE().connect();
        Statement stmt = con.createStatement();


        String sql =  String.format("select customer.id,customer.first_name,customer.last_name,customer.father_name,customer.fin_code,\n" +
                "customer.doc_serial,customer.birth_date,customer.email_address,customer.create_date,\n" +
                "address.country,address.country_code,address.city,address.district,address.street,\n" +
                "contact_info.phone_number1,contact_info.phone_number2,contact_info.home_number\n" +
                "from customer \n" +
                "inner join address on address.customer_id = customer.id \n" +
                "inner join contact_info on contact_info.customer_id = customer.id\n" +
                "where customer.fin_code = '%s'",finCode);

        ResultSet rs = stmt.executeQuery(sql);
        Customer customer = new Customer();
        Address address = new Address();
        ContactInfo contactInfo = new ContactInfo();
        while (rs.next()) {
            customer.setId(rs.getInt("id"));
            customer.setFirstName(rs.getString("first_name"));
            customer.setLastName(rs.getString("last_name"));
            customer.setFatherName(rs.getString("father_name"));
            customer.setFinCode(rs.getString("fin_code"));
            customer.setDocSerial(rs.getString("doc_serial"));
            customer.setBirthDate(rs.getDate("birth_date").toLocalDate());
            customer.setEmailAddress(rs.getString("email_address"));
           // customer.setCreatedDate(rs.getTimestamp("create_date").toLocalDateTime());

            address.setCountry(rs.getString("country"));
            address.setCountryCode(rs.getString("country_code"));
            address.setCity(rs.getString("city"));
            address.setDistrict(rs.getString("district"));
            address.setStreet(rs.getString("street"));

            contactInfo.setPhoneNumber1(rs.getString("phone_number1"));
            contactInfo.setPhoneNumber2(rs.getString("phone_number2"));
            contactInfo.setHomeNumber(rs.getString("home_number"));
        }
        customer.setAddress(address);
        customer.setContactInfo(contactInfo);
        con.close();

        if(StringUtils.isBlank(customer.getFinCode())){
            throw new NotFoundException(String.format(CommonMessage.Messages.NOT_FOUND_BY_FIN_CODE,finCode));
        }

        return customer;

    }

    @Override
    public void deleteById(int id) {

    }
}
