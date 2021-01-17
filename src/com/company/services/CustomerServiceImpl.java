package com.company.services;

import com.company.constants.CommonMessage;
import com.company.dto.*;
import com.company.dto.request.CustomerCreateRequest;
import com.company.dto.response.CustomerDetailResponse;
import com.company.dto.response.CustomerDetailResponseList;
import com.company.entity.Address;
import com.company.entity.ContactInfo;
import com.company.entity.Customer;
import com.company.mapper.CustomerMapper;
import com.company.repository.AddressRepository;
import com.company.repository.ContactInfoRepository;
import com.company.repository.CustomerRepository;
import com.company.repository.impl.AddressRepositoryImpl;
import com.company.repository.impl.ContactInfoRepositoryImpl;
import com.company.repository.impl.CustomerRepositoryImpl;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class CustomerServiceImpl implements CustomerService {

   private final CustomerRepository customerRepository = new CustomerRepositoryImpl();
   private final AddressRepository addressRepository = new AddressRepositoryImpl();
   private final ContactInfoRepository contactInfoRepository = new ContactInfoRepositoryImpl();

    private CommonResponse fillResponseData(CustomerDetailResponse cr){
        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setUid(getUUID());
        commonResponse.setStatusCode(200);
        commonResponse.setMessage(CommonMessage.ErrorType.SUCCESS);
        commonResponse.setObject(cr);
        return commonResponse;
    }

    private CommonResponse fillResponseDataV2(CustomerDetailResponseList responseList){
        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setUid(getUUID());
        commonResponse.setStatusCode(200);
        commonResponse.setMessage(CommonMessage.ErrorType.SUCCESS);
        commonResponse.setObject(responseList);
        return commonResponse;
    }

    private CommonResponse fillErrorResponse(){
        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setUid(getUUID());
        commonResponse.setMessage(CommonMessage.ErrorType.ERROR);
        commonResponse.setStatusCode(404);
        return commonResponse;
    }

    @Override
    public CommonResponse createCustomer(CustomerCreateRequest createReq) throws SQLException, ClassNotFoundException {
        Customer customer = CustomerMapper.toEntity(createReq);
        Address address = CustomerMapper.toEntity(createReq.getAddressRequest());
        ContactInfo cInfo = CustomerMapper.toEntity(createReq.getContactInfoDTO());

        customer.setCreatedDate(LocalDateTime.now());
        customer = customerRepository.save(customer);

        address.setCustomerId(customer.getId());
        address = addressRepository.saveAddress(address);

        cInfo.setCustomerID(customer.getId());
        cInfo = contactInfoRepository.saveContactInfo(cInfo);

        customer.setAddress(address);
        customer.setContactInfo(cInfo);

        return fillResponseData(CustomerMapper.customerResponse(customer));
    }

    @Override
    public CommonResponse findByFinCode(String finCode) throws SQLException, ClassNotFoundException {
        try{
         Customer customer = customerRepository.findByFinCode(finCode);
         return fillResponseData(CustomerMapper.customerResponse(customer));
        }catch (Exception e){
            System.err.println(e.getMessage());
        }

        return fillErrorResponse();
    }


    @Override
    public CommonResponse deleteByFinCode(String finCode) {
        return null;
    }

    @Override
    public CommonResponse getAllCustomers() throws SQLException, ClassNotFoundException {
        List<Customer> customerList = customerRepository.findAll();
        return fillResponseDataV2(CustomerMapper.toResponseList(customerList));
    }

    @Override
    public CommonResponse getCustomerDetailsByFinCode(String finCode) throws SQLException, ClassNotFoundException {
        try {
            Customer customer = customerRepository.findCustomerDetailByFinCode(finCode);
            return fillResponseData(CustomerMapper.customerResponse(customer));
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
        return fillErrorResponse();
    }

    @Override
    public CommonResponse findAllCustomerLast24Hours() {
        return null;
    }

    @Override
    public CommonResponse updateByFincode(String finCode) {
        return null;
    }

    private String getUUID(){
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}
