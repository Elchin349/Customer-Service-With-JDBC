package com.company.repository;

import com.company.entity.ContactInfo;

import java.sql.SQLException;

public interface ContactInfoRepository {

    ContactInfo saveContactInfo(ContactInfo contactInfo) throws ClassNotFoundException, SQLException;

    ContactInfo findByCustomerId(Integer id) throws ClassNotFoundException, SQLException;
}
