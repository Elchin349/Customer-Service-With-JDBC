package com.company.services;

import com.company.dto.CommonResponse;
import com.company.dto.request.CustomerCreateRequest;

import java.sql.SQLException;

public interface CustomerService {
   CommonResponse createCustomer(CustomerCreateRequest createReq) throws SQLException, ClassNotFoundException;
   CommonResponse findByFinCode (String finkod ) throws SQLException, ClassNotFoundException;
   CommonResponse deleteByFinCode (String finCode);
   CommonResponse getAllCustomers () throws SQLException, ClassNotFoundException;
   CommonResponse getCustomerDetailsByFinCode (String finCode) throws SQLException, ClassNotFoundException;
   CommonResponse findAllCustomerLast24Hours ();
   CommonResponse updateByFincode (String findoce);



}
